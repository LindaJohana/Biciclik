package com.example.biciclik.DrawerMain;

public interface DrawerInterfaces {
    interface activities{
        void logOut();
    }
    interface activitiesHeader{

    }
    interface presenters{
        void logOutPresenters();
        void profileHeaderPresenter();
    }
    interface models{
        void logOutModels(presenters presenter);
        void profileHeaderModel(presenters presenter);
    }
}
