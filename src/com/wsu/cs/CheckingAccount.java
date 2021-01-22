package com.wsu.cs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CheckingAccount extends CreateFile{
    public static void entry(Scanner in) throws IOException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        float entry;
        String test;
        Stack<Float> stack = new Stack<>();
        String entryConversion;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File file = fileMaker("checkingAccount.txt");
        BufferedWriter bufferedWriter = writeToFile(file.getAbsolutePath());
        System.out.print("Enter amount: ");

        test = in.next();
        entry = Calculator.testForNumber(test);


        in = readFromFile(file);

        if(in.hasNext()){
            entryConversion = entry + " " + simpleDateFormat.format(timestamp) + " " + (convertToStack(in).peek() + entry);
        }
        else {
            entryConversion = entry + " " + simpleDateFormat.format(timestamp) + " " + entry;
        }
        bufferedWriter.write(entryConversion);
        bufferedWriter.newLine();
        bufferedWriter.close();
        MessagesToUser.goalAchievement();
    }

    public static void printCheckingAccount(Scanner in) throws IOException {
        File file = fileMaker("checkingAccount.txt");
        in = readFromFile(file);
        System.out.println("*********************************************************");
        System.out.println("Transaction               Date                      Total ");
        System.out.println("*********************************************************");
        while(in.hasNext()){
            System.out.printf ("%9S              %5S             %11.2f\n", in.next(), in.next(), in.nextFloat());
        }
    }
    public static Stack<Double> convertToStack(Scanner in) throws IOException {
        Stack<Double> stack = new Stack<>();
        double sum = 0;
        File file = fileMaker("checkingAccount.txt");
        in = readFromFile(file);

        while(in.hasNext()){
            in.next();
            in.next();
            stack.push(in.nextDouble());
        }

        return stack;
    }

    //maybe duplicate remove if necessary YES DELETE ME
    public static Stack<Double> convertToStackNoAddition(Scanner in) throws IOException {
        Stack<Double> stack = new Stack<>();
        File file = CreateFile.fileMaker("checkingAccount.txt");
        in = CreateFile.readFromFile(file);
        double sum;
        while(in.hasNext()){
            in.next();
            in.next();
            stack.push(in.nextDouble());
        }

        sum = in.nextDouble() + stack.peek();
        stack.push(sum);

        return stack;
    }

    public static void adjustAccount(Scanner in, float entry) throws IOException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String entryConversion;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File file = fileMaker("checkingAccount.txt");
        BufferedWriter bufferedWriter = writeToFile(file.getAbsolutePath());
        in = readFromFile(file);


        if(!in.hasNext()){
            entryConversion = (entry * -1) + " " + simpleDateFormat.format(timestamp) + " " + (entry * -1);
        }
        else {
            entryConversion = (entry * -1) + " " + simpleDateFormat.format(timestamp) + " " + (convertToStack(in).peek() - entry);
        }

        bufferedWriter.write(entryConversion);
        bufferedWriter.newLine();
        bufferedWriter.close();
        if(CheckingAccount.convertToStack(in).pop() <= 0) MessagesToUser.overdraft();
    }
}
