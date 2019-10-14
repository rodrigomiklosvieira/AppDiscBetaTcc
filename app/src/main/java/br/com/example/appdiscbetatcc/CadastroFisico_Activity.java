package br.com.example.appdiscbetatcc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class CadastroFisico_Activity extends AppCompatActivity {

    String urlwebservices = "https://disc.cf/apiRest/cadastrausuario.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Button CancelarCF;
    Button EnviarCF;
    EditText txtNomeFisico;
    EditText txtCpfFisico;
    EditText txtEmailFisico;
    EditText txtSenha;
    EditText txtSenhaConfirme;
    EditText txtTel;

    int id2;
    String nomeempresa;
    EditText txtCodigoAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_fisico_layout);

        requestQueue = Volley.newRequestQueue(this);

        CancelarCF = (Button) findViewById(R.id.btnCancelarFisico);
        EnviarCF = (Button) findViewById(R.id.btnEnviar);

        txtNomeFisico = findViewById(R.id.txtNomeFisico);
        txtCpfFisico = findViewById(R.id.txtCpfFisico);
        txtEmailFisico = findViewById(R.id.txtEmailFisico);
        txtSenha = findViewById(R.id.txtSenha);
        txtSenhaConfirme = findViewById(R.id.txtSenhaConfirme);
        txtTel = findViewById(R.id.txtTel);

        txtCodigoAcesso = findViewById(R.id.txtCodigoAcesso);

        txtTel.addTextChangedListener(mascara.insert("(##)#####-####", txtTel));

        txtCpfFisico.addTextChangedListener(mascara.insert("###.###.###-##", txtCpfFisico));

    }

    public void CancelarCF(View view) {
        finish();
    }

    public void EnviarCF(View view) {

        boolean validado = true;

        if (txtNomeFisico.getText().length() == 0) {
            txtNomeFisico.setError("Campo nome Obrigatório");
            txtNomeFisico.requestFocus();
            validado = false;
        }

        if (txtCpfFisico.getText().length() == 0) {
            txtCpfFisico.setError("Campo cpf Obrigatório");
            txtCpfFisico.requestFocus();
            validado = false;
        }

        if (txtEmailFisico.getText().length() == 0) {
            txtEmailFisico.setError("Campo email Obrigatório");
            txtEmailFisico.requestFocus();
            validado = false;
        }

        if (validateEmailFormat(txtEmailFisico.getText().toString()) == false) {
            txtEmailFisico.setError("Digite um email válido");
            txtEmailFisico.requestFocus();
            validado = false;
        }

        if (txtSenha.getText().length() == 0) {
            txtSenha.setError("Campo senha Obrigatório");
            txtSenha.requestFocus();
            validado = false;
        }

        if (txtSenhaConfirme.getText().length() == 0) {
            txtSenhaConfirme.setError("Campo de confirmação Obrigatório");
            txtSenhaConfirme.requestFocus();
            validado = false;
        }

        String senha = txtSenha.getText().toString();
        String confirma = txtSenhaConfirme.getText().toString();

        if (!senha.equals(confirma)) {
            txtSenhaConfirme.setError("As senhas não se coincidem");
            txtSenhaConfirme.requestFocus();
            validado = false;
        }

        if (txtTel.getText().length() == 0) {
            txtTel.setError("Campo telefone Obrigatório");
            txtTel.requestFocus();
            validado = false;
        }

        if (txtTel.getText().length() < 13) {
            txtTel.setError("Preencha o campo com DD + telefone");
            txtTel.requestFocus();
            validado = false;
        }

        if (txtCpfFisico.getText().length() < 14) {
            txtCpfFisico.setError("Preencha o campo com 11 numeros");
            txtCpfFisico.requestFocus();
            validado = false;
        }

        if (txtCodigoAcesso.getText().length() == 0) {
            txtCodigoAcesso.setError("Campo código de acesso obrigatório!");
            txtCodigoAcesso.requestFocus();
            validado = false;
        }

        if (validado) {
            Toast.makeText(getApplicationContext(), "Cadastrando...aguarde...", Toast.LENGTH_SHORT).show();

            validarCadastro();

        }
    }

    private boolean validateEmailFormat(final String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    private void validarCadastro() {

        EnviarCF.setEnabled(false);

        stringRequest = new StringRequest(Request.Method.POST, urlwebservices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {

                                Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                                EnviarCF.setEnabled(true);
                            } else {

                                String nome = txtNomeFisico.getText().toString();

                                String email = txtEmailFisico.getText().toString();
                                Intent intent = new Intent(CadastroFisico_Activity.this, Descricao_Activity.class);
                                intent.putExtra("nome", nome);
                                intent.putExtra("nomeempresa", jsonObject.getString("nomeempresa"));
                                intent.putExtra("login", email);
                                startActivity(intent);
                                finish();
                            }

                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                            EnviarCF.setEnabled(true);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                        EnviarCF.setEnabled(true);
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("nome", txtNomeFisico.getText().toString());
                params.put("email", txtEmailFisico.getText().toString());
                params.put("cpf", txtCpfFisico.getText().toString());
                params.put("senha", txtSenha.getText().toString());
                params.put("telefone", txtTel.getText().toString());

                params.put("codigo_acesso_rh", txtCodigoAcesso.getText().toString());

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

}
