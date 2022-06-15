package com.techelevator.view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PurchaseItems {

    Display display = new Display();
    InventoryBuilder inventoryBuilder = new InventoryBuilder();
    private double currentBal;

    String input;
    Balance balance = new Balance();

    Map<String, Double> itemMap = new HashMap<>();
    Map<String, String[]> vendingItems = new HashMap<>();
    String[] itemSpecs;

    public PurchaseItems() {
        currentBal = balance.getBal();
        setVendingItemsMap();
    }
    public int maxInventory() {
        return 5;
    }
   /* private void itemPurchased() {
        this.maxInventory = maxInventory - 1;
    }*/
    private void setVendingItemsMap() {
        this.vendingItems = inventoryBuilder.getVending();
    }
    public void buy(String input) {
        this.input = input;
        mapInfo(input);
        quantityDecrement();
        vendingItems.replace(input, itemSpecs);
        System.out.println(" Item name: " + itemSpecs[0] + " Item Price: " + itemSpecs[1] + " item quantity: " + itemSpecs[3]);
    }
    private String[] mapInfo(String input) {
        for (Map.Entry<String, String[]> items : vendingItems.entrySet()) {
            //input is equal to location
            if (input.equalsIgnoreCase(items.getKey())) {
                //creates array of Map String[]
                String[] values = items.getValue();

                String name = values[0];
                String price = values[1];
                String type = values[2];
                String quantity = values[3];

                return this.itemSpecs = new String[]{name, price, type, quantity};
            }
        }
        return this.itemSpecs;
    }
    private void quantityDecrement () {
            itemSpecs[3] = String.valueOf(Integer.parseInt(itemSpecs[3]) - 1);
    }
}
