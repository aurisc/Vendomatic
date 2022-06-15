package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryBuilder {

    public InventoryBuilder()
    {
        makeInventory();//testing map construction
    }
    HashMap<String, String[]> vending;
    private File inventory = new File("vendingmachine.csv");

    public void makeInventory()
    {
        vending = new HashMap<String, String[]>();
        try(Scanner file = new Scanner(this.inventory))
        {
            while(file.hasNextLine())
            {
                String linetext = file.nextLine();
                String[] vendingEntry = linetext.split("\\|");
                this.vending.put(vendingEntry[0], new String []{vendingEntry[1], vendingEntry[2], vendingEntry[3], String.valueOf(5)});
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String[]> getVending()
    {
        return this.vending;
    }
}
