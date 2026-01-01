package oncall.controller;

import oncall.dto.DayInfoDTO;
import oncall.model.StartMonthAndDay;
import oncall.model.WorkSequence;
import oncall.service.OnCallService;
import oncall.util.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class OnCallController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final WorkSequence workSequence = new WorkSequence();
    private final OnCallService onCallService = new OnCallService(workSequence);

    public void run() {
        StartMonthAndDay startMonthAndDay = inputAndParseMonthAndStartDay();
        inputWeekAndHolidaySequence();
        List<DayInfoDTO> dayInfos = onCallService.make(startMonthAndDay);
        outputView.printResult(dayInfos);
    }

    private void inputWeekAndHolidaySequence() {
        while(true) {
            try {
                String inputWeekSequence = inputView.inputWeekSequence();
                String inputHolidaySequence = inputView.inputHolidaySequence();
                String[] weekSequence = InputParser.parseWorkSequence(inputWeekSequence);
                String[] holidaySequence = InputParser.parseWorkSequence(inputHolidaySequence);
                onCallService.saveWorkSequence(weekSequence, holidaySequence);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private StartMonthAndDay inputAndParseMonthAndStartDay() {
        while(true) {
            try {
                String input = inputView.inputMonthAndStartDay();
                return InputParser.parserMonthAndDayInput(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
