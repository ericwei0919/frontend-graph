package com.lmml.graph.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class TimestampUtil {

    private static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String parseTimestampToString(Timestamp timestamp) {
        DateFormat sdf = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return sdf.format(timestamp);
    }
}
