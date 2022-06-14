package com.techelevator.view;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Display {
    HashMap<String, String[]> vending;

   public Display()
    {
        HashMap<String, String[]> vending = new HashMap<String, String[]>();
    }


    private File inventory = new File("vendingmachine.csv");


    public void displayItems()
    {
        try(Scanner file = new Scanner(this.inventory))
        {
            while(file.hasNextLine())
            {
                String linetext = file.nextLine();
                System.out.println(linetext);
                String[] vendingEntry = linetext.split("\\|");
                this.vending.put(vendingEntry[0], new String []{vendingEntry[1], vendingEntry[2], vendingEntry[3]});
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String[]> getVending()
    {
        return vending;
    }
}
