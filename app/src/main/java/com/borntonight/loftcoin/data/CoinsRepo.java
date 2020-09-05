package com.borntonight.loftcoin.data;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import java.io.IOException;
import java.util.List;

public interface CoinsRepo {

    @NonNull
    @WorkerThread
    List<? extends Coin> listings(@NonNull String currency) throws IOException; // ? - коин абстрактный класс - этим символом указываем, что хотим отдавать любую реализацию
}
