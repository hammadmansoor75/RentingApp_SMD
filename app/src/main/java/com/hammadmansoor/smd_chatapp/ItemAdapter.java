package com.hammadmansoor.smd_chatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemModel> itemList;

    public ItemAdapter(List<ItemModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        ItemModel item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.dateTextView.setText(item.getDate());
        holder.hourlyRateTextView.setText(String.valueOf(item.getHourlyRate()));
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView dateTextView;
        public TextView hourlyRateTextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            dateTextView = itemView.findViewById(R.id.date);
            hourlyRateTextView = itemView.findViewById(R.id.hourlyRate);
        }
    }
}
