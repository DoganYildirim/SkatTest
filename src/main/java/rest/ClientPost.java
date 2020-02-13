package rest;

import com.google.gson.Gson;
import mappers.GameResultMapper;
import mappers.ResultMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientPost {
    public ResultMapper sendScore(GameResultMapper gameResultMapper) {
        ResultMapper resultMapper = null;
        try {
            URL url = new URL("http://13.74.31.101/api/points");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            // Using Gson to convert my data into Json format
            String input = new Gson().toJson(gameResultMapper);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            // If our response code isn't of status 200 we throw a Runtime exception
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            // Using Gson to convert output into class object
            String output;
            while ((output = br.readLine()) != null) {
                resultMapper = new Gson().fromJson(output, ResultMapper.class);
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMapper;
    }
}