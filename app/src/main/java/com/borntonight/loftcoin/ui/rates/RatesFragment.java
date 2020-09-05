package com.borntonight.loftcoin.ui.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.borntonight.loftcoin.R;
import com.borntonight.loftcoin.data.Coin;
import com.borntonight.loftcoin.data.CurrencyRepo;
import com.borntonight.loftcoin.data.CurrencyRepoImpl;
import com.borntonight.loftcoin.databinding.FragmentRatesBinding;
import com.borntonight.loftcoin.utill.PercentFormatter;
import com.borntonight.loftcoin.utill.PriceFormatter;

import java.util.List;

import timber.log.Timber;

public class RatesFragment extends Fragment {

    private FragmentRatesBinding binding;

    private RatesAdapter adapter;

    private RatesViewModel viewModel;

    private CurrencyRepo currencyRepo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RatesViewModel.class);
        adapter = new RatesAdapter(new PriceFormatter(), new PercentFormatter());
        currencyRepo = new CurrencyRepoImpl(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding = FragmentRatesBinding.bind(view);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.swapAdapter(adapter, false); // Используем старый адаптер
        binding.recycler.setHasFixedSize(true);
        viewModel.coins().observe(getViewLifecycleOwner(), adapter::submitList);
        viewModel.isRefreshing().observe(getViewLifecycleOwner(), binding.refresher::setRefreshing);
        currencyRepo.currency().observe(getViewLifecycleOwner(), (currency) -> {
            Timber.d("%s", currency);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.rates, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.currency_dialog == item.getItemId()) {
            NavHostFragment
                    .findNavController(this)
                    .navigate(R.id.currency_dialog);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        binding.recycler.swapAdapter(null, false); // Удаляем адаптер, т.к. вью уничтожилась
        super.onDestroyView();
    }

}
