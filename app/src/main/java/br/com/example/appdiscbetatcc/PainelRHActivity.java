package br.com.example.appdiscbetatcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PainelRHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_rh);
    }

    public void Cancelar(View view) {
        finish();
    }
}
