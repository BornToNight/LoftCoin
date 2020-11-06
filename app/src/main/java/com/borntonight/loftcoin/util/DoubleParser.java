package com.borntonight.loftcoin.util;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DoubleParser {

    @Inject
    public DoubleParser(@NonNull String s) throws NumberFormatException {

    }

    public static Double parseToDouble(@NonNull String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
        return d;
    }
}
