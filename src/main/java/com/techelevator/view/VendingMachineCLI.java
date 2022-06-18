package com.techelevator.view;
import java.io.*;
import java.util.List;

import com.techelevator.view.*;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
    PurchaseItems purchaseItems = new PurchaseItems();
    InventoryBuilder inventoryBuilder = new InventoryBuilder();

    List<Products> myList = inventoryBuilder.getProdList();
    private Menu menu;



    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        inventoryBuilder.makeInventory();
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                purchaseItems.displayItems(myList);
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                PurchaseMenu purchase = new PurchaseMenu(this.menu, myList);
                purchase.purchaseMenu();
            }
                //stops program
            else if (choice.equals(MAIN_MENU_OPTION_EXIT)) { break;}

        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
