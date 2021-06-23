package com.example.biciclik.DrawerMain;

public interface DrawerInterfaces {
    interface activities{
        void logOut();
    }
    interface presenters{
        void logOutPresenters();
    }
    interface models{
        void logOutModels(presenters presenter);
    }
    interface requests{

    }
}
