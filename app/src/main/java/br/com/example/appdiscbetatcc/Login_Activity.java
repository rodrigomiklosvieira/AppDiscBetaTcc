package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Activity extends AppCompatActivity {

    Button Entrar;
    Button CancelarLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Entrar = (Button)findViewById(R.id.btnEntrar);
        CancelarLog = (Button)findViewById(R.id.btnCancelarLog);
    }

    public void CancelarLog(View view) {

        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void Entrar(View view) {
        Intent intent = new Intent(Login_Activity.this, Descricao_Activity.class);
        startActivity(intent);

    }
}
