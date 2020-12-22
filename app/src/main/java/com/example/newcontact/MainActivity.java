package com.example.newcontact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView ivphone;
    ImageView ivmood;
    ImageView ivweb;
    ImageView ivlocation;
    String mood;
    Button btncreate;
    final int create_contact=1;
    String name="",number="",web="",location="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivphone=findViewById(R.id.ivphone);
        ivmood=findViewById(R.id.ivmood);
        ivweb=findViewById(R.id.ivweb);
        ivlocation=findViewById(R.id.ivlocation);
        btncreate=findViewById(R.id.btncreate);
        ivlocation.setVisibility(View.GONE);
        ivweb.setVisibility(View.GONE);
        ivmood.setVisibility(View.GONE);
        ivphone.setVisibility(View.GONE);
        ivphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
        ivlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+location));
                startActivity(intent);
            }
        });
        ivweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+web));
                startActivity(intent);
            }
        });
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,com.example.newcontact.CreateContact.class);
                startActivityForResult(intent,create_contact);
            }
        });

    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if(requestCode==create_contact)
            {
                if(resultCode==RESULT_OK)
                {
                    ivlocation.setVisibility(View.VISIBLE);
                    ivweb.setVisibility(View.VISIBLE);
                    ivmood.setVisibility(View.VISIBLE);
                    ivphone.setVisibility(View.VISIBLE);
                    name=data.getStringExtra("name");
                    number=data.getStringExtra("number");
                    web=data.getStringExtra("web");
                    location=data.getStringExtra("location");
                    mood=data.getStringExtra("mood");
                    if(mood.equals("happy"))
                    {
                        ivmood.setImageResource(R.drawable.happy);
                    }
                    else if(mood.equals("ok"))
                    {
                        ivmood.setImageResource(R.drawable.ok);
                    }
                    else{
                        ivmood.setImageResource(R.drawable.sad);
                    }
                }
                else{
                    Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}