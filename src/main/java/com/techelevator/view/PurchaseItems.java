package com.techelevator.view;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;


public class PurchaseItems {
    Display display = new Display();
    private Menu menu;
    InventoryBuilder inventoryBuilder = new InventoryBuilder();
    String input;

    DecimalFormat df = new DecimalFormat("0.00");

    private double bal;

    Map<String, Double> itemMap = new HashMap<>();
    Map<String, String[]> vendingItems = new HashMap<>();
    String[] itemSpecs;

    public PurchaseItems() {

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
        System.out.println(bal);
        if (bal >= Double.parseDouble(itemSpecs[1]))
        {
            bal -= Double.parseDouble(itemSpecs[1]);
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
        else if (bal < Double.parseDouble(itemSpecs[1]))
        {
            System.out.println("Error insert more money");
        }
    }
    public void feedMoney(int money){
        if(money == 1){bal +=1;}
        else if(money ==2){bal +=2;}
        else if(money == 5){bal +=5;}
        else if(money ==10){bal +=10;}
        else{
            System.out.println("input correct whole dollar amount");
        }

        System.out.println("Current Money Provided: $" + df.format(bal) );
    }

    public void changeReturn(double moneyLeft)
    {
        int quarters =0;
        int dimes =0;
        int nickels=0;
        while (moneyLeft > 0)
        {
            if (moneyLeft -.25 >= 0)
            {
                quarters++;
                moneyLeft -= .25;
            }
            else if (moneyLeft -.10 >= 0)
            {
                dimes++;
                moneyLeft -=.10;
            }
            else if (moneyLeft - .05 >= 0)
            {
                nickels++;
                moneyLeft -=.05;
            }
        }
        setBal(0);
        System.out.println("Quarters" + quarters +" Dimes "+ dimes +" Nickels" + nickels);
    }

    public double getBal()
    {
        return bal;
    }

    public void setBal(double bal)
    {
        this.bal = bal;
    }
}
