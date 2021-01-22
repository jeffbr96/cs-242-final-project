package com.wsu.cs;

import java.util.*;
import java.io.*;

/**
 Author Jefferson Bourguignon Coutinho
 Date 12/15/2020
 */

public class Driver extends MessagesToUser{
    /**
     * Description: Driver class run intro and menu initializes the scanner used elsewhere
     * @param //String args
     * @return void
     * @see //MessagesToUser, Menu
     * @since           1.0
     */

    public static void main(String args[]) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        intro();
        Menu.menu(in);
    }
}