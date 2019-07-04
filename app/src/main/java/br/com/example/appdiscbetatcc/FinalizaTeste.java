package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalizaTeste extends AppCompatActivity {

    int d,i,s,c;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_teste);






        Intent intent = getIntent();
        d = intent.getIntExtra("d", 0);
        i = intent.getIntExtra("i", 0);
        s = intent.getIntExtra("s", 0);
        c = intent.getIntExtra("c", 0);


    }


    public void Cancelar(View view) {

        finish();
    }

    public void enviaTeste(View view) {


    }
}
