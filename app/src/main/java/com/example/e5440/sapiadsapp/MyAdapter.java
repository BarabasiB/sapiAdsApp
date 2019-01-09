package com.example.e5440.sapiadsapp;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private List<MarvelResults> dataList;





    public MyAdapter(List<MarvelResults> dataList){
        this.dataList=dataList;
    }

    /*public void addNewList(ArrayList<MarvelResults> filteredResult) {
        this.dataList.clear();
        this.dataList.addAll(filteredResult);
        notifyDataSetChanged();
    }*/


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
        holder.textCharacter.setText(dataList.get(position).getName());
        holder.textDescription.setText(dataList.get(position).getDescription());

        Uri uri = Uri.parse(dataList.get(position).getThumbnail());
        Glide.with(holder.imageView.getContext()).load(uri).into(holder.imageView);
        Log.d(TAG, "onBindViewHolder() called with: holder = [" + uri + "], position = [" + position + "]");

        /*String name = dataList.get(position).getName();
        String description = dataList.get(position).getDescription();
        String thumbnail = dataList.get(position).getThumbnail();

        SaveCharacter saveData = new SaveCharacter(name,description,thumbnail);

        databaseReference.push().setValue(saveData);

        Log.i(name,"'s data pushed!");*/

    }

    //calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}