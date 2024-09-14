package com.example.newclendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekViewHolder> {
    private List<Date> weekDays;
    private Date selectedDate;  // Track the currently selected date
    private OnDateClickListener listener;

    // Listener interface for item clicks
    public interface OnDateClickListener {
        void onDateClick(Date date);
    }

    public WeekAdapter(List<Date> weekDays, OnDateClickListener listener) {
        this.weekDays = weekDays;
        this.listener = listener;
        this.selectedDate = null;  // No date selected initially
    }

    @NonNull
    @Override
    public WeekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekViewHolder holder, int position) {
        Date date = weekDays.get(position);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());

        holder.tvDayName.setText(dayFormat.format(date));
        holder.tvDayDate.setText(dateFormat.format(date));

        // Set indicator background for the selected date
        if (selectedDate != null && selectedDate.equals(date)) {
            holder.indicatorLayout.setBackgroundResource(R.drawable.calendar_indicator);
            holder.tvDayName.setTextColor(Color.WHITE);
            holder.tvDayDate.setTextColor(Color.WHITE);
        } else {
            holder.indicatorLayout.setBackgroundResource(android.R.color.transparent);
            holder.tvDayName.setTextColor(Color.BLACK);
            holder.tvDayDate.setTextColor(Color.DKGRAY);
        }

        // Handle click events
        holder.itemView.setOnClickListener(v -> {
            selectedDate = date;
            notifyDataSetChanged();
            if (listener != null) {
                listener.onDateClick(date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public static class WeekViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayName, tvDayDate;
        LinearLayout indicatorLayout;

        public WeekViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayName = itemView.findViewById(R.id.tvDayName);
            tvDayDate = itemView.findViewById(R.id.tvDayDate);
            indicatorLayout = itemView.findViewById(R.id.indicatorLayout);
        }
    }

    // Method to update data
    public void updateData(List<Date> newWeekDays) {
        this.weekDays = newWeekDays;
        notifyDataSetChanged();
    }
}




