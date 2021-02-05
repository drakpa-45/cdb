package com.ngn.spring.project.global.global;
import com.sun.xml.fastinfoset.sax.Properties;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by USER on 3/9/2020.
 */
public class SmsSender {
    private SMSUtil smsSender = null;
    Properties properties = null;
    private static String SMS_URL = null;
    public static Boolean smsSender(String phoneNumber, String sentSMSFrom, File attachmentFile,String messageBody, String subject) throws Exception {
        boolean result = false;
      ;String trayMessage = null;
        try{
                SmsSender notification =new SmsSender();
                ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
                String smsurl =resourceBundle1.getString("smsNotificationURL");
                if(smsurl!=null)
                    SMS_URL = smsurl;
                    if(!phoneNumber.equalsIgnoreCase("")){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(phoneNumber);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent(messageBody);
                        notification.smsSender.setSubject(subject);
                        notification.smsSender.sentFrom(sentSMSFrom);
                        notification.smsSender.sendSMS();

                        trayMessage = "SMS is sent successfully to "+ phoneNumber ;
                    }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        final String finalTrayMessage = trayMessage;
        new Thread(){
            public void run(){
                try{
                    //Transport.send(message);
                    SystemTrayIcon.displayTray(finalTrayMessage);
                } catch(Exception e){
                    String errorMsg = "Mail cannot be sent. Check your net connection or configuration or "+
                            "if destination email address is valid or not.";
                    SystemTrayIcon.displayTray(errorMsg);
                }
            }
        }.start();

        return result;
    }
}
