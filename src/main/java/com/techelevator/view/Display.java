package com.techelevator.view;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Display {
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
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
