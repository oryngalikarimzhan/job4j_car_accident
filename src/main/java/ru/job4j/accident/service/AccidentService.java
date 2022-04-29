package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentService {
    private final AccidentRepository repository;

    public AccidentService(AccidentRepository repository) {
        this.repository = repository;
    }

    public Collection<Accident> getAllAccidents() {
        List<Accident> res = new ArrayList<>();
        repository.findAll().forEach(res::add);
        return res;
    }

    public void saveAccident(Accident accident, String[] rIds) {
        accident.setRules(
                Arrays.stream(rIds)
                        .map(id -> getRule(Integer.parseInt(id)))
                        .collect(Collectors.toSet()));
        repository.save(accident);
    }

    public Accident getAccident(int id) {
        return repository.findAccidentById(id);
    }

    public void removeAccident(int id) {
        repository.delete(getAccident(id));
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return repository.findAllAccidentTypes();
    }
    public Collection<Rule> getAllRules() {
        return repository.findAllRules();
    }

    public Rule getRule(int id) {
        return repository.findRuleById(id);
    }
}
