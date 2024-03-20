
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import utils.Tools;
import utils.Types;
import utils.Type;

/*
 * I added some requirements that were not in the original document and made assumptions that should be 
 * documented in the comments
 * Since the directions also stated that they desired to see how I think and solve a problem, I have added my reasoning behind them.
 * 
 * Thinks I would definitely change if this was a real world thing, all the text strings would be in a static class, and all hard coded texts would 
 * simply reference the appropriate variables etc.
 */

public class MySolution {

    static Tools tools;
    static Types types;
    static MyCalendar holidays;

    // Initialize the class and its dependancies.
    public MySolution() {
        tools = new Tools();
        types = new Types();
        holidays = new MyCalendar();
    }

    public static void main(String[] args) {
        // Instantiate self
        MySolution h = new MySolution();
        RentalAgreement rentalAgreement = null;

        // Get User Input

        Scanner sc = new Scanner(System.in);

        String code = getCode(sc);
        while ((code == null) || (tools.get(code) == null)) {
            System.out.println("Invalid Code, please enter a valid Tool Code :" + tools.getKeys());
            code = getCode(sc);
        }
        int days = getDays(sc);
        while ((days < 1) || (days > 365)) {
            System.out.println("Rental Days must be greater than 0 and less than 365");
            days = getDays(sc);
        }
        float discount = getDiscount(sc);
        while ((discount < 0) || (discount > 100)) {
            System.out.println("discount must be between 0 and 100");
            discount = getDiscount(sc);
        }

        // Get the CheckoutDate
        // Assumed limiation no rental more than a year in the future and
        // no rental can be scheduled in the past
        // and no rental length of more than a year in length

        boolean stat = true;
        LocalDate checkOutDate = LocalDate.now();
        while (stat) {
            try {
                checkOutDate = getDateAsString(sc);
                if (checkOutDate.isAfter(LocalDate.now().plusYears(1)))
                    throw new BadInputException("You cannot schedule a rental more than a year ahead of time.");
                    /* 
                    No Specification to not be able to post date rentals, so have commented this check out.
                else if (checkOutDate.isBefore(LocalDate.now()))
                    throw new BadInputException("You cannot schedule a rental to begin in the past)");
                    */
                stat = false;
            } catch (DateTimeParseException p) {
                System.out.println("Checkout Date Format invalid, please Enter Checkout date in proper format");

            } catch (BadInputException b) {
                System.out.println(b.getMessage());
            }

        }

        try {
            
            rentalAgreement = h.checkout(code, days, checkOutDate, discount);

        } catch (Exception e) {
            System.out.println(e);
        }

        rentalAgreement.PrintStatement();
    }

    private static float calculateDiscount(float amount, float percentage) {
        float discount = amount * percentage;
        float f = Math.round(discount * 100);
        f = f / 100;
        return (f);
    }

    private static LocalDate getDateAsString(Scanner sc) throws DateTimeParseException {

        System.out.println("Checkout Date (\"MM/DD/YYYY\"):");
        String dateAsString = sc.next();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate checkoutDate = LocalDate.parse(dateAsString, formatter);

        return (checkoutDate);
    }

    private static String getCode(Scanner sc) {
        System.out.println("Enter Tool Code");
        String code = sc.nextLine();
        return (code);
    }

    private static float getDiscount(Scanner sc) {
        System.out.println("Enter Discount Percentage:");
        float discount = (float)(sc.nextInt())/100;
        return discount;
    }

    private static int getDays(Scanner sc) {
        System.out.println("Enter Rental Day Count:");
        int days = sc.nextInt();
        return days;
    }

    /*
     * Perform the "checkout" operation with the user input data, and return the rental agreement object.
     */
    public RentalAgreement checkout(String toolCode, int rentalDays, LocalDate checkOutDate, float discount)
            throws BadInputException {

        Type billingType = types.get(tools.get(toolCode).getType());

        float total = 0;
        float dailyRate = billingType.getCharge();
        boolean chargeWeekend = billingType.isWeekend();
        boolean chargeHoliday = billingType.isHoliday();
        boolean chargeWeekday = billingType.isWeekDay();

        LocalDate workingCheckOutDate = checkOutDate;

        LocalDate dueDate = checkOutDate.plusDays(rentalDays);

        int chargeDays = 0;
        for (int x = 0; x < rentalDays; x++) {
            if (chargeHoliday && holidays.isHoliday(workingCheckOutDate)) {
                total += dailyRate;
                chargeDays++;
            } else if (chargeWeekend && holidays.isWeekend(workingCheckOutDate)) {
                total += dailyRate;
                chargeDays++;
            } else if (chargeWeekday && holidays.isWeekday(workingCheckOutDate)) {
                total += dailyRate;
                chargeDays++;
            }
            workingCheckOutDate = workingCheckOutDate.plusDays(1);

        }

        float discountAmount = calculateDiscount(total, discount);
        return (new RentalAgreement(toolCode, tools.get(toolCode).getType(), tools.get(toolCode).getBrand(), rentalDays,
                checkOutDate,
                dueDate, dailyRate, chargeDays, total, discount, discountAmount, total - discountAmount));

    }

}