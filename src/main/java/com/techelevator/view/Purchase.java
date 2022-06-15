package com.techelevator.view;
import com.techelevator.VendingMachineCLI;

import java.text.DecimalFormat;
import java.util.*;
public class Purchase extends VendingMachineCLI {
 DecimalFormat df = new DecimalFormat("0.00");
    private Menu menu;
    Balance balance = new Balance();

    private static final String MAIN_MENU_FEED_MONEY = "Feed Money";
    private static final String MAIN_MENU_SELECT_PRODUCT= "Select Product";
    private static final String MAIN_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] Purchase_MENU_OPTIONS = {MAIN_MENU_FEED_MONEY, MAIN_MENU_SELECT_PRODUCT, MAIN_MENU_FINISH_TRANSACTION};
     Scanner in = new Scanner(System.in);


    public Purchase(Menu menu){
        super(menu);
        this.menu = menu;}


    public void purchaseMenu()
    {
        while(true)
        {
            String choice = (String) menu.getChoiceFromOptions(Purchase_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_FEED_MONEY))
            {
                System.out.println("Input whole dollar amount");
                while(true) {
                    try {
                        int money = in.nextInt();
                        if(money ==3){break;}
                        balance.feedMoney(money);
                        System.out.println("When all money has been added press 3 to return to menu");

                    } catch (IllegalArgumentException e) {
                        System.out.println("incorrect dollar amount");
                    }

                }
            }
            else if(choice.equals(MAIN_MENU_SELECT_PRODUCT))
            {
                PurchaseItems purchaseItems = new PurchaseItems();
                Display display = new Display();
                display.displayItems();
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                purchaseItems.buy(input);


            }
            else if(choice.equals(MAIN_MENU_FINISH_TRANSACTION))
            {
                balance.changeReturn(balance.getBal());
                break;
            }
            System.out.println("Current Money Provided: $" + df.format(balance.getBal()));
        }
    }
}
