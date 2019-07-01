package br.com.example.appdiscbetatcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TesteDsicActivity extends AppCompatActivity {


    TextView numPergunta,pergunta1,pergunta2,pergunta3,pergunta4;
    int quadradoMais = R.id.rbQuadradoMais;
    int zMais = R.id.rbZmais;
    int musicaMais = R.id.rbMusicaMais;
    int trianguloMais = R.id.rbTrianguloMais;

    int quadradoMaisPontos;
    int zMaisPontos;
    int musicaMaisPontos;
    int trianguloMaisPontos;

    int quadradoMenos = R.id.rbQuadradoMenos;
    int zMenos = R.id.rbZmenos;
    int musicaMenos = R.id.rbMusicaMenos;
    int trianguloMenos = R.id.rbTrianguloMenos;

    int quadradoMenosPontos;
    int zMenosPontos;
    int musicaMenosPontos;
    int trianguloMenosPontos;
    RadioGroup rgMais;
    RadioGroup rgMenos;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_dsic);


        numPergunta = (TextView)findViewById(R.id.numPergunta);
        pergunta1 = (TextView)findViewById(R.id.pergunta1);
        pergunta2 = (TextView)findViewById(R.id.pergunta2);
        pergunta3 = (TextView)findViewById(R.id.pergunta3);
        pergunta4 = (TextView)findViewById(R.id.pergunta4);
        rgMais = (RadioGroup)findViewById(R.id.rgMais);
        rgMenos = (RadioGroup)findViewById(R.id.rgMenos);
    }

    public void btnResponderOnClick(View view) {




        RadioButton rb = (RadioButton)findViewById(rgMais.getCheckedRadioButtonId());
        RadioButton rbmenos2 = (RadioButton)findViewById(rgMenos.getCheckedRadioButtonId());


        if(rgMais.getCheckedRadioButtonId() == quadradoMais) {

            quadradoMaisPontos++;
            Toast.makeText(getApplicationContext(),"quadradoMais" + quadradoMaisPontos,Toast.LENGTH_SHORT).show();
        }else if(rgMais.getCheckedRadioButtonId() == zMais){
            zMaisPontos++;
        }else if(rgMais.getCheckedRadioButtonId() == musicaMais){
            musicaMaisPontos++;
        }else if(rgMais.getCheckedRadioButtonId() == trianguloMais){
            trianguloMaisPontos++;
        }


        if(rgMenos.getCheckedRadioButtonId() == quadradoMenos) {

            quadradoMenosPontos++;
        }else if(rgMenos.getCheckedRadioButtonId() == zMenos){
            zMenosPontos++;
        }else if(rgMenos.getCheckedRadioButtonId() == musicaMenos){
            musicaMenosPontos++;
        }else if(rgMenos.getCheckedRadioButtonId() == trianguloMenos){
            trianguloMenosPontos++;
        }




        onRestart();


    }


    List<questao> questoes = new ArrayList<questao>(){
        {
            add(new questao("2",
                             R.id.rbMusicaMais, R.id.rbZmais,R.id.rbQuadradoMais,R.id.rbTrianguloMais,
                        R.id.rbMusicaMenos,R.id.rbZmenos,R.id.rbQuadradoMenos, 0,
                    "Cauteloso","Determinado", "Convincente", "Boa Pessoa"));

            add(new questao("3",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbZmais,0,
                    0,R.id.rbMusicaMenos,R.id.rbZmenos, R.id.rbTrianguloMenos,
                    "Amigo","Exato", "Sincero","Calmo"));

            add(new questao("4",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbTrianguloMais,R.id.rbZmais,
                    R.id.rbQuadradoMenos,R.id.rbMusicaMenos,R.id.rbTrianguloMenos, R.id.rbZmenos,
                    "Falador","Controlado", "Convencional", "Decisivo"));

            add(new questao("5",
                    R.id.rbZmais, R.id.rbMusicaMais,R.id.rbQuadradoMais,R.id.rbTrianguloMais,
                    R.id.rbZmenos,R.id.rbMusicaMenos,R.id.rbQuadradoMenos, R.id.rbTrianguloMenos,
                    "Aventureiro","Observador", "Aberto", "Moderado"));

            add(new questao("6",
                    R.id.rbTrianguloMais, R.id.rbQuadradoMais,0,0,
                    R.id.rbTrianguloMenos,0,R.id.rbMusicaMenos, R.id.rbZmenos,
                    "Gentil","Persuasivo", "Modesto", "Criativo"));

            add(new questao("7",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbZmais,0,
                    R.id.rbQuadradoMenos,R.id.rbMusicaMenos,R.id.rbZmenos, R.id.rbTrianguloMenos,
                    "Expressivo","Consciencioso", "Dominante", "Disposto"));

            add(new questao("8",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbTrianguloMais,R.id.rbZmais,
                    R.id.rbQuadradoMenos,0,R.id.rbTrianguloMenos, R.id.rbZmenos,
                    "De boa vontade","Analitico", "Modesto", "Impaciente"));

            add(new questao("9",
                    R.id.rbMusicaMais, R.id.rbTrianguloMais,R.id.rbQuadradoMais,0,
                    R.id.rbMusicaMenos,R.id.rbTrianguloMenos,R.id.rbQuadradoMenos, 0,
                    "Tem Tato","Agradável", "Magnético", "Insistente"));

            add(new questao("10",
                    R.id.rbZmais, R.id.rbQuadradoMais,R.id.rbTrianguloMais,0,
                    R.id.rbZmenos,R.id.rbQuadradoMenos,R.id.rbTrianguloMenos, R.id.rbMusicaMenos,
                    "Valente","Encorajador", "Sereno", "Tímido"));

            add(new questao("11",
                    R.id.rbMusicaMais, R.id.rbTrianguloMais,R.id.rbZmais,R.id.rbQuadradoMais,
                    R.id.rbMusicaMenos,R.id.rbTrianguloMenos,R.id.rbZmenos, R.id.rbQuadradoMenos,
                    "Reservado","Prestativo", "Voluntarioso", "Alegre"));

            add(new questao("12",
                    R.id.rbQuadradoMais, R.id.rbTrianguloMais,R.id.rbMusicaMais,R.id.rbZmais,
                    R.id.rbQuadradoMenos,R.id.rbTrianguloMenos,R.id.rbMusicaMenos, R.id.rbZmenos,
                    "Estimulante","Bondoso", "Impessoal", "Independente"));

            add(new questao("13",
                    R.id.rbZmais, R.id.rbTrianguloMais,R.id.rbQuadradoMais,R.id.rbMusicaMais,
                    R.id.rbZmenos,R.id.rbTrianguloMenos,R.id.rbQuadradoMenos, R.id.rbMusicaMenos,
                    "Competitivo","Tem Consideração", "Feliz", "Recolhido"));

            add(new questao("14",
                    R.id.rbMusicaMais, R.id.rbTrianguloMais,R.id.rbZmais,R.id.rbQuadradoMais,
                    R.id.rbMusicaMenos,R.id.rbTrianguloMenos,R.id.rbZmenos, R.id.rbQuadradoMenos,
                    "Detalhista","Obediente", "Firme", "Brincalhão"));

            add(new questao("15",
                    R.id.rbQuadradoMais, R.id.rbZmais,R.id.rbMusicaMais,R.id.rbTrianguloMais,
                    R.id.rbQuadradoMenos,R.id.rbZmenos,R.id.rbMusicaMenos, R.id.rbTrianguloMenos,
                    "Atraente","Introspectivo", "Teimoso", "Ordeiro"));

            add(new questao("16",
                    R.id.rbMusicaMais, R.id.rbZmais,R.id.rbTrianguloMais,R.id.rbQuadradoMais,
                    R.id.rbMusicaMenos,R.id.rbZmenos,R.id.rbTrianguloMenos, R.id.rbQuadradoMenos,
                    "Lógico","Audacioso", "Leal", "Encantador"));

            add(new questao("17",
                    R.id.rbQuadradoMais, R.id.rbTrianguloMais,R.id.rbZmais,R.id.rbMusicaMais,
                    R.id.rbQuadradoMenos,R.id.rbTrianguloMenos,R.id.rbZmenos, R.id.rbMusicaMenos,
                    "Sociável","Paciente", "Seguro de Si", "Manso no Falar"));

            add(new questao("18",
                    R.id.rbTrianguloMais, R.id.rbZmais,R.id.rbMusicaMais,R.id.rbQuadradoMais,
                    R.id.rbTrianguloMenos,0,R.id.rbMusicaMenos, R.id.rbQuadradoMenos,
                    "Dependente","Impulsivo", "Faz por Completo", "Entusiasmado"));

            add(new questao("19",
                    R.id.rbZmais, R.id.rbQuadradoMais,R.id.rbTrianguloMais,0,
                    R.id.rbZmenos,R.id.rbQuadradoMenos,R.id.rbTrianguloMenos, R.id.rbMusicaMenos,
                    "Batalhador","Extrovertido", "Tranquilizador", "Evita conflito"));

            add(new questao("20",
                    R.id.rbQuadradoMais, R.id.rbTrianguloMais,0,R.id.rbZmais,
                    R.id.rbQuadradoMenos,R.id.rbTrianguloMenos,R.id.rbMusicaMenos, R.id.rbZmenos,
                    "Tem Humor","Solidário", "Justo", "Inflexivel"));

            add(new questao("21",
                    R.id.rbMusicaMais, R.id.rbTrianguloMais,R.id.rbQuadradoMais,R.id.rbZmais,
                    R.id.rbMusicaMenos,R.id.rbTrianguloMenos,R.id.rbQuadradoMenos, R.id.rbZmenos,
                    "Controlado","Generoso", "Animado", "Persistente"));

            add(new questao("22",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbZmais,R.id.rbTrianguloMais,
                    R.id.rbQuadradoMenos,R.id.rbMusicaMenos,R.id.rbZmenos, R.id.rbTrianguloMenos,
                    "Divertido","Introvertido", "Energético", "Descontraído"));

            add(new questao("23",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbZmais,R.id.rbTrianguloMais,
                    R.id.rbQuadradoMenos,R.id.rbMusicaMenos,R.id.rbZmenos, R.id.rbTrianguloMenos,
                    "Bem Integrado","Refinado", "Vigoroso", "Clemente"));

            add(new questao("24",
                    R.id.rbQuadradoMais, R.id.rbTrianguloMais,R.id.rbZmais,R.id.rbMusicaMais,
                    R.id.rbQuadradoMenos,R.id.rbTrianguloMenos,R.id.rbZmenos, R.id.rbMusicaMenos,
                    "Cativante","Contente", "Exigente", "Complacente"));

            add(new questao("25",
                    R.id.rbZmais, R.id.rbMusicaMais,R.id.rbTrianguloMais,R.id.rbQuadradoMais,
                    R.id.rbZmenos,R.id.rbMusicaMenos,R.id.rbTrianguloMenos, R.id.rbQuadradoMenos,
                    "Do Contra","Sistemático", "Cooperador", "Afetivo"));

            add(new questao("26",
                    R.id.rbQuadradoMais, R.id.rbMusicaMais,R.id.rbZmais,R.id.rbTrianguloMais,
                    R.id.rbQuadradoMenos,R.id.rbMusicaMenos,R.id.rbZmenos, R.id.rbTrianguloMenos,
                    "Bem Humorado","Preciso", "Direto", "Equilibrado"));

            add(new questao("27",
                    R.id.rbZmais, R.id.rbTrianguloMais,R.id.rbQuadradoMais,R.id.rbMusicaMais,
                    R.id.rbZmenos,R.id.rbTrianguloMenos,R.id.rbQuadradoMenos, R.id.rbMusicaMenos,
                    "Busca Mudanças","Amigavel", "Alto Astral", "Cuidadoso"));

            add(new questao("28",
                    R.id.rbMusicaMais, R.id.rbZmais,R.id.rbQuadradoMais,R.id.rbTrianguloMais,
                    R.id.rbMusicaMenos,R.id.rbZmenos,R.id.rbQuadradoMenos, R.id.rbTrianguloMenos,
                    "Respeitoso","Inovador", "Otimista", "Colaborador"));

        }
    };


    private void carregarQuestao(){

        if(questoes.size() > 0) {

        questao q = questoes.remove(0);
        numPergunta.setText(q.getPergunta());
        List<String> resposta = q.getRespostas();
        pergunta1.setText(resposta.get(0));
        pergunta2.setText(resposta.get(1));
        pergunta3.setText(resposta.get(2));
        pergunta4.setText(resposta.get(3));

        quadradoMais = q.getQuadradoMais();
         zMais = q.getzMais();
         musicaMais = q.getMusicaMais();
         trianguloMais = q.getTrianguloMais();

        quadradoMenos = q.getQuadradoMenos();
        zMenos = q.getzMenos();
        musicaMenos = q.getMusicaMenos();
        trianguloMenos = q.getTrianguloMenos();


            rgMais.setSelected(false);
            rgMenos.setSelected(false);


        }
        else{ //acabaram as questões
            Intent intent = new Intent(this, FinalizaTeste.class);
            intent.putExtra("pontos", quadradoMaisPontos);
            startActivity(intent);
            finish();
        }
    }



    @Override
    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }
}
