package com.example.yearprogress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DotAdapter extends RecyclerView.Adapter<DotAdapter.DotViewHolder> {

    private final int totalDays;
    private final int currentDay;
    private Context context;

    public DotAdapter(Context context, int totalDays, int currentDay) {
        this.totalDays = totalDays;
        this.currentDay = currentDay;
        this.context = context;
    }

    @NonNull
    @Override
    public DotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dot, parent, false);
        return new DotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DotViewHolder holder, int position) {
        int dayNumber = position + 1;

        int completedColor = ContextCompat.getColor(context,R.color.completed);
        int futureColor = ContextCompat.getColor(context,R.color.future);

        if (dayNumber <= currentDay) {
            holder.dot.setBackgroundColor(completedColor);
        } else {
            holder.dot.setBackgroundColor(futureColor);
        }
    }

    @Override
    public int getItemCount() {
        return totalDays;
    }

    static class DotViewHolder extends RecyclerView.ViewHolder {
        View dot;

        public DotViewHolder(@NonNull View itemView) {
            super(itemView);
            dot = itemView;
        }
    }
}