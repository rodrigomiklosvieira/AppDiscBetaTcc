package br.com.example.appdiscbetatcc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login_Activity extends AppCompatActivity {

    String urlwebServices = "https://disc.cf/apiRest/validalogin.php";
    String urlwebservices2 = "https://disc.cf/apiRest/esqueceusenha.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Button Entrar;
    Button CancelarLog;
    EditText txtLogin;
    EditText textPassword;
    String nome;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        requestQueue = Volley.newRequestQueue(this);

        Entrar = (Button) findViewById(R.id.btnEntrar);
        CancelarLog = (Button) findViewById(R.id.btnCancelarLog);
        txtLogin = findViewById(R.id.txtLogin);
        textPassword = findViewById(R.id.txtSenhaLogin);
        dialog = new Dialog(this);
    }

    public void CancelarLog(View view) {

        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Entrar(View view) {

        boolean validado = true;
        if (txtLogin.getText().length() == 0) {
            txtLogin.setError("Campo email Obrigatório");
            txtLogin.requestFocus();
            validado = false;
        }

        if (textPassword.getText().length() == 0) {
            textPassword.setError("Campo senha Obrigatório");
            textPassword.requestFocus();
            validado = false;
        }

        if (validado) {

            Toast.makeText(getApplicationContext(), "Validando seus dados...espere...", Toast.LENGTH_SHORT).show();

            validarLogin();

        }
    }

    private void validarLogin() {

        stringRequest = new StringRequest(Request.Method.POST, urlwebServices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogLogin", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {

                                Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            } else {

                                int nivel = jsonObject.getInt("perfil");
                                if (nivel == 1) {

                                    nome = (jsonObject.getString("nome"));
                                    String nomeempresa = (jsonObject.getString("nomeempresa"));
                                    String email = (jsonObject.getString("login"));
                                    Intent intent = new Intent(Login_Activity.this, Descricao_Activity.class);
                                    intent.putExtra("nome", nome);
                                    intent.putExtra("nomeempresa", nomeempresa);
                                    intent.putExtra("login", email);
                                    startActivity(intent);
                                    finish();

                                } else if (nivel == 2) {
                                    String empresaidlogin = (jsonObject.getString("empresaidlogin"));
                                    String empresalogin = (jsonObject.getString("empresalogin"));
                                    Intent intent = new Intent(Login_Activity.this, PainelRHActivity.class);
                                    intent.putExtra("empresalogin", empresalogin);
                                    intent.putExtra("empresaidlogin", empresaidlogin);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        } catch (Exception e) {
                            Log.v("LogLogin", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Loglogin", error.getMessage());
                    }
                }) {
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("login", txtLogin.getText().toString());
                params.put("senha", textPassword.getText().toString());

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

    public void esqueceu_senha(View view) {

        TextView txtclose;
        final EditText txtEmailSenha;
        Button btnEnviar;
        dialog.setContentView(R.layout.popup_esqueceu_senha);

        txtclose = (TextView) dialog.findViewById(R.id.txtclose);

        txtEmailSenha = (EditText) dialog.findViewById(R.id.txtEmailSenha);
        btnEnviar = (Button) dialog.findViewById(R.id.btnEnviar);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                dialog.dismiss();
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                String email = txtEmailSenha.getText().toString();

                if (txtEmailSenha.getText().length() == 0) {
                    txtEmailSenha.setError("Campo email Obrigatório");
                    txtEmailSenha.requestFocus();

                } else if (validateEmailFormat(email) == false) {
                    txtEmailSenha.setError("Digite um e-mail valido!");
                    txtEmailSenha.requestFocus();

                } else {

                    stringRequest = new StringRequest(Request.Method.POST, urlwebservices2,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.v("LogLogin", response);

                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        boolean isErro = jsonObject.getBoolean("erro");

                                        if (isErro) {

                                            Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                                        } else {

                                            Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                                            dialog.dismiss();
                                        }

                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                                }
                            }) {
                        protected Map<String, String> getParams() {

                            Map<String, String> params = new HashMap<>();
                            params.put("email", txtEmailSenha.getText().toString());

                            return params;
                        }

                    };

                    requestQueue.add(stringRequest);

                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private boolean validateEmailFormat(final String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }
}
