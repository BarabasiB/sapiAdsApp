package com.example.e5440.sapiadsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//extend the RecyclerView.Adapter class

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private List<MarvelResults> dataList;

    public MyAdapter(List<MarvelResults> dataList){
        this.dataList=dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        //get a reference to the Views in our layout
        public final View myView;

        TextView textCharacter;

        CustomViewHolder(View itemView){
            super(itemView);
            myView = itemView;

            textCharacter = myView.findViewById(R.id.character);
        }
    }

    //construct a RecyclerView.ViewHolder
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override

    //set the data

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textCharacter.setText(dataList.get(position).getName());

    }

    //calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}