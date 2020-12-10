package com.guillermo.hito;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Listado extends Activity {

    ImageView img1;
    ImageView img2;
    ImageView img3;

    int code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        Intent intent = getIntent();
        code = intent.getIntExtra("code",code);

        toasShow(code);
        iniciador();
    }

    public void findImagenes(){
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
    }
    public void setImagenes(){
        int draw1 = R.drawable.img_gofres;
        int draw2 = R.drawable.img_arrozleche;
        int draw3 = R.drawable.img_tortitas;

        if(code==1){
            draw1 = R.drawable.img_hamburguesa;
            draw2 = R.drawable.img_berenjenas;
            draw3 = R.drawable.img_cremacalabacin;
        }

        List<ImageView> imagenLista = new ArrayList<>(Arrays.asList(img1,img2,img3));
        List<Integer> drawList = new ArrayList<>(Arrays.asList(draw1,draw2,draw3));

        for(int i=0;i<imagenLista.size();i++){
            imagenLista.get(i).setImageResource(drawList.get(i));
        }

    }
   public void iniciador(){
        findImagenes();
        setImagenes();

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
