package br.com.andersonmatte.calculafreela.entidade;

import java.io.Serializable;

import io.realm.RealmObject;

public class ValorHora extends RealmObject implements Serializable {

    private Double quantoPorMes;

    private Long caragaHorariaDia;

    private Long diasSemana;

    private Long semanasFerias;

    public Double getQuantoPorMes() {
        return quantoPorMes;
    }

    public void setQuantoPorMes(Double quantoPorMes) {
        this.quantoPorMes = quantoPorMes;
    }

    public Long getCaragaHorariaDia() {
        return caragaHorariaDia;
    }

    public void setCaragaHorariaDia(Long caragaHorariaDia) {
        this.caragaHorariaDia = caragaHorariaDia;
    }

    public Long getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(Long diasSemana) {
        this.diasSemana = diasSemana;
    }

    public Long getSemanasFerias() {
        return semanasFerias;
    }

    public void setSemanasFerias(Long semanasFerias) {
        this.semanasFerias = semanasFerias;
    }

}
