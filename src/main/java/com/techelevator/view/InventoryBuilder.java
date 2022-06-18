package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InventoryBuilder {

    Products products;
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
                        Double.parseDouble(vendingEntry[2]), vendingEntry[3], String.valueOf(5));
                this.prodList.add(products);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Products> getProdList() {return prodList;}
}
