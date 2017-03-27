package com.example.myFirstProject.service;

import org.omg.SendingContext.RunTime;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

/**
 * Created by serena on 3/27/17.
 */
@Service(value = "processAccessImp")
public class ProcessAccessImp implements ProcessAccess {
    public static final String PID = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

    @Override
    public String printProcess() {
        StringBuilder sb = new StringBuilder();

        try {
            Process process = Runtime.getRuntime().exec("jstack " +PID);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = input.readLine()) != null) {
                sb.append(line + "\n");
            }
            process.getInputStream().close();
        } catch (IOException e) {
            System.err.println ("IOException " + e.getMessage());
        }
        return sb.toString();

    }
}
