package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Five extends AppCompatActivity {
    EditText e3,e4;
    Button b11,b12;
    FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_five);
        e3=(EditText)findViewById(R.id.editTextText3);
        e4=(EditText) findViewById(R.id.editTextText4);
        b11=(Button)findViewById(R.id.button11);
        b12=(Button) findViewById(R.id.button12);
        firebaseAuth=FirebaseAuth.getInstance();
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Five.this, Four.class);
                startActivity(i);
                finish();
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String s3=e3.getText().toString().trim();
             String s4=e4.getText().toString();
             if (s3.isEmpty())
             {
                 e3.setError("Fill email");
                 return;
             }
             else {
                 if(s4.isEmpty())
                 {
                     e4.setError("fill password");
                     return;

                 }
                 else {
                     firebaseAuth.createUserWithEmailAndPassword(s3,s4).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                     {
                         @Override
                                 public void onComplete(@NonNull Task<AuthResult> task){
                             if(task.isSuccessful())
                             {
                                 Toast.makeText(Five.this, "Update database", Toast.LENGTH_SHORT).show();
                                 Intent m=new Intent(Five.this, Four.class);
                                 startActivity(m);
                                 finish();
                             }

                     }
                     });
                 }
             }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}