package matt.toolrental;

public class RentalTool {
    private String toolCode;
    private String toolBrand; 
    private String toolType;
    private double dailyCharge;
    private boolean isWeekendCharge;
    private boolean isHolidayCharge;

    public RentalTool(String toolCode, String toolBrand, String toolType, double dailyCharge, boolean isWeekendCharge, boolean isHolidayCharge) {
        this.toolCode = toolCode;
        this.toolBrand = toolBrand;
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.isWeekendCharge = isWeekendCharge;
        this.isHolidayCharge = isHolidayCharge;
    }


    public String getToolCode() {
        return this.toolCode;
    }

    public String getToolBrand() {
        return this.toolBrand;
    }

    public String getToolType() {
        return this.toolType;
    }

    public double getDailyCharge() {
        return this.dailyCharge;
    }

    public boolean isWeekendCharge() {
        return this.isWeekendCharge;
    }

    public boolean isHolidayCharge() {
        return this.isHolidayCharge;
    }

}
