package com.am.butterknife;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    /*@BindView(R.id.btnButton)
    Button button;*/

    /*@BindView(R.id.tbtnToggle)
    ToggleButton toggleButton;*/

    //ENLAZAMOS CADA VIEW CON SU RESPECTIVO TIPO DE CONTROL
    @BindView(R.id.rbtnRadio1)
    RadioButton radioButton1;

    @BindView(R.id.rbtnRadio2)
    RadioButton radioButton2;

    @BindView(R.id.swtSwitch)
    Switch switch1;

    //UN CHECKBOX NATURAL SOLO PARA PRUEBAS
    CheckBox checkBox;

    //METODO QUE SIEMPRE SE EJECUTA AL LLAMAR A UN ACIIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ENLAZAMON ESTA INSTACIA DE BUTTERKNIFE CON EL ACTIVITY
        ButterKnife.bind(this);

        //USAMOS FINDVIEWBYID forma clasica PARA ENCONTRAR EL VIEW EN EL LAYOUT
        checkBox = (CheckBox) this.findViewById(R.id.chkCheckBox);

        //LE ASIGNAMOS UN EVENTO ON CHECKEDCHAGE
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked())
                    Toast.makeText(MainActivity.this, "CHECKBOX CHECKED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ENLACZAMOS TODOS LOS VIEWS A SU RESPECTIVO EVENTO
    @OnClick(R.id.btnButton)
    public void onClick() {
        Toast.makeText(this, "BUTTON CLICKED", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.tbtnToggle)
    public void onCheckChanged(ToggleButton tbtn) {
        if (tbtn.isChecked()) {
            Toast.makeText(this, "TOGGLE ON", Toast.LENGTH_SHORT).show();
        }
        else if(!tbtn.isChecked()){
            Snackbar.make(tbtn, "TOGGLE OFF", Snackbar.LENGTH_SHORT).show();
        }
    }

    @OnCheckedChanged({R.id.rbtnRadio1, R.id.rbtnRadio2})
    public void onRadioChanged(){
        if(radioButton1.isChecked()){
            Snackbar.make(radioButton1, "RADIO 1 CHECKED", Snackbar.LENGTH_SHORT).show();
        }
        else if(radioButton2.isChecked()){
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setTitle("Mensaje");
            ad.setMessage("Radio 2 checked");
            ad.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }

    }

    @OnCheckedChanged(R.id.swtSwitch)
    public void onSwitch(Switch swi){
        if(swi.isChecked())
            Snackbar.make(swi, "SWITCH ON", Snackbar.LENGTH_SHORT).show();
        else if(!swi.isChecked()){
            Snackbar.make(swi, "SWITCH OFF", Snackbar.LENGTH_SHORT).show();
        }
    }

    @OnLongClick(R.id.btnButton)
    public boolean OnLongClick(Button btn){
        Snackbar.make(btn, "BUTTON LONG CLICKED", Snackbar.LENGTH_SHORT).show();
        return true;
    }
}

