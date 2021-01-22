package com.wsu.cs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class CreateFileTest {
    @Test
    public void FileMakerTest() throws IOException {
        String test;
        File test1;
        test = System.getProperty("user.dir") + "\\test.notAFile";
        test1 = CreateFile.fileMaker("test.notAFile");
        assertEquals(test, test1.getAbsolutePath());
    }
}
