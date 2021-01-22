package com.wsu.cs;

import java.io.*;
import java.util.Stack;

public class WishList extends CreateFile{
    File file;
    Stack<String> stack;
    double price;
    String item;

    public WishList(String item, double price) throws IOException {
        this.item = item;
        this.price = price;
        if(price > 10000){
            MessagesToUser.wishOverReached();
        }
        else{
            file = fileMaker("wishLog.txt");
            stack = generateStackFromFile(file);
            writeToFile(item + " " + price, file.getAbsolutePath());
        }

    }

    public WishList() throws IOException {
        file = fileMaker("wishLog.txt");
        stack = generateStackFromFile(file);
    }

    public File getFile(){
        return file;
    }

    public void printWishList(){
        if(stack.empty())return;
        System.out.println(stack.pop());
        printWishList();
    }
}
