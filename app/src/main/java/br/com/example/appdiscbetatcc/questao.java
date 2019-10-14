package br.com.example.appdiscbetatcc;


import java.util.ArrayList;
import java.util.List;

public class questao {

    private String numpPergunta;
    private List<String> respostas = new ArrayList<>();
    int quadradoMais;
    int zMais;
    int musicaMais;
    int trianguloMais;

    int quadradoMenos;
    int zMenos;
    int musicaMenos;
    int trianguloMenos;

    public questao(String numPergunta, int quadradoMais, int zMais, int musicaMais, int trianguloMais, int quadradoMenos, int zMenos, int musicaMenos, int trianguloMenos, String... respostas) {
        this.numpPergunta = numPergunta;
        this.respostas.add(respostas[0]);
        this.respostas.add(respostas[1]);
        this.respostas.add(respostas[2]);
        this.respostas.add(respostas[3]);

        this.quadradoMais = quadradoMais;
        this.zMais = zMais;
        this.musicaMais = musicaMais;
        this.trianguloMais = trianguloMais;

        this.quadradoMenos = quadradoMenos;
        this.zMenos = zMenos;
        this.musicaMenos = musicaMenos;
        this.trianguloMenos = trianguloMenos;

        //  this.respostaCerta = respostaCerta;
    }

    public String getPergunta() {
        return this.numpPergunta;
    }

    public List<String> getRespostas() {
        return this.respostas;
    }

    public int getQuadradoMais() {
        return this.quadradoMais;
    }

    public int getzMais() {
        return this.zMais;
    }

    public int getMusicaMais() {
        return this.musicaMais;
    }

    public int getTrianguloMais() {
        return this.trianguloMais;
    }

    public int getQuadradoMenos() {
        return this.quadradoMenos;
    }

    public int getzMenos() {
        return this.zMenos;
    }

    public int getMusicaMenos() {
        return this.musicaMenos;
    }

    public int getTrianguloMenos() {
        return this.trianguloMenos;
    }
}
