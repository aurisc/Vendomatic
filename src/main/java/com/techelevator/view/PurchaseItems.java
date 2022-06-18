package com.techelevator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import static org.graalvm.compiler.debug.DebugOptions.Log;


public class PurchaseItems {
    DecimalFormat df = new DecimalFormat("0.00");
    LocalDateTime timeNow = LocalDateTime.now();
    DateTimeFormatter formatedTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private String time = timeNow.format(formatedTime);
    private Menu menu;
    private Products products;
    private double bal;
    private String[] itemSpecs;

    private final int QUARTER = 25;
    private final int DIME = 10;
    private final int NICKEL = 5;

    private final int DOLLAR = 1;
    private final int TWO_DOLLAR = 2;
    private final int FIVE_DOLLAR = 5;
    private final int TEN_DOLLAR = 10;


    public String buy(String input, List<Products> productsList) {
        this.itemSpecs = null;
        //informs user input is incorrect
        if(input.equals("")){
            System.out.println("Please input correct slot");
        }
        //iterates over list provided
        for(Products product : productsList) {
            //input is equal to location
            if(product.getName().equals("")){
                //if there is no name for the input continue to the next section
                continue;
            }
            if (input.equalsIgnoreCase(product.getSlot())) {
                //creates String array with slot values
                String name = product.getName();
                double price = product.getPrice();
                String type = product.getType();
                String quantity = product.getQuantity();
                this.itemSpecs = new String[]{name, String.valueOf(price), type, quantity};
            }
        }
        //checks if array was created and prompts user to try a different slot
        if (itemSpecs == null)
        {
            System.out.println("The code entered does not exist\n");
            return "The code entered does not exist\n";
        }
        else
        {
            try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
                if (bal >= Double.parseDouble(itemSpecs[1]) && !itemSpecs[3].equals("OUT OF STOCK"))
                {
                    //decrements the quantity and displays OUT OF STOCK if it reaches 0
                    input = input.toUpperCase();
                    for(Products product : productsList) {
                        if (input.equals(product.getSlot())) {
                            product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) - 1));
                            if(Integer.parseInt(product.getQuantity()) == 0 || product.getQuantity().equals("OUT OF STOCK"))
                            {
                                product.setQuantity("OUT OF STOCK");
                            }
                        }
                    }
                    //checks the type of item and outputs correct sound
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
                    //updates balance and writes the purchase log
                    fw.write(time+" "+itemSpecs[0]+" "+ input.toUpperCase()+" $"+
                            df.format((double) Math.round(getBal()*100)/100)+" -> $"+
                            df.format((double) (Math.round(getBal()*100) - Double.parseDouble(itemSpecs[1])*100)/100)+"\n");
                    bal -= Double.parseDouble(itemSpecs[1]);
                    fw.close();
                }
                //stops user from purchasing item without adequate funds
                else if (bal < Double.parseDouble(itemSpecs[1]))
                {
                    //logs the purchase of the item
                    fw.write(time+" "+itemSpecs[0]+" "+ input.toUpperCase()+" $"
                            +df.format((double) Math.round(getBal()*100)/100)+" -> $"+
                            df.format((double) (Math.round(getBal()*100) - Double.parseDouble(itemSpecs[1])*100)/100)+"\n");
                    System.out.println("Error insert more money");
                }
                //informs user if item they are attempting to purchase is out of stock
                else if (itemSpecs[3].equals("OUT OF STOCK"))
                {
                    System.out.println("The item chosen is currently out of stock.");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //prints name, price, and money the user has remaining
            System.out.println(" Item name: " + itemSpecs[0] + " Item Price: " +
                   df.format(Double.parseDouble(itemSpecs[1])) +" Money Remaining $"+df.format(getBal()));
        }
        return " Item name: " + itemSpecs[0] + " Item Price: " +
                df.format(Double.parseDouble(itemSpecs[1])) +" Money Remaining $"+df.format(getBal());
    }

    public String feedMoney(int money){
        //checks quantities against approved dollar amounts
        if(money == DOLLAR){bal +=DOLLAR;}
        else if(money ==TWO_DOLLAR){bal +=TWO_DOLLAR;}
        else if(money == FIVE_DOLLAR){bal +=FIVE_DOLLAR;}
        else if(money ==TEN_DOLLAR){bal +=TEN_DOLLAR;}
        else{
            //notifies user if incorrect amount was added
            System.out.println("input correct whole dollar amount");
            return "input correct whole dollar amount";
        }

        //logs amount added
        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true )) {
            fw.write(time+" Feed Money: $"+df.format(money)+" -> $"+ df.format(getBal())+"\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //informs user of amount added
        System.out.println("Current Money Provided: $" + df.format(bal) );
        return "Current Money Provided: $" + df.format(bal);
    }

    public int changeReturn(double moneyLeft) {
        //money left format to fit log standards
        double logMoney = moneyLeft;

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        //moneyLeft format to two floating decimal places
        moneyLeft = (double) Math.round(moneyLeft*100);
        while (moneyLeft > 0)
        {
            //dispenses quarters first
            if (moneyLeft - QUARTER >= 0) {
                quarters++;
                moneyLeft -= QUARTER;

            } else if (moneyLeft - DIME>= 0) {
                dimes++;
                moneyLeft -= DIME;
            }
            else if (moneyLeft - NICKEL >= 0) {
                nickels++;
                moneyLeft -= NICKEL;
            }
        }
                //writes time and change given to log file
        try (FileWriter fw = new FileWriter("src/main/java/com/techelevator/log/Log.txt", true)) {
            fw.write(time + " GIVE CHANGE: $" + df.format(logMoney) + " -> Quarters " + quarters + " Dimes " + dimes + " Nickels "
                    + nickels + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //sets balances to zero
        setBal(0);

        //prints amount of quarters, dimes and nickels provided as change
        System.out.println("Quarters " + quarters + " Dimes " + dimes + " Nickels " + nickels);
        return quarters + dimes + nickels;
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
        //iterates through product list showing slot, name, price and quantity
        for(Products products: productsList){
               System.out.println(products.getSlot() + " | "+ products.getName() +" | "
                       + products.getPrice() + " | " + products.getQuantity());
            }
    }
}
