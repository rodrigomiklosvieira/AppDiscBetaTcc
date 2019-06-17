package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroFisico_Activity extends AppCompatActivity {

    Button CancelarCF;
    Button EnviarCF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_fisico_layout);

        CancelarCF = (Button)findViewById(R.id.btnCancelarFisico);
        EnviarCF = (Button)findViewById(R.id.btnEnviar);
    }

    public void CancelarCF(View view) {
        Intent intent = new Intent(CadastroFisico_Activity.this, MainActivity.class);
        startActivity(intent);

    }

    public void EnviarCF(View view) {
        Intent intent = new Intent(CadastroFisico_Activity.this, Descricao_Activity.class);
        startActivity(intent);
    }
}
