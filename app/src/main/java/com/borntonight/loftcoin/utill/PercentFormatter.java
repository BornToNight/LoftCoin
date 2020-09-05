package com.borntonight.loftcoin.utill;

import androidx.annotation.NonNull;

import java.util.Locale;

public class PercentFormatter implements Formatter<Double> {

    @NonNull
    @Override
    public String format(@NonNull Double value) {
        return String.format(Locale.US, "%.2f%%", value); // формат - 2 символа после точки и экранирование процент
    }

}