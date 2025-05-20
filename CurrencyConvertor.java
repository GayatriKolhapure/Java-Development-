import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CurrencyConvertor {
      // Define conversion rates as constants
    public static final BigDecimal INR_TO_USD = BigDecimal.valueOf(0.012);
    public static final BigDecimal INR_TO_EUR = BigDecimal.valueOf(0.011);
    public static final BigDecimal INR_TO_GBP = BigDecimal.valueOf(0.0096);
    public static final BigDecimal USD_TO_INR = BigDecimal.valueOf(83.0);
    public static final BigDecimal EUR_TO_INR = BigDecimal.valueOf(90.0);
    public static final BigDecimal GBP_TO_INR = BigDecimal.valueOf(100.0);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.println("Choose an option...!");

        System.out.println("1. INR to USD");
        System.out.println("2. INR to EUR");
        System.out.println("3. INR to GBP");
        System.out.println("4. USD to INR");
        System.out.println("5. EUR to INR");
        System.out.println("6. GBP to INR");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();

        System.out.println("Enter Amount...!");
        BigDecimal amount = sc.nextBigDecimal();

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        switch (choice) {
            case 1:
                System.out.println("INR to USD: " + convert(amount, INR_TO_USD));
                break;
            case 2:
                System.out.println("INR to EUR: " + convert(amount, INR_TO_EUR));
                break;
            case 3:
                System.out.println("INR to GBP: " + convert(amount, INR_TO_GBP));
                break;
            case 4:
                System.out.println("USD to INR: " + convert(amount, USD_TO_INR));
                break;
            case 5:
                System.out.println("EUR to INR: " + convert(amount, EUR_TO_INR));
                break;
            case 6:
                System.out.println("GBP to INR: " + convert(amount, GBP_TO_INR));
                break;
            default:
                System.out.println("Invalid choice");
        }


    }
    public static BigDecimal convert(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
   
    
}
