package matt.toolrental;

public class RentalTool {
    String toolCode;
    String toolBrand; 
    String toolType; 
    boolean isWeekendCharge;
    boolean isHolidayCharge;

    public RentalTool(String toolCode, String toolBrand, String toolType, boolean isWeekendCharge, boolean isHolidayCharge) {
        this.toolCode = toolCode;
        this.toolBrand = toolBrand;
        this.toolType = toolType;
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

    public boolean isWeekendCharge() {
        return this.isWeekendCharge;
    }

    public boolean isHolidayCharge() {
        return this.isHolidayCharge;
    }

}
