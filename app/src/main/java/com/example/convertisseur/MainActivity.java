package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderSpinner(R.id.spi_in, 0);
        renderSpinner(R.id.spi_out, 1);

        btnOnClick();
    }

    public void renderSpinner(int idSpinner, int selectedValue) {
        Spinner spinner = (Spinner) findViewById(idSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.currency_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(selectedValue);
    }

    public void btnOnClick() {
        Button button = findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.inp_montant);
                Spinner spinerIn = findViewById(R.id.spi_in);
                Spinner spinerOut = findViewById(R.id.spi_out);
                TextView textView = findViewById(R.id.lab_return);

                double editTextValue = Double.parseDouble(editText.getText().toString());
                String spinerInValue = spinerIn.getSelectedItem().toString();
                String spinerOutValue = spinerOut.getSelectedItem().toString();

                if (editText.getText() != null) {
                    final double eurosValue = 1;
                    final double btcValue = 19953.28;
                    final double ethValue = 1363.75;

                    if (spinerInValue.equals("Euros") && spinerOutValue.equals("Euros")) {
                        textView.setText(new DecimalFormat("##.##").format(editTextValue) + " €");
                    } else if (spinerInValue.equals("Euros") && spinerOutValue.equals("Bitcoin")) {
                        textView.setText(new DecimalFormat("##.##").format((editTextValue * eurosValue) / btcValue) + " ₿");
                    } else if (spinerInValue.equals("Euros") && spinerOutValue.equals("Ether")) {
                        textView.setText(new DecimalFormat("##.##").format((editTextValue * eurosValue) / btcValue) + " ETH");

                    }

                    if (spinerInValue.equals("Bitcoin") && spinerOutValue.equals("Bitcoin")) {
                        textView.setText(new DecimalFormat("##.##").format(editTextValue) + " ₿");
                    } else if (spinerInValue.equals("Bitcoin") && spinerOutValue.equals("Euros")) {
                        textView.setText(new DecimalFormat("##.##").format(editTextValue * btcValue) + " €");
                    } else if (spinerInValue.equals("Bitcoin") && spinerOutValue.equals("Ether")) {
                        textView.setText(new DecimalFormat("##.##").format((editTextValue * eurosValue * btcValue) / ethValue) + " ETH");
                    }

                    if (spinerInValue.equals("Ether") && spinerOutValue.equals("Ether")) {
                        textView.setText(new DecimalFormat("##.##").format(editTextValue) + " ETH");
                    } else if (spinerInValue.equals("Ether") && spinerOutValue.equals("Euros")) {
                        textView.setText(new DecimalFormat("##.##").format(editTextValue * ethValue) + " €");
                    } else if (spinerInValue.equals("Ether") && spinerOutValue.equals("Bitcoin")) {
                        textView.setText(new DecimalFormat("##.##").format((editTextValue * eurosValue * ethValue) / btcValue) + " ₿");
                    }

                }


            }
        });
    }


}