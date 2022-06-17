package com.techelevator;
import java.io.*;

import com.techelevator.view.*;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
    PurchaseItems purchaseItems = new PurchaseItems();
    private Menu menu;

    InventoryBuilder inventoryBuilder = new InventoryBuilder();


    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        inventoryBuilder.makeInventory();
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                purchaseItems.displayItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // do purchase
                Purchase purchase = new Purchase(this.menu);
                purchase.purchaseMenu();
            }
            else if (choice.equals(MAIN_MENU_OPTION_EXIT)) { break;}

        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}