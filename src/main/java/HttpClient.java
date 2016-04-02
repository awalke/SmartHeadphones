import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Allison on 4/2/16.
 */
public class HttpClient {

    public static int getHttp(String urlToRead) {
        JsonObject jsonObject = new JsonObject();
        StringBuilder result = new StringBuilder();
        String token = "235ad2c91cac25aa4950542f1f9e4c21e8dab041";
        int value = -1;

        try {
            URL url = new URL(urlToRead);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //connection.addRequestProperty("access_token", token);

            connection.connect();

            OutputStream requestBody = connection.getOutputStream();
            requestBody.write(jsonObject.toString().getBytes());
            requestBody.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseBody = connection.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }

                String responseBodyData = baos.toString();

                JsonObject response = new JsonParser().parse(responseBodyData).getAsJsonObject();

                JsonElement element = response.get("return_value");
                String valueStr = element.toString();
                value = Integer.parseInt(valueStr);

            }
            else {
                System.out.println(connection.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }
}
