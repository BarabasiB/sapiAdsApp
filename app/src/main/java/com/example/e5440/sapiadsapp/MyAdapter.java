package com.example.e5440.sapiadsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//extend the RecyclerView.Adapter class

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private List<MarvelResults> dataList;

    public MyAdapter(List<MarvelResults> dataList){
        this.dataList=dataList;
    }

    public void addNewList(ArrayList<MarvelResults> filteredResult) {
        this.dataList.clear();
        this.dataList.addAll(filteredResult);
        notifyDataSetChanged();
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

        /*new DownloadImageTask((ImageView) holder.imageView)
                .execute("http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg");*/
            //holder.imageView.setImageResource(dataList.get(position).getThumbnail());
        Uri uri = Uri.parse(dataList.get(position).getThumbnail());
        Glide.with(holder.imageView.getContext()).load(uri).into(holder.imageView);
        Log.d(TAG, "onBindViewHolder() called with: holder = [" + uri + "], position = [" + position + "]");

        //holder.imageView.setImageURI(uri);
            //glide.load(glide,"http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg",holder.imageView);
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