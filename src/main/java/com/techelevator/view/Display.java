package com.techelevator.view;
import java.io.*;
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
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
