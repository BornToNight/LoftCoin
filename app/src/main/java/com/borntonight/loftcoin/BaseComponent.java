package com.borntonight.loftcoin;

import android.content.Context;

import com.borntonight.loftcoin.data.CoinsRepo;
import com.borntonight.loftcoin.data.CurrencyRepo;
import com.borntonight.loftcoin.util.ImageLoader;

public interface BaseComponent {
    Context context();
    CoinsRepo coinsRepo();
    CurrencyRepo currencyRepo();
    ImageLoader imageLoader();
}