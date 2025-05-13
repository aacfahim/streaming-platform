package com.aacfahim.streaming_platform.utils;

import java.util.concurrent.atomic.AtomicReference;

public class UniqueIDGenerator {

    private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

    public static String getNextId(String prefix) {

        return prefix + (currentTime.accumulateAndGet(System.currentTimeMillis(),
                (prev, next) -> next > prev ? next : prev + 1) % Integer.MAX_VALUE);
    }
}
