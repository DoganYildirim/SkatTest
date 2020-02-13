package controller;

import gameEngine.Game;
import mappers.GameMapper;
import mappers.GameResultMapper;
import mappers.ResultMapper;
import rest.ClientGet;
import rest.ClientPost;
import java.util.ArrayList;

public class GameController {

    public ResultMapper executeGame() {

        ClientGet clientGet = new ClientGet();
        // Calls API to get token and points
        GameMapper gameMapper = clientGet.getGamePoints();
        // Throwing an exception if the API does'nt return anything
        if (gameMapper == null) {
            throw new RuntimeException("Could not fetch game points from API");
        }
        Game game = new Game();
        // Returns an ArrayList with the sum of each of the rounds added up
        ArrayList<Integer> allScores = game.roll(gameMapper.toArrayOfHits()).getScoreSumPerRound();
        GameResultMapper gameResultMapper = new GameResultMapper();
        gameResultMapper.setToken(gameMapper.getToken());
        gameResultMapper.setPoints(allScores);
        ResultMapper resultMapper = sendResultScore(gameResultMapper);
        return resultMapper;
    }

    // Using the POST request to send back our result
    private ResultMapper sendResultScore(GameResultMapper gameResultMapper) {
        ClientPost clientPost = new ClientPost();
        ResultMapper resultMapper = clientPost.sendScore(gameResultMapper);
        return resultMapper;
    }
}
