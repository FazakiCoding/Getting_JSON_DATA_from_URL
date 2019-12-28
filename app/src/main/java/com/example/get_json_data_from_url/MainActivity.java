package com.example.get_json_data_from_url;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult=findViewById(R.id.text_view_result);
        Button buttonGetData=findViewById(R.id.button_get_data);

        requestQueue= Volley.newRequestQueue(this);

        buttonGetData.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
               jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url="https://api.myjson.com/bins/d72ck";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("students");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject student=jsonArray.getJSONObject(i);

                                String name=student.getString("name");
                                int age=student.getInt("age");
                                String major=student.getString("major");

                                textViewResult.append(name+","+String.valueOf(age)+","+major+"\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
