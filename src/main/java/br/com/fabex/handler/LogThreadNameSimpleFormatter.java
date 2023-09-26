package br.com.fabex.handler;

import java.util.logging.LogRecord;

public class LogThreadNameSimpleFormatter extends java.util.logging.SimpleFormatter {
    public String format(LogRecord record) {
        String formatted = super.format(record);
        return "[%12s] %s".formatted(Thread.currentThread().getName(), formatted);
    }
}