package mitya.exchangerates.domain;

import java.util.Map;

/**
 * Created by Mitya on 04.07.2017.
 */

public class CurrencyRate {
    public final String base;
    public final String date;
    public final Map<String, Double> rates;

    public CurrencyRate(String base, String date, Map<String, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
