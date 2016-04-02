import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;

/**
 * Created by Allison on 4/2/16.
 */



public class KeySimulator {
    private static int state = 0;

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isMac() {
        if (OS.equals("mac os x")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void switchFocus(int value) {
        try {
            Robot r = new Robot();


            if (value == 0 && state == 0) {
                r.keyPress(KeyEvent.VK_SPACE);
                r.keyRelease(KeyEvent.VK_SPACE);
                state = 1;
            }
            else if (value == 1 && state == 1) {
                r.keyPress(KeyEvent.VK_SPACE);
                r.keyRelease(KeyEvent.VK_SPACE);
                state = 0 ;  
            }
        } catch(AWTException e) {
            e.printStackTrace();
        }
    }




}
