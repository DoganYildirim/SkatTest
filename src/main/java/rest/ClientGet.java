package rest;

import com.google.gson.Gson;
import mappers.GameMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientGet {
    public GameMapper getGamePoints() {
        GameMapper gameMapper = null;
        try {
            URL url = new URL("http://13.74.31.101/api/points");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            // If the response code isn't 200 HTTP ok we throw an exception
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            // Run the code below as long as the output isn't null
            while ((output = br.readLine()) != null) {
                // Using Gson to convert Json output into a GameMapper object
                gameMapper = new Gson().fromJson(output, GameMapper.class);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameMapper;
    }
}
