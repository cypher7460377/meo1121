package matt.toolrental;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class RentalAgreement {
    private LocalDate checkOutDate;
    private int rentalDays;
    private double discountPercent;
    private LocalDate dueDate;
    private double dailyCharge;
    private long chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;
    private RentalTool rentalTool;
    private static Map<String, String> typeMap = new HashMap<>();
    private static Map<String, String> brandMap = new HashMap<>();
    private static Map<String, Double> priceMap = new HashMap<>();
    private static Map<String, Boolean> weekendChargeMap = new HashMap<>();
    private static Map<String, Boolean> holidayChargeMap = new HashMap<>();
    static {
        priceMap.put("LADW", 1.99);
        typeMap.put("LADW", "Ladder");
        brandMap.put("LADW", "Werner");
        weekendChargeMap.put("LADW", true);
        holidayChargeMap.put("LADW", false);

        priceMap.put("CHNS", 1.49);
        typeMap.put("CHNS", "Chainsaw");
        brandMap.put("CHNS", "Stihl");
        weekendChargeMap.put("CHNS", false);
        holidayChargeMap.put("CHNS", true);
        
        priceMap.put("JAKD", 2.99);
        typeMap.put("JAKD", "Jackhammer");
        brandMap.put("JAKD", "DeWalt");
        weekendChargeMap.put("JAKD", false);
        holidayChargeMap.put("JAKD", false);
        
        priceMap.put("JAKR", 2.99);
        typeMap.put("JAKR", "Jackhammer");
        brandMap.put("JAKR", "Rigid");
        weekendChargeMap.put("JAKR", false);
        holidayChargeMap.put("JAKR", false);
    }

    public RentalAgreement(String toolCode, String checkOutDate, int rentalDays, double discountPercent) {
        this.rentalTool = new RentalTool(toolCode, brandMap.get(toolCode), typeMap.get(toolCode), weekendChargeMap.get(toolCode), holidayChargeMap.get(toolCode));
        this.checkOutDate = LocalDate.parse(checkOutDate, DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;

        this.dueDate = this.checkOutDate.plusDays(this.rentalDays);
        this.dailyCharge = priceMap.get(toolCode);
        this.chargeDays = calculateChargeDays(this.checkOutDate.plusDays(1), this.dueDate.plusDays(1), this.rentalTool, this.rentalDays);
        this.preDiscountCharge = this.chargeDays * this.dailyCharge;
        this.discountAmount = this.discountPercent * this.preDiscountCharge;
        this.finalCharge = this.preDiscountCharge - this.discountAmount;
    }

    private long calculateChargeDays(LocalDate startDate, LocalDate endDate, RentalTool tool, int days) {
        final Set<LocalDate> holidays = calculateHolidays(endDate, tool, days);
        final Set<DayOfWeek> weekdays = calculateWeekdays(tool);
        return startDate.datesUntil(endDate)
        .filter(date -> weekdays.contains(date.getDayOfWeek()))
        .filter(date -> !holidays.contains(date)).count();
    }

    private Set<DayOfWeek> calculateWeekdays(RentalTool tool) {
        Set<DayOfWeek> days = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
        if (tool.isWeekendCharge()) {
            days.add(DayOfWeek.SATURDAY);
            days.add(DayOfWeek.SUNDAY);
        }
        return days;
    }

    private Set<LocalDate> calculateHolidays(LocalDate end, RentalTool tool, int days) {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        Set<LocalDate> holidays = new HashSet<>();

        if (!tool.isHolidayCharge()) {
            int years = (days/365) + (int)(Math.ceil((double)(days%365)/365));
            int year = end.getYear();
            for (int i = 0; i < years; i++) {
                LocalDate independenceDay = LocalDate.of(year+i, 7, 4);
                if (weekend.contains(independenceDay.getDayOfWeek())) {
                    if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) independenceDay = independenceDay.minusDays(1);
                    else independenceDay = independenceDay.plusDays(1);
                }
                holidays.add(independenceDay);

                LocalDate laborDay = LocalDate.of(year+i, 9, 1);
                switch(laborDay.getDayOfWeek()) {
                    case TUESDAY:
                        laborDay = laborDay.plusDays(6);
                        break;
                    case WEDNESDAY:
                        laborDay = laborDay.plusDays(5);
                        break;
                    case THURSDAY:
                        laborDay = laborDay.plusDays(4);
                        break;
                    case FRIDAY:
                        laborDay = laborDay.plusDays(3);
                        break;
                    case SATURDAY:
                        laborDay = laborDay.plusDays(2);
                        break;
                    case SUNDAY:
                        laborDay = laborDay.plusDays(1);
                        break;
                    default:
                        break;
                }
                holidays.add(laborDay);
            }
        }
        return holidays;
    }

    public String generateRentalAgreement() {
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        return
        " Tool code: " + getToolCode() + System.lineSeparator() +
        " Tool type: " + getToolType() + System.lineSeparator() +
        " Tool brand: " + getToolBrand() + System.lineSeparator() +
        " Rental days: " + getRentalDays() + System.lineSeparator() +
        " Check out date: " + getCheckoutDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + System.lineSeparator() +
        " Due date: " + getDueDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + System.lineSeparator() +
        " Daily rental charge: " + dollarFormat.format(getDailyCharge()) + System.lineSeparator() +
        " Charge days: " + getChargeDays() + System.lineSeparator() +
        " Pre-discount charge: " + dollarFormat.format(getPreDiscountCharge()) + System.lineSeparator() +
        " Discount percent: " + percentFormat.format(getDiscountPercent()) + System.lineSeparator() +
        " Discount amount: " + dollarFormat.format(getDiscountAmount()) + System.lineSeparator() +
        " Final charge: " + dollarFormat.format(getFinalCharge());
    }

    public String getToolCode() {
        return this.rentalTool.getToolCode();
    }

    public String getToolType() {
        return this.rentalTool.getToolType();
    }

    public String getToolBrand() {
        return this.rentalTool.getToolBrand();
    }

    public int getRentalDays() {
        return this.rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return this.checkOutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public double getDailyCharge() {
        return this.dailyCharge;
    }

    public long getChargeDays() {
        return this.chargeDays;
    }

    public double getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public double getDiscountPercent() {
        return this.discountPercent;
    }

    public double getDiscountAmount() {
        return this.discountAmount;
    }

    public double getFinalCharge() {
        return this.finalCharge;
    }

}
