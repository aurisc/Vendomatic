package com.techelevator.view;

import java.text.DecimalFormat;

public class Balance {
DecimalFormat df = new DecimalFormat("0.00");
    private double bal = 0;
    public void feedMoney(int money){
        if(money == 1){bal +=1;}
        else if(money ==2){bal +=2;}
        else if(money == 5){bal +=5;}
        else if(money ==10){bal +=10;}
        else{
            System.out.println("input correct whole dollar amount");
        }

        System.out.println("Current Money Provided: $" + df.format(bal) );
    }

    public void changeReturn(double moneyLeft)
    {
        int quarters =0;
        int dimes =0;
        int nickels=0;
        while (moneyLeft > 0)
        {
            if (moneyLeft -.25 >= 0)
            {
                quarters++;
                moneyLeft -= .25;
            }
            else if (moneyLeft -.10 >= 0)
            {
                dimes++;
                moneyLeft -=.10;
            }
            else if (moneyLeft - .05 >= 0)
            {
                nickels++;
                moneyLeft -=.05;
            }
        }
        System.out.println("Quarters" + quarters +" Dimes "+ dimes +" Nickels" + nickels);
    }


    public double getBal()
    {
        return bal;
    }

    public double setBal()
    {
        return bal;
    }
}
