package com.example.newclendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtils {

    // Date formatters
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

    // Get the list of week days from the given date
    public static List<Date> getWeekDaysFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set calendar to the start of the week (Monday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        List<Date> weekDays = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weekDays.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);  // Move to the next day
        }
        return weekDays;
    }

    // Get the month and year for a week; handles cases where a week spans two months
    public static String getMonthYear(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        // Get the start date's month and year
        String startMonth = monthFormat.format(calendar.getTime());

        // Move to the end of the week (Sunday)
        calendar.add(Calendar.DAY_OF_WEEK, 6);

        // Get the end date's month and year
        String endMonth = monthFormat.format(calendar.getTime());
        String year = yearFormat.format(calendar.getTime());

        // If the months are the same, return just one month with the year
        if (startMonth.equals(endMonth)) {
            return startMonth + " " + year;
        } else {
            // If the months are different, return the format "Month1-Month2 Year"
            return startMonth + "-" + endMonth + " " + year;
        }
    }
}


