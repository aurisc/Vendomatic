package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.rmi.server.ExportException;

import static org.junit.Assert.assertEquals;

public class PurchaseItemsTest {


    @Test
    public void testBuy() {
    }

    @Test
    public void testFeedMoney() {
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
    public void testGetBal() {
    }

    @Test
    public void testSetBal() {
    }

}
