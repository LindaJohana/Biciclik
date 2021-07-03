package com.example.biciclik.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import com.example.biciclik.R;


import java.util.List;

public abstract class SpinnerCustom extends SingleSpinnerSearch  {

    private List<KeyPairBoolDataCustom> items;
    private SpinnerListener listener;
    private String defaultText = "";

    public SpinnerCustom(Context context) {
        super(context);
    }

    public SpinnerCustom(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public SpinnerCustom(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    public void setItemsCustom(List<KeyPairBoolDataCustom> items, SpinnerListener listener) {

        this.items = items;
        this.listener = listener;

        for (KeyPairBoolDataCustom item : items) {
            if (item.isSelected()) {
                defaultText = item.getName();
                break;
            }
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(), R.layout.textview_for_spinner, new String[]{defaultText});
        setAdapter(adapterSpinner);
    }

    public abstract void onItemsSelected(KeyPairBoolDataCustom selectedItem);
}
