package menu.controller;

import menu.domain.CoachRepository;
import menu.domain.FoodRepository;
import menu.dto.MenuRecommendsInfosDTO;
import menu.service.MenuService;
import menu.view.InputView;
import menu.view.OutputView;

import java.util.List;

public class MenuController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final FoodRepository foodRepository = new FoodRepository();
    private final CoachRepository coachRepository = new CoachRepository();
    private final MenuService menuService = new MenuService(foodRepository, coachRepository);

    public void run() {
        try {
            outputView.printStartMessage();
            List<String> names = getCoachNames();
            menuService.registerCoaches(names);
            setIgnoreFoods(names);
            runMenuRecommend();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }

    private void runMenuRecommend() {
        MenuRecommendsInfosDTO menuRecommendsInfosDTO = menuService.runMenuRecommend();
        outputView.printResult(menuRecommendsInfosDTO);
    }

    private void setIgnoreFoods(List<String> names) {
        for(String name : names) {
            setIgnoreFoodsPerCoach(name);
        }
    }

    private void setIgnoreFoodsPerCoach(String name) {
        while (true) {
            try {
                outputView.printRequestIgnoreFoodsPerCoach(name);
                List<String> ignoreFoods = inputView.inputIgnoreFoodsPerCoach();
                menuService.setIgnoreFoodsPerCoach(name, ignoreFoods);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private List<String> getCoachNames() {
        while(true) {
            try {
                outputView.printRequestCoachesName();
                return inputView.inputCoachNames();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
