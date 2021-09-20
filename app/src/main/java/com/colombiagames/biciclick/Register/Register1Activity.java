package com.colombiagames.biciclick.Register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.objects.CompanyData;
import com.colombiagames.biciclick.objects.Register1Data;
import com.colombiagames.biciclick.objects.UserData;
import com.colombiagames.biciclick.utils.KeyPairBoolDataCustom;
import com.colombiagames.biciclick.utils.SpinnerCustom;
import com.colombiagames.biciclick.utils.SpinnerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Register1Activity extends Activity implements RegisterInterfaces.activities1 {
    SpinnerCustom singleSpinnerSearch;
    private static final String TAG = "Registro";
    public Button ButtonIngresar;
    public TextView TextIngresa, txt23, txt24, txt25, txt26, txt27, txt28, txt29, txt30, txt31, txt32;
    Register1Data register1Data;
    UserData userData;
    public EditText InputFirtsName, InputSecondName, InputTextTelefono, InputTextEmailRegistro, InputTextDireccion, InputTextCont;
    RegisterPresenters presenter;
    ArrayList<CompanyData> companies;
    private String id_company;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);
        initObjects();

        //Letra verdana
        Typeface fuente = Typeface.createFromAsset(getAssets(),"fonts/verdana.ttf");
        txt23.setTypeface(fuente);
        txt24.setTypeface(fuente);
        txt25.setTypeface(fuente);
        InputFirtsName.setTypeface(fuente);
        txt26.setTypeface(fuente);
        InputSecondName.setTypeface(fuente);
        txt27.setTypeface(fuente);
        InputTextTelefono.setTypeface(fuente);
        txt28.setTypeface(fuente);
        InputTextEmailRegistro.setTypeface(fuente);
        txt29.setTypeface(fuente);
        txt30.setTypeface(fuente);
        InputTextDireccion.setTypeface(fuente);
        txt31.setTypeface(fuente);
        InputTextCont.setTypeface(fuente);
        ButtonIngresar.setTypeface(fuente);
        txt32.setTypeface(fuente);
        TextIngresa.setTypeface(fuente);

        presenter.getCompanyPresenters();
        ButtonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InputFirtsName.getText().toString().isEmpty()){
                    InputFirtsName.setError("Campo Vacio");
                    InputFirtsName.requestFocus();
                    return;
                }
                if (InputTextTelefono.getText().toString().isEmpty()){
                    InputTextTelefono.setError("Campo Vacio");
                    InputTextTelefono.requestFocus();
                    return;
                }
                if (InputTextEmailRegistro.getText().toString().isEmpty()){
                    InputTextEmailRegistro.setError("Campo vacio");
                    InputTextEmailRegistro.requestFocus();
                    return;
                }
                if (!validarEmail(InputTextEmailRegistro.getText().toString())){
                    InputTextEmailRegistro.setError("Email no válido");
                    return;
                }
                if (singleSpinnerSearch.getSelectedItemsCustom().isEmpty()){
                    Toast.makeText(getBaseContext(), "Campo Empresa Vacio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (InputTextDireccion.getText().toString().isEmpty()){
                    InputTextDireccion.setError("Campo vacio");
                    InputTextDireccion.requestFocus();
                    return;
                }
                if (InputTextCont.getText().toString().isEmpty()){
                    InputTextCont.setError("Campo vacio");
                    InputTextCont.requestFocus();
                    return;
                }
                register1();
                //lanzarRegistroF(null);
            }
        });
        TextIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarLogin(null);
            }
        });
    }
    public void initObjects(){
        ButtonIngresar=(Button)findViewById(R.id.buttonIngresar);
        TextIngresa=findViewById(R.id.textIngresa);
        singleSpinnerSearch = findViewById(R.id.singleItemSelectionSpinner);
        InputFirtsName =findViewById(R.id.inputTextFirtsName);
        InputSecondName=findViewById(R.id.inputTextSecondName);
        InputTextTelefono=findViewById(R.id.inputTextTelefono);
        InputTextEmailRegistro=findViewById(R.id.inputTextEmailRegistro);
        InputTextDireccion=findViewById(R.id.inputTextDireccion);
        InputTextCont=findViewById(R.id.inputTextCont);
        presenter=new RegisterPresenters(this, null, null, null);
        companies = new ArrayList<CompanyData>();
        id_company="";
        txt23=findViewById(R.id.txt23);
        txt24=findViewById(R.id.txt24);
        txt25=findViewById(R.id.txt25);
        txt26=findViewById(R.id.txt26);
        txt27=findViewById(R.id.txt27);
        txt28=findViewById(R.id.txt28);
        txt29=findViewById(R.id.txt29);
        txt30=findViewById(R.id.txt30);
        txt31=findViewById(R.id.txt31);
        txt32=findViewById(R.id.txt32);
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    public void lanzarRegistroF(View view){
        Intent i = new Intent(this, Register2Activity.class );
        startActivity(i);
    }

    @SuppressLint("WrongConstant")
    public void setError(String message) {
        Toast.makeText(getBaseContext(), message, 5000).show();
    }

    @Override
    public void setcompany(ArrayList<CompanyData> company) {
        companies=company;
    }



    @Override
    public void addItemsOnSpinner(List<KeyPairBoolDataCustom> names) {
        singleSpinnerSearch.setSearchEnabled(true);
        singleSpinnerSearch.setSearchHint("Seleccione su Empresa");
        singleSpinnerSearch.setItems(names, new SpinnerListener() {

            @Override
            public void onItemsSelected(KeyPairBoolDataCustom selectedItem) {
                id_company=selectedItem.getId();
            }
            @Override
            public void onClear() {
            }
        });
    }

    public void lanzarLogin(View view){
        Intent i = new Intent(this, LoginActivities.class );
        startActivity(i);
    }

    @Override
    public void register1() {
        userData=new UserData(InputTextEmailRegistro.getText().toString(), InputFirtsName.getText().toString(),
                InputSecondName.getText().toString(),InputTextCont.getText().toString(),InputTextEmailRegistro.getText().toString());
        register1Data= new Register1Data(InputTextTelefono.getText().toString(),
                id_company, InputTextDireccion.getText().toString());
        presenter.register1Presenters(userData,register1Data);
    }
}
