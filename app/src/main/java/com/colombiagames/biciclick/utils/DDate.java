package com.colombiagames.biciclick.utils;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DDate {


    public String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }

    public Date returnDate(String date){
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date returnDateToday(String date){
        return new Date();
    }



    public String getOnlyDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }

    public String getOnlyDateWithDate(String date) throws ParseException {
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateformat.format(date1.getTime());
        return datetime;
    }
}

