package mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMapper {

    private ArrayList<Integer[]> points = new ArrayList<>();
    private String token;

    public String getToken() {
        return token;
    }

    public ArrayList<Integer[]> getPoints() {
        return points;
    }

    // Collects all round hits into an Array of all hits
    public int[] toArrayOfHits() {
        List<Integer> points = new ArrayList<Integer>();

        for (Integer[] pointList : this.getPoints()) {
            List<Integer> arrListPoints = Arrays.asList(pointList);
            points.addAll(arrListPoints);
        }
        int[] arrIntPoints = points.stream().mapToInt(i -> i).toArray();
        return arrIntPoints;
    }
}
