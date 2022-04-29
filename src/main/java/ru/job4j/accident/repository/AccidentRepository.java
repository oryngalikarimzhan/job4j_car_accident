package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("select distinct acc from Accident acc join fetch acc.type join fetch acc.rules")
    Collection<Accident> findAll();

    @Query("from AccidentType order by id asc")
    Collection<AccidentType> findAllAccidentTypes();

    @Query("from Rule order by id asc")
    Collection<Rule> findAllRules();

    @Query("from Rule where id = :id")
    Rule findRuleById(@Param("id") int id);

    @Query("select distinct acc from Accident acc join fetch acc.type join fetch acc.rules where acc.id = :id")
    Accident findAccidentById(@Param("id") int id);
}