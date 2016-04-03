import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import com.sun.tools.javac.util.*;
import com.sun.tools.javac.util.List;
import com.sun.xml.internal.bind.v2.util.QNameMap;

import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Allison on 4/2/16.
 */
public class Main {
    public static void main(String args[]) throws ScriptException, Exception{
        String baseURL = "https://api.particle.io/v1/devices/330035000347343337373738/analogread?access_token=235ad2c91cac25aa4950542f1f9e4c21e8dab041";
        String amazonURL = "https://api.amazon.com/auth/O2/token";

//        AWSCredentials credentials = null;
//        try {
//            credentials = new ProfileCredentialsProvider().getCredentials();
//        } catch (Exception e) {
//            throw new AmazonClientException(
//                    "Cannot load the credentials from the credential profiles file. " +
//                            "Please make sure that your credentials file is at the correct " +
//                            "location (~/.aws/credentials), and is in valid format.",
//                    e);
//        }
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ2IBNZWMYM3FQNBA", "Dk9GVFGAaMSWeIvuIlmXY+EFWKZypWzXrVwEgVNk");
//
        AmazonSQSClient sqsClient = new AmazonSQSClient(awsCreds);
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/787859457047/BuzzQueue");


        int value = -1;

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(510);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);

        while(true) {
            value = HttpClient.getHttp(baseURL);
            KeySimulator.switchFocus(value);

            ArrayList<Message> messages = (ArrayList)sqsClient.receiveMessage(receiveMessageRequest).getMessages();
            if (messages.size() != 0) {
                String url = "https://api.particle.io/v1/devices/330035000347343337373738/buzz?access_token=235ad2c91cac25aa4950542f1f9e4c21e8dab041";
                HttpClient.getHttp(url);
                String messageReceiptHandle = messages.get(0).getReceiptHandle();
                sqsClient.deleteMessage(new DeleteMessageRequest("https://sqs.us-east-1.amazonaws.com/787859457047/BuzzQueue", messageReceiptHandle));
            }
        }
    }
}
