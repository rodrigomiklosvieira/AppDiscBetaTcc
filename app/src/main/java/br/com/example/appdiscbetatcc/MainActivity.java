package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sairSistema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sairSistema = (Button)findViewById(R.id.btnSair);
    }

    public void ShowPopup(View view) {
        Intent intent = new Intent(MainActivity.this, ShowPopup_ctivity.class);
        startActivity(intent);

    }

    public void Login(View view) {
        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent);
    }

    public void Sair(View view) {

    }
}
