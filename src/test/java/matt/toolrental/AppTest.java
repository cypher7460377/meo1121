package matt.toolrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Test 1
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(2)
    public void checkExceptionForPercentDiscountTest() throws Exception {
        String expectedPercentError = "Invalid value for option '--discount-percent': Invalid discount percent value. Provided value must fall between 0-100 as a whole number (e.g. -p 20)." + System.lineSeparator() +
        "Usage: Tool Rental [-hV] -d=<checkOutDate> -p=<discountPercent> -r=<rentalDays>" + System.lineSeparator() +
        "                   -t=<toolCode>" + System.lineSeparator() +
        "  -d, --checkout-date=<checkOutDate>" + System.lineSeparator() +
        "                  Check out date in the format MM/dd/yy" + System.lineSeparator() +
        "  -h, --help      Show this help message and exit." + System.lineSeparator() +
        "  -p, --discount-percent=<discountPercent>" + System.lineSeparator() +
        "                  Discount percent" + System.lineSeparator() +
        "  -r, --rental-days=<rentalDays>" + System.lineSeparator() +
        "                  Rental days" + System.lineSeparator() +
        "  -t, --tool-code=<toolCode>" + System.lineSeparator() +
        "                  Tool code for the rental tool. Acceptable tool codes are" + System.lineSeparator() +
        "                    LADW, CHNS, JAKD, JAKR." + System.lineSeparator() +
        "  -V, --version   Print version information and exit." + System.lineSeparator();
        String[] args = new String[]{"-d", "9/3/15", "-p", "101", "-r", "5", "-t", "JAKR"};
        App.main(args);
    
        assertEquals(expectedPercentError, errContent.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
    }

    /**
     * Test 2
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(0)
    public void checkLadderRentalTest() throws Exception {
        String[] args = new String[]{"-d", "7/2/20", "-p", "10", "-r", "3", "-t", "LADW"};
        App.main(args);
    
        String expectedLadderOutput = 
" Tool code: LADW" + System.lineSeparator() +
" Tool type: Ladder" + System.lineSeparator() +
" Tool brand: Werner" + System.lineSeparator() +
" Rental days: 3" + System.lineSeparator() +
" Check out date: 7/2/20" + System.lineSeparator() +
" Due date: 7/5/20" + System.lineSeparator() +
" Daily rental charge: $1.99" + System.lineSeparator() +
" Charge days: 2" + System.lineSeparator() +
" Pre-discount charge: $3.98" + System.lineSeparator() +
" Discount percent: 10%" + System.lineSeparator() +
" Discount amount: $0.40" + System.lineSeparator() +
" Final charge: $3.58"  + System.lineSeparator();
        assertEquals(expectedLadderOutput, outContent.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
    }

    /**
     * Test 3
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(0)
    public void checkChainsawRentalTest() throws Exception {
        String[] args = new String[]{"-d", "7/2/15", "-p", "25", "-r", "5", "-t", "CHNS"};
        App.main(args);
    
        String expectedChainsawOutput = 
" Tool code: CHNS" + System.lineSeparator() +
" Tool type: Chainsaw" + System.lineSeparator() +
" Tool brand: Stihl" + System.lineSeparator() +
" Rental days: 5" + System.lineSeparator() +
" Check out date: 7/2/15" + System.lineSeparator() +
" Due date: 7/7/15" + System.lineSeparator() +
" Daily rental charge: $1.49" + System.lineSeparator() +
" Charge days: 3" + System.lineSeparator() +
" Pre-discount charge: $4.47" + System.lineSeparator() +
" Discount percent: 25%" + System.lineSeparator() +
" Discount amount: $1.12" + System.lineSeparator() +
" Final charge: $3.35"  + System.lineSeparator();
        // assertEquals(expectedChainsawOutput, errContent.toString());
        assertEquals(expectedChainsawOutput, outContent.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
    }

    /**
     * Test 4
     * Test Jackhammer rental over Labor Day holiday
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(0)
    public void checkJackhammerRentalTest() throws Exception {
        String[] args = new String[]{"-d", "9/3/15", "-p", "0", "-r", "6", "-t", "JAKD"};
        App.main(args);
    
        String expectedChainsawOutput = 
" Tool code: JAKD" + System.lineSeparator() +
" Tool type: Jackhammer" + System.lineSeparator() +
" Tool brand: DeWalt" + System.lineSeparator() +
" Rental days: 6" + System.lineSeparator() +
" Check out date: 9/3/15" + System.lineSeparator() +
" Due date: 9/9/15" + System.lineSeparator() +
" Daily rental charge: $2.99" + System.lineSeparator() +
" Charge days: 3" + System.lineSeparator() +
" Pre-discount charge: $8.97" + System.lineSeparator() +
" Discount percent: 0%" + System.lineSeparator() +
" Discount amount: $0.00" + System.lineSeparator() +
" Final charge: $8.97"  + System.lineSeparator();
        // assertEquals(expectedChainsawOutput, errContent.toString());
        assertEquals(expectedChainsawOutput, outContent.toString());
    }
    /**
     * Test 5
     * Test Rigid Jackhammer rental over Independence Day holiday
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(0)
    public void checkRigidJackhammerRentalTest() throws Exception {
        String[] args = new String[]{"-d", "7/2/15", "-p", "0", "-r", "9", "-t", "JAKR"};
        App.main(args);
    
        String expectedChainsawOutput = 
" Tool code: JAKR" + System.lineSeparator() +
" Tool type: Jackhammer" + System.lineSeparator() +
" Tool brand: Rigid" + System.lineSeparator() +
" Rental days: 9" + System.lineSeparator() +
" Check out date: 7/2/15" + System.lineSeparator() +
" Due date: 7/11/15" + System.lineSeparator() +
" Daily rental charge: $2.99" + System.lineSeparator() +
" Charge days: 5" + System.lineSeparator() +
" Pre-discount charge: $14.95" + System.lineSeparator() +
" Discount percent: 0%" + System.lineSeparator() +
" Discount amount: $0.00" + System.lineSeparator() +
" Final charge: $14.95"  + System.lineSeparator();
        assertEquals(expectedChainsawOutput, outContent.toString());
    }

    /**
     * Test 5
     * Test Rigid Jackhammer rental over Independence Day holiday with a discount
     * @throws Exception
     */
    @Test
    @ExpectSystemExitWithStatus(0)
    public void checkRigidJackhammerDiscountRentalTest() throws Exception {
        String[] args = new String[]{"-d", "7/2/15", "-p", "0", "-r", "9", "-t", "JAKR"};
        App.main(args);
    
        String expectedChainsawOutput = 
" Tool code: JAKR" + System.lineSeparator() +
" Tool type: Jackhammer" + System.lineSeparator() +
" Tool brand: Rigid" + System.lineSeparator() +
" Rental days: 9" + System.lineSeparator() +
" Check out date: 7/2/15" + System.lineSeparator() +
" Due date: 7/11/15" + System.lineSeparator() +
" Daily rental charge: $2.99" + System.lineSeparator() +
" Charge days: 5" + System.lineSeparator() +
" Pre-discount charge: $14.95" + System.lineSeparator() +
" Discount percent: 0%" + System.lineSeparator() +
" Discount amount: $0.00" + System.lineSeparator() +
" Final charge: $14.95"  + System.lineSeparator();
        assertEquals(expectedChainsawOutput, outContent.toString());
    }
}
