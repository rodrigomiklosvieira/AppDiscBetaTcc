package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class PainelRHActivity extends AppCompatActivity {
    Spinner selecionadados;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<spinner> goodModelArrayList;


    StringRequest stringRequest;
    RequestQueue requestQueue;
    String nomeempresa;
    TextView nomeempresaa;
    Spinner spinner;
    List<String> lista;
    String itemSelecionado;
    ListView LstPainel;
    String id_empresa;
    String[] nome,telefone,email;
    String teste;
    private ArrayList<candidato> candidato;

    private ArrayList<String> nome_candidatos = new ArrayList<String>();
    private ArrayList<String> email_candidatos = new ArrayList<String>();

    String urlwebservices = "https://carlos.cf/apiRest/buscacandidato.php";

    String[] nome2 = {"teste","teste","teste"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_rh);

        requestQueue = Volley.newRequestQueue(this);


        nomeempresaa = (TextView)findViewById(R.id.textorh);
        Intent intent = getIntent();
        String nomeemp = intent.getStringExtra("empresalogin");
        id_empresa= intent.getStringExtra("empresaidlogin");
        nomeempresaa.setText("Bem-Vindo(a), " + nomeemp + ".");
        LstPainel = findViewById(R.id.LstPainel);



        CustomAdapter customAdapter = new CustomAdapter();
        LstPainel.setAdapter(customAdapter);
        LstPainel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ListarDadosCandidatos.class);
                intent.putExtra("name",nome2[position]);

                startActivity(intent);
            }
        });




        selecionaCandidato();

        spinner();





    }

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nome2.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view1 = getLayoutInflater().inflate(R.layout.carrega_lista_rh, null);

            TextView name = (TextView) view1.findViewById(R.id.nome);


            name.setText(nome2[position]);



            return view1;
        }
    }

    public void spinner(){


        spinner = (Spinner) findViewById(R.id.selecionadados);
        lista = new ArrayList<>(Arrays.asList("Nome","E-mail","Telefone"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

                int posicao = spinner.getSelectedItemPosition();
                itemSelecionado = lista.get(posicao);
              // Toast.makeText(getApplicationContext(),"teste"+teste,Toast.LENGTH_SHORT).show();

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    public void Cancelar(View view) {
        Intent intent = new Intent(PainelRHActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }





    private void selecionaCandidato() {

        stringRequest = new StringRequest(Request.Method.POST, urlwebservices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            if (jsonObject.optString("status").equals("true")) {

                                candidato = new ArrayList<>();

                                JSONArray dataArray = jsonObject.getJSONArray("data");





                                for (int i = 0; i < dataArray.length(); i++) {

                                    candidato candidato2 = new candidato();

                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                   candidato2.setNome(dataobj.getString("nome"));
                                    candidato2.setEmail(dataobj.getString("email"));

                                    candidato.add(candidato2);

                                }

                                for (int i = 0; i < candidato.size(); i++) {


                                    nome_candidatos.add(candidato.get(i).getNome());
                                    email_candidatos.add(candidato.get(i).getEmail());

                                }

                                //arrumar


                                Toast.makeText(getApplicationContext(),"teste "+nome_candidatos,Toast.LENGTH_LONG).show();




                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
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
                params.put("id", id_empresa);
                return params;
            }


        };


        requestQueue.add(stringRequest);

    }
    }
