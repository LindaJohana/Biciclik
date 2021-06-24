package com.example.biciclik.TakeBici;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerListener;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.biciclik.R;
import com.example.biciclik.Register.Register1Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TakeBici2Fragment extends Fragment {
    SingleSpinnerSearch singleSpinnerSearch;
    private static final String TAG = "TakeBici2";
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.take_bici2, container, false);

        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.planets_array));
        final List<KeyPairBoolData> listArray0 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }
        singleSpinnerSearch = view.findViewById(R.id.singleItemSelectionSpinner);
        singleSpinnerSearch.setSearchEnabled(true);
        singleSpinnerSearch.setSearchHint("Selecciona la ubicacion");
        singleSpinnerSearch.setItems(listArray0, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.i(TAG, "Selected Item : " + selectedItem.getName());
            }
            @Override
            public void onClear() {
                Toast.makeText(getContext(), "Limpiar item", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
