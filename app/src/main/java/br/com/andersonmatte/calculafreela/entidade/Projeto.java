package br.com.andersonmatte.calculafreela.entidade;

import java.io.Serializable;

import io.realm.RealmObject;

public class Projeto extends RealmObject implements Serializable {

    private String nome;

    private Long dias;

    private Double valorHora;

    private Long cargaHoraria;

    private Long ferias;

    private Double desconto;

    private Double total;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getDias() {
        return dias;
    }

    public void setDias(Long dias) {
        this.dias = dias;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Long getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Long cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Long getFerias() {
        return ferias;
    }

    public void setFerias(Long ferias) {
        this.ferias = ferias;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
