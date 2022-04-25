package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final ConcurrentHashMap<Integer, Accident> accidents;
    private final CopyOnWriteArrayList<AccidentType> types;
    private AtomicInteger id = new AtomicInteger(5);

    public AccidentMem() {
        this.types = new CopyOnWriteArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        this.accidents = new ConcurrentHashMap<>();
        this.accidents.put(1, Accident.of(
                1, "speed limit", "2 cars violated the speed limit", "astana", types.get(0)));
        this.accidents.put(2, Accident.of(
                2, "crashing", "3 cars crashed", "almaty", types.get(0)));
        this.accidents.put(3, Accident.of(
                3, "speed limit", "4 cars violated the speed limit", "moscow", types.get(0)));
        this.accidents.put(4, Accident.of(
                4, "crashing", "5 cars crashed", "new-york", types.get(0)));

    }
    public Collection<Accident> getAccidents() {
        return this.accidents.values();
    }

    public void add(Accident accident) {
        accident.setId(id.get());
        accident.setType(findTypeById(accident.getType().getId()));
        this.accidents.put(id.get(), accident);
        this.id.incrementAndGet();
    }

    public void update(Accident accident) {
        accident.setType(findTypeById(accident.getType().getId()));
        accidents.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return this.accidents.get(id);
    }

    public Collection<AccidentType> getTypes() {
        return this.types;
    }

    public AccidentType findTypeById(int id) {
        return this.types.get(id - 1);
    }
}
