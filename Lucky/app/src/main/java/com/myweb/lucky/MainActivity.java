package com.myweb.lucky;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewJSON";
    private List<Course> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    String idStr,picStr,nameStr,priceStr,amountStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
        String url = "http://10.51.4.1/spa/course_get_post.php";
        new GetDataBinding().execute(url);

    }

    private class GetDataBinding extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected Integer doInBackground(String... strings) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());

                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new MyRecyclerViewAdapter(MainActivity.this, feedsList);
                mRecyclerView.setAdapter(adapter);


                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Course item) {
                        //Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, item.getId_course(), Toast.LENGTH_LONG).show();
                        idStr = item.getId_course();
                        picStr = item.getPic();
                        nameStr = item.getName();
                        priceStr = item.getPrice();
                        amountStr = item.getAmount();
                        Intent intent = new Intent(MainActivity.this, CourseDataActivity.class);
                        intent.putExtra("c_id", idStr);
                        intent.putExtra("c_pic", picStr);
                        intent.putExtra("c_name", nameStr);
                        intent.putExtra("c_price", priceStr);
                        intent.putExtra("c_amount", amountStr);
                        startActivity(intent);

                    }
                });

            } else {
                Toast.makeText(MainActivity.this, "Failed to fetch JSON data!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void parseResult(String s) {
        try {
            JSONObject response = new JSONObject(s);
            JSONArray posts = response.optJSONArray("result");
            feedsList = new ArrayList<>();
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                Course item = new Course();
                item.setId_course(post.optString("id_course"));
                item.setPic(post.optString("pic"));
                item.setName(post.optString("name_course"));
                item.setPrice(post.optString("price_course"));
                item.setAmount(post.optString("amount"));
                feedsList.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
