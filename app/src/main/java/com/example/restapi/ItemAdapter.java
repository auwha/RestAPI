package com.example.restapi;

import android.content.Context;
import android.content.Intent;
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

    public void addToDataset(Item item) {
        this.dataset.add(item);
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
        String imageLink = dataset.get(position).getImageLink();
        String phone = dataset.get(position).getPhone();
        String email = dataset.get(position).getEmail();
        String address = dataset.get(position).getAddress();
        String birthday = dataset.get(position).getBirthday();
        String gender = dataset.get(position).getGender();

        holder.nameTV.setText(name);
        holder.descriptionTV.setText(email);
        Picasso.get().load(imageLink).into(holder.iconIV);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("image", imageLink);
            intent.putExtra("phone", phone);
            intent.putExtra("email", email);
            intent.putExtra("address", address);
            intent.putExtra("birthday", birthday);
            intent.putExtra("gender", gender);
            context.startActivity(intent);
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
