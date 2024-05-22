package com.currencyrates.maps;

import java.util.HashMap;

public class PriceMaps {
    private static HashMap<String, Double> priceMap;

    public static Double getPriceOfCurrency(HashMap<String, Double> map,String currency){
        return priceMap.get(currency);
    }
}
