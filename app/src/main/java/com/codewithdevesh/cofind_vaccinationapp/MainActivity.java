package com.codewithdevesh.cofind_vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fb_search;
    private EditText pin;
    private TextView tv;
    private RecyclerView rv;
    private SpinKitView pb;
    private ArrayList<RVModel> arrayList;
    private RVAdapter adapter;
    int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb_search = findViewById(R.id.fab_search);
        rv=findViewById(R.id.rv);
        pin = findViewById(R.id.tv_pinCode);
        pb = findViewById(R.id.pb);
        tv =findViewById(R.id.txt);
        arrayList = new ArrayList<>();
        adapter = new RVAdapter(this, arrayList);
        rv.setAdapter(adapter);

        fb_search.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String x = pin.getText().toString();
                final Calendar calendar = Calendar.getInstance();

                if (x.length() != 6) {
                    Toast.makeText(getApplicationContext(), "Enter Valid PinCode", Toast.LENGTH_SHORT).show();
                } else {
                    arrayList.clear();
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year_s, int month_s, int dayOfMonth) {
                            pb.setVisibility(View.VISIBLE);
                            tv.setVisibility(View.GONE);
                            int t = month_s+1;
                            String dt = dayOfMonth+"-"+t+"-"+year_s;
                            getDetails(x, dt);
                        }
                    }, year, month, day);
                    dpd.show();
                }

            }
        });
    }

    private void getDetails(String code, String date) {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+code+"&date="+date;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pb.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                try {
                    JSONArray arr = response.getJSONArray("centers");
                    if(arr.length()==0){
                        Toast.makeText(getApplicationContext(), "Unable to fetch data of given PinCode", Toast.LENGTH_SHORT).show();
                    }

                    for(int i=0;i< arr.length();i++){
                       JSONObject obj = arr.getJSONObject(i);
                       String centerName = obj.getString("name");
                       String centerLocc = obj.getString("address");
                       String centerTimeFrom = obj.getString("from");
                       String centerTimeTo = obj.getString("to");
                       String fee = obj.getString("fee_type");
                       JSONObject sessionObj   = obj.getJSONArray("sessions").getJSONObject(0);
                       int avail =  sessionObj.getInt("available_capacity");
                       int ageLim = sessionObj.getInt("min_age_limit");
                       String vaccName = sessionObj.getString("vaccine");
                       arrayList.add(new RVModel(centerName,centerLocc,centerTimeFrom,centerTimeTo,fee,ageLim,vaccName,avail));
                    }
                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    pb.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(objectRequest);
    }
}