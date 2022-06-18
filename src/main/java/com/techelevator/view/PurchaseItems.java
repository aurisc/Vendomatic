package com.techelevator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

//import static org.graalvm.compiler.debug.DebugOptions.Log;


public class PurchaseItems {
    DecimalFormat df = new DecimalFormat("0.00");
    private Menu menu;
    Products products;
    String input;
    private double bal;
    String[] itemSpecs;


    public void buy(String input, List<Products> productsList) {
        this.input = input;
        this.itemSpecs = null;
        for(Products product : productsList) {
            //input is equal to location
            if (input.equalsIgnoreCase(product.getSlot())) {
                //creates array of Map String[]
                String name = product.getName();
                double price = product.getPrice();
                String type = product.getType();
                String quantity = product.getQuantity();
                this.itemSpecs = new String[]{name, String.valueOf(price), type, quantity};
            }
        }
        if (itemSpecs == null)
        {
            System.out.println("The code entered does not exist\n");
        }
        else
        {
            purchaseProduct(input, productsList);
            System.out.println(" Item name: " + itemSpecs[0] + " Item Price: " + itemSpecs[1] +" Money Remaining $"+df.format(getBal()));
        }
    }
    private void quantityDecrement (String choice, List<Products> productsList) {
        choice = choice.toUpperCase();
        for(Products product : productsList) {
            if (choice.equals(product.getSlot())) {
                product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) - 1));
                if(Integer.parseInt(product.getQuantity()) == 0 || product.getQuantity().equals("OUT OF STOCK"))
                {
                    product.setQuantity("OUT OF STOCK");
                }
            }
        }
    }

    private void purchaseProduct(String choice, List<Products> productsList)
    {
        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
            System.out.println(bal);
            if (bal >= Double.parseDouble(itemSpecs[1]) && !itemSpecs[3].equals("OUT OF STOCK"))
            {
                quantityDecrement(choice, productsList);
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
            else if (itemSpecs[3].equals("OUT OF STOCK"))
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

    public int changeReturn(double moneyLeft) {
        double logMoney = moneyLeft;
        // System.out.println(moneyLeft);
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        moneyLeft = (double) Math.round(moneyLeft*100);
        while (moneyLeft > 0)
        {
            if (moneyLeft - 25 >= 0) {
                quarters++;
                moneyLeft -= 25;
            } else if (moneyLeft - 10>= 0) {
                dimes++;
                moneyLeft -= 10;
            }
            else if (moneyLeft - 5 >= 0) {
                nickels++;
                moneyLeft -= 5;
            }
        }

        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true)) {
            fw.write(LocalDateTime.now() + "GIVE CHANGE: $" + df.format(logMoney) + " -> Quarters " + quarters + " Dimes " + dimes + " Nickels "
                    + nickels + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBal(0);
        System.out.println("Quarters " + quarters + " Dimes " + dimes + " Nickels " + nickels);
        return quarters + dimes + nickels;
        //return quarters;
    }

    public double getBal()
    {
        return bal;
    }

    public void setBal(double bal)
    {
        this.bal = bal;
    }

    public void displayItems(List<Products> productsList)
    {
        for(Products products: productsList){
               System.out.println(products.getSlot() + " | "+ products.getName() +" | "
                       + products.getPrice() + " | " + products.getQuantity());
            }
    }
}
