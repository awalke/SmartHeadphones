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
    private int state = 1;

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isMac() {
        if (OS.equals("mac os x")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void switchFocus() {
        try {
            Robot r = new Robot();

            r.keyPress(KeyEvent.VK_META);
            r.keyPress(KeyEvent.VK_TAB);
            r.delay(500);
            r.keyRelease(KeyEvent.VK_META);
            r.keyRelease(KeyEvent.VK_TAB);
            r.delay(1000);
            r.keyPress(KeyEvent.VK_SPACE);
            r.keyRelease(KeyEvent.VK_SPACE);
        } catch(AWTException e) {
            e.printStackTrace();
        }
    }
}
