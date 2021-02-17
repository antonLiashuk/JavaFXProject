package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_feels_like;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text pressure;

    @FXML
    void initialize(){
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {

                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=3abf6138d14ce4ce09669e5c7c4ebf0e&units=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp")+" C");
                    temp_feels_like.setText("Feels like: " + obj.getJSONObject("main").getDouble("feels_like")+" C");
                    temp_max.setText("Maximum: " + obj.getJSONObject("main").getDouble("temp_max")+" C");
                    temp_min.setText("Minimum: " + obj.getJSONObject("main").getDouble("temp_min")+" C");
                    pressure.setText("Pressure: " + obj.getJSONObject("main").getDouble("pressure")+" hPa");
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("The city hasn't been founded.");
        }
        return content.toString();
    }
}
