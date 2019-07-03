package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Descricao_Activity extends AppCompatActivity {

    Button Iniciar;
    Button Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_layout);


        TextView nome = (TextView)findViewById(R.id.nome);
        TextView nomeempresa = (TextView)findViewById(R.id.recebeEmpresa);


        Intent intent = getIntent();
        String usuario = intent.getStringExtra("nome");
        String nomeemp = intent.getStringExtra("nomeempresa");



        nome.setText("Bem-Vindo, " + usuario + ".");
        nomeempresa.setText("O TESTE será enviado para: " + nomeemp + ".");

        Iniciar = (Button)findViewById(R.id.btnIniciar);
        Cancelar = (Button)findViewById(R.id.btnCancelar);
    }

    public void Iniciar(View view) {
        Intent intent = new Intent(Descricao_Activity.this, TesteDsicActivity.class);
        startActivity(intent);
        finish();
    }

    public void Cancelar(View view) {
        Intent intent = new Intent(Descricao_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
