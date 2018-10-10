package com.example.job;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author pengsong
 */
@Component
public class FirstJob {

    public void task() {
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + ", first job...");
    }
}
