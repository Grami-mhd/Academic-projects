/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.nexmo.messaging.sdk.NexmoSmsClient;
import com.nexmo.messaging.sdk.SmsSubmissionResult;
import com.nexmo.messaging.sdk.messages.TextMessage;

/**
 *
 * @author grami
 */
public class SMS {

    public static final String API_KEY = "6f409b3d";
    public static final String API_SECRET = "d03c023f672d6696";
    public static final String SMS_FROM = "Guest House";
    
    public static void sendSMS(String txt,long nbr){
         NexmoSmsClient client = null;
        try {
            client = new NexmoSmsClient(API_KEY, API_SECRET);
        } catch (Exception e) {
            System.err.println("Failed to instantiate a Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to instantiate a Nexmo Client");
        }

        // Create a Text SMS Message request object ...

        TextMessage message = new TextMessage(SMS_FROM, "+216"+nbr, txt);

        // Use the Nexmo client to submit the Text Message ...

        SmsSubmissionResult[] results = null;
        try {
            results = client.submitMessage(message);
        } catch (Exception e) {
            System.err.println("Failed to communicate with the Nexmo Client");
            e.printStackTrace();
            throw new RuntimeException("Failed to communicate with the Nexmo Client");
        }

        // Evaluate the results of the submission attempt ...
        System.out.println("... Message submitted in [ " + results.length + " ] parts");
        for (int i=0;i<results.length;i++) {
            System.out.println("--------- part [ " + (i + 1) + " ] ------------");
            System.out.println("Status [ " + results[i].getStatus() + " ] ...");
            if (results[i].getStatus() == SmsSubmissionResult.STATUS_OK)
                System.out.println("SUCCESS");
            else if (results[i].getTemporaryError())
                System.out.println("TEMPORARY FAILURE - PLEASE RETRY");
            else
                System.out.println("SUBMISSION FAILED!");
            System.out.println("Message-Id [ " + results[i].getMessageId() + " ] ...");
            System.out.println("Error-Text [ " + results[i].getErrorText() + " ] ...");

            if (results[i].getMessagePrice() != null)
                System.out.println("Message-Price [ " + results[i].getMessagePrice() + " ] ...");
            if (results[i].getRemainingBalance() != null)
                System.out.println("Remaining-Balance [ " + results[i].getRemainingBalance() + " ] ...");
        }      
    }

}
