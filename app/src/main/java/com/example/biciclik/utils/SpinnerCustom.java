package com.example.biciclik.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;

import com.example.biciclik.R;


import java.util.ArrayList;
import java.util.List;

public class SpinnerCustom extends SingleSpinnerSearch implements DialogInterface.OnCancelListener {

    private static final String TAG = "SpinnerCustom";
    private List<KeyPairBoolDataCustom> items;
    private List<KeyPairBoolData> items2;
    private SpinnerListener listener;
    MyAdapterCustom adapter;
    private String defaultText = "";
    private String spinnerTitle = "";
    private String emptyTitle = "Not Found!";
    private String searchHint = "Type to search";
    private boolean isSearchEnabled = true;
    private boolean colorseparation = false;

    public SpinnerCustom(Context context) {
        super(context);
    }


    public SpinnerCustom(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        TypedArray a = arg0.obtainStyledAttributes(arg1, R.styleable.SingleSpinnerSearch);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.MultiSpinnerSearch_hintText) {
                spinnerTitle = a.getString(attr);
                defaultText = spinnerTitle;
                break;
            }
        }
        Log.i(TAG, "spinnerTitle: " + spinnerTitle);
        a.recycle();
    }


    public SpinnerCustom(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }


    public void setItems(List<KeyPairBoolDataCustom> items, SpinnerListener listener) {

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

    @Override
    public void onCancel(DialogInterface dialog) {
        // refresh text on spinner
        String spinnerText = null;
        KeyPairBoolDataCustom selectedItem = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isSelected()) {
                selectedItem = items.get(i);
                spinnerText = selectedItem.getName();
                break;
            }
        }
        if (spinnerText == null) {
            spinnerText = defaultText;
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(), R.layout.textview_for_spinner, new String[]{spinnerText});
        setAdapter(adapterSpinner);

        if (adapter != null)
            adapter.notifyDataSetChanged();

        listener.onItemsSelected(selectedItem);
    }

    @Override
    public boolean performClick() {
//        super.performClick();
        builder = new AlertDialog.Builder(getContext());
        builder.setTitle(spinnerTitle);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.alert_dialog_listview_search, null);
        builder.setView(view);

        final ListView listView = view.findViewById(R.id.alertSearchListView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setFastScrollEnabled(false);
        adapter = new SpinnerCustom.MyAdapterCustom(getContext(), items);

        listView.setAdapter(adapter);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isSelected()) {
                listView.setSelection(i);
                break;
            }
        }
        final TextView emptyText = view.findViewById(R.id.empty);
        emptyText.setText(emptyTitle);
        listView.setEmptyView(emptyText);

        EditText editText = view.findViewById(R.id.alertSearchEditText);
        if (isSearchEnabled) {
            editText.setVisibility(VISIBLE);
            editText.setHint(searchHint);
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s.toString());
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        } else {
            editText.setVisibility(GONE);
        }

        builder.setPositiveButton("Clear", (dialog, which) -> {

            for (int i = 0; i < items.size(); i++) {
                items.get(i).setSelected(false);
            }

            ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(getContext(), R.layout.textview_for_spinner, new String[]{defaultText});
            setAdapter(adapterSpinner);

            if (adapter != null)
                adapter.notifyDataSetChanged();

            listener.onClear();
            dialog.dismiss();
        });

        //builder.setOnCancelListener(this);
        ad = builder.show();
        return true;
    }

    public void setEmptyTitle(String emptyTitle) {
        this.emptyTitle = emptyTitle;
    }

    public void setSearchHint(String searchHint) {
        this.searchHint = searchHint;
    }

    public class MyAdapterCustom extends BaseAdapter implements Filterable {

        final LayoutInflater inflater;
        List<KeyPairBoolDataCustom> arrayList;
        List<KeyPairBoolDataCustom> mOriginalValues; // Original Values

        public MyAdapterCustom(Context context, List<KeyPairBoolDataCustom> arrayList) {
            this.arrayList = arrayList;
            this.inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Log.i(TAG, "getView() enter");
            SpinnerCustom.MyAdapterCustom.ViewHolder holder;

            final KeyPairBoolDataCustom data = arrayList.get(position);

            if (convertView == null) {
                holder = new SpinnerCustom.MyAdapterCustom.ViewHolder();
                convertView = inflater.inflate(R.layout.item_listview_single, parent, false);
                holder.textView = convertView.findViewById(R.id.alertTextView);
                convertView.setTag(holder);
            } else {
                holder = (SpinnerCustom.MyAdapterCustom.ViewHolder) convertView.getTag();
            }

            holder.textView.setText(data.getName());

            int color = R.color.white;
            if (colorseparation) {
                final int backgroundColor = (position % 2 == 0) ? R.color.list_even : R.color.list_odd;
                color = backgroundColor;
                convertView.setBackgroundColor(ContextCompat.getColor(getContext(), backgroundColor));
            }

            if (data.isSelected()) {
                holder.textView.setTypeface(null, Typeface.BOLD);
                holder.textView.setTextColor(Color.WHITE);
                convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.list_selected));
            } else {
                holder.textView.setTextColor(Color.DKGRAY);
                holder.textView.setTypeface(null, Typeface.NORMAL);
                convertView.setBackgroundColor(ContextCompat.getColor(getContext(), color));
            }

            convertView.setOnClickListener(v -> {
                String selectedName = arrayList.get(position).getName();
                for (int i = 0; i < items.size(); i++) {
                    items.get(i).setSelected(false);
                    if (items.get(i).getName().equalsIgnoreCase(selectedName)) {
                        items.get(i).setSelected(true);
                    }
                }
                ad.dismiss();
                SpinnerCustom.this.onCancel(ad);
            });

            return convertView;
        }

        @SuppressLint("DefaultLocale")
        @Override
        public Filter getFilter() {
            return new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    arrayList = (List<KeyPairBoolDataCustom>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    List<KeyPairBoolDataCustom> FilteredArrList = new ArrayList<>();
                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<>(arrayList); // saves the original data in mOriginalValues
                    }

                    /*
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            Log.i(TAG, "Filter : " + mOriginalValues.get(i).getName() + " -> " + mOriginalValues.get(i).isSelected());
                            String data = mOriginalValues.get(i).getName();
                            if (data.toLowerCase().contains(constraint.toString())) {
                                FilteredArrList.add(mOriginalValues.get(i));
                            }
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
        }

        private class ViewHolder {
            TextView textView;
        }
    }

}
