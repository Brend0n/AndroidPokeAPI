package com.brendon.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.brendon.myapplication.models.Pokemon;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    String TAG = "MainActivity";

    Context context = this;

    MainActivityPresenter presenter;

    ProgressBar progressBar;
    LinearLayout lytResult;
    EditText search;
    TextView name, id;
    ImageView avatar, noResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);

        progressBar = findViewById(R.id.progressBar);
        lytResult = findViewById(R.id.linLyt_result);
        search = findViewById(R.id.et_search);
        name = findViewById(R.id.tv_Name);
        id = findViewById(R.id.tv_ID);
        avatar = findViewById(R.id.img_Avatar);
        noResult = findViewById(R.id.img_NoResult);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() >0){
                    Log.i(TAG, "Searching for "+ charSequence.toString());
                    showProgressBar();
                    presenter.getPokemon(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        if(pokemon != null){
            noResult.setVisibility(ImageView.INVISIBLE);
            name.setText(pokemon.getName());
            id.setText(pokemon.getId().toString());
            Glide.with(context).load(pokemon.getSprites().getFrontDefault()).into(avatar);
            lytResult.setVisibility(LinearLayout.VISIBLE);
        }
        else {
            lytResult.setVisibility(LinearLayout.INVISIBLE);
            noResult.setVisibility(ImageView.VISIBLE);
        }

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
