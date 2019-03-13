package br.com.andersonmatte.calculafreela.atividade.valorHora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.entidade.ValorHora;

public class ValorHora5Activity extends AppCompatActivity {

    private ValorHora valorHoraRecebido;
    private TextView valorHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora5);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("valorHora");
        if (bundle != null) {
            this.valorHoraRecebido = (ValorHora) bundle.getSerializable("resultado");
            valorHora = (TextView) findViewById(R.id.valorHoraFinal);
            calculaValorHora();
        }
        Button botaoReiniciar = (Button) findViewById(R.id.botaoReiniciar);
        botaoReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prepara o objeto para passar para a próxima activity.
                ValorHora valorHora = new ValorHora();
                Bundle bundle = new Bundle();
                bundle.putSerializable("resultado", valorHora);
                //Chama a próxima Activity já com o objeto populado.
                Intent intentPerfil = new Intent(ValorHora5Activity.this, ValorHora1Activity.class);
                intentPerfil.putExtra("valorHora", bundle);
                startActivity(intentPerfil);
            }
        });
    }

    private void calculaValorHora() {
        Long horasPorSemana = this.valorHoraRecebido.getCaragaHorariaDia() * this.valorHoraRecebido.getDiasSemana();
        Long horasPorAnoDeFolga = horasPorSemana * this.valorHoraRecebido.getSemanasFerias();
        Double valorHoraFinal = this.valorHoraRecebido.getQuantoPorMes() * 12 / (52.1 * horasPorSemana -horasPorAnoDeFolga);
        valorHoraFinal += valorHoraFinal * 20/100;
        Locale ptBr = new Locale("pt", "BR");
        String valorTotalString = NumberFormat.getCurrencyInstance(ptBr).format(valorHoraFinal);
        valorHora.setText(valorTotalString);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ValorHora4Activity.class);
        startActivity(intent);
        this.finish();
    }

}
