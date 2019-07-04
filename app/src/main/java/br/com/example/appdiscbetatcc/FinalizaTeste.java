package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class FinalizaTeste extends AppCompatActivity {

    int d,i,s,c;
    String url = "https://carlos.cf/apiRest/cadastrateste.php";
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String email;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaliza_teste);

        requestQueue = Volley.newRequestQueue(this);



        Intent intent = getIntent();
        d = intent.getIntExtra("d", 0);
        i = intent.getIntExtra("i", 0);
        s = intent.getIntExtra("s", 0);
        c = intent.getIntExtra("c", 0);

        email = intent.getStringExtra("login");
    }


    public void Cancelar(View view) {

        finish();
    }

    public void enviaTeste(View view) {
   validaEnvio();

    }

    private void validaEnvio() {

        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {

                                Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "TESTE enviado com sucesso!", Toast.LENGTH_LONG).show();
                                finish();

                            }

                        } catch (Exception e) {

                            Log.v("LogCadastro", e.getMessage());
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
                params.put("d", Integer.toString(d));
                params.put("i", Integer.toString(i));
                params.put("s", Integer.toString(s));
                params.put("c", Integer.toString(c));
                params.put("email", email);

                return params;
            }


        };


        requestQueue.add(stringRequest);


    }


}
