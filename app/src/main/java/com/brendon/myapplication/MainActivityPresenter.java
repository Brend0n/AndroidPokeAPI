package com.brendon.myapplication;

import android.view.View;

import com.brendon.myapplication.models.Pokemon;
import com.brendon.myapplication.network.NetworkClient;
import com.brendon.myapplication.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter {
    View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    public void getPokemon(String search){
        getObservable(search).subscribeWith(getObserver());
    }

    public Observable<Pokemon> getObservable(String search){
        return NetworkClient.
                getRetrofit().create(NetworkInterface.class)
                .getPokemon(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Pokemon> getObserver(){
        return new DisposableObserver<Pokemon>() {
            @Override
            public void onNext(Pokemon pokemon) {
                view.updatePokemon(pokemon);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.hideProgressBar();
            }
        };
    }

    public interface View{

        void updatePokemon(Pokemon pokemon);
        void showProgressBar();
        void hideProgressBar();

    }


}
