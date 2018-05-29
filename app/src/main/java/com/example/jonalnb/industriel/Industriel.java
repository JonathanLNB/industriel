package com.example.jonalnb.industriel;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Industriel extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private  ZXingScannerView escaner;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industriel);
        ButterKnife.bind(this);
    }
    protected void onPause() {
        super.onPause();
        if(escaner!=null)
            escaner.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(escaner!=null)
            escaner.startCamera();
    }

    public void handleResult(Result result) {
        if(result!=null) {
            Intent maquinas = new Intent(getBaseContext(), Maquinas.class);
            Bundle datos = new Bundle();
            datos.putString("ID", result.getText());
            maquinas.putExtras(datos);
            startActivity(maquinas);
        }
        escaner.resumeCameraPreview(this);
    }
    @OnClick({R.id.bCamara, R.id.vInfo})
    public void leer(){
        escaner = new ZXingScannerView(this);
        setContentView(escaner);
        escaner.setResultHandler(this);
        escaner.startCamera();
    }
}
