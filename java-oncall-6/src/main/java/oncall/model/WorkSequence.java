package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class WorkSequence {
    private final List<String> weekSequence = new ArrayList<>();
    private final List<String> holidaySequence = new ArrayList<>();
    private String prevWorker = null;
    private int weekPrevIdx = 0;
    private int holidayPrevIdx = 0;

    public void addWeekWorker(String name) {
        weekSequence.add(name);
    }

    public void addHolidayWorker(String name) {
        holidaySequence.add(name);
    }

    public String findNextWeekWorker() {
        if(prevWorker == null) return weekSequence.get(0);

        String nextWorker = weekSequence.get(weekPrevIdx%weekSequence.size());
        weekPrevIdx++;
        return nextWorker;
    }

    public String findNextHolidayWorker() {
        if(prevWorker == null) return holidaySequence.get(0);

        String nextWorker = holidaySequence.get(holidayPrevIdx%holidaySequence.size());
        holidayPrevIdx++;
        return nextWorker;
    }

    public String switchWorkerSequence(String currentWorker, boolean isHoliday) {
        if(isHoliday) {
           int currentidx = holidaySequence.indexOf(currentWorker);
           holidaySequence.set(currentidx, holidaySequence.get(currentidx+1));
           holidaySequence.set(currentidx+1, currentWorker);
           return holidaySequence.get(currentidx);
        }
        int currentidx = weekSequence.indexOf(currentWorker);
        weekSequence.set(currentidx, weekSequence.get(currentidx+1));
        weekSequence.set(currentidx+1, currentWorker);
        return weekSequence.get(currentidx);
    }

    public String getPrevWorker() {
        return prevWorker;
    }

    public String allocWorker(int date, boolean isHoliday) {
        String nextWorker;
        if(isHoliday) {
            nextWorker = findNextHolidayWorker();
            if(prevWorker==null) {
                prevWorker = nextWorker;
                holidayPrevIdx++;
                return nextWorker;
            }
            if(nextWorker == prevWorker) {
                nextWorker = switchWorkerSequence(nextWorker, isHoliday);
            }
            prevWorker = nextWorker;
            return nextWorker;
        }
        nextWorker = findNextWeekWorker();
        if(prevWorker==null) {
            prevWorker = nextWorker;
            weekPrevIdx++;
            return nextWorker;
        }
        if(nextWorker == prevWorker) {
            nextWorker = switchWorkerSequence(nextWorker, isHoliday);
        }
        prevWorker = nextWorker;
        return nextWorker;
    }
}
