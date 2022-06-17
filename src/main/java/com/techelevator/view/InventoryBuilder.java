package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InventoryBuilder {

    Products products;
    public InventoryBuilder()
    {
        makeInventory();//testing map construction
    }
    HashMap<String, String[]> vending;
    List<String[]> productList = new ArrayList<>();
    List<Products> prodList = new ArrayList<>();
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
                String[] entry = {vendingEntry[0], vendingEntry[1], vendingEntry[2], "5"};
                this.productList.add(entry);
                products = new Products(vendingEntry[0], vendingEntry[1],
                        Double.parseDouble(vendingEntry[2]), vendingEntry[3], 5);
                this.prodList.add(products);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String[]> getVending()
    {
        return this.vending;
    }

    public List<String[]> getProductList() {return this.productList;}
    public List<Products> getProdList() {return prodList;}
}
