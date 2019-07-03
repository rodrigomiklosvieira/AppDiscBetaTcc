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

public class Login_Activity extends AppCompatActivity {

    String urlwebServices = "https://carlos.cf/apiRest/validalogin.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Button Entrar;
    Button CancelarLog;
    EditText txtLogin;
    EditText textPassword;
    String nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        requestQueue = Volley.newRequestQueue(this);

        Entrar = (Button)findViewById(R.id.btnEntrar);
        CancelarLog = (Button)findViewById(R.id.btnCancelarLog);
        txtLogin = findViewById(R.id.txtLogin);
        textPassword = findViewById(R.id.txtSenhaLogin);

    }

    public void CancelarLog(View view) {

        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void Entrar(View view) {

        boolean validado = true;
        if(txtLogin.getText().length()==0){
            txtLogin.setError("Campo email Obrigatório");
            txtLogin.requestFocus();
            validado = false;
        }

        if(textPassword.getText().length()==0){
            textPassword.setError("Campo senha Obrigatório");
            textPassword.requestFocus();
            validado = false;
        }

        if(validado){

            Toast.makeText(getApplicationContext(),"Validando seus dados...espere...",Toast.LENGTH_SHORT).show();

            validarLogin();

        }



    }


    private void validarLogin(){

       stringRequest = new StringRequest(Request.Method.POST, urlwebServices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogLogin", response);

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro){

                                Toast.makeText(getApplicationContext(),jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            }else{

                                int nivel = jsonObject.getInt("perfil");
                                if(nivel==1){

                                    nome = (jsonObject.getString("nome"));
                                    Intent intent = new Intent(Login_Activity.this, Descricao_Activity.class);
                                    intent.putExtra("nome", nome);
                                    startActivity(intent);
                                    finish();

                                }else if(nivel==2){
                                    Intent intent = new Intent(Login_Activity.this, PainelRHActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }

                        }catch (Exception e){

                            Log.v("LogLogin", e.getMessage());
                        }

                    }
                },
               new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Loglogin",error.getMessage());
            }
        }){
           protected Map<String, String> getParams(){

               Map<String, String> params = new HashMap<>();
               params.put("login", txtLogin.getText().toString());
               params.put("senha", textPassword.getText().toString());

               return params;
           }


       };

        requestQueue.add(stringRequest);


    }


}
