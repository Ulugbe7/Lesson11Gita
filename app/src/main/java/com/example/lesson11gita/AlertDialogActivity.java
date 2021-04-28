package com.example.lesson11gita;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogActivity extends AppCompatActivity {
    ViewGroup group;
    Button btn1, btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        group = findViewById(R.id.alert_container);
        findViewById(R.id.btn1).setOnClickListener(v -> showMessage("ulanish"));
        findViewById(R.id.btn2).setOnClickListener(v -> showMessage("ulashish"));

        group.setOnClickListener(v -> group.setVisibility(View.GONE));

        findViewById(R.id.alert1).setOnClickListener(this::alert1);

        findViewById(R.id.alert2).setOnClickListener(this::alert2);
        findViewById(R.id.alert3).setOnClickListener(this::alert3);


    }

    private void alert3(View view) {
        group.setVisibility(View.VISIBLE);
    }

    private void alert2(View view) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        View v = LayoutInflater.from(this).inflate(R.layout.view, null);
        Button button = v.findViewById(R.id.goTarif);
        builder.setView(v);
        builder.setCancelable(true);
        button.setOnClickListener(v1 -> {
            showMessage("Tarifga o'tildi");
        });

        builder.show();

    }


    private void alert1(View view) {

        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        View v = LayoutInflater.from(this).inflate(R.layout.view2, null);
        Button button = v.findViewById(R.id.btnShare);
        builder.setView(v);
        builder.setCancelable(true);
        button.setOnClickListener(v1 -> {
            showMessage("ulashish");
        });

        builder.show();

    }


    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
