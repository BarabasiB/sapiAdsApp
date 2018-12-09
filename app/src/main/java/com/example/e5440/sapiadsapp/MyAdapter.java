package com.example.e5440.sapiadsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

//extend the RecyclerView.Adapter class

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private List<MarvelResults> dataList;
    private final RequestManager glide;

    public MyAdapter(List<MarvelResults> dataList, RequestManager glide){
        this.dataList=dataList;
        this.glide = glide;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        //get a reference to the Views in our layout
        public final View myView;

        TextView textCharacter;
        TextView textDescription;
        ImageView imageView;
        LinearLayout linearLayout;

        CustomViewHolder(View itemView){
            super(itemView);
            myView = itemView;

            textCharacter = myView.findViewById(R.id.character);
            textDescription = myView.findViewById(R.id.description);
            imageView = myView.findViewById(R.id.imageView);
            /*linearLayout = myView.findViewById(R.id.linearLayout);
            linearLayout.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (linearLayout.getVisibility() == View.VISIBLE) {
                        linearLayout.setVisibility(View.GONE);
                    } else {
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                    /*
                    if(linearLayout.getVisibility() == )
                    linearLayout.setVisibility(View.VISIBLE);*/
             //   }
           //});*/
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
        //if(dataList.get(position).getDescription().isEmpty() == true){
            //onBindViewHolder(holder,position+1);
       // }else{
            holder.textCharacter.setText(dataList.get(position).getName());
            holder.textDescription.setText(dataList.get(position).getDescription());
            glide.load(glide,"http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg",holder.imageView);
        //Glide.load(dataList.get(position).getThumbnail()).into(holder.imageView);
        //}

    }

    //calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /*static loadImage(RequestManager glide, String url, ImageView view){
        glide.load(url).into(view);
    }*/

}