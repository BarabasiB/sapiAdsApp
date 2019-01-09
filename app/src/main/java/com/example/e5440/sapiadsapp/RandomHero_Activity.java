package com.example.e5440.sapiadsapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomHero_Activity extends AppCompatActivity {
    private int offset = 0;
    private int limit = 1;

    private TextView name,description;
    private ImageView image;


    DatabaseReference databaseReference;

    private RequestManager glide;

    int ts = 1;

    private static final String API_KEY = "c05b97de2b2e597428a65451d0df12a7";
    private static final String HASH = "9e36e240cadffa03ecaa206887b23335";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_hero_);
        this.glide = glide;
        name = findViewById(R.id.name1);
        description = findViewById(R.id.description1);
        image = findViewById(R.id.imageView1);

        offset = new Random().nextInt(1001);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Characters");

//Create a handler for the RetrofitInstance interface//

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<MarvelResponse> call = service.getAllCharacters(limit,offset,Long.valueOf(ts), API_KEY, HASH);

        call.enqueue(new Callback<MarvelResponse>() {

            @Override

//Handle a successful response//

            public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                MarvelResponse usersList = response.body();

                List<MarvelResults> dataList = usersList.getData().getResults();
                name.setText(dataList.get(0).getName());
                description.setText(dataList.get(0).getDescription());

                Uri uri = Uri.parse(dataList.get(0).getThumbnail());

                MarvelCharacters character = new MarvelCharacters(dataList.get(0).getName(),dataList.get(0).getDescription(),dataList.get(0).getThumbnail());

                databaseReference.push().setValue(character);

                Glide.with(image.getContext()).load(uri).into(image);
                //databaseReference.push().setValue(response.body());
            }

            @Override

//Handle execution failures//

            public void onFailure(Call<MarvelResponse> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(RandomHero_Activity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });

    }

//Display the retrieved data as a list//

}

