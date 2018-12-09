package com.example.e5440.sapiadsapp;

/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        TextView text = findViewById(R.id.text);
        text.setText(user.getEmail());
    }
}
*/

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;

    private RequestManager glide;

    Date today = new java.util.Date();
    //    Long ts = today.getTime();
    int ts = 1;

    private static final String API_KEY = "c05b97de2b2e597428a65451d0df12a7";
    private static final String HASH = "9e36e240cadffa03ecaa206887b23335";
    //private static final long ts = ts1.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.glide = glide;

        /*SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.e5440.sapiadsapp", Context.MODE_PRIVATE);

        ArrayList<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");*/

//Create a handler for the RetrofitInstance interface//

        GetData service = MarvelClient.getRetrofitInstance().create(GetData.class);

//        Map<String, String> params = new HashMap<Long,String, String>();
//        params.put("ts", "1");
//        params.put("apikey", API_KEY);
//        params.put("hash", HASH);

        Call<MarvelResponse> call = service.getAllCharacters(Long.valueOf(ts), API_KEY, HASH);

//Execute the request asynchronously//

        call.enqueue(new Callback<MarvelResponse>() {

            @Override

//Handle a successful response//

            public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                loadDataList(response.body());
            }

            @Override

//Handle execution failures//

            public void onFailure(Call<MarvelResponse> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

//Display the retrieved data as a list//

    private void loadDataList(MarvelResponse usersList) {

//Get a reference to the RecyclerView//

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(usersList.getData().getResults(), glide);

//Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

//Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }

}