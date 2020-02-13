package start;

import controller.GameController;
import mappers.ResultMapper;

public class StartGame {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        ResultMapper resultMapper = gameController.executeGame();

        System.out.println(resultMapper.getSuccess());
        for (int score : resultMapper.getInput()) {
            System.out.println(score);
        }
    }
}
