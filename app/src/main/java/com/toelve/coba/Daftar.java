package com.toelve.coba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Daftar extends AppCompatActivity {
    private EditText etTitle,etFullname,etEmail,etPassword,etConfirm;
    private String title,fullname,email,password,confirm;
    private Button btMasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        etTitle=findViewById(R.id.etTitle);
        etFullname=findViewById(R.id.etFullname);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etConfirm=findViewById(R.id.etConfirm);
        btMasuk=findViewById(R.id.btMasuk);
        btMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=etEmail.getText().toString();
                password=etPassword.getText().toString();
                title=etTitle.getText().toString();
                fullname=etFullname.getText().toString();
                confirm=etConfirm.getText().toString();
                if(title.isEmpty()){
                    Snackbar.make(view,"Masukan title",Snackbar.LENGTH_SHORT).show();
                }else
                if(fullname.isEmpty()){
                    Snackbar.make(view,"Masukan Full Name",Snackbar.LENGTH_SHORT).show();
                }else
                if(email.isEmpty()){
                    Snackbar.make(view,"Masukan email",Snackbar.LENGTH_SHORT).show();
                }else
                if(password.isEmpty()){
                    Snackbar.make(view,"Masukan password",Snackbar.LENGTH_SHORT).show();
                }else
                if(confirm.isEmpty()){
                    Snackbar.make(view,"Masukan password",Snackbar.LENGTH_SHORT).show();
                }
                else
                if(!confirm.equals(password)){
                    Snackbar.make(view,"Password dan confirmasi password tidak sama",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Dialog load = new Dialog(Daftar.this);
                    load.setContentView(R.layout.dialogload2);
                    load.setCancelable(false);
                    load.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    ImageView ivX = load.findViewById(R.id.ivX);
                    load.show();
                    new RegisterTask(Daftar.this,load).requestAction(title,fullname,password,email,confirm);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Daftar.this,MainActivity.class));
        finish();
    }
}