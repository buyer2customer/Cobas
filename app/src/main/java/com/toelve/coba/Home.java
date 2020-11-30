package com.toelve.coba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {
    private String response;
    private SharedPreferences boyprefs;
private EditText etresop,etNama,etToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boyprefs = getSharedPreferences(getResources().getString(R.string.prefensi), Context.MODE_PRIVATE);
        setContentView(R.layout.activity_home);
        response=boyprefs.getString("response","");
        etresop=findViewById(R.id.etresop);
        etNama=findViewById(R.id.etNama);
        etToken=findViewById(R.id.etToken);
        etresop.setText(response);
        JSONArray as= null;
        try {
            JSONObject jsonObj = new JSONObject(response);
            String ass=jsonObj.getString("data");
            JSONObject jsonObj2 = new JSONObject(ass);
            etNama.setText(jsonObj2.getString("fullName"));
            etToken.setText(jsonObj2.getString("jwtToken"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home.this);
        alertDialogBuilder.setMessage("Keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        (dialog, id) -> {
                            dialog.cancel();
                            finish();
                        });
        alertDialogBuilder.setNegativeButton("Tidak",
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}