package com.ngn.spring.project.global.global;

import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Created by USER on 10/21/2020.
 */
public class SmsSender {

    //private EmailUtil emailSender = null;
    private SMSUtil smsSender = null;
    private static final String EMAIL_BODY_PART1 = "Your application";
    private static final String SMS_BODY_PART2 = ".Click on the link above to make payment";
    private static String SMS_URL = null;
    Properties properties = null;

    public static boolean notifyOnRejection(String appNumber, String[] contactNumbers, String reason)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                SmsSender notification =new SmsSender();
                notification.properties = GlobalUtil.getPropertiesFromFile(ProtocolConstant.DOP_COMMON_PROPERTIES_FILE_PATH);
                //notification.properties = ResourceBundle.getBundle("documentuploads");
                if(notification.properties.getProperty("smsNotificationURL")!=null)
                    SMS_URL = notification.properties.getProperty("smsNotificationURL");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);
                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent(EMAIL_BODY_PART1+" "+appNumber+" has been rejected."+reason+" ");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnApproval(String appNumber, String[] contactNumbers,String smsContent, String allotRange) {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                SmsSender notification =new SmsSender();
                notification.properties = GlobalUtil.getPropertiesFromFile(ProtocolConstant.DOP_COMMON_PROPERTIES_FILE_PATH);
                //notification.properties = ResourceBundle.getBundle("documentuploads");
                if(notification.properties.getProperty("smsNotificationURL")!=null)
                    SMS_URL = notification.properties.getProperty("smsNotificationURL");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);
                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent(EMAIL_BODY_PART1 + " " + appNumber +  "" + smsContent+   allotRange+" Office");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

}
