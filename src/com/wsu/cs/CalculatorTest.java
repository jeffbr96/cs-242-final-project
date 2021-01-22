package com.wsu.cs;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest extends Calculator{
    @Test
    public void TotalExpensesTest() throws IOException, InterruptedException {
        double test;
        String expected;
        test = totalExpenses();
        expected = "Your total is " + (test + 1);
        Expenses expenses = new Expenses("test", 1);
        assertEquals(test + 1, totalExpenses());
    }

    @Test
    public void IndividualSumTest() throws IOException {
        File file = CreateFile.fileMaker("uniqueValues.txt");
        Scanner in = CreateFile.readFromFile(file);
        HashMap<String, Double> test = CreateFile.generateHashMapFromFile(file);
        String testKey;
        Double testValue;
        while(in.hasNext()){
            testKey = in.next();
            testValue = in.nextDouble();
            assertEquals(test.get(testKey), testValue);
            assertTrue(test.containsKey(testKey));

        }

    }

}
