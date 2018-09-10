package com.madonasyombua.roomexample.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madonasyombua.roomexample.R;
import com.madonasyombua.roomexample.models.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {


    private List<Item> itemList;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.displayItemOne.setText(itemList.get(position).getItemOne());
        holder.displayItemTwo.setText(itemList.get(position).getItemTwo());
        holder.displayItemThree.setText(itemList.get(position).getItemThree());


    }
    public void setItemList(List<Item> itemList1){
        itemList = itemList1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(itemList != null)
        return itemList.size();
        else return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.display_item_one)
        TextView displayItemOne;
        @BindView(R.id.display_item_two)
        TextView displayItemTwo;
        @BindView(R.id.display_item_three)
        TextView displayItemThree;


        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
