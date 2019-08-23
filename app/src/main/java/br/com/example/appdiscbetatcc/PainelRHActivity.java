package br.com.example.appdiscbetatcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class PainelRHActivity extends AppCompatActivity {
    Spinner selecionadados;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<spinner> goodModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_rh);

        selecionadados = (Spinner) findViewById(R.id.selecionadados);




        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(PainelRHActivity.this, simple_spinner_item, names);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        selecionadados.setAdapter(spinnerArrayAdapter);


        selecionadados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
    });


    }



    public void Cancelar(View view) {
        finish();
    }
}
