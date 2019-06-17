package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroJuridico_Activivity extends AppCompatActivity {

    Button Enviar;
    Button Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrojuridico__layout);

        Enviar = (Button)findViewById(R.id.btnEnviar);
        Cancelar = (Button)findViewById(R.id.btnCancelarJurid);
    }

    public void EnviarJur(View view) {
        Intent intent = new Intent(CadastroJuridico_Activivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void CancelarJur(View view) {
        Intent intent = new Intent(CadastroJuridico_Activivity.this, MainActivity.class);
        startActivity(intent);
    }
}
