package caseifood.app.com.caseifood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import caseifood.app.com.presenter.FoodSuggestionPresenter;

public class MainActivity extends AppCompatActivity {

    private TextView temperatura;
    private TextView descTemp;
    private TextView sugestaoComida;

    private EditText textoBusca;

    private Button buscar;
    private FoodSuggestionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new FoodSuggestionPresenter();
        presenter.attachView(listener);

        temperatura = findViewById(R.id.idTemperatura);
        descTemp = findViewById(R.id.idDescTemperatura);
        sugestaoComida = findViewById(R.id.idSugestao);
        buscar = findViewById(R.id.idButao);
        textoBusca = findViewById(R.id.idTextoBusca);



        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBusca.getText();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(listener);

    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.requestFoodSuggestion();
    }

    FoodSuggestionPresenter.Callback listener = new FoodSuggestionPresenter.Callback() {

        @Override
        public void onFoodSuggestionSuccess(String foodName, String temperature, String weatherDescriptionCode) {
            temperatura.setText("Temperatura: " + temperature);
            descTemp.setText("No momento está: " + weatherDescriptionCode);
            sugestaoComida.setText("Uma boa pedida para agora seria: " + foodName);
        }

        @Override
        public void onFoodSuggestionRequestError() {
            Toast.makeText(getApplicationContext(), "Erro ao sugerir", Toast.LENGTH_SHORT).show();
        }

    };

}
