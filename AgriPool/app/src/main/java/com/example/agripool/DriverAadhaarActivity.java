package com.example.agripool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverAadhaarActivity extends AppCompatActivity {

    private Button button;
    private EditText aadhaar;
    private EditText dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_aadhaar);


        button = (Button)findViewById(R.id.submit);
        aadhaar = (EditText) findViewById(R.id.aadhaar);
        dl = (EditText) findViewById(R.id.drive);
        final String unm = getIntent().getStringExtra("user");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String num = aadhaar.getText().toString().trim();
                final String dlnum = dl.getText().toString().trim();

                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDatabase.child("Driver").child(unm).child("aadhaar").setValue(num);
                        mDatabase.child("Driver").child(unm).child("dlic").setValue(dlnum);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),databaseError.getMessage() ,Toast.LENGTH_LONG ).show();
                    }
                });

            }
        });
    }
}