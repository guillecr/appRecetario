package com.guillermo.hito;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Listado extends Activity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    TextView text1;
    TextView text2;
    TextView text3;

    int code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        Intent intent = getIntent();
        code = intent.getIntExtra("code",code);
        iniciador();
    }

    public void findElements(){
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        text3 = findViewById(R.id.txt3);
    }
    public void setImagenes(){
        Resources res = getResources();
        TypedArray imgs = res.obtainTypedArray(R.array.imgFaciles);
        if(code==1) imgs = res.obtainTypedArray(R.array.imgMedio);
        else if(code==2) imgs = res.obtainTypedArray(R.array.imgDificil);

        List<ImageView> imagenLista = new ArrayList<>(Arrays.asList(img1,img2,img3));
        for(int i=0;i<imagenLista.size();i++)
            imagenLista.get(i).setImageDrawable(imgs.getDrawable(i));

        imgs.recycle();
    }

    public void setTexto(){
        List<TextView> txtLista = new ArrayList<>(Arrays.asList(text1,text2,text3));
        Resources res = getResources();
        String[] textos = res.getStringArray(R.array.rFaciles);
        if(code==1) textos = res.getStringArray(R.array.rMedio);
        else if(code==2) textos = res.getStringArray(R.array.rDificil);

        for(int i=0;i<txtLista.size();i++){
            txtLista.get(i).setText(textos[i]);
        }


    }
   public void iniciador(){
        findElements();
        setImagenes();
        setTexto();

    }




    public void toasShow(Object o){
        toasShow(String.valueOf(o));
    }
    public void toasShow(String mensaje){
        Context c = getApplicationContext();
        int i = Toast.LENGTH_LONG;
        Toast.makeText(c,mensaje,i).show();
    }

}
