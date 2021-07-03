package com.example.biciclik.Register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.biciclik.Login.LoginActivities;
import com.example.biciclik.R;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.UserData;
import com.example.biciclik.utils.KeyPairBoolDataCustom;
import com.example.biciclik.utils.SpinnerCustom;
import com.example.biciclik.utils.SpinnerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Register1Activity extends Activity implements RegisterInterfaces.activities1 {
    SpinnerCustom singleSpinnerSearch;
    private static final String TAG = "Registro";
    public Button ButtonIngresar;
    public TextView TextIngresa;
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
                if (singleSpinnerSearch.getSelectedItems().isEmpty()){
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
        presenter=new RegisterPresenters(this, null);
        companies = new ArrayList<CompanyData>();
        id_company="";
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
        singleSpinnerSearch.setItemsCustom(names, new SpinnerListener() {

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
        userData=new UserData(InputFirtsName.getText().toString()+" "+InputSecondName.getText().toString(),
                InputTextCont.getText().toString(),InputTextEmailRegistro.getText().toString());
        register1Data= new Register1Data(InputTextTelefono.getText().toString(),
                id_company, InputTextDireccion.getText().toString());
        Log.e("Register1", "Register1");
        presenter.register1Presenters(userData,register1Data);
    }
}
