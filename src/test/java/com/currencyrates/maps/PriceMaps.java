package com.currencyrates.maps;

import java.util.HashMap;

public class PriceMaps {
    public static Double getPriceOfCurrency(HashMap<String, Double> map,String currency){
        return map.get(currency);
    }
}
