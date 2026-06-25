import java.util.Scanner;

public class FinancialForecast {

    // Recursive method to calculate future value
    public static double predictFutureValue(double currentValue, double growthRate, int years) {

        if (years == 0) {
            return currentValue;
        }

        return predictFutureValue(
                currentValue * (1 + growthRate / 100),
                growthRate,
                years - 1
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter current value: ");
        double currentValue = sc.nextDouble();

        System.out.print("Enter annual growth rate (%): ");
        double growthRate = sc.nextDouble();

        System.out.print("Enter number of years: ");
        int years = sc.nextInt();

        double futureValue = predictFutureValue(currentValue, growthRate, years);

        System.out.println("\n--- Financial Forecast ---");
        System.out.printf("Predicted Future Value: %.2f%n", futureValue);

        System.out.println("\nOptimization:");
        System.out.println("Memoization or an iterative approach can reduce overhead.");

        sc.close();
    }
}