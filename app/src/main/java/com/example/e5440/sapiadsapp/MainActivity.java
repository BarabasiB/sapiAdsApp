package com.example.e5440.sapiadsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    private RecyclerView myRecyclerView;
    private int offset = 0;
    private int limit = 10;
    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;

    DatabaseReference databaseReference;

    private RequestManager glide;

    Date today = new Date();
    int ts = 1;

    private static final String API_KEY = "c05b97de2b2e597428a65451d0df12a7";
    private static final String HASH = "9e36e240cadffa03ecaa206887b23335";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.glide = glide;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference().child("Characters");
        databaseReference = database.getReference().child("message");
        Log.i("Database Reference",databaseReference.toString());
        databaseReference.push().setValue("test");

        /*@Override
        public void onScrolled(myRecyclerView, int dx, int dy) {
            super.onScrolled(myRecyclerView, dx, dy);

            visibleItemCount = myRecyclerView.getChildCount();
            totalItemCount = myRecyclerView.getLayoutManager().getItemCount();
            firstVisibleItem = visibleItemCount - 20;

            if(loading){
                if(totalItemCount > previousTotal){
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }

            if(!loading && (visibleItemCount + firstVisibleItem) >= totalItemCount){
                //end has been reached

                offset += 20;

                loading  = true;
            }*/

       /* myRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = myRecyclerView.getLayoutManager().getItemCount();
                firstVisibleItem = visibleItemCount - 20;

                if(loading){
                    if(totalItemCount > previousTotal){
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if(!loading && (visibleItemCount + firstVisibleItem) >= totalItemCount){
                    //end has been reached

                    offset += 20;

                    loading  = true;
                }

            }
        });*/

//Create a handler for the RetrofitInstance interface//

        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

        Call<MarvelResponse> call = service.getAllCharacters(limit,offset,Long.valueOf(ts), API_KEY, HASH);

        call.enqueue(new Callback<MarvelResponse>() {

            @Override

//Handle a successful response//

            public void onResponse(Call<MarvelResponse> call, Response<MarvelResponse> response) {
                loadDataList(response.body());
                MarvelResponse data = response.body();
                List<MarvelResults> dataList = data.getData().getResults();


                //databaseReference.push().setValue(response.body());
            }

            @Override

//Handle execution failures//

            public void onFailure(Call<MarvelResponse> call, Throwable throwable) {

//If the request fails, then display the following toast//

                Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
            }
        });

        SearchView searchView =(SearchView) findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

//Display the retrieved data as a list//

    private void loadDataList(MarvelResponse usersList) {

//Get a reference to the RecyclerView//

        myRecyclerView = findViewById(R.id.myRecyclerView);
        myAdapter = new MyAdapter(usersList.getData().getResults());


//Use a LinearLayoutManager with default vertical orientation//

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myRecyclerView.setLayoutManager(layoutManager);

//Set the Adapter to the RecyclerView//

        myRecyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return true;
    }
}
