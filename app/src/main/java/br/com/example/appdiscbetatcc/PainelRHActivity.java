package br.com.example.appdiscbetatcc;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.widget.Button;
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



    StringRequest stringRequest;
    RequestQueue requestQueue;

    TextView nomeempresaa;
    Spinner spinner;
    List<String> lista;
    String itemSelecionado;
    ListView LstPainel;
    String id_empresa;

    int posicao;
    EditText eText;
    List<candidato> candidatoList;

    Dialog dialog;
    String urlwebservices = "https://carlos.cf/apiRest/buscacandidato.php";
    String urlwebservices2 = "https://carlos.cf/apiRest/verteste.php";
    EditText editEnviar;
    Button atualizaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel_rh);
        requestQueue = Volley.newRequestQueue(this);
        nomeempresaa = (TextView) findViewById(R.id.textorh);
        Intent intent = getIntent();
        String nomeemp = intent.getStringExtra("empresalogin");
        id_empresa = intent.getStringExtra("empresaidlogin");
        nomeempresaa.setText("Bem-Vindo(a), " + nomeemp + ".");
        LstPainel = (ListView) findViewById(R.id.LstPainel);
        atualizaLista = (Button) findViewById(R.id.atualizaLista);
        candidatoList = new ArrayList<>();
        eText = (EditText) findViewById(R.id.eText);
        dialog = new Dialog(this);





        spinner = (Spinner) findViewById(R.id.selecionadados);
        lista = new ArrayList<>(Arrays.asList("Nome", "E-mail", "Telefone"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lista);
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

        selecionaCandidato();


        LstPainel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //    Intent intent = new Intent(getApplicationContext(), ListarDadosCandidatos.class);
                final candidato cand = candidatoList.get(position);
                // intent.putExtra("name", cand.getId());
                test(Integer.toString(cand.getId()));

                // startActivity(intent);
            }
        });

        atualizaLista.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {


                selecionaCandidato();
            }
        });
    }

    public void test(String id){
        Button PJuridica;
        Button PFisica;
        TextView txtclose;
        ImageView ver;
        ImageView imprime;
        ImageView enviar;
        ImageView apagar;
        dialog.setContentView(R.layout.popup_rh_escolha);

       final String id_candidato = id;


        txtclose =(TextView) dialog.findViewById(R.id.txtclose);

        ver =(ImageView) dialog.findViewById(R.id.ver);
        imprime =(ImageView) dialog.findViewById(R.id.imprime);
        enviar =(ImageView) dialog.findViewById(R.id.enviar);
        apagar =(ImageView) dialog.findViewById(R.id.apagar);




        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                dialog.dismiss();
            }
        });

        ver.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {

                Intent intent = new Intent(getApplicationContext(), ListarDadosCandidatos.class);
                intent.putExtra("id_candidato", id_candidato);
                intent.putExtra("id_empresa", id_empresa);
                startActivity(intent);
            }
        });

        imprime.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {

              int  flag=1;

                imprimeCandidato(id_candidato,flag);
            }
        });

        enviar.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {
                dialog.dismiss();

                popupEnviar(id_candidato);


            }
        });

        apagar.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {
                dialog.dismiss();

                popupExcluir(id_candidato);


            }
        });





        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    public void popupEnviar(String id){

       Button enviar;
        dialog.setContentView(R.layout.popup_envia_email);
        TextView txtclose;
        final String id_candidato = id;


        txtclose =(TextView) dialog.findViewById(R.id.txtclose);


        enviar =(Button) dialog.findViewById(R.id.enviar);
        editEnviar = (EditText)dialog.findViewById(R.id.editEnviar);



        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                dialog.dismiss();
            }
        });



        enviar.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {

                int flag=2;

                String email = editEnviar.getText().toString();



                if (editEnviar.getText().length() == 0) {
                    editEnviar.setError("Campo email Obrigatório");
                    editEnviar.requestFocus();

                }else if(validateEmailFormat(email) == false) {
                    editEnviar.setError("Digite um e-mail valido!");
                    editEnviar.requestFocus();

                }else {

                        imprimeCandidato(id_candidato, flag);
                        Toast.makeText(getApplicationContext(), "E-mail enviado com sucesso!", Toast.LENGTH_LONG).show();

                        dialog.dismiss();
                    }
                }

        });





        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void popupExcluir(String id){

        Button excluir;
        dialog.setContentView(R.layout.popup_apagar_teste);
        TextView txtclose;
        final String id_candidato = id;


        txtclose =(TextView) dialog.findViewById(R.id.txtclose);


        excluir =(Button) dialog.findViewById(R.id.excluir);




        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                dialog.dismiss();
            }
        });



        excluir.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v2) {

                int flag=3;



                    imprimeCandidato(id_candidato, flag);
                    Toast.makeText(getApplicationContext(), "Excluido com sucesso!", Toast.LENGTH_LONG).show();
                selecionaCandidato();

                    dialog.dismiss();

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

                                final CandidatoAdapter adapter = new CandidatoAdapter(candidatoList);
                            adapter.notifyDataSetChanged();
                                LstPainel.setAdapter(adapter);


                                eText.addTextChangedListener(new TextWatcher() {
                                    @Override

                                    public void afterTextChanged(Editable editable) {

                                    }
                                    @Override
                                    public void beforeTextChanged(CharSequence cs, int arg1,
                                                                  int arg2, int arg3) {

                                    }
                                    @Override
                                    public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                                              int arg3) {

                                      adapter.getFilter().filter(cs);


                                    }
                                });


                            }


                        } catch (JSONException e) {

                            Toast.makeText(getApplicationContext(), "Erro, sem comunicação com o servidor, verifique a internet e tente novamente!", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Nenhum candidato encontrado!", Toast.LENGTH_LONG).show();

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


            if (idposicao == 0) {

                textViewName.setText(cand.getNome());

            } else if (idposicao == 1) {

                textViewName.setText(cand.getEmail());

            } else if (idposicao == 2) {


                textViewName.setText(mascara.addMask(cand.getTelefone(), "(##)#####-####"));
            }

            return listViewItem;
        }
    }



    private void imprimeCandidato(final String id_candidato, final int flag) {

        final String id_cand = id_candidato;



        stringRequest = new StringRequest(Request.Method.POST, urlwebservices2,
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


                                String nome = (jsonObject.getString("nome_candidato"));
                                String telefone = (jsonObject.getString("telefone_candidato"));
                                String email = (jsonObject.getString("email_candidato"));

                                String maior = (jsonObject.getString("mensagemMaior"));
                                String maiorCor = (jsonObject.getString("corMaior"));

                               String segMaior = (jsonObject.getString("mensagemSegMaior"));
                                String segMaiorCor = (jsonObject.getString("corSegMaior"));

                                String d,i,s,c;

                                d = jsonObject.getString("d");
                                i = jsonObject.getString("i");
                                s = jsonObject.getString("s");
                                c = jsonObject.getString("c");


                                String masktel = (mascara.addMask(telefone, "(##)#####-####"));


                                    if(flag==1) {
                                        String url = "https://carlos.cf/apiRest/imprimeteste.php?d=" + d + "&i=" + i + "&s=" + s + "&c=" + c + " " +
                                                "&nome=" + nome + "&telefone=" + masktel + "&email=" + email + "&maior=" + maior + "&maiorcor=" + maiorCor + "&segmaior="
                                                + segMaior + "&segmaiorcor=" + segMaiorCor + "";
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(url));
                                        startActivity(intent);

                                    }

                            }

                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), "Erro, tente novamente!", Toast.LENGTH_LONG).show();

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
                params.put("id_candidato", id_cand);
                params.put("id_empresa", id_empresa);
                params.put("flag", String.valueOf(flag));

                if(flag==2) {
                    params.put("email", editEnviar.getText().toString());
                }

                return params;
            }


        };


        requestQueue.add(stringRequest);


    }



}
