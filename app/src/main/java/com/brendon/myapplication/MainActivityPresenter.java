package com.brendon.myapplication;

import android.view.View;

import com.brendon.myapplication.models.Pokemon;

public class MainActivityPresenter {
    View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }


    public interface View{

        void updatePokemon(Pokemon pokemon);
        void showProgressBar();
        void hideProgressBar();

    }


}
