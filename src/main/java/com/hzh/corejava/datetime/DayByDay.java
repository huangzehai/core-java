package com.hzh.corejava.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 2016/3/25.
 */
public class DayByDay {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static void main(String[] args) {
        String start="2016-03-01";
        String end="2016-03-11";
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date startDate = df.parse(start);
            Date endDate = df.parse(end);
            Date now = new Date();
            if (endDate.after(now)) {
                endDate = now;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            Date currentStartDate;
            Date currentEndDate;
            do {
                currentStartDate = calendar.getTime();
                calendar.add(Calendar.DATE, 1);
                currentEndDate = calendar.getTime();
                System.out.println("=====================");
                System.out.println(currentStartDate);
                System.out.println(currentEndDate);

            } while (calendar.getTime().before(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
