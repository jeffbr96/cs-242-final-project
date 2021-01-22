package com.wsu.cs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class CheckingAccountTest {
    @Test
    public void ConvertToStackTest() throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        Stack<Double> test;
        Assertions.assertSame( test = CheckingAccount.convertToStack(in), test);
    }
}