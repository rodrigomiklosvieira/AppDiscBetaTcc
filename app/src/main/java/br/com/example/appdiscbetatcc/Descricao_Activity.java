package br.com.example.appdiscbetatcc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class Descricao_Activity extends AppCompatActivity {

    Button Iniciar;
    Button Cancelar;
    LinearLayout seleciona;
    LinearLayout sim;

    String urlwebservices = "https://carlos.cf/apiRest/alterarempresa.php";
    String urlwebservices2 = "https://carlos.cf/apiRest/selecionaempresa.php";
    String urlwebservices3 = "https://carlos.cf/apiRest/verificateste.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Spinner chbEmpresa;
    private ArrayList<spinner> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> ida = new ArrayList<Integer>();
    private static ProgressDialog mProgressDialog;
    int id2;
    String nomeempresa;
    TextView nomeempresaa;
    EditText txtCodigoAcesso;

    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descricao_layout);

        requestQueue = Volley.newRequestQueue(this);

        TextView nome = (TextView)findViewById(R.id.nome);
        nomeempresaa = (TextView)findViewById(R.id.recebeEmpresa);
        txtCodigoAcesso = findViewById(R.id.txtCodigoAcesso);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra("nome");
        String nomeemp = intent.getStringExtra("nomeempresa");
        email = intent.getStringExtra("login");



        nome.setText("Bem-Vindo(a), " + usuario + ".");
        nomeempresaa.setText("O TESTE será enviado para: " + nomeemp + ".");

        Iniciar = (Button)findViewById(R.id.btnIniciar);
        Cancelar = (Button)findViewById(R.id.btnCancelar);

      //  sim = findViewById(R.id.sim);
      //  seleciona.setVisibility(View.GONE);




    }

    public void Iniciar(View view) {
       verificaTeste();
    }

    public void Cancelar(View view) {
        Intent intent = new Intent(Descricao_Activity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

   // public void abreSelecao(View view) {

     //   sim.setVisibility(View.GONE);
     //   seleciona.setVisibility(View.VISIBLE);


  //  }



    private void alteraEmpresa() {

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

                                Toast.makeText(getApplicationContext(),"Alteração Efetuada!", Toast.LENGTH_LONG).show();


                                nomeempresaa.setText("O TESTE será enviado para: "+ jsonObject.getString("nomeempresa") + ".");
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

                params.put("codigo_acesso", txtCodigoAcesso.getText().toString());
                params.put("email", email);


                return params;
            }


        };


        requestQueue.add(stringRequest);


    }




    private void verificaTeste() {

        stringRequest = new StringRequest(Request.Method.POST, urlwebservices3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {

                                Intent intent = new Intent(Descricao_Activity.this, TesteDsicActivity.class);
                                intent.putExtra("login", email);
                                startActivity(intent);



                            } else {

                                Toast.makeText(getApplicationContext(), "ERRO! Você já fez o TESTE para RH/Psicologo(a) selecionado!", Toast.LENGTH_LONG).show();
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


                params.put("email", email);


                return params;
            }


        };


        requestQueue.add(stringRequest);


    }





    public void alterar(View view) {

     alteraEmpresa();

     //   sim.setVisibility(View.VISIBLE);
     //   seleciona.setVisibility(View.GONE);
      //  chbEmpresa.setSelected(false);

    }
}
