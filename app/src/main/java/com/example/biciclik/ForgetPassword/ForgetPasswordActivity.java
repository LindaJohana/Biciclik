package com.example.biciclik.ForgetPassword;

import android.app.Activity;
import android.os.Bundle;

import com.example.biciclik.R;

public class ForgetPasswordActivity extends Activity implements ForgetPasswordInterfaces.activities{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
    }
}
