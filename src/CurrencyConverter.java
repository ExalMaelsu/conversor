import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la moneda base (ej. USD, EUR): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda a la que desea convertir (ej. USD, EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la cantidad a convertir: ");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = CurrencyApi.getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;

            System.out.printf("%.2f %s equivale a %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (IOException e) {
            System.out.println("Error al obtener el tipo de cambio: " + e.getMessage());
        }
    }
}
