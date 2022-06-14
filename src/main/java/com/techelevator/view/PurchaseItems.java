package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class PurchaseItems {
    Display display = new Display();
    private double currentBal;
    private final int MAX_INVENTORY = 5;


    Map<String, Double> itemMap = new HashMap<>();

    public PurchaseItems()
    {

    }

    public void buy()
    {
        for (Map.Entry<String, String[]> test: display.getVending().entrySet()) {
            System.out.println(test.getValue());
        }
    }

}
