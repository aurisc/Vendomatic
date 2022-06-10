package com.techelevator.view;
import java.util.*;
public class Purchase {

    public Object purchaseMenu;
    private Menu menu;
    private int bal = 0;
    private static final String MAIN_MENU_FEED_MONEY = "Feed Money";
    private static final String MAIN_MENU_SELECT_PRODUCT= "Select Product";
    private static final String MAIN_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] Purchase_MENU_OPTIONS = {MAIN_MENU_FEED_MONEY, MAIN_MENU_SELECT_PRODUCT, MAIN_MENU_FINISH_TRANSACTION};
    private Scanner in;

    public Purchase(Menu menu){
        this.in = new Scanner(System.in);
        this.menu = menu;}


    public void purchaseMenu()
    {
        while(true)
        {
            String choice = (String) menu.getChoiceFromOptions(Purchase_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_FEED_MONEY))
            {
                System.out.println("Input whole dollar amount");
                Boolean feed = true;
                while(feed) {
                    try {
                        int money = in.nextInt();
                        feed= feedMoney(money);
                        System.out.println("When all money has been added press 3 to return to menu");

                    } catch (IllegalArgumentException e) {
                        System.out.println("incorrect dollar amount");
                    }

                }


            }
            else if(choice.equals(MAIN_MENU_SELECT_PRODUCT))
            {Display display = new Display();
                display.displayItems();

            }
            else if(choice.equals(MAIN_MENU_FINISH_TRANSACTION))
            {

            }
            System.out.println("Current Money Provided: $" + bal + ".00");
        }
    }

    private Boolean feedMoney(int money){
        if(money == 1){bal +=1;}
        else if(money ==2){bal +=2;}
            else if(money == 5){bal +=5;}
        else if(money ==10){bal +=10;}
        else if(money ==3){return false;}
            else{
            System.out.println("input correct whole dollar amount");
        }

        System.out.println("Current Money Provided: $" + bal + ".00");
        return true;
    }



    public int getBal() {

        return bal;
    }
}
