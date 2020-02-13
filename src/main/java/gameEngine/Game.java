package gameEngine;

import java.util.ArrayList;
import java.util.List;

public class Game {

    // Counts the amount of total throws in a game
    private int countThrows = 0;

    // Array with size 21 which is the most throws you can have
    private int[] throwAmount = new int[21];

    // When a throw comes in we store it in the array and increment countThrows
    public void roll(int pinsDown) {
        throwAmount[countThrows++] = pinsDown;
    }

    // Used to count up and place the points for each round
    public Game roll(int[] rolls) {
        // Assigns the value of rolls to pinsDown each time
        for (int pinsDown : rolls) {
           roll(pinsDown);
        }
        return this;
    }

    // This method is for getting each round sum and ad it in an ArrayList
    public ArrayList<Integer> getScoreSumPerRound() {
        ArrayList<Integer> totalScores = new ArrayList<Integer>();
        // Creating a list of sums per round
        List<Integer> scores = getScoreArray();
        int totalScore = 0;
        // Adding each sum of scores per round to score
        for (Integer score : scores) {
            // Adding score to totalScore to get a total sum
            totalScore += score;
            totalScores.add(totalScore);
        }
        // Returning the ArrayList created at the beginning with the sums of each round added
        return totalScores;
    }

    public List<Integer> getScoreArray() {
        List<Integer> scores = new ArrayList<>();
        //For keeping track of where we are in the rolls array
        int rollNumber = 0;
        int counter = 0;

        //Iterating over the 10 frames
        for (int frame = 0; frame < 10; frame++) {
            counter++;

            if (countThrows / 2 == counter) {
                scores.add(throwAmount[rollNumber] + throwAmount[rollNumber + 1]);
                break;
            }
            if (isAStrike(rollNumber)) {
                scores.add(10 + throwAmount[rollNumber + 2] + throwAmount[rollNumber + 3]);
                rollNumber += 2;
            } else if (isASpare(rollNumber)) {
                scores.add(10 + throwAmount[rollNumber + 2]);
                rollNumber += 2;
            } else {
                scores.add(throwAmount[rollNumber] + throwAmount[rollNumber + 1]);
                rollNumber += 2;
            }
        }
        return scores;
    }

    // Asking if the throw we currently are on -->
    // and the next throw together equals 10
    private boolean isASpare(int rollTimes) {
        return throwAmount[rollTimes] + throwAmount[rollTimes + 1] == 10;
    }

    // Asking if the throw we currently are on equals a 10
    private boolean isAStrike(int rollTimes) {
        return throwAmount[rollTimes] == 10;
    }
}
