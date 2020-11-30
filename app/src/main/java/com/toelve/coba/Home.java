package com.toelve.coba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    private String response;
    private SharedPreferences boyprefs;
private EditText etresop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boyprefs = getSharedPreferences(getResources().getString(R.string.prefensi), Context.MODE_PRIVATE);
        setContentView(R.layout.activity_home);
        response=boyprefs.getString("response","");
        etresop=findViewById(R.id.etresop);
        etresop.setText(response);
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