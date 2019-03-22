package com.brendon.myapplication.network;

import com.brendon.myapplication.models.Pokemon;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkInterface {
    @GET("pokemon/{searchName}")
    Observable<Response<Pokemon>> getPokemon(@Path("searchName") String searchName);

    @GET("pokemon")
    Call<Object> listPokemons();
}
