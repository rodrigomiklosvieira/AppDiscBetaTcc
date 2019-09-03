package br.com.example.appdiscbetatcc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
    String nome,telefone,email;
    String teste;
    int posicao;
    EditText eText;

    List<candidato> candidatoList;

    ArrayList<String> pesquisa = new ArrayList<String>();





    String urlwebservices = "https://carlos.cf/apiRest/buscacandidato.php";



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
        LstPainel = (ListView)findViewById(R.id.LstPainel);
        candidatoList = new ArrayList<>();
        eText = (EditText) findViewById(R.id.eText);



        LstPainel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ListarDadosCandidatos.class);
                final candidato cand = candidatoList.get(position);
                intent.putExtra("name",cand.getId());

                startActivity(intent);
            }
        });


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


                selecionaCandidato();
                posicao = spinner.getSelectedItemPosition();
                itemSelecionado = lista.get(posicao);



               CandidatoAdapter adapter = new CandidatoAdapter(candidatoList);
             LstPainel.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


      //  Pesquisar();

        eText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        //        Pesquisar();

                LstPainel.setAdapter(new ArrayAdapter<String>(PainelRHActivity.this, android.R.layout.simple_list_item_1, pesquisa));
            }
        });






        selecionaCandidato();







    }


 /*   public void Pesquisar() {
        int textlength = eText.getText().length();
        pesquisa.clear();



        for (int i = 0; i < candidato.length; i++) {
            if (textlength <= lstFrutas[i].length()) {
                if (eText.getText().toString().equalsIgnoreCase((String) lstFrutas[i].subSequence(0, textlength))) {
                    pesquisa.add(lstFrutas[i]);
                }
            }
        }

    }
*/



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
                            boolean isErro = jsonObject.getBoolean("erro");

                            if (isErro) {

                                Toast.makeText(getApplicationContext(), jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            } else {

                                candidatoList.clear();



                                JSONArray dataArray = jsonObject.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {



                                    JSONObject obj = dataArray.getJSONObject(i);

                                    candidatoList.add(new candidato(
                                            obj.getInt("id"),
                                            obj.getString("nome"),
                                            obj.getString("telefone"),
                                            obj.getString("email")
                                    ));

                                }

                                CandidatoAdapter adapter = new CandidatoAdapter(candidatoList);
                                adapter.notifyDataSetChanged();
                                LstPainel.setAdapter(adapter);


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


    class CandidatoAdapter extends ArrayAdapter<candidato> {
        List<candidato> candidatoList;

        public CandidatoAdapter(List<candidato> candidatoList) {
            super(PainelRHActivity.this, R.layout.carrega_lista_rh, candidatoList);
            this.candidatoList = candidatoList;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.carrega_lista_rh, null, true);

            TextView textViewName = listViewItem.findViewById(R.id.nome);


            int idposicao = spinner.getSelectedItemPosition();
            String nome = spinner.getSelectedItem().toString();

            final candidato cand = candidatoList.get(position);


            if(idposicao  == 0) {

                textViewName.setText(cand.getNome());

            }else if(idposicao == 1){

                textViewName.setText(cand.getEmail());

            }else if(idposicao  == 2){

                textViewName.setText(cand.getTelefone());
            }

            return listViewItem;
        }
    }



    }
