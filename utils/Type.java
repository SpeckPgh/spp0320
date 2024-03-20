package utils;

public class Type{
    Float charge;
    boolean weekDay;
    boolean weekend;
    boolean holiday;

    public Float getCharge() {
        return charge;
    }

    public void setCharge(Float charge) {
        this.charge = charge;
    }

    public boolean isWeekDay() {
        return weekDay;
    }

    public void setWeekDay(boolean weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }

    public Type(Float charge, boolean weekDay, boolean weekend, boolean holiday) {
        this.charge = charge;
        this.weekDay = weekDay;
        this.weekend = weekend;
        this.holiday = holiday;
    }

    
}