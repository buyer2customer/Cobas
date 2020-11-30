package com.toelve.coba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail,etPassword;
    private String email,password;
    private Button btMasuk;
    private TextView tvDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPassword=findViewById(R.id.etPassword);
        etEmail=findViewById(R.id.etEmail);
        btMasuk=findViewById(R.id.btMasuk);
        tvDaftar=findViewById(R.id.tvDaftar);
        btMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etEmail.getText().toString();
                password=etPassword.getText().toString();
                if(email.isEmpty()){
                    Snackbar.make(view,"Masukan email",Snackbar.LENGTH_SHORT).show();
                }else
                if(password.isEmpty()){
                    Snackbar.make(view,"Masukan password",Snackbar.LENGTH_SHORT).show();
                }else{
                    Dialog load = new Dialog(MainActivity.this);
                    load.setContentView(R.layout.dialogload2);
                    load.setCancelable(false);
                    load.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    ImageView ivX = load.findViewById(R.id.ivX);
                    load.show();
                    new LoginTask(MainActivity.this,load).requestAction(email,password);
                }
            }
        });

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Daftar.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("Keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}