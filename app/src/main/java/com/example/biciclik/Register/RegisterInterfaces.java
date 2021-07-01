package com.example.biciclik.Register;

import android.view.View;

import com.example.biciclik.objects.Register1Data;
import com.example.biciclik.objects.Register2Data;
import com.example.biciclik.objects.UserData;

public interface RegisterInterfaces {
    interface activities1{
        void register1();
        void lanzarRegistroF(View view);
    }
    interface activities2{
        void register2();
    }
    interface activities3{

    }
    interface presenters{
        void register1Presenters(UserData userData, Register1Data register1Data);
        void register2Presenters(Register2Data register2Data);
        void devuelvisP();
    }
    interface models{
        void register1Model(UserData userData, Register1Data register1Data, presenters presenter);
        void register2Model(Register2Data register2Data, presenters presenter);
    }
    interface requests{
        void register1Request(presenters presenter);
    }
}
