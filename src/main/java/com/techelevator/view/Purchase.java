package com.techelevator.view;
import java.util.*;
public class Purchase {

    private Menu menu;
    private int bal = 0;
    private static final String MAIN_MENU_FEED_MONEY = "Feed Money";
    private static final String MAIN_MENU_SELECT_PRODUCT= "Select Product";
    private static final String MAIN_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] Purchase_MENU_OPTIONS = {MAIN_MENU_FEED_MONEY, MAIN_MENU_SELECT_PRODUCT, MAIN_MENU_FINISH_TRANSACTION};
    Purchase(Menu menu){this.menu = menu;}

    public void purchaseMenu()
    {
        while(true)
        {
            String choice = (String) menu.getChoiceFromOptions(Purchase_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_FEED_MONEY))
            {
                System.out.println("Current balance "+bal);
                if (){bal +=1;}
                else if(){bal +=2;}
                else if(){bal +=5;}
                else if(){bal +=10;}
            }
            else if(choice.equals(MAIN_MENU_SELECT_PRODUCT))
            {

            }
            else if(choice.equals(MAIN_MENU_FINISH_TRANSACTION))
            {

            }
        }
    }


}
