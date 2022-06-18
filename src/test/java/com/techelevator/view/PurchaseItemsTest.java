package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.rmi.server.ExportException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PurchaseItemsTest {
DecimalFormat df = new DecimalFormat("0.00");

    @Test
    public void testBuy() {
        String correctLower = "a1";
        String incorrect = "Z4";
        String nothing = "";
        List<Products> testList = new ArrayList<>();
        Products productFreeze = new Products(correctLower,"freezie", 1.10, "Gum", "5");
        Products large = new Products(incorrect,"snuffle", 10.15, "Chips", "5");
        Products empty = new Products(nothing,"", 0, "", "");

        testList.add(productFreeze);
        testList.add(large);
        testList.add(empty);
        PurchaseItems purchaseItems = new PurchaseItems();
        purchaseItems.feedMoney(5);

        assertEquals(" Item name: freezie Item Price: 1.10 Money Remaining $"
                + df.format(purchaseItems.getBal()-productFreeze.getPrice()), purchaseItems.buy(correctLower, testList));

        assertEquals("The code entered does not exist\n", purchaseItems.buy(nothing, testList));

        assertEquals(" Item name: snuffle Item Price: 10.15 Money Remaining $"
                + df.format(purchaseItems.getBal()), purchaseItems.buy(incorrect, testList));

        assertEquals("The code entered does not exist\n", purchaseItems.buy("3", testList));
    }

    @Test
    public void testFeedMoney() {
        PurchaseItems purchaseItems = new PurchaseItems();

        int twoDollars = 2;
        int fourDollars = 4;
        int oneHundred = 100;


        assertEquals("Current Money Provided: $2.00"
                ,purchaseItems.feedMoney(twoDollars));
        assertEquals("input correct whole dollar amount",purchaseItems.feedMoney(fourDollars));
        assertEquals("input correct whole dollar amount",purchaseItems.feedMoney(oneHundred));


    }

    @Test
    public void testChangeReturn() {
       // String expected =  "Quarters " + 4 +" Dimes "+ 0 +" Nickels " + 0;
        PurchaseItems purchaseItems = new PurchaseItems();
        int pass = purchaseItems.changeReturn(1);
        assertEquals(4, pass);
        pass = purchaseItems.changeReturn(.05);
        assertEquals(1, pass);
        pass = purchaseItems.changeReturn(.15);
        assertEquals(2, pass);
        pass = purchaseItems.changeReturn(.25);
        assertEquals(1, pass);
        pass = purchaseItems.changeReturn(1.15);
        assertEquals(6, pass);

    }

    @Test
    public void testDisplayItems(){
        String correctLower = "a1";
        String incorrect = "Z4";
        String nothing = "";
        List<Products> testList = new ArrayList<>();
        Products productFreeze = new Products(correctLower,"freezie", 1.10, "Gum", "5");
        Products large = new Products(incorrect,"snuffle", 10.15, "Chips", "5");
        Products empty = new Products(nothing,"", 0, "", "");
        testList.add(productFreeze);
        testList.add(large);
        testList.add(empty);

        PurchaseItems purchaseItems = new PurchaseItems();

        purchaseItems.displayItems(testList);
    }

}
