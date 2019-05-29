package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Descricao_Activity extends AppCompatActivity {

    Button Iniciar;
    Button Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_layout);

        Iniciar = (Button)findViewById(R.id.btnIniciar);
        Cancelar = (Button)findViewById(R.id.btnCancelar);
    }

    public void Iniciar(View view) {
        Intent intent = new Intent(Descricao_Activity.this, Teste_Activity.class);
        startActivity(intent);
    }

    public void Cancelar(View view) {
    }
}
