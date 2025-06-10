package com.banking.utils;

import java.time.Duration;

public class WaitHelper {
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}