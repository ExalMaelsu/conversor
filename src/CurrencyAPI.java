import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyAPI {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/0a724bbcb7c6c3518740857b/latest/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        URL url = new URL(API_URL + baseCurrency);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parse JSON response to get exchange rate
            String jsonResponse = response.toString();
            String targetRateKey = "\""+targetCurrency+"\"";
            int start = jsonResponse.indexOf(targetRateKey);
            int end = jsonResponse.indexOf(",", start);
            String targetRateString = jsonResponse.substring(start, end).replaceAll("[\\D]", "");
            double targetRate = Double.valueOf(targetRateString) / 10000;

            return targetRate;
        } else {
            throw new IOException("Error al conectar con la API: " + responseCode);
        }
    }
}