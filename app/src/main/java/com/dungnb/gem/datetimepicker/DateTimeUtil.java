package com.dungnb.gem.datetimepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
  public static Calendar calendarSetTime(String strTime) {
    Date date;
    SimpleDateFormat sdf;
    try {
      sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      date = sdf.parse(strTime);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return Calendar.getInstance();
  }


  public static String getStringDate(Calendar calendar) {
    String date_current = "";
    String year = String.valueOf(calendar.get(Calendar.YEAR));
    String month = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
    String day = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
    date_current = day + "-" + month + "-" + year;
    return date_current;
  }

  public static String getStringTime(Calendar calendar) {
    String time_current = "";
    String hour = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY));
    String minute = String.format("%02d", calendar.get(Calendar.MINUTE));
    String second = String.format("%02d", calendar.get(Calendar.SECOND));
    time_current = hour + ":" + minute + ":" + second;
    return time_current;
  }
}
