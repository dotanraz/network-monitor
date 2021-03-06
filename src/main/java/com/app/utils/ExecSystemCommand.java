package com.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecSystemCommand {

    public static String exec(String command) {
        Process execCommand = null;
        try {
            execCommand = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(execCommand.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}
