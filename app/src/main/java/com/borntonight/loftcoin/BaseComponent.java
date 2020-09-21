package com.borntonight.loftcoin;

import android.content.Context;

import com.borntonight.loftcoin.data.CoinsRepo;
import com.borntonight.loftcoin.data.CurrencyRepo;
import com.borntonight.loftcoin.data.WalletsRepo;
import com.borntonight.loftcoin.util.ImageLoader;
import com.borntonight.loftcoin.util.Notifier;
import com.borntonight.loftcoin.util.RxSchedulers;

public interface BaseComponent {
    Context context();

    CoinsRepo coinsRepo();

    CurrencyRepo currencyRepo();

    WalletsRepo walletsRepo();

    ImageLoader imageLoader();

    RxSchedulers schedulers();

    Notifier notifier();
}