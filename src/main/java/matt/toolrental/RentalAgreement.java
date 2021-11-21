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
    private long chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;
    private RentalTool rentalTool;
    private static Map<String, RentalTool> inventory = new HashMap<>();
    static {
        inventory.put("LADW", new RentalTool("LADW", "Werner", "Ladder", 1.99, true, false));
        inventory.put("CHNS", new RentalTool("CHNS", "Stihl", "Chainsaw", 1.49, false, true));
        inventory.put("JAKD", new RentalTool("JAKD", "DeWalt", "Jackhammer", 2.99, false, false));
        inventory.put("JAKR", new RentalTool("JAKR", "Ridgid", "Jackhammer", 2.99, false, false));
    }

    public RentalAgreement(String toolCode, String checkOutDate, int rentalDays, double discountPercent) {
        this.rentalTool = inventory.get(toolCode);
        this.checkOutDate = LocalDate.parse(checkOutDate, DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;

        this.dueDate = this.checkOutDate.plusDays(this.rentalDays);
        this.chargeDays = calculateChargeDays(this.checkOutDate.plusDays(1), this.dueDate.plusDays(1), this.rentalTool, this.rentalDays);
        this.preDiscountCharge = this.chargeDays * this.rentalTool.getDailyCharge();
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
        return this.rentalTool.getDailyCharge();
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
