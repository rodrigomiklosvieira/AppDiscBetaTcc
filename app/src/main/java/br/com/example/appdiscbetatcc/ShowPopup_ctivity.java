package br.com.example.appdiscbetatcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ShowPopup_ctivity extends AppCompatActivity {

    Button PJuridica;
    Button PFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpopup_layout);

        PJuridica = (Button) findViewById(R.id.btnJuridico);
        PFisica = (Button) findViewById(R.id.btnFisica);

    }

    public void setPFisica(Button PFisica) {
        Intent intent = new Intent(ShowPopup_ctivity.this, CadastroFisico_Activity.class);
        startActivity(intent);

    }

    public void setPJuridica(Button PJuridica) {
        Intent intent = new Intent(ShowPopup_ctivity.this, CadastroJuridico_Activivity.class);
        startActivity(intent);

        }

    public void PFisica(View view) {
        Intent intent = new Intent(ShowPopup_ctivity.this, CadastroFisico_Activity.class);
        startActivity(intent);
    }

    public void PJuridica(View view) {
        Intent intent = new Intent(ShowPopup_ctivity.this, CadastroJuridico_Activivity.class);
        startActivity(intent);
    }
}


