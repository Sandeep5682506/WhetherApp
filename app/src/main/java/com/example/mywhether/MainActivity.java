package com.example.mywhether;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}";
    String apikey = "ceed1ab42a2422402e9c1c9ca8dc637b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        tv = findViewById(R.id.tv);
    }

    public void getwheather(View v){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        wheatherapi myapi = retrofit.create(wheatherapi.class);
        Call<Example> examplecall = myapi.getweather(et.getText().toString().trim(),apikey);
        examplecall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.code()==404){
                    Toast.makeText(MainActivity.this, "Please Enter a Valid City", Toast.LENGTH_SHORT).show();
                }
                else if(!(response.isSuccessful())){

                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    et.getText().clear();

                    
                }
                Example mydata = response.body();
                Main main = mydata.getMain();
                Double temp = main.getTemp();
                Integer temperature = (int)(temp-273.15);
                tv.setText(String.valueOf(temperature)+" C");


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}