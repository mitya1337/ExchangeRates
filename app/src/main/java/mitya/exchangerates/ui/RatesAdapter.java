package mitya.exchangerates.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitya.exchangerates.R;
import mitya.exchangerates.domain.Rate;

/**
 * Created by Mitya on 04.07.2017.
 */

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ViewHolder> {
    private final List<Rate<String, Double>> items = new ArrayList<>();

    public void addRates(Map<String, Double> rates) {
        int startPosition = getItemCount();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            items.add(new Rate<>(entry.getKey(), entry.getValue()));
        }
        this.notifyItemRangeInserted(startPosition, items.size());
    }

    public void clearList(){
        int size = items.size();
        items.clear();
        this.notifyItemRangeRemoved(0, size);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.currencyView.setText(items.get(position).getL());
        holder.rateView.setText(Double.toString(items.get(position).getR()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.currency)
        TextView currencyView;
        @BindView(R.id.rate)
        TextView rateView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
