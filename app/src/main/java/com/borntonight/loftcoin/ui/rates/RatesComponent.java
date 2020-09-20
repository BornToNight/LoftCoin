package com.borntonight.loftcoin.ui.rates;

import androidx.lifecycle.ViewModelProvider;

import com.borntonight.loftcoin.BaseComponent;
import com.borntonight.loftcoin.util.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RatesModule.class,
        ViewModelModule.class
}, dependencies = {
        BaseComponent.class
})
abstract class RatesComponent {

    abstract ViewModelProvider.Factory viewModelFactory();

    abstract RatesAdapter ratesAdapter();

}