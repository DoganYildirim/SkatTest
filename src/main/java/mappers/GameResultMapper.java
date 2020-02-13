package mappers;

import java.util.ArrayList;

public class GameResultMapper {

    private ArrayList<Integer> points = new ArrayList<Integer>();
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPoints(ArrayList<Integer> points) {
        this.points = points;
    }
}
