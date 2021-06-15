package com.example.biciclik.Register1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;


import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.biciclik.R;
import com.example.biciclik.Register2.Register2Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register1Activity extends Activity {
    SingleSpinnerSearch singleSpinnerSearch;
    private static final String TAG = "Registro";
    public Button ButtonIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1);
        ButtonIngresar=(Button)findViewById(R.id.buttonIngresar);
        ButtonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarRegistroF(null);
            }
        });
        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.planets_array));
        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }
        singleSpinnerSearch = findViewById(R.id.singleItemSelectionSpinner);
        singleSpinnerSearch.setSearchEnabled(true);
        singleSpinnerSearch.setSearchHint("Select your mood");
        singleSpinnerSearch.setItems(listArray0, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.i(TAG, "Selected Item : " + selectedItem.getName());
            }
            @Override
            public void onClear() {
                Toast.makeText(Register1Activity.this, "Cleared Selected Item", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void lanzarRegistroF(View view){
        Intent i = new Intent(this, Register2Activity.class );
        startActivity(i);
    }
}
