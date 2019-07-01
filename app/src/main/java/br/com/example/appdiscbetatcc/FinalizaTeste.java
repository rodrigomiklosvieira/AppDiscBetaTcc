package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalizaTeste extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_teste);



        TextView resposta = (TextView)findViewById(R.id.resposta);


        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);



            resposta.setText("Fez " + pontos + " pontos!");




    }


}
