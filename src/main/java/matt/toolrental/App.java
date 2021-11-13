package matt.toolrental;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ITypeConverter;
import picocli.CommandLine.Option;
import picocli.CommandLine.TypeConversionException;

@Command(name = "Tool Rental", mixinStandardHelpOptions = true)
public class App implements Runnable
{
    static class PercentConverter implements ITypeConverter<Double> {

        @Override
        public Double convert(String value) throws Exception {
            double percentValue = Double.parseDouble(value);
            if (percentValue % 1 != 0 || percentValue < 0 || percentValue > 100) {
                throw new TypeConversionException("Invalid discount percent value. Provided value must fall between 0-100 as a whole number (e.g. -p 20).");
            }
            return Double.valueOf(percentValue/100);
        }

    }

    static class RentalDaysConverter implements ITypeConverter<Integer> {

        @Override
        public Integer convert(String value) throws Exception {
            Integer rentalDays = Integer.parseInt(value);
            if (rentalDays < 1) {
                throw new TypeConversionException("Invalid value for rental days. Provided value must be greater than 0.");
            }
            return rentalDays;
        }
    }

    
    @Option(names = {"-t", "--tool-code"}, description = "Tool code for the rental tool. Acceptable tool codes are LADW, CHNS, JAKD, JAKR.", required = true)
    String toolCode;
    
    @Option(names = {"-r", "--rental-days"}, description = "Rental days", required = true)
    int rentalDays;
    
    @Option(names = {"-d", "--checkout-date"}, description = "Check out date in the format MM/dd/yy", required = true)
    String checkOutDate;

    @Option(names = {"-p", "--discount-percent"}, description = "Discount percent", required = true, converter = PercentConverter.class)
    double discountPercent;
    public static void main( String[] args ) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public void run() {
        RentalAgreement rentalAgreement = new RentalAgreement(toolCode, checkOutDate, rentalDays, discountPercent);
        System.out.println(rentalAgreement.generateRentalAgreement());
    }
}
