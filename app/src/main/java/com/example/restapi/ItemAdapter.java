package com.example.restapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> dataset = new ArrayList<>();
    private final Context context;

    public ItemAdapter(List<Item> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setDataset(List<Item> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String name = dataset.get(position).getName();
        String description = dataset.get(position).getDescription();
        String imageLink = dataset.get(position).getImageLink();

        holder.nameTV.setText(name);
        holder.descriptionTV.setText(description);
        Picasso.get().load(imageLink).into(holder.iconIV);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView descriptionTV;
        ImageView iconIV;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.text_view_name);
            descriptionTV = itemView.findViewById(R.id.text_view_description);
            iconIV = itemView.findViewById(R.id.image_view_icon);
        }
    }
}
