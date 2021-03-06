import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import com.sun.tools.javac.util.*;
import com.sun.xml.internal.bind.v2.util.QNameMap;

import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

/**
 * Created by Allison on 4/2/16.
 */
public class Main {
    public static void main(String args[]) throws ScriptException, Exception{
        String baseURL = "https://api.particle.io/v1/devices/330035000347343337373738/analogread?access_token=235ad2c91cac25aa4950542f1f9e4c21e8dab041";

        int value = -1;

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(510);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);


        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                while(true)
                {
                    echoConnect();
                }
            }
        });
        t.start();
        while(true) {
            value = HttpClient.getHttp(baseURL);
            KeySimulator.switchFocus(value);
        }
    }

    public static void echoConnect() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ2IBNZWMYM3FQNBA", "Dk9GVFGAaMSWeIvuIlmXY+EFWKZypWzXrVwEgVNk");
//
        AmazonSQSClient sqsClient = new AmazonSQSClient(awsCreds);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/787859457047/BuzzQueue");

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
        if (messages.size() != 0) {
            String url = "https://api.particle.io/v1/devices/330035000347343337373738/buzz?access_token=235ad2c91cac25aa4950542f1f9e4c21e8dab041";
            HttpClient.getHttp(url);

            String messageReceiptHandle = messages.get(0).getReceiptHandle();
            sqsClient.deleteMessage(new DeleteMessageRequest("https://sqs.us-east-1.amazonaws.com/787859457047/BuzzQueue", messageReceiptHandle));
        }

    }
}
