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
}
