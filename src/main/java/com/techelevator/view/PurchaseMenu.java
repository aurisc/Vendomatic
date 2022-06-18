package com.techelevator.view;

import java.text.DecimalFormat;
import java.util.*;
public class PurchaseMenu extends VendingMachineCLI {
    DecimalFormat df = new DecimalFormat("0.00");
    PurchaseItems purchaseItems = new PurchaseItems();
    private Menu menu;

    private static final String MAIN_MENU_FEED_MONEY = "Feed Money";
    private static final String MAIN_MENU_SELECT_PRODUCT= "Select Product";
    private static final String MAIN_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] Purchase_MENU_OPTIONS = {MAIN_MENU_FEED_MONEY, MAIN_MENU_SELECT_PRODUCT, MAIN_MENU_FINISH_TRANSACTION};
    Scanner in = new Scanner(System.in);
    private List<Products> myList;


    public PurchaseMenu(Menu menu, List<Products> myList){
        super(menu);
        this.menu = menu;
        this.myList=myList;
    }


    public void purchaseMenu()
    {
        while(true)
        {
            //users choice from the purchase menu options
            String choice = (String) menu.getChoiceFromOptions(Purchase_MENU_OPTIONS);
            //adds money to the machine
            if (choice.equals(MAIN_MENU_FEED_MONEY))
            {
                //prompts user to add money
                System.out.println("Input whole dollar amount");
                while(true) {
                    try {
                        //accepts users input
                        int money = in.nextInt();

                        //program stops accepting money when 3 is input
                        if(money ==3){break;}
                        //calls feedMoney method to accept user's money input
                        purchaseItems.feedMoney(money);
                        System.out.println("When all money has been added press 3 to return to menu");

                        //catches incorrect input
                    } catch (IllegalArgumentException | InputMismatchException e) {
                        System.out.println("incorrect dollar amount");
                        break;
                    }

                }
            }
            else if(choice.equals(MAIN_MENU_SELECT_PRODUCT))
            {
                //displays list of items for sale
                purchaseItems.displayItems(myList);
                Scanner scanner = new Scanner(System.in);

                //accepts input for item slot selection
                String input = scanner.nextLine();

                //allows user to purchase items from the list provided
                purchaseItems.buy(input,myList );
            }
            else if(choice.equals(MAIN_MENU_FINISH_TRANSACTION))
            {
                //provides the users change
                purchaseItems.changeReturn(purchaseItems.getBal());
                break;
            }
            //informs the user of the amount of money they have in the program
            System.out.println("Current Money Provided: $" + df.format(purchaseItems.getBal()));
        }
    }
}
