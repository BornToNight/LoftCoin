package com.borntonight.loftcoin.util;

import androidx.annotation.NonNull;

// Перенос бизнес лоики по форматировани сюда из Адаптера
public interface Formatter<T> {
    @NonNull
    String format(@NonNull T value);
}