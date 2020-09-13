package com.borntonight.loftcoin.util;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import timber.log.Timber;

public class DebugTree extends Timber.DebugTree {

    // Переопределение для показа откуда вызывался Log
    @Override
    protected void log(int priority, String tag, @NotNull String message, Throwable t) {
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        final StackTraceElement ste = stackTrace[5]; // 5 элемент - это наш метод
        super.log(priority, tag, String.format(Locale.US,
                "[%s] %s.%s(%s:%d)", // имя потока, имя класа, имя метода, имя файла, строка
                Thread.currentThread().getName(),
                ste.getClassName(),
                ste.getMethodName(),
                ste.getFileName(),
                ste.getLineNumber(),
                message
                ), t);
    }
}
