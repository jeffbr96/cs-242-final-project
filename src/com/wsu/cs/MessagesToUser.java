package com.wsu.cs;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 Author Jefferson Bourguignon Coutinho
 Date 12/15/2020
 */
public class MessagesToUser {

    /**
     * Description: prints message
     * @param // void
     * @return  void
     * @see //
     * @since           1.0
     */
    public static void wishOverReached() throws IOException {
        System.out.println("This is a long term commitment consider professional help\nA loan may be considered but be careful");
    }

    public static void overdraft(){
        System.out.println("You have overdraft!\nTime to control your expenses!");
    }

    /**
     * Description: test financial ability to purchase something on the wish list
     * @param // float purchase
     * @return void
     * @see //    CheckingAccount, Calculator
     * @since           1.0
     */
    public static void goalAchievement(float purchase) throws IOException {

        double percentage, leastExpensiveWish, currentChecking;
        Scanner in = new Scanner(System.in);
        Stack<Double> stack;
        Stack<String> wishStack;
        File file = CreateFile.fileMaker("wishLog.txt");
        wishStack = WishList.generateStackFromFile(file);
        stack = CheckingAccount.convertToStack(in);
        char answer;
        leastExpensiveWish = Calculator.sortWishes();

        if (leastExpensiveWish == 0.0 || stack.empty()) return;

        currentChecking = stack.pop() - purchase;

        if(!stack.empty() || !wishStack.empty()){
            percentage = ((currentChecking / leastExpensiveWish) * 100);

            if(percentage > 100){
                Stack<String> correctOrder;
                correctOrder = CreateFile.generateStackFromFile(file);
                Collections.reverse(correctOrder);
                Calculator.adjustWishes(in);

                System.out.printf("Congratulations you have achieved your goal!\n%s will be deleted from your wish list\n", correctOrder.peek());
                System.out.printf("This is how would reflect on your checking    : %8.2f\n", (CheckingAccount.convertToStack(in).pop() - leastExpensiveWish));
                System.out.print ("Type Y(yes) to adjust your checking account   :        ");
                answer = in.next().toLowerCase().charAt(0);
                if(answer == 'y'){
                    CheckingAccount.adjustAccount(in, (float)leastExpensiveWish);
                }

                return;
            }
            else if(percentage > 0 && percentage <30){
                System.out.println("Try working on your savings!");
            }
            else if(percentage < 0){
                System.out.printf("Consider re-examining your income!\nThis will drag you %.1f percent behind a positive balance!\n", percentage);
                return;
            }

            System.out.printf("You have %.2f percent of the funds to achieve your goal!\n", percentage);

            return;
        }
    }

    /**
     * Description: tests either how far to reach a goal after every purchase or give message for goal achieved
     * @param // void
     * @return  void
     * @see //    CheckingAccount, Calculator,
     * @since           1.0
     */
    public static void goalAchievement() throws IOException {

        double percentage, leastExpensiveWish;
        Scanner in = new Scanner(System.in);
        Stack<Double> stack;
        stack = CheckingAccount.convertToStack(in);
        char answer;
        leastExpensiveWish = Calculator.sortWishes();
        if (leastExpensiveWish == 0.0) return;

        if(!stack.empty()){
            percentage = ((stack.pop() / leastExpensiveWish) * 100);

            if(percentage > 100){
                File file = CreateFile.fileMaker("wishLog.txt");
                Stack<String> correctOrder;
                correctOrder = CreateFile.generateStackFromFile(file);
                Collections.reverse(correctOrder);

                System.out.printf("Congratulations you have achieved your goal!\n%s will be deleted from your wish list\n", correctOrder.peek());
                Calculator.adjustWishes(in);
                System.out.printf("This is how would reflect on your checking    : %8.2f\n", (CheckingAccount.convertToStack(in).pop() - leastExpensiveWish));
                System.out.print ("Type Y(yes) to adjust your checking account   :        ");

                answer = in.next().toLowerCase().charAt(0);
                if(answer == 'y'){
                    CheckingAccount.adjustAccount(in, (float)leastExpensiveWish);
                }

                return;
            }
            else if(percentage < 20 && percentage > 0){
                System.out.println("Try working on your savings!");
            }
            else if(percentage < 0){
                System.out.printf("Consider re-examining your income!\nYou are %.1f percent behind a positive balance!\n", percentage);
                return;
            }

            System.out.printf("You have %.2f percent of the funds to achieve your goal!\n", percentage);

            return;
        }

        System.out.println("Consider a form of income!");
    }

    /**
     * Description: fun animation to begin main menu
     * @param // void
     * @return  void
     * @see //
     * @since           1.0
     */
    public static void intro() throws InterruptedException {

        String array[][] = {{"                ","","          *","",""},
                            {"                     *","","    *","","    *"},
                            {"                    *","   *"," *"," *","   *"},
                            {"                      *"," *"," *"," *"," *"},
                            {"                          *","","","",""},
        };

        System.out.println();

        for (int i = 0; i < array.length; i++){
            Thread.sleep(43);
            for(int j = 0; j < array.length; j++){
                System.out.print(array[i][j]);
                Thread.sleep(77);
            }
            System.out.println();
        }

        System.out.println("WELCOME TO YOUR PERSONAL FINANCIAL ORGANIZER AND ADVISER");
    }

}
