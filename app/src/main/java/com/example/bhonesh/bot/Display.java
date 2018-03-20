package com.example.bhonesh.bot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    final String TAG = "check";
    private String place="";
   // TextView days,runst;
    ProgressDialog pd;
    ArrayList<position>  list;
    ListView listdis;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        list=new ArrayList<>();
        listdis=(ListView)findViewById(R.id.listview);


        String q=getIntent().getStringExtra("query");
        String text=getIntent().getStringExtra("text");

        Log.e("qu znd te",q+" "+text);
     //     place=getIntent().getStringExtra("place");

        GPSTracker gps = new GPSTracker(Display.this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();





        pd = new ProgressDialog(Display.this);
        pd.setMessage("Loading . . .");

  String url=" ";
        if(q.equals("0"))
        {
            url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+text+"+&key=AIzaSyCN1gq8Wgt0wBsZUkn2dldgNKZFXdT-iTc";
        }
        else if(q.equals("1"))
        {
            url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+
                    "&rankby=distance&types="+text+"&key=AIzaSyCN1gq8Wgt0wBsZUkn2dldgNKZFXdT-iTc";
        }
        else if(q.equals("2"))
        {
            Intent i=new Intent(Display.this,Mapsactivity.class);
            i.putExtra("lat",latitude);
            i.putExtra("lng",longitude);
            startActivity(i);
        }
//
//        String url;
//        if(place==null)
//        {
//             url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+
//                    "&rankby=distance&types=food&key=AIzaSyCN1gq8Wgt0wBsZUkn2dldgNKZFXdT-iTc";
//        }
//        else{
//             url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurantsin+"+place+"+&key=AIzaSyCN1gq8Wgt0wBsZUkn2dldgNKZFXdT-iTc";
//        }

        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jObject  = null;
                        try {
                            //   https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&key=YOUR_API_KEY
                            jObject = new JSONObject(response);
                            JSONArray jsonarray = new JSONArray(jObject.getString("results"));


                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String rate="not avilable";
                                String geometry=jsonobject.getString("geometry");
                                String runs  = jsonobject.getString("name");
                                String width=" ";
                                String pref=" ";
                                try{
                                    String photo=jsonobject.getString("photos");
                                    JSONObject jphoto=new JSONObject(photo);
                                    width=jphoto.getString("width");
                                     pref=jphoto.getString("photo_refrence");

                                     try{
                                         rate   = jsonobject.getString("rating");
                                     }catch(Exception e)
                                     {}
                                }
                                catch(Exception e){
                                    Log.e("excption","exception over here");
                                }

                                String t=jsonobject.getString("types");
                                JSONObject jgeo=new JSONObject(geometry);
                                String loc=jgeo.getString("location");
                                JSONObject jloc=new JSONObject(loc);
                                String lat=jloc.getString("lat");
                                String lng=jloc.getString("lng");
                                JSONArray jsonarray2 = new JSONArray(jsonobject.getString("types"));
                                 String typearray="";

                                for(int j=0; j < 2; j++)
                                {
                                    typearray+=jsonarray2.getString(j)+",";
                                }

                                list.add(new position(runs,lat,lng,rate,typearray,pref,width));
                                pd.cancel();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("wrong","exception over here");
                            pd.hide();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );



        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

//        Log.e("length of list",list.size()+" ");
//        for(int i=0;i<list.size();i++)
//        {
//            position p=list.get(i);
//            Log.e("list:",p.getName()+" "+p.getLang()+" "+p.getPhoto_ref());
//        }



        adapter=new ListAdapter(Display.this,list);
        listdis.setAdapter(adapter);

        listdis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position dataModel= list.get(position);

                // Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                //       .setAction("No action", null).show();
                Toast.makeText(Display.this,dataModel.getLang()+" "+dataModel.getLat(),Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Display.this,Mapsactivity.class);
                i.putExtra("lat",dataModel.getLat());
                i.putExtra("lng",dataModel.getLang());
                startActivity(i);
            }
        });


    }
}
