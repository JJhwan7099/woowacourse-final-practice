package menu.domain;

import menu.constants.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CoachRepository {
    private final List<Coach> coaches = new ArrayList<>();

    public void add(Coach coach) {
        if(isExist(coach.getName())) throw new IllegalArgumentException(Error.COACH_ALREADY_EXIST.getMessage());
        coaches.add(coach);
    }

    public List<Coach> getCoaches() {
        return Collections.unmodifiableList(coaches);
    }

    public Coach findCoachByName(String name) {
        return coaches.stream()
                .filter(coach -> coach.getName().equals(name))
                .findFirst().orElseThrow(
                        () -> new IllegalArgumentException(Error.COACH_NOT_FOUND.getMessage())
                );
    }

    public boolean isExist(String name) {
        return coaches.stream()
                .anyMatch(coach -> coach.getName().equals(name));
    }

    public void updateCoach(Coach coach) {
        for(int i=0; i< coaches.size(); i++) {
            if(coaches.get(i).getName().equals(coach.getName())) {
                coaches.get(i).update(coach);
            }
        }
    }
}
