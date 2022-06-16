package com.techelevator.view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PurchaseItems {
    Display display = new Display();
    private Menu menu;
    InventoryBuilder inventoryBuilder = new InventoryBuilder();
    String input;
    Balance balance = new Balance();
    private double currentBal;

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

    private void setVendingItemsMap() {
        this.vendingItems = inventoryBuilder.getVending();
    }
    public void buy(String input) {
        this.input = input;
        mapInfo(input);
        if (itemSpecs == null)
        {
            System.out.println("The code entered does not exist\n");
            //Scanner scanner = new Scanner(System.in);
           // String errorInput = scanner.nextLine();
            //mapInfo(errorInput);
            Purchase purchase = new Purchase(menu);
            purchase.purchaseMenu();
        }

        purchaseProduct(input);
        vendingItems.replace(input, itemSpecs);
        System.out.println(" Item name: " + itemSpecs[0] + " Item Price: " + itemSpecs[1] + " item quantity: " + itemSpecs[3]);
    }
    private String[] mapInfo(String input) {
        this.itemSpecs = null;
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

    private void purchaseProduct(String choice)
    {
        currentBal = balance.getBal();
        System.out.println(currentBal);
        if (currentBal >= Double.parseDouble(itemSpecs[1]))
        {
            currentBal -= Double.parseDouble(itemSpecs[1]);
            balance.setBal(currentBal);
            quantityDecrement();
            if(itemSpecs[2].equals("Chip"))
            {
                System.out.println("Crunch Crunch, Yum!");
            }
            if(itemSpecs[2].equals("Candy"))
            {
                System.out.println("Munch Munch, Yum!");
            }
            if(itemSpecs[2].equals("Drink"))
            {
                System.out.println("Glug Glug, Yum!");
            }
            if(itemSpecs[2].equals("Gum"))
            {
                System.out.println("Chew Chew, Yum!");
            }
        }
        else if (currentBal < Double.parseDouble(itemSpecs[1]))
        {
            System.out.println("Error insert more money");
        }
    }


     /* private void itemPurchased() {
        this.maxInventory = maxInventory - 1;
    }*/
}
