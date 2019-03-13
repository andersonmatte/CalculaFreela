package br.com.andersonmatte.calculafreela.atividade.valorHora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.atividade.HomeActivity;
import br.com.andersonmatte.calculafreela.entidade.ValorHora;
import es.dmoral.toasty.Toasty;

public class ValorHora1Activity extends AppCompatActivity {

    private EditText valorHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora1);
        valorHora = (EditText) findViewById(R.id.valorHora);
        //Remove APP Title Bar.
        getSupportActionBar().hide();
        Button botaoValorHora = (Button) findViewById(R.id.botaoValorHora);
        botaoValorHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    //prepara o objeto para passar para a próxima activity.
                    ValorHora valorHoraObjeto = new ValorHora();
                    valorHoraObjeto.setQuantoPorMes(Double.valueOf(valorHora.getText().toString()));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", valorHoraObjeto);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(ValorHora1Activity.this, ValorHora2Activity.class);
                    intentPerfil.putExtra("valorHora", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o valorHora foi preenchido.
    private Boolean validaForm() {
        if (valorHora.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else if (valorHora.getText().toString() != null){
            Double quantoPorMes = Double.valueOf(valorHora.getText().toString().replaceAll("[$,.]", ""));
            if (quantoPorMes < 100){
                Toasty.error(this, this.getResources().getString(R.string.validaForm2), Toast.LENGTH_SHORT, true).show();
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

}
