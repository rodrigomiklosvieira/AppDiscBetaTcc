package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sairSistema;
    Button Cadastrar;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sairSistema = (Button)findViewById(R.id.btnSair);
        Cadastrar = (Button)findViewById(R.id.btnCadastrar);
        Login = (Button)findViewById(R.id.btnLogin);
    }

    public void ShowPopup(View view) {
        Intent intent = new Intent(MainActivity.this, ShowPopup_ctivity.class);
        startActivity(intent);
        finish();

    }

    public void Login(View view) {
        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent);
        finish();
    }

    public void Sair(View view) {
        finish();
    }

    public void dinamica(View view) {
        Intent intent = new Intent(MainActivity.this, dominancia_Activity.class);
        startActivity(intent);

    }

    public void influencia(View view) {
        Intent intent = new Intent(MainActivity.this, influencia_Activity.class);
        startActivity(intent);

    }

    public void estabilidade(View view) {
        Intent intent = new Intent(MainActivity.this, estabilidade_Activity.class);
        startActivity(intent);
    }

    public void cautela(View view) {
        Intent intent = new Intent(MainActivity.this, cautela_Activity.class);
        startActivity(intent);
    }
}
