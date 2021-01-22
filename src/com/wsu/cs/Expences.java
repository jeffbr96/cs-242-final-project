package com.wsu.cs;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

/**
 Author Jefferson Bourguignon Coutinho
 Date 12/15/2020
 */
class Expenses extends CreateFile{
    private String item, financeLogAbsolutePath;
    private float price;
    private File file;
    Scanner in;
    Stack<String> stack;

    /**
     * Description: constructor it also writes to file
     * @param // String item, float price
     * @return n/a
     * @see //    CreateFile, CheckingAccount
     * @since           1.0
     */
    public Expenses(String item, float price) throws IOException {
        this.item = item;
        this.price = price;
        CheckingAccount.adjustAccount(in, price);
        file = fileMaker("financeLog.txt");
        stack = generateStackFromFile(file);
        writeToFile(item + " " + price, file.getAbsolutePath());
    }

    /**
     * Description: overloaded constructor tha takes no param
     * @param // void
     * @return void
     * @see //    CreateFile
     * @since           1.0
     */
    public Expenses() throws IOException {
        file = fileMaker("financeLog.txt");
        stack = generateStackFromFile(file);
    }

    /**
     * Description: returns a File
     * @param // void
     * @return File file
     * @see //    n\a
     * @since           1.0
     */
    public File getFile(){
        return file;
    }

    /**
     * Description: print all expenses
     * @param // void
     * @return void
     * @see //    n/a
     * @since           1.0
     */
    public void printExpenses(){
        System.out.println(stack.pop());
        if(stack.empty())return;
        printExpenses();
    }
}
