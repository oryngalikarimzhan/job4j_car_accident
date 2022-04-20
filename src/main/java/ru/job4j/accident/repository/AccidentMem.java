package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Map;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = Map.of(
            1, Accident.of(1, "speed limit", "2 cars violated the speed limit", "astana"),
            2, Accident.of(2, "crashing", "3 cars crashed", "almaty"),
            3, Accident.of(3, "speed limit", "4 cars violated the speed limit", "moscow"),
            4, Accident.of(4, "crashing", "5 cars crashed", "new-york")
    );
    public Map<Integer, Accident> getAccidents() {
        return this.accidents;
    }

}
