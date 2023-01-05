package fastvagas.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

/** This class contains useful Date and Time conversions and parsers. */
public class DateUtil {

  public static int getMonthFromDate(Date pDate) {
    Calendar c = Calendar.getInstance();
    c.setTime(pDate);
    return c.get(Calendar.MONTH) + 1;
  }

  public static Integer getYearFromDate(Date pDate) {
    Calendar c = Calendar.getInstance();
    c.setTime(pDate);
    return c.get(Calendar.YEAR);
  }

  public static Date createDate(Integer pDay, Integer pMonth, Integer pYear) {
    final String pattern = "dd/MM/yyyy";
    try {
      final String s =
          String.format("%02d", pDay)
              + "/"
              + String.format("%02d", pMonth)
              + "/"
              + String.format("%04d", pYear);

      return new SimpleDateFormat(pattern).parse(s);
    } catch (ParseException pe) {
      pe.printStackTrace();
    }

    return null;
  }

  public static String formatLocalDate(LocalDate localDate) {
    String fmt = "dd/MM/yyyy";
    return DateTimeFormatter.ofPattern(fmt).format(localDate);
  }

  public static String formatLocalDateTime(LocalDateTime localDateTime) {
    if (Optional.ofNullable(localDateTime).isEmpty()) {
      return "";
    }
    String fmt = "dd/MM/yyyy hh:mm:ss";
    return DateTimeFormatter.ofPattern(fmt).format(localDateTime);
  }

  public static String formatDate(Date pDate, boolean withSeconds) {
    if (pDate == null) {
      return "null";
    }

    String fmt = "dd/MM/yyyy";
    if (withSeconds) {
      fmt += " HH:mm:ss";
    }

    SimpleDateFormat sdf = new SimpleDateFormat(fmt);
    return sdf.format(pDate);
  }

  public static String formatDate(Date pDate) {
    return formatDate(pDate, false);
  }

  public static Timestamp getGmtTimestamp(Date pDate) {
    if (pDate == null) {
      return null;
    }

    Calendar c = Calendar.getInstance();
    c.setTime(pDate);
    c.setTimeZone(TimeZone.getTimeZone("GMT"));
    return new Timestamp(c.getTimeInMillis());
  }

  public static Date getDateFromTimestamp(Timestamp ts) {
    if (ts == null) {
      return null;
    }

    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(ts.getTime());
    c.setTimeZone(TimeZone.getTimeZone("GMT"));

    return c.getTime();
  }

  public static LocalDateTime getLocalDateTimeFromTimestamp(Timestamp ts) {
    Date d = getDateFromTimestamp(ts);
    if (d == null) {
      return null;
    }

    return getLocalDateTimeFromDate(d);
  }

  public static LocalDate getLocalDateFromDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime getLocalDateTimeFromDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static Date getDateFromLocalDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
    return Date.from(localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public static Date addMonths(Date pDate, Integer pMonths) {
    if (pDate == null || pMonths == null || pMonths.equals(0)) {
      return pDate;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(pDate);
    calendar.add(Calendar.MONTH, pMonths);
    return calendar.getTime();
  }

  public static Date addDays(Date pDate, Integer pDays) {
    if (pDate == null || pDays == null || pDays.equals(0)) {
      return pDate;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(pDate);
    calendar.add(Calendar.DAY_OF_MONTH, pDays);
    return calendar.getTime();
  }

  public static Date subtractMonths(Date pDate, Integer pMonths) {
    return addMonths(pDate, pMonths * -1);
  }

  public static Date subtractDays(Date pDate, Integer pDays) {
    return addDays(pDate, pDays * -1);
  }

  public static boolean isGreater(Date baseDate, Date other) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(baseDate);

    Calendar c2 = Calendar.getInstance();
    c2.setTime(other);

    return c2.getTimeInMillis() > c1.getTimeInMillis();
  }

  public static boolean equalsIgnoringHours(Date pDateA, Date pDateB) {
    if (pDateA == null || pDateB == null) {
      return false;
    }

    String dateA = formatDate(pDateA);
    String dateB = formatDate(pDateB);

    return dateA.equals(dateB);
  }

  public static String formatHour(Date pDate) {
    String dateFmt = formatDate(pDate, true);
    return dateFmt.split(" ")[1].trim();
  }

  public static Date setCurrentHour(Date pDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(pDate);

    Calendar c2 = Calendar.getInstance();

    calendar.set(Calendar.HOUR, c2.get(Calendar.HOUR));
    calendar.set(Calendar.MINUTE, c2.get(Calendar.MINUTE));
    calendar.set(Calendar.SECOND, c2.get(Calendar.SECOND));

    return calendar.getTime();
  }

  public static LocalDate getCurrentLocalDate() {
    return getLocalDateFromDate(new Date());
  }

  public static LocalDateTime getCurrentLocalDateTime() {
    return getLocalDateTimeFromDate(new Date());
  }
}
