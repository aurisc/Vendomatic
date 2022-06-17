package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.text.DecimalFormat;

//import static org.graalvm.compiler.debug.DebugOptions.Log;


public class PurchaseItems {
        private Menu menu;
    InventoryBuilder inventoryBuilder = new InventoryBuilder();
    Products products;
        String input;
    DecimalFormat df = new DecimalFormat("0.00");
    private double bal;
    Map<String, String[]> vendingItems = new HashMap<>();
    String[] itemSpecs;
    String[] itemDisplay;
    List<Products> productsList = inventoryBuilder.getProdList();



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
    }
    private void  mapInfo(String input) {
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

                this.itemSpecs = new String[]{name, price, type, quantity};
            }
        }
        if (itemSpecs == null)
        {
            System.out.println("The code entered does not exist\n");
        }
        else
        {
            purchaseProduct(input);
            vendingItems.put(input, itemSpecs);
            System.out.println(" Item name: " + itemSpecs[0] + " Item Price: " + itemSpecs[1] + " item quantity: " + itemSpecs[3]);
        }
    }
    private void quantityDecrement (String choice) {
        itemSpecs[3] = String.valueOf(Integer.parseInt(itemSpecs[3]) - 1);
        choice = choice.toUpperCase();
        for(Products product : productsList) {
            if (choice.equals(product.getSlot()))
                  product.setQuantity(product.getQuantity() - 1);

        }
    }

    private void purchaseProduct(String choice)
    {
        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
            System.out.println(bal);
            if (bal >= Double.parseDouble(itemSpecs[1]) && Integer.parseInt(itemSpecs[3]) > 0)
            {
                quantityDecrement(choice);
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
                fw.write(LocalDateTime.now()+" "+itemSpecs[0]+" "+ choice.toUpperCase()+" $"+getBal()+" -> $"+
                        (getBal() - Double.parseDouble(itemSpecs[1]))+"\n");
                bal -= Double.parseDouble(itemSpecs[1]);
                fw.close();
            }
            else if (bal < Double.parseDouble(itemSpecs[1]))
            {
                fw.write(LocalDateTime.now()+" "+itemSpecs[0]+" "+ choice.toUpperCase()+" $"+df.format(getBal())+" -> $"+
                        df.format (getBal() - Double.parseDouble(itemSpecs[1]))+"\n");
                System.out.println("Error insert more money");
            }
            else if (Integer.parseInt(itemSpecs[3]) == 0)
            {
                System.out.println("The item chosen is currently out of stock.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
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
        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
            fw.write(LocalDateTime.now()+" Feed Money: $"+df.format(money)+" -> $"+ df.format(getBal())+"\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Current Money Provided: $" + df.format(bal) );
    }

    public void changeReturn(double moneyLeft)
    {
        moneyLeft = (moneyLeft*100)/100;
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
            else if (moneyLeft > 0 && moneyLeft < .05) {break;}
        }
        setBal(0);
        System.out.println("Quarters " + quarters +" Dimes "+ dimes +" Nickels " + nickels);

        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
            fw.write(LocalDateTime.now() + "GIVE CHANGE: $" + moneyLeft +" -> $"+ getBal()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getBal()
    {
        return bal;
    }

    public void setBal(double bal)
    {
        this.bal = bal;
    }

    public void displayItems()
    {
        for(Products products: productsList){
               System.out.println(products.getSlot() + " | "+ products.getName() +" | "
                       + products.getPrice() + " | " + products.getQuantity());
            }
    }
}
