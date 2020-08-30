package com.borntonight.loftcoin.ui.wallets;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.borntonight.loftcoin.databinding.LiWalletBinding;

class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private int walletsCount = 10; // заглушка

    @Override
    public int getItemCount() {
        return walletsCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiWalletBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull LiWalletBinding binding) {
            super(binding.getRoot());
        }

    }

}
