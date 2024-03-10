/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class DateTimeHelper {
      public static Date getWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - calendar.getFirstDayOfWeek();
        calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(utilDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return new java.sql.Date(calendar.getTimeInMillis());
        } else {
            return null;
        }
    }

    public static Date addDaysToDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public static ArrayList<java.sql.Date> getDatesBetween(java.sql.Date fromDate, java.sql.Date toDate) {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Both fromDate and toDate cannot be null.");
        }
        if (fromDate.after(toDate)) {
            throw new IllegalArgumentException("fromDate cannot be after toDate.");
        }
        
        Date from = new Date(fromDate.getTime());
        Date to = new Date(toDate.getTime());
        
        Date temp = from;

        ArrayList<java.sql.Date> dates = new ArrayList<>();
        while(!temp.after(to))
        {
            dates.add(convertUtilDateToSqlDate(temp));
            temp=addDaysToDate(temp, 1);
        }
        return dates;
    }
    
      public static String findDayOfWeek(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        String dayOfWeekString = dayOfWeek.toString();
        
        dayOfWeekString = dayOfWeekString.substring(0, 1).toUpperCase() + dayOfWeekString.substring(1).toLowerCase();
        
        return dayOfWeekString;
    }
       public static ArrayList<String> findDayOfWeekRange(String startDateString, String endDateString) {
        ArrayList<String> dayOfWeekList = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            String dayOfWeekString = dayOfWeek.toString();
            dayOfWeekString = dayOfWeekString.substring(0, 1).toUpperCase() + dayOfWeekString.substring(1).toLowerCase();
            dayOfWeekList.add(dayOfWeekString);
            currentDate = currentDate.plusDays(1); // Move to the next date
        }
        
        return dayOfWeekList;
    }

}
