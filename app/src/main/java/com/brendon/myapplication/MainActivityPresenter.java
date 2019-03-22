package com.brendon.myapplication;

import android.util.Log;
import android.view.View;

import com.brendon.myapplication.models.Pokemon;
import com.brendon.myapplication.network.NetworkClient;
import com.brendon.myapplication.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivityPresenter {
    String TAG = "MainActivityPresenter";

    View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    public void getPokemon(String search){
        getObservable(search).subscribeWith(getObserver());
    }

    public Observable<Response<Pokemon>> getObservable(String search){
        return NetworkClient.
                getRetrofit().create(NetworkInterface.class)
                .getPokemon(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Response<Pokemon>> getObserver(){
        return new DisposableObserver<Response<Pokemon>>() {
            @Override
            public void onNext(Response<Pokemon> pokemonResponse) {
//                Log.i(TAG, "ERROR: "+pokemonResponse.getError());
                if(pokemonResponse.code() == 404){
                    view.updatePokemon(null);
                }else{
                    view.updatePokemon(pokemonResponse.body());
                }
                view.hideProgressBar();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
                view.hideProgressBar();

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
