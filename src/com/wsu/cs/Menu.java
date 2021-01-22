package com.wsu.cs;

import java.io.IOException;
import java.util.Scanner;

public class Menu extends Calculator{

    public static void subMenu1(int menu) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        String item, test;
        float price = 0;
        Expenses expenses;
        WishList wishes;

        switch(menu){
            case 1: {
                System.out.print("Enter item name: ");
                item = in.next();

                System.out.print("Enter price: ");
                test = in.next();

                price = testForNumber(test);
                MessagesToUser.goalAchievement(price);

                expenses = new Expenses(item, price);

                individualSum(item, price);

                menu(in);
                break;
            }
            case 2: {
                System.out.print("Enter item name: ");
                item = in.next();

                System.out.print("Enter price: ");
                test = in.next();

                price = Calculator.testForNumber(test);

                wishes = new WishList(item, price);
                MessagesToUser.goalAchievement(price);

                if(price == 0){
                    System.out.println("Value cannot be zero");
                    subMenu1(2);
                }

                menu(in);
                break;
            }
        }
    }

    public static void subMenu3(int menu, Scanner in) throws IOException, InterruptedException {
        switch (menu){
            case 1:{
                Expenses expenses = new Expenses();
                expenses.printExpenses();
                menu(in);
                break;
            }
            case 3:{
                WishList wishes = new WishList();
                wishes.printWishList();
                menu(in);
                break;
            }
            case 2:{
                char choice;
                System.out.println("Your total is " + Calculator.totalExpenses());
                System.out.print("Would you like the individual itemized view? y(yes) n(no): ");
                choice = in.next().toLowerCase().charAt(0);
                if(choice == 'y')Calculator.printIndividualSum();
                menu(in);
                break;
            }
        }
    }

    public static void subMenu2(int menu, Scanner in) throws IOException, InterruptedException {
        switch (menu){
            case 1:{
                CheckingAccount.entry(in);
                menu(in);
                break;
            }
            case 3:{
                CheckingAccount.printCheckingAccount(in);
                menu(in);
                break;
            }
            case 2:{
                System.out.println(CheckingAccount.convertToStack(in).pop());
                menu(in);
                break;
            }

        }
    }

    public static void subMenu4(int menu, Scanner in) throws IOException, InterruptedException {
        switch (menu){
            case 1:{
                System.out.println("**********************ENTRY MENU*************************");
                System.out.print  ("Enter 1 for expenses                       2 for wishes:  ");
                menu = in.nextInt();
                subMenu1(menu);
                break;
            }
            case 2:{
                System.out.println("**********************VIEW MENU**************************");
                System.out.print("Enter 1 for all expenses     2 for total   3  for wishes: ");
                menu = in.nextInt();
                subMenu3(menu, in);
                break;
            }
        }
    }

    public static void menu(Scanner in) throws IOException, InterruptedException {
        int menu;
        System.out.println("**********************MAIN MENU**************************");
        System.out.print  ("Enter 1 for payday    2 for expenses        3 to quit:  ");
        menu = in.nextInt();
        switch (menu){
            case 2:{
                System.out.println("********************EXPENSES MENU************************");
                System.out.print("Enter 1 for entries                          2 for view:  ");
                menu = in.nextInt();
                subMenu4(menu, in);
                break;
            }
            case 1:{
                System.out.println("*********************PAYDAY MENU*************************");
                System.out.print  ("Enter 1 for deposit   2 for balance   3 for statement:  ");
                menu = in.nextInt();
                subMenu2(menu, in);
                break;
            }
            default:{
                System.out.println("**********************************************************");
                System.out.println("********************have a nice day***********************");
                System.out.println("**********************************************************");
                break;
            }
        }
    }
}