package com.lmml.graph.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class IdentifierUtil {
    private static AtomicInteger count = new AtomicInteger();

    public static long generateID() {
        long time = System.currentTimeMillis();
        int seq = count.getAndIncrement() % 1000000;
        long id = Long.valueOf(String.valueOf(time) + String.valueOf(seq));
        return id;
    }
}