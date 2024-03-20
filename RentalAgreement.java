
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {

    String toolCode;
    String toolType;

    String toolBrand;
    int rentalDays;
    LocalDate checkoutDate;
    LocalDate dueDate;
    float dailyRentalCharge;
    int chargeDays;
    float preDiscountCharge;
    float discountPercent;
    float discountAmount;
    float finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, LocalDate checkoutDate,
            LocalDate dueDate, float dailyRentalCharge, int chargeDays, float preDiscountCharge, float discountPercent,
            float discountAmount, float finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }


    public void PrintStatement() {

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
          NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("en","US"));
          NumberFormat percentFormatter = NumberFormat.getPercentInstance();
          percentFormatter.setMinimumFractionDigits(0);
            System.out.println("====== Rental Agreement ======");
            System.out.printf("Tool Code: %s\n", toolCode);
            System.out.printf("Tool Type: %s\n", toolType);
            System.out.printf("Tool Brand: %s\n", toolBrand);
            System.out.printf("Rental Days: %d\n", rentalDays);
            System.out.printf("Check Out Date: %s\n",formatter.format(checkoutDate));
            System.out.printf("Due Date: %s\n",formatter.format(dueDate));
            System.out.printf("Daily Rental Charge: %s\n", currencyFormatter.format(dailyRentalCharge));
            System.out.printf("Charge Days: %d\n",chargeDays);
            System.out.printf("Pre-Discount Charge: %s\n",currencyFormatter.format(preDiscountCharge));
            System.out.printf("Discount Percent: %s\n",percentFormatter.format(discountPercent));
            System.out.printf("Diacount Amount: %s\n",currencyFormatter.format(discountAmount));
            System.out.printf("final Charge: %s\n",currencyFormatter.format(finalCharge));
}

}
