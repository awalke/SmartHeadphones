import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * Created by Allison on 4/2/16.
 */
public class Main {
    public static void main(String args[]) throws ScriptException, Exception{
        String baseURL = "https://api.particle.io/v1/devices/330035000347343337373738/digitalread?access_token=235ad2c91cac25aa4950542f1f9e4c21e8dab041";
        // object to Json string
//        JsonObject object = new JsonObject();
//        object.addProperty("hi", "I'm a json value");
//        object.toString();

        // Json string to object
//        JsonObject deserialized = new JsonParser().parse("{\"hi\":\"ayyyy\"}").getAsJsonObject();

        int value = -1;

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(510);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);

        while(true) {
            value =  HttpClient.getHttp(baseURL);
            KeySimulator.switchFocus(value);
        }




    }
}
