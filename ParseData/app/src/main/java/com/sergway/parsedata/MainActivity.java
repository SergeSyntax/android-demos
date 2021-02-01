package com.sergway.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String url = "https://jsonplaceholder.typicode.com/todos";
    String getSingleUrl = "https://jsonplaceholder.typicode.com/todos/1";
    //    RequestQueue queue;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        queue = Volley.newRequestQueue(this);
        queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        TextView textView = findViewById(R.id.textView);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getSingleUrl, null,
                response -> {
                    try {
                        Log.d("JsonObj", "onCreate: " + response.getString("title"));
                        textView.setText(response.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, err -> {
            Log.d("", "onCreate: Failed!");
        });

        queue.add(jsonObjectRequest);
//        getJsonArrayRequests();
//        StringRequest stringRequest = getStringRequest();
//        queue.add(stringRequest);
    }

    private void getJsonArrayRequests() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    Log.d("JSON", "onCreate: " + jsonObject.getString("title"));
                } catch (JSONException e) {
                    Log.d("JsonException", "onCreate: " + e.toString());
                }
            }
        }, error -> {
            Log.d("JSON", "onCreate: Failed!");
        });

        queue.add(jsonArrayRequest);
    }

//    private StringRequest getStringRequest() {
//        return new StringRequest(Request.Method.GET, url,
//                response -> {
//                    Log.d("Main", "onCreate: " + response);
//                }, error -> {
//            Log.d("Main", "Failed to get info!");
//        });
//    }

}