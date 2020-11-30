package com.toelve.coba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class Verifikasi extends AppCompatActivity {
 private EditText etToken;
 private String token;
 private Button btMasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi);
        etToken=findViewById(R.id.etToken);
        btMasuk=findViewById(R.id.btMasuk);
        btMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token=etToken.getText().toString();
                if(token.isEmpty()){
                    Snackbar.make(view,"Masukan Token",Snackbar.LENGTH_SHORT).show();
                }else{
                    Dialog load = new Dialog(Verifikasi.this);
                    load.setContentView(R.layout.dialogload2);
                    load.setCancelable(false);
                    load.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    ImageView ivX = load.findViewById(R.id.ivX);
                    load.show();
                    new VerifikasiTask(Verifikasi.this,load).requestAction(token);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Verifikasi.this,MainActivity.class));
        finish();
    }
}