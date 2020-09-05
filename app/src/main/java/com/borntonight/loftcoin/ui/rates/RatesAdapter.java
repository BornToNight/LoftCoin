package com.borntonight.loftcoin.ui.rates;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.borntonight.loftcoin.BuildConfig;
import com.borntonight.loftcoin.R;
import com.borntonight.loftcoin.data.Coin;
import com.borntonight.loftcoin.databinding.LiRateBinding;
import com.borntonight.loftcoin.utill.OutlineCircle;
import com.borntonight.loftcoin.utill.PercentFormatter;
import com.borntonight.loftcoin.utill.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class RatesAdapter extends ListAdapter<Coin, RatesAdapter.ViewHolder> {

    private final PriceFormatter priceFormatter;

    private final PercentFormatter percentFormatter;

    private LayoutInflater inflater;

    private int colorNegative = Color.RED;

    private int colorPositive = Color.GREEN;

    RatesAdapter(PriceFormatter priceFormatter, PercentFormatter percentFormatter) {
        super(new DiffUtil.ItemCallback<Coin>() {
            @Override
            public boolean areItemsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return oldItem.id() == newItem.id();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
        this.priceFormatter = priceFormatter;
        this.percentFormatter = percentFormatter;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiRateBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Coin coin = getItem(position);
        holder.binding.symbol.setText(coin.symbol());
        holder.binding.price.setText(priceFormatter.format(coin.price()));
        holder.binding.change.setText(percentFormatter.format(coin.change24h()));
        if (coin.change24h() > 0) {
            holder.binding.change.setTextColor(colorPositive);
        } else {
            holder.binding.change.setTextColor(colorNegative);
        }
        Picasso.get()
                .load(BuildConfig.IMG_ENDPOINT + coin.id() + ".png")
                .into(holder.binding.logo);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = recyclerView.getContext();
        inflater = LayoutInflater.from(context);
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textNegative, v, true);
        colorNegative = v.data;
        context.getTheme().resolveAttribute(R.attr.textPositive, v, true);
        colorPositive = v.data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LiRateBinding binding;

        public ViewHolder(@NonNull LiRateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            OutlineCircle.apply(binding.logo);
        }
    }

}