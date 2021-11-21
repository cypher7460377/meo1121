package matt.toolrental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class RentalAgreementTest {

	@Test
	public void checkRentalAgreementForLadderNoWeekendNoHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("LADW", "1/6/20", 3, 0.1);
		assertEquals("LADW", rentalAgreement.getToolCode());
		assertEquals("Ladder", rentalAgreement.getToolType());
		assertEquals("Werner", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 1, 6), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 1, 9), rentalAgreement.getDueDate());
		assertEquals(1.99, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(5.97, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.1, rentalAgreement.getDiscountPercent());
		assertEquals(0.597, rentalAgreement.getDiscountAmount());
		assertEquals(5.372999999999999, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForLadderNoWeekendHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("LADW", "7/2/20", 3, 0.1);
		assertEquals("LADW", rentalAgreement.getToolCode());
		assertEquals("Ladder", rentalAgreement.getToolType());
		assertEquals("Werner", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 5), rentalAgreement.getDueDate());
		assertEquals(1.99, rentalAgreement.getDailyCharge());
		assertEquals(2, rentalAgreement.getChargeDays());
		assertEquals(3.98, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.1, rentalAgreement.getDiscountPercent());
		assertEquals(0.398, rentalAgreement.getDiscountAmount());
		assertEquals(3.582, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForChainsawNoWeekendNoHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("CHNS", "1/6/20", 3, 0.1);
		assertEquals("CHNS", rentalAgreement.getToolCode());
		assertEquals("Chainsaw", rentalAgreement.getToolType());
		assertEquals("Stihl", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 1, 6), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 1, 9), rentalAgreement.getDueDate());
		assertEquals(1.49, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(4.47, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.1, rentalAgreement.getDiscountPercent());
		assertEquals(0.447, rentalAgreement.getDiscountAmount());
		assertEquals(4.023, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForChainsawWeekendHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("CHNS", "7/2/15", 5, 0.25);
		assertEquals("CHNS", rentalAgreement.getToolCode());
		assertEquals("Chainsaw", rentalAgreement.getToolType());
		assertEquals("Stihl", rentalAgreement.getToolBrand());
		assertEquals(5, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 7, 7), rentalAgreement.getDueDate());
		assertEquals(1.49, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(4.47, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.25, rentalAgreement.getDiscountPercent());
		assertEquals(1.1175, rentalAgreement.getDiscountAmount());
		assertEquals(3.3525, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForDeWaltJackhammerNoWeekendNoHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("JAKD", "1/6/20", 3, 0.1);
		assertEquals("JAKD", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("DeWalt", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 1, 6), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 1, 9), rentalAgreement.getDueDate());
		assertEquals(2.99, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(8.97, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.1, rentalAgreement.getDiscountPercent());
		assertEquals(0.8970000000000001, rentalAgreement.getDiscountAmount());
		assertEquals(8.073, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForDeWaltJackhammerWeekendHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("JAKD", "9/3/15", 6, 0.0);
		assertEquals("JAKD", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("DeWalt", rentalAgreement.getToolBrand());
		assertEquals(6, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2015, 9, 3), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2015, 9, 9), rentalAgreement.getDueDate());
		assertEquals(2.99, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(8.97, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.0, rentalAgreement.getDiscountPercent());
		assertEquals(0.0, rentalAgreement.getDiscountAmount());
		assertEquals(8.97, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForRidgidJackhammerNoWeekendNoHolidayTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("JAKR", "1/6/20", 3, 0.1);
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getToolBrand());
		assertEquals(3, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 1, 6), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 1, 9), rentalAgreement.getDueDate());
		assertEquals(2.99, rentalAgreement.getDailyCharge());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals(8.97, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.1, rentalAgreement.getDiscountPercent());
		assertEquals(0.8970000000000001, rentalAgreement.getDiscountAmount());
		assertEquals(8.073, rentalAgreement.getFinalCharge());
	}

	@Test
	public void checkRentalAgreementForRidgidJackhammerWeekendHolidayDiscountTest() {
		RentalAgreement rentalAgreement = new RentalAgreement("JAKR", "7/2/20", 4, 0.5);
		assertEquals("JAKR", rentalAgreement.getToolCode());
		assertEquals("Jackhammer", rentalAgreement.getToolType());
		assertEquals("Ridgid", rentalAgreement.getToolBrand());
		assertEquals(4, rentalAgreement.getRentalDays());
		assertEquals(LocalDate.of(2020, 7, 2), rentalAgreement.getCheckoutDate());
		assertEquals(LocalDate.of(2020, 7, 6), rentalAgreement.getDueDate());
		assertEquals(2.99, rentalAgreement.getDailyCharge());
		assertEquals(1, rentalAgreement.getChargeDays());
		assertEquals(2.99, rentalAgreement.getPreDiscountCharge());
		assertEquals(0.5, rentalAgreement.getDiscountPercent());
		assertEquals(1.495, rentalAgreement.getDiscountAmount());
		assertEquals(1.495, rentalAgreement.getFinalCharge());
	}
	
}