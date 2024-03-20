
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCalendar {

    HashMap<Integer, List<LocalDate>> holidayMap = new HashMap<>();

 /* Given the requirements do not specify a maximum number of rental days, theorhetically we can rent a tool indefinitely, so we must calcluate holidays for each year that we might
  * need up to theorhetical infinity. Not practical, but to be within the context of the requirements, Calculate the holidays for a year, (In the real world I would follow up on this requirement to get a maxumum rental days)
  * but this is not the real world, so will code this to be able to handle multiple year rentals, so must calculate holidays for any year it gets... only do this once per year, store result so aren't calculating them
  * with every single check.
  */

    private List<LocalDate> getHolidays(int year) {
        if (holidayMap.get(year) == null) {
            List<LocalDate> holidays = new ArrayList<>();
            LocalDate ld = LocalDate.ofYearDay(year, 1);
            ld = (ld.withMonth(Month.SEPTEMBER.getValue())).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

            holidays.add(ld);

            LocalDate July4 = LocalDate.now();
            July4 = (July4.withMonth(Month.JULY.getValue())).withDayOfMonth(4);
            if (July4.getDayOfWeek() == DayOfWeek.SATURDAY)
                July4 = July4.minusDays(1);
            else if (July4.getDayOfWeek() == DayOfWeek.SUNDAY)
                July4 = July4.plusDays(1);
            holidays.add(July4);

            holidayMap.put(year, holidays);
        }
        return holidayMap.get(year);
    }

    /*
     * Is the date a Holiday
     */
    public boolean isHoliday(LocalDate date){
        List<LocalDate> holidays = getHolidays(date.getYear());
        System.out.println(holidays);
       return(holidays.stream().filter( holiday -> holiday.isEqual(date)).count()>0);
    
    }
    // Assumption Holiday takes precidence over any other kind of day
    // meaning if a day is a holiday, it will return false for a weekday or weekend
    // check
    // even though the day may indeed be a weekday or a weekend
    // I do not see this explicitely spelled out in the document, but without making
    // this assumption
    // you will wind up with conflicting directives on whether or not to bill.

    public boolean isWeekend(LocalDate date) {
        return (!isHoliday(date) && (date.getDayOfWeek() == DayOfWeek.SATURDAY)
                || (date.getDayOfWeek() == DayOfWeek.SUNDAY));
    }

    // Assumption, Holiday takes presidence over any other kind of day.
    public boolean isWeekday(LocalDate date) {
        return (!isHoliday(date)) && (!isWeekend(date));
    }

}
