package com.basit.samplemvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.basit.samplemvvm.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {


    private ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding
                .getRoot());


        String type = getIntent().getStringExtra("type");


        if (type.equals("update"))
        {
            setTitle("update");


            binding.title.setText(getIntent().getStringExtra("title"));
            binding.descrption.setText(getIntent().getStringExtra("description"));

            int id = getIntent().getIntExtra("id" , 0);

            binding.btnSave.setText("Update");

            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("desc" , binding.descrption.getText().toString());
                    intent.putExtra("id" , id);
                    setResult(RESULT_OK , intent);
                    finish();
                }
            });

        }
else {
            setTitle("Add Mode");
            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent();
                    intent.putExtra("title", binding.title.getText().toString());
                    intent.putExtra("desc" , binding.descrption.getText().toString());
                    setResult(RESULT_OK , intent);
                    finish();
                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));

        finish();
    }
}