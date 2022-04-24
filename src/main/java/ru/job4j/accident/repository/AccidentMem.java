package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private final ConcurrentHashMap<Integer, Accident> accidents;
    private int id = 5;

    public AccidentMem() {
        this.accidents = new ConcurrentHashMap<>();
        this.accidents.put(1, Accident.of(
                1, "speed limit", "2 cars violated the speed limit", "astana"));
        this.accidents.put(2, Accident.of(
                2, "crashing", "3 cars crashed", "almaty"));
        this.accidents.put(3, Accident.of(
                3, "speed limit", "4 cars violated the speed limit", "moscow"));
        this.accidents.put(4, Accident.of(
                4, "crashing", "5 cars crashed", "new-york"));
    }
    public Collection<Accident> getAccidents() {
        return this.accidents.values();
    }

    public void add(Accident accident) {
        accident.setId(id);
        this.accidents.put(id, accident);
        this.id++;
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return this.accidents.get(id);
    }
}
