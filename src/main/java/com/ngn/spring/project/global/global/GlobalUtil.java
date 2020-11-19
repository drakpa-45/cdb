package com.ngn.spring.project.global.global;

import javax.activation.MimetypesFileTypeMap;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by USER on 6/27/2020.
 */
public final class GlobalUtil {
    public static String formatMessage(String applicationResourceRef, String messageKey, String... messagePlaceHolders){
        String formattedMessage="";
        try {
            MessageFormat formatter = new MessageFormat("");
            ResourceBundle resourceBundle = ResourceBundle.getBundle(applicationResourceRef);
            formatter.applyPattern(resourceBundle.getString(messageKey));
            formattedMessage = formatter.format(messagePlaceHolders);
        } catch (Exception exception) {
            //Log.error("", exception.fillInStackTrace());
            exception.printStackTrace();
        }
        return formattedMessage;
    }
    public static Properties getPropertiesFromFile(String filePath) {

        Properties properties = new Properties();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(filePath);

            //Log.debug("loaded local Resource Bundle File:" +  filePath);


            String key = null;
            if (resourceBundle != null) {
                Enumeration<String> localenum = resourceBundle.getKeys();
                while (localenum.hasMoreElements()) {
                    key = localenum.nextElement();
                    properties.put(key, resourceBundle.getString(key));
                }
            }

        } catch (MissingResourceException ex) {
            //Log.fatal("could not find file:" + ex);
            ex.printStackTrace();
        }
        return properties;
    }

    public static String getMimeType(String fileName) {

        String mimeType = null;

        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        mimeType = fileNameMap.getContentTypeFor(fileName);

        if (checkBlank(mimeType)) {
            mimeType = new MimetypesFileTypeMap().getContentType(fileName);
        }

        return mimeType;
    }

    public static boolean checkBlank(String strValue) {
        return (strValue == null || strValue.trim().length()==0);
    }
    public static String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int monthId = cal.get(Calendar.MONTH);
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        String month = "";
        if (monthId >= 0 && monthId <= 11 ) {
            month = months[monthId];
        }
        return month;
    }

    public static String getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR));
    }
}
