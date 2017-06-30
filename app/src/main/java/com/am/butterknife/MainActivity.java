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

    //HABIAMOS ENLAZADO ESTOS VIEWS PERO DESPUES NOS DIMOS CUENTA QUE NO ERA NECESARIO 
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

        //ENLAZAMOS ESTA INSTACIA DE BUTTERKNIFE CON EL ACTIVITY
        ButterKnife.bind(this);

        //USAMOS FINDVIEWBYID (forma clasica) PARA ENCONTRAR EL VIEW EN EL LAYOUT
        checkBox = (CheckBox) this.findViewById(R.id.chkCheckBox);

        //LE ASIGNAMOS UN EVENTO ON CHECKECHANGED
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //SI ESTA CHECKADO MOSTRAMOS UN TOAST
                if (checkBox.isChecked())
                    Toast.makeText(MainActivity.this, "CHECKBOX CHECKED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ENLACZAMOS TODOS LOS VIEWS A SU RESPECTIVO EVENTO
    @OnClick(R.id.btnButton)
    public void onClick() {
        //EN ESTE CASO ES EL EVENTO CLICK EN EL CUAL MOSTRAMOS UN TOAST
        Toast.makeText(this, "BUTTON CLICKED", Toast.LENGTH_SHORT).show();
    }

    //AQUI USAMOS EL EVENTO CHECKCHANGED QUE MONITOREA CADA VEZ QUE ESTADO 'CHECKED'
    //DEL TOGGLE BUTTON CAMBIA
    @OnCheckedChanged(R.id.tbtnToggle)
    public void onCheckChanged(ToggleButton tbtn) {//PONEMOS COMO PARAMETRO EL TOGGLEBUTTON ASIGNADO
        //CONDICIONAL SI ESTA CHECKADO
        if (tbtn.isChecked()) {
            Toast.makeText(this, "TOGGLE ON", Toast.LENGTH_SHORT).show();
        }
        //CONDICIONAL SI NO LO ESTA
        else if(!tbtn.isChecked()){
            Snackbar.make(tbtn, "TOGGLE OFF", Snackbar.LENGTH_SHORT).show();
        }
    }

    //ASIGNAMOS OTRO EVENT CHECKCHANGED ESTA VEZ A DOS RADIO BUTTONS
    @OnCheckedChanged({R.id.rbtnRadio1, R.id.rbtnRadio2})
    public void onRadioChanged(){
        //SI EL PRIMER RADIO BUTTON ESTA CHECKADO MOSTRAMOS UN SNACKBAR
        if(radioButton1.isChecked()){
            Snackbar.make(radioButton1, "RADIO 1 CHECKED", Snackbar.LENGTH_SHORT).show();
        }
        //SI EL SEGUNDO ESTA CHECKADO UN ALERTDIALOG
        else if(radioButton2.isChecked()){
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setTitle("Mensaje");
            ad.setMessage("Radio 2 checked");
            //ASIGNAMOS UN BOTON NEUTRAL
            ad.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                //Y UN EVENTO ONCLICK PARA EL MISMO
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }

    }

    //OTRO EVENTO CHECKCHANGED PARA EL SWITCH
    @OnCheckedChanged(R.id.swtSwitch)
    public void onSwitch(Switch swi){
        //REVISAMOS SI ESTA CHECKADO O NO
        if(swi.isChecked())
            Snackbar.make(swi, "SWITCH ON", Snackbar.LENGTH_SHORT).show();
        else if(!swi.isChecked()){
            Snackbar.make(swi, "SWITCH OFF", Snackbar.LENGTH_SHORT).show();
        }
    }

    //FINALMENTE UN EVENTO LONGCLICK PARA EL BOTON PRINCIPAL, LO QUE SIGNIFICA QUE DESPUES DE UN
    //CLICK LARGO SE MOSTRARA UN SNACKBAR, TOMAR EN CUENTA QUE ESTE METODO DEBE SER BOOLEANO
    @OnLongClick(R.id.btnButton)
    public boolean OnLongClick(Button btn){
        Snackbar.make(btn, "BUTTON LONG CLICKED", Snackbar.LENGTH_SHORT).show();
        return true;
    }
}

