package be.kuleuven.myfirstapp;


import java.util.HashMap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class DataParser {
    private  String Pname;
    //Obtain all data from getNearby places , then put it in to the googlePlace hashMap.
    private HashMap<String, String> getPlace(JSONObject googlePlaceJson)
    {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "--NA--";
        String vicinity= "--NA--";
        String latitude= "";
        String longitude="";
        String reference="";

        Log.d("DataParser","jsonobject ="+googlePlaceJson.toString());  //
        //Vic->heverlee
        //la->4555555

        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }

            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googlePlaceJson.getString("reference");


            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);





        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }
    private List<HashMap<String, String>>getPlaces(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        List<HashMap<String, String>> placelist = new ArrayList<>();
        HashMap<String, String> placeMap = null;

        for(int i = 0; i<count;i++)
    {
        try {
            placeMap = getPlace((JSONObject) jsonArray.get(i));
            placelist.add(placeMap);// put the getplace hashmaps in the list
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
        return placelist;
    }

    public List<HashMap<String, String>> parse(String jsonData, String Pname)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        this.Pname=Pname;

        Log.d("json data", jsonData);

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");//get results section from a lot of data
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
}
