package com.brendon.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.brendon.myapplication.models.Pokemon;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    String TAG = "MainActivity";

    Context context = this;

    MainActivityPresenter presenter;

    EditText search;
    TextView name, id;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);

        search = findViewById(R.id.et_search);
        name = findViewById(R.id.tv_Name);
        id = findViewById(R.id.tv_ID);
        avatar = findViewById(R.id.img_Avatar);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.getPokemon(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public void updatePokemon(Pokemon pokemon) {
        name.setText(pokemon.getName());
        id.setText(pokemon.getId().toString());
        Glide.with(context).load(pokemon.getSprites().getFrontDefault()).into(avatar);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
