package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/*@Service*/
public class AccidentService {
    private final AccidentMem memStore;

    public AccidentService(AccidentMem accidentMem) {
        this.memStore = accidentMem;
    }

    public Collection<Accident> getAll() {
        return memStore.getAccidents();
    }

    public void create(Accident accident, String[] ids) {
        accident.setRules(Arrays.stream(ids).map(id -> findRuleById(Integer.parseInt(id))).collect(Collectors.toSet()));
        memStore.add(accident);
    }

    public void change(Accident accident, String[] ids) {
        accident.setRules(Arrays.stream(ids).map(id -> findRuleById(Integer.parseInt(id))).collect(Collectors.toSet()));
        memStore.update(accident);
    }

    public Accident findById(int id) {
        return memStore.get(id);
    }

    public Collection<AccidentType> getAllTypes() {
        return memStore.getTypes();
    }
    public Collection<Rule> getAllRules() {
        return memStore.getRules();
    }

    public Rule findRuleById(int id) {
        return memStore.getRule(id);
    }
}
