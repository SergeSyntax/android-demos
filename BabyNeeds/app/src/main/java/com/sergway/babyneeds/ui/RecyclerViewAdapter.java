package com.sergway.babyneeds.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sergway.babyneeds.R;
import com.sergway.babyneeds.data.DatabaseHandler;
import com.sergway.babyneeds.model.Item;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Item> itemList;

    public RecyclerViewAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(String.format("Item: %s", item.getItemName()));
        holder.itemColor.setText(String.format("Color: %s", item.getItemColor()));
        holder.quantity.setText(String.format("Qty: %s", String.valueOf(item.getItemQuantity())));
        holder.size.setText(String.format("Size: %s", String.valueOf(item.getItemSize())));
        holder.dateAdded.setText(String.format("Added on: %s", item.getDateItemAdded()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName;
        public TextView itemColor;
        public TextView quantity;
        public TextView size;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;
        public int id;
        private AlertDialog.Builder builder;
        private AlertDialog alertDialog;
        private LayoutInflater layoutInflater;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;


            itemName = itemView.findViewById(R.id.list_item_name);
            itemColor = itemView.findViewById(R.id.list_item_color);
            quantity = itemView.findViewById(R.id.list_item_quantity);
            size = itemView.findViewById(R.id.list_item_size);
            dateAdded = itemView.findViewById(R.id.list_item_date);

            editButton = itemView.findViewById(R.id.list_edit_button);
            deleteButton = itemView.findViewById(R.id.list_delete_button);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Item item = itemList.get(position);
            switch (v.getId()) {
                case R.id.list_edit_button:
                    editItem(item);
                    break;
                case R.id.list_delete_button:
                    deleteItem(item.getId());
                    break;
            }
        }

        private void editItem(Item item) {

            builder = new AlertDialog.Builder(context);
            layoutInflater = layoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.popup, null);

            Button saveButton;
            EditText babyItem;
            EditText itemQuantity;
            EditText itemColor;
            EditText itemSize;
            TextView title;

            babyItem = view.findViewById(R.id.babyItem);
            itemQuantity = view.findViewById(R.id.itemQuantity);
            itemColor = view.findViewById(R.id.itemColor);
            itemSize = view.findViewById(R.id.itemSize);
            saveButton = view.findViewById(R.id.saveButton);
            saveButton.setText(R.string.update);
            title = view.findViewById(R.id.title);

            title.setText(R.string.edit_item);


            babyItem.setText(item.getItemName());
            itemQuantity.setText(String.valueOf(item.getItemQuantity()));
            itemColor.setText(item.getItemColor());
            itemSize.setText(String.valueOf(item.getItemSize()));

            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

            saveButton.setOnClickListener(v -> {
                //update our item
                DatabaseHandler databaseHandler = new DatabaseHandler(context);

                //update items
                item.setItemName(babyItem.getText().toString());
                item.setItemColor(itemColor.getText().toString());
                item.setItemQuantity(Integer.parseInt(itemQuantity.getText().toString()));
                item.setItemSize(Integer.parseInt(itemSize.getText().toString()));

                if (!babyItem.getText().toString().isEmpty()
                        && !itemColor.getText().toString().isEmpty()
                        && !itemQuantity.getText().toString().isEmpty()
                        && !itemSize.getText().toString().isEmpty()) {

                    databaseHandler.updateItem(item);
                    notifyItemChanged(getAdapterPosition(),item); //important!


                }else {
                    Snackbar.make(view, "Fields Empty",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }

                alertDialog.dismiss();
          });
        }

        private void deleteItem(int id) {
            builder = new AlertDialog.Builder(context);
            layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.comfirmation_pop, null);

            Button noButton = view.findViewById(R.id.conf_no_button);
            Button yesButton = view.findViewById(R.id.conf_yes_button);

            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

            noButton.setOnClickListener(v -> {
                alertDialog.dismiss();
            });
            yesButton.setOnClickListener(v -> {
                DatabaseHandler db = new DatabaseHandler(context);
                db.deleteItem(id);
                itemList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());

                alertDialog.dismiss();
            });
        }
    }
}
