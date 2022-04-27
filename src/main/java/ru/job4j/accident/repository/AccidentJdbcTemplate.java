package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident, String[] rIds) {
        Accident rsl = null;
        if (accident.getId() == 0) {
            rsl = create(accident, rIds);
        } else {
            rsl = update(accident, rIds);
        }
        return rsl;
    }

    public Accident create(Accident accident, String[] rIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into accident(name, text, address, type_id) values (?, ?, ?, ?)",
                            new String[] {"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder
        );
        accident.setId(keyHolder.getKey().intValue());
        setNewRules(accident.getId(), rIds);
        return accident;
    }

    public Accident update(Accident accident, String[] rIds) {
        jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        setNewRules(accident.getId(), rIds);
        return accident;
    }

    private void setNewRules(int id, String[] rIds) {
        deleteAccidentRules(id);
        for (String rId : rIds) {
            jdbc.update("insert into accident_rules(accident_id, rule_id) values (?, ?)",
                    id, Integer.parseInt(rId));
        }
    }

    private void deleteAccidentRules(int id) {
        jdbc.update("delete from accident_rules where accident_id = ?", id);
    }


    public List<Accident> getAllAccidents() {
        return jdbc.query("select * from accident",
                (rs, row) -> buildAccidentObj(rs));
    }

    public Accident findAccidentById(int id) {
        return jdbc.queryForObject("select * from accident where id = ?",
                (rs, row) -> buildAccidentObj(rs),
                id
        );
    }

    public void deleteAccidentById(int id) {
        deleteAccidentRules(id);
        jdbc.update("delete from accident where id = ?", id);
    }

    private Accident buildAccidentObj(ResultSet rs) throws SQLException {
        Accident accident = new Accident();
        accident.setId(rs.getInt("id"));
        accident.setName(rs.getString("name"));
        accident.setText(rs.getString("text"));
        accident.setAddress(rs.getString("address"));
        accident.setType(findAccidentTypeById(rs.getInt("type_id")));
        accident.setRules(findRulesByAccidentId(rs.getInt("id")));
        return accident;
    }

    public AccidentType findAccidentTypeById(int id) {
        return jdbc.queryForObject("select * from accident_type where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id
        );
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject("select * from rule where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id
        );
    }

    public Set<Rule> findRulesByAccidentId(int id) {
        List<Integer> ids = jdbc.query("select * from accident_rules where accident_id = ?",
                (rs, row) -> rs.getInt("rule_id"),
                id
        );
        Set<Rule> rules = new HashSet<>();
        for (int ruleId : ids) {
            rules.add(getRuleById(ruleId));
        }
        return rules;
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Collection<Rule> getAllRules() {
        return jdbc.query("select * from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

}