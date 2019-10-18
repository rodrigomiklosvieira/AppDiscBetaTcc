package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastroJuridico_Activivity extends AppCompatActivity {

    String urlwebservices = "https://www.avaliacaodisc.com/apiRest/cadastrarh.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Button Enviar;
    Button Cancelar;

    EditText txtRazaoSocial;
    EditText txtCnpj;
    EditText txtEmailJurid;
    EditText txtSenhaJ;
    EditText txtSenhaJConfirme;
    EditText txtTel;
    EditText txtCodigoJAcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrojuridico__layout);

        requestQueue = Volley.newRequestQueue(this);

        Enviar = (Button) findViewById(R.id.btnEnviar);
        Cancelar = (Button) findViewById(R.id.btnCancelarJurid);

        txtRazaoSocial = findViewById(R.id.txtRazaoSocial);
        txtCnpj = findViewById(R.id.txtCnpj);
        txtEmailJurid = findViewById(R.id.txtEmailJurid);
        txtSenhaJ = findViewById(R.id.txtSenhaJ);
        txtSenhaJConfirme = findViewById(R.id.txtSenhaJConfirme);
        txtTel = findViewById(R.id.txtTel);

        txtCodigoJAcesso = findViewById(R.id.txtCodigoJAcesso);

        txtTel.addTextChangedListener(mascara.insert("(##)#####-####", txtTel));

        txtCnpj.addTextChangedListener(mascara.insert("##.###.###/####-##", txtCnpj));
    }

    public void EnviarJur(View view) {

        boolean validado = true;

        if (txtRazaoSocial.getText().length() == 0) {
            txtRazaoSocial.setError("Campo nome Obrigatório");
            txtRazaoSocial.requestFocus();
            validado = false;
        }

        if (txtCnpj.getText().length() == 0) {
            txtCnpj.setError("Campo cpf Obrigatório");
            txtCnpj.requestFocus();
            validado = false;
        }

        if (txtEmailJurid.getText().length() == 0) {
            txtEmailJurid.setError("Campo email Obrigatório");
            txtEmailJurid.requestFocus();
            validado = false;
        }

        if (validateEmailFormat(txtEmailJurid.getText().toString()) == false) {
            txtEmailJurid.setError("Digite um email válido!");
            txtEmailJurid.requestFocus();
            validado = false;
        }

        if (txtSenhaJ.getText().length() == 0) {
            txtSenhaJ.setError("Campo senha Obrigatório");
            txtSenhaJ.requestFocus();
            validado = false;
        }

        if (txtSenhaJConfirme.getText().length() == 0) {
            txtSenhaJConfirme.setError("Campo de confirmação Obrigatório");
            txtSenhaJConfirme.requestFocus();
            validado = false;
        }

        String senha = txtSenhaJ.getText().toString();
        String confirma = txtSenhaJConfirme.getText().toString();

        if (!senha.equals(confirma)) {
            txtSenhaJConfirme.setError("As senhas não se coincidem");
            txtSenhaJConfirme.requestFocus();
            validado = false;
        }

        if (txtTel.getText().length() == 0) {
            txtTel.setError("Campo telefone Obrigatório");
            txtTel.requestFocus();
            validado = false;
        }

        if (txtCodigoJAcesso.getText().length() == 0) {
            txtCodigoJAcesso.setError("Campo código de acesso obrigatório!");
            txtCodigoJAcesso.requestFocus();
            validado = false;
        }

        if (txtTel.getText().length() < 13) {
            txtTel.setError("Preencha o campo com DD + telefone");
            txtTel.requestFocus();
            validado = false;
        }

        if (txtCnpj.getText().length() < 14) {
            txtCnpj.setError("Preencha o campo com 11 numeros para cpf ou 14 numeros para cnpj");
            txtCnpj.requestFocus();
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
        Enviar.setEnabled(false);

        stringRequest = new StringRequest(Request.Method.POST, urlwebservices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {
                                Enviar.setEnabled(true);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(CadastroJuridico_Activivity.this, PainelRHActivity.class);
                                intent.putExtra("empresalogin", txtRazaoSocial.getText().toString());
                                startActivity(intent);
                                finish();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                            Enviar.setEnabled(true);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                        Enviar.setEnabled(true);
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nome", txtRazaoSocial.getText().toString());
                params.put("cnpj", txtCnpj.getText().toString());
                params.put("email", txtEmailJurid.getText().toString());
                params.put("senha", txtSenhaJ.getText().toString());
                params.put("telefone", txtTel.getText().toString());
                params.put("codigo_acesso", txtCodigoJAcesso.getText().toString());

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void CancelarJur(View view) {
        finish();
    }
}
