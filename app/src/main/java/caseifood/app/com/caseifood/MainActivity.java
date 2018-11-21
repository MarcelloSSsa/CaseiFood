package caseifood.app.com.caseifood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView temperatura;
    private TextView descTemp;
    private TextView sugestaoComida;

    private EditText textoBusca;

    private Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatura = findViewById(R.id.idTemperatura);
        descTemp = findViewById(R.id.idDescTemperatura);
        sugestaoComida = findViewById(R.id.idSugestao);
        buscar = findViewById(R.id.idButao);
        textoBusca = findViewById(R.id.idTextoBusca);

        temperatura.setText("Temperatura: ");
        descTemp.setText("No momento está: ");
        sugestaoComida.setText("Uma boa pedida para agora seria: ");

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBusca.getText();
            }
        });

    }

}
