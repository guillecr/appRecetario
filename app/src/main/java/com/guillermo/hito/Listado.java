package com.guillermo.hito;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Listado extends Activity {

    // Elementos de la pantalla
    TextView titulo;

    CardView card1;
    CardView card2;
    CardView card3;
    List<CardView> cardLista;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    List<ImageView> imgLista;

    TextView text1;
    TextView text2;
    TextView text3;
    List<TextView> txtLista;

    // Contenido de la pantalla
    TypedArray imgs;
    String[] textos;

    int code = 1; // Valor por defecto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        code = getIntent().getIntExtra("code",code);
        iniciador();
    }

    public void iniciador(){
        findElements();
        getElement();
        setElementos();
    }

    public void findElements(){
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        text3 = findViewById(R.id.txt3);
        titulo = findViewById(R.id.lblTituLista);
        imgLista = new ArrayList<>(Arrays.asList(img1,img2,img3));
        txtLista = new ArrayList<>(Arrays.asList(text1,text2,text3));
        cardLista = new ArrayList<>(Arrays.asList(card1,card2,card3));

    }

    public void getElement(){
        Resources res = getResources();
        int resTitulo = R.string.facil;
        int resImg = R.array.imgFaciles;
        int resTextos = R.array.listaFaciles;

        if(code==1) {
            resTitulo = R.string.medio;
            resImg = R.array.imgMedio;
            resTextos = R.array.listaMedio;
        }
        else if(code==2) {
            resTitulo = R.string.dificil;
            resImg = R.array.imgDificil;
            resTextos = R.array.listaDificil;
        }

        titulo.setText(res.getString(resTitulo));
        imgs = res.obtainTypedArray(resImg);
        textos = res.getStringArray(resTextos);
    }

    public void setElementos(){
        for(int i=0;i<cardLista.size();i++) {
            txtLista.get(i).setText(textos[i]);
            imgLista.get(i).setImageDrawable(imgs.getDrawable(i));
            cardLista.get(i).setOnClickListener(this::verReceta);
        }
        imgs.recycle();
    }

    public void verReceta(View w){
        Intent intent = new Intent(this,Receta.class);
        Integer recetaCode = 0;
        for(int i = 0; i<imgLista.size();i++)
            if(w.getId()==cardLista.get(i).getId()) recetaCode=i;

        intent.putExtra("listaCode",code);
        intent.putExtra("recetaCode",recetaCode);
        startActivity(intent);
    }
}
