package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class ListarDadosCandidatos extends AppCompatActivity {

    String id_candidato, id_empresa;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    TextView id_maior;
    LinearLayout idCorMaior;
    Button gerarGrafico;
    TextView id_Segmaior;
    LinearLayout idCorSegMaior;
    TextView id_cand;
    String urlwebservices = "https://www.avaliacaodisc.com/apiRest/verteste.php";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_dados_candidatos);
        requestQueue = Volley.newRequestQueue(this);

        id_cand = (TextView) findViewById(R.id.id_candidato);

        id_maior = (TextView) findViewById(R.id.id_maior);
        idCorMaior = (LinearLayout) findViewById(R.id.id_corMaior);

        id_Segmaior = (TextView) findViewById(R.id.id_Segmaior);
        idCorSegMaior = (LinearLayout) findViewById(R.id.id_corSegMaior);
        Intent intent = getIntent();

        id_candidato = intent.getStringExtra("id_candidato");
        id_empresa = intent.getStringExtra("id_empresa");

        webView = (WebView) findViewById(R.id.webview);

        verCandidato();
    }

    private void verCandidato() {

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
                            } else {

                                id_cand.setText("Candidato: " + jsonObject.getString("nome_candidato"));

                                id_maior.setText(jsonObject.getString("mensagemMaior"));
                                idCorMaior.setBackgroundColor(Color.parseColor("#" + jsonObject.getString("corMaior")));

                                id_Segmaior.setText(jsonObject.getString("mensagemSegMaior"));
                                idCorSegMaior.setBackgroundColor(Color.parseColor("#" + jsonObject.getString("corSegMaior")));

                                String d, i, s, c;

                                d = jsonObject.getString("d");
                                i = jsonObject.getString("i");
                                s = jsonObject.getString("s");
                                c = jsonObject.getString("c");

                                webView.getSettings().setJavaScriptEnabled(true);
                                webView.loadUrl("https://www.avaliacaodisc.com/apiRest/grafico.php?d=" + d + "&i=" + i + "&s=" + s + "&c=" + c + "");

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
                params.put("id_candidato", id_candidato);
                params.put("id_empresa", id_empresa);

                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
}
