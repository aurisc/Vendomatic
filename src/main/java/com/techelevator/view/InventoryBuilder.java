package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
Takes in a csv file, splits the information using | as a delimiter,
creates a custom list of product information separated by vending slot
 */

public class InventoryBuilder {

    Products products;
    private final int MAX_QUANTITY = 5;
    List<Products> prodList = new ArrayList<>();
    private File inventory = new File("vendingmachine.csv");

    public void makeInventory()
    {
        try(Scanner file = new Scanner(this.inventory))
        {
            while(file.hasNextLine())
            {
                String linetext = file.nextLine();
                String[] vendingEntry = linetext.split("\\|");

                products = new Products(vendingEntry[0], vendingEntry[1],
                        Double.parseDouble(vendingEntry[2]), vendingEntry[3], String.valueOf(MAX_QUANTITY));
                this.prodList.add(products);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Products> getProdList() {return prodList;}
}
