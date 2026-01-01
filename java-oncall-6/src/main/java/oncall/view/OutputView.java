package oncall.view;

import oncall.dto.DayInfoDTO;

import java.util.List;

public class OutputView {
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printResult(List<DayInfoDTO> dayInfos) {
        for(DayInfoDTO dayInfoDTO: dayInfos) {
            if(dayInfoDTO.isHoliday()) {
                System.out.println(dayInfoDTO.month() + "월 " + dayInfoDTO.date() + "일 " + dayInfoDTO.day() + "(휴일) " + dayInfoDTO.workerName());
                return;
            }
            System.out.println(dayInfoDTO.month() + "월 " + dayInfoDTO.date() + "일 " + dayInfoDTO.day() + " " + dayInfoDTO.workerName());
        }
    }
}
