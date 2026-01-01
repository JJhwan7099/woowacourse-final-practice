package oncall.service;

import oncall.dto.DayInfoDTO;
import oncall.model.StartMonthAndDay;
import oncall.model.WorkSequence;

import java.util.ArrayList;
import java.util.List;

public class OnCallService {
    private final WorkSequence workSequence;

    public OnCallService(WorkSequence workSequence) {
        this.workSequence = workSequence;
    }

    public void saveWorkSequence(String[] weekSequence, String[] holidaySequence) {
        for(String name: weekSequence) workSequence.addWeekWorker(name);
        for(String name: holidaySequence) workSequence.addHolidayWorker(name);
    }


    public List<DayInfoDTO> make(StartMonthAndDay startMonthAndDay) {
        List<DayInfoDTO> dayInfos = new ArrayList<>();
        for(int i=1; i<= startMonthAndDay.getDayOfMonth(); i++) {
            String allocatedWorkerName = workSequence.allocWorker(i, startMonthAndDay.isHoliday(i));
            dayInfos.add(new DayInfoDTO(startMonthAndDay.getStartMonth(), i, startMonthAndDay.getDay(i), startMonthAndDay.isHolidayButWeekDay(i), allocatedWorkerName));
        }
        return dayInfos;
    }
}
