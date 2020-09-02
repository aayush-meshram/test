package com.myapp.myapplication2;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    private List<nasaInfo> stringList;
    public RequestQueue mQueue;

    public List<nasaInfo> getResults(String URL, Context context) {
        mQueue = Volley.newRequestQueue(context);
        stringList = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject collection = response.getJSONObject("collection");
                            Log.i("IN THE ", "response");
                            JSONArray items = collection.getJSONArray("items");

                            for (int i = 0; i < 10; i++) {
                                if(items.length() < 10)
                                    break;
                                JSONObject item = items.getJSONObject(i);
                                JSONArray data = item.getJSONArray("data");
                                JSONObject details = data.getJSONObject(0);

                                String title = details.getString("title");
                                String nasa_id = details.getString("nasa_id");
                                nasaInfo CONTENT = new nasaInfo(title, nasa_id);
                                CONTENT.title = title;
                                CONTENT.nasa_id = nasa_id;
                                Log.i("TAAAG!", "onResponse: "+CONTENT.getTitle()+" id :"+CONTENT.getNasa_id());
                                CONTENT.setTitle(title);
                                CONTENT.setNasa_id(nasa_id);
                                stringList.add(CONTENT);
                                Log.i("CHECKING STRINGLIST", "CONTENT "+CONTENT+" title: "+CONTENT.getTitle());
                                Log.i("IN THE " + title, "title and nasa" + nasa_id + "content" + CONTENT);
                                System.out.println(stringList);
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
        mQueue.add(request);
        Log.i("ATAAAAAAAAAAG","+lsit"+stringList);
        return stringList;
    }

}
