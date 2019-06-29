package br.com.example.appdiscbetatcc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    String urlwebservices = "https://carlos.cf/apiRest/cadastrausuario.php";
    String urlwebservices2 = "https://carlos.cf/apiRest/selecionaempresa.php";

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
    Spinner chbEmpresa;
    private ArrayList<spinner> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> ida = new ArrayList<Integer>();
    private static ProgressDialog mProgressDialog;
    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_fisico_layout);

        requestQueue = Volley.newRequestQueue(this);

        CancelarCF = (Button)findViewById(R.id.btnCancelarFisico);
        EnviarCF = (Button)findViewById(R.id.btnEnviar);

        txtNomeFisico = findViewById(R.id.txtNomeFisico);
        txtCpfFisico = findViewById(R.id.txtCpfFisico);
        txtEmailFisico = findViewById(R.id.txtEmailFisico);
        txtSenha = findViewById(R.id.txtSenha);
        txtSenhaConfirme = findViewById(R.id.txtSenhaConfirme);
        txtTel = findViewById(R.id.txtTel);
        chbEmpresa =(Spinner)findViewById(R.id.chbEmpresa);

        selecionaEmpresa();


    }
















    public void CancelarCF(View view) {
        Intent intent = new Intent(CadastroFisico_Activity.this, MainActivity.class);
        startActivity(intent);

    }

    public void EnviarCF(View view) {

        boolean validado = true;

        if(txtNomeFisico.getText().length()==0){
            txtNomeFisico.setError("Campo email Obrigatório");
            txtNomeFisico.requestFocus();
            validado = false;
        }

        if(txtCpfFisico.getText().length()==0){
            txtCpfFisico.setError("Campo email Obrigatório");
            txtCpfFisico.requestFocus();
            validado = false;
        }

        if(txtEmailFisico.getText().length()==0){
            txtEmailFisico.setError("Campo email Obrigatório");
            txtEmailFisico.requestFocus();
            validado = false;
        }

        if(txtSenha.getText().length()==0){
            txtSenha.setError("Campo email Obrigatório");
            txtSenha.requestFocus();
            validado = false;
        }

        if(txtSenhaConfirme.getText().length()==0){
            txtSenhaConfirme.setError("Campo email Obrigatório");
            txtSenhaConfirme.requestFocus();
            validado = false;
        }

        String senha = txtSenha.getText().toString();
        String confirma = txtSenhaConfirme.getText().toString();

        if(senha != confirma){
          txtSenhaConfirme.setError("As senhas não se coincidem");
          txtSenhaConfirme.requestFocus();
           validado = false;

       }

        if(txtTel.getText().length()==0){
            txtTel.setError("Campo email Obrigatório");
            txtTel.requestFocus();
            validado = false;
        }



        if(validado){

            Toast.makeText(getApplicationContext(),"Validando seus dados...espere...",Toast.LENGTH_SHORT).show();

            validarCadastro();

        }


    }

    private void validarCadastro(){


        stringRequest = new StringRequest(Request.Method.POST, urlwebservices,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogCadastro", response);

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro){

                                Toast.makeText(getApplicationContext(),jsonObject.getString("mensagem"), Toast.LENGTH_LONG).show();
                            }else{

                                    Intent intent = new Intent(CadastroFisico_Activity.this, Descricao_Activity.class);
                                    startActivity(intent);
                                    finish();

                            }

                        }catch (Exception e){

                            Log.v("LogCadastro", e.getMessage());
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
                params.put("nome", txtNomeFisico.getText().toString());
                params.put("email", txtEmailFisico.getText().toString());
                params.put("cpf", txtCpfFisico.getText().toString());
                params.put("senha", txtSenha.getText().toString());
                params.put("telefone", txtTel.getText().toString());
                int id = ((spinner)chbEmpresa.getSelectedItem()).id;
               // params.put("empresa", Integer.toString(id));

                params.put("empresa", "19");

                return params;
            }


        };



        requestQueue.add(stringRequest);


    }



    private void selecionaEmpresa(){

        stringRequest = new StringRequest(Request.Method.GET, urlwebservices2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);

                        try{
                            JSONObject jsonObject = new JSONObject(response);


                            if(jsonObject.optString("status").equals("true")){

                                goodModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = jsonObject.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    spinner playerModel = new spinner();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setNome(dataobj.getString("nome"));
                                    playerModel.setId(dataobj.getInt("id"));


                                    goodModelArrayList.add(playerModel);

                                }

                                for (int i = 0; i < goodModelArrayList.size(); i++){


                                    names.add(goodModelArrayList.get(i).getNome());
                                    ida.add(goodModelArrayList.get(i).getId());

                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(CadastroFisico_Activity.this, simple_spinner_item, names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                chbEmpresa.setAdapter(spinnerArrayAdapter);




                            }




                        }catch (JSONException e){

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest);




    }

}
