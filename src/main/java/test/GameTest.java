package test;

import gameEngine.Game;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

     Game game;

    @Before
    public void initializer() {
        game = new Game();
    }

    // Used to verify if the list we are giving has the expected results
    private void verifyScore(List<Integer> expectedList, List<Integer> actualList){
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i));
        }
    }

    @Test
    public void canScoreOnlyZero() {
        // The sum of each round/frame
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        // The points of each throw
        game.roll(new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0, 0,0});
        // Comparing the sums of the throws we give as input
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    public void canScoreOnlyOnes() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20));
        game.roll(new int[]{1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1, 1,1});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    public void canScoreOnlySpare() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(15, 30, 45, 60, 75, 90, 105, 120, 135, 145));
        game.roll(new int[]{5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5, 5,5});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    public void canScoreRandomGameWithStrikeAndSpare() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(15, 22, 40, 48, 53, 68, 73, 75, 90, 99));
        game.roll(new int[]{5,5, 5,2, 10,0, 3,5, 2,3, 10,0, 2,3, 1,1, 5,5, 5,4});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    // If the last hit is a strike no bonus points are given
    public void canScoreLastStrike() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(15, 22, 40, 48, 53, 68, 73, 75, 78, 88));
        game.roll(new int[]{5,5, 5,2, 10,0, 3,5, 2,3, 10,0, 2,3, 1,1, 1,2, 10,0});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    // If the last hit is a spare no bonus is given
    public void canScoreStrikeAndSpare() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(16, 22, 37, 42));
        game.roll(new int[]{10,0, 5,1, 5,5, 5,0});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }

    @Test
    public void canScoreSmallGame() {
        List<Integer> expectedSums = new ArrayList<>(Arrays.asList(18, 26, 33, 50, 57));
        game.roll(new int[]{10,0, 4,4, 5,2, 10,0, 4,3});
        verifyScore(expectedSums, game.getScoreSumPerRound());
    }
}
