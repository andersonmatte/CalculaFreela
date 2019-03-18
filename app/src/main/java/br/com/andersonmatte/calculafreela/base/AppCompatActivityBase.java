package br.com.andersonmatte.calculafreela.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.atividade.MeusProjetosActivity;
import br.com.andersonmatte.calculafreela.atividade.SobreActivity;
import br.com.andersonmatte.calculafreela.atividade.valorHora.ValorHora1Activity;
import io.realm.Realm;

public class AppCompatActivityBase extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    protected Realm realm;

    private BottomNavigationView navigationView;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //Cria o BottomNavigationView.
        this.navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        this.navigationView.setOnNavigationItemSelectedListener(this);
        this.criaBancoRealm();
    }

    //Define os itens do BottomNavigationView e chama as suas Activities conforme seleção.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_valorHora: {
                Intent intent = new Intent(this, ValorHora1Activity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.menu_meusProjetos: {
                Intent intent = new Intent(this, MeusProjetosActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.menu_sobre: {
                Intent intent = new Intent(this, SobreActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }

    public void criaBancoRealm() {
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

}
