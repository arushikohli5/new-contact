package com.example.newcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {
    ImageView ivsad,ivhappy,ivok;
    EditText etname,etnumber,etweb,etlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        ivsad=findViewById(R.id.ivsad);
        ivhappy=findViewById(R.id.ivhappy);
        ivok=findViewById(R.id.ivok);
        etname=findViewById(R.id.etname);
        etnumber=findViewById(R.id.etnumber);
        etweb=findViewById(R.id.etweb);
        etlocation=findViewById(R.id.etlocation);
        ivsad.setOnClickListener(this);
        ivhappy.setOnClickListener(this);
        ivok.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (etname.getText().toString().trim().isEmpty() || etnumber.getText().toString().trim().isEmpty() ||
                etweb.getText().toString().trim().isEmpty() || etlocation.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter att the fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("name", etname.getText().toString().trim());
            intent.putExtra("number", etnumber.getText().toString().trim());
            intent.putExtra("web", etweb.getText().toString().trim());
            intent.putExtra("location", etlocation.getText().toString().trim());

            if (view.getId() == R.id.ivhappy) {
                intent.putExtra("mood", "happy");
            } else if (view.getId() == R.id.ivok) {
                intent.putExtra("mood", "ok");
            } else {
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);
            CreateContact.this.finish();
        }
    }
}