package HW4.Market;

import java.time.LocalDate;
import java.time.Year;

public enum Holiday {
    NON(),
    DAY23(LocalDate.of(Year.now().getValue(), 2, 23)),
    DAY8(LocalDate.of(Year.now().getValue(), 3, 8)),
    NEW_YEAR(new LocalDate[] {LocalDate.of(Year.now().getValue(), 12, 31),
            LocalDate.of(Year.now().getValue(), 1, 1),
            LocalDate.of(Year.now().getValue(), 1, 2)});

//    private String day;
//    private String[] days;
    private LocalDate date;
    private LocalDate[] dates;
    Holiday(){}
//    Holiday(String day) {
//        this.day = day;
//    }
//    Holiday(String[] days) {
//        this.days = days;
//    }
    Holiday(LocalDate date) {this.date = date;}
    Holiday(LocalDate[] dates) {this.dates = dates;}

    public LocalDate getDate() {
        return date;
    }

    public LocalDate[] getDates() {
        return dates;
    }

    //    public String getDay() {
//        return day;
//    }
//
//    public String[] getDays() {
//        return days;
//    }
}
