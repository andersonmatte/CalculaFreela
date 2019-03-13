package br.com.andersonmatte.calculafreela.atividade.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.atividade.HomeActivity;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;

public class Projeto1Activity extends AppCompatActivityBase {

    private EditText nomeProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto1);
        //Remove APP Title Bar.
        getSupportActionBar().hide();
        nomeProjeto = (EditText) findViewById(R.id.nomeProjeto);
        Button botaoNomeProjeto = (Button) findViewById(R.id.botaoNomeProjeto);
        botaoNomeProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    Projeto projeto = new Projeto();
                    projeto.setNome(nomeProjeto.getText().toString());
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", projeto);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(Projeto1Activity.this, Projeto2Activity.class);
                    intentPerfil.putExtra("projeto", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o nome do projeto foi preenchido.
    private Boolean validaForm() {
        if (nomeProjeto.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.erroValidaForm), Toast.LENGTH_SHORT).show();
            return false;
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
