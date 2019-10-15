package br.com.example.appdiscbetatcc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sairSistema;
    Button Cadastrar;
    Button Login;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sairSistema = (Button) findViewById(R.id.btnSair);
        Cadastrar = (Button) findViewById(R.id.btnCadastrar);
        Login = (Button) findViewById(R.id.btnLogin);
        dialog = new Dialog(this);

        sairSistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void test() {
        Button PJuridica;
        Button PFisica;
        TextView txtclose;
        dialog.setContentView(R.layout.showpopup_layout);

        txtclose = (TextView) dialog.findViewById(R.id.txtclose);
        PJuridica = (Button) dialog.findViewById(R.id.btnJuridico);
        PFisica = (Button) dialog.findViewById(R.id.btnFisica);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                dialog.dismiss();
            }
        });

        PJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                Intent intent = new Intent(MainActivity.this, CadastroJuridico_Activivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        PFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroFisico_Activity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void ShowPopup(View view) {
        test();
    }

    public void Login(View view) {
        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
        startActivity(intent);
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
