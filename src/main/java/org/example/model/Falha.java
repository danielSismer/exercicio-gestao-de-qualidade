package org.example.model;

import java.sql.Date;

public class Falha {

    private int id;
    private int equipamentoID;
    private Date dataHoraOcorrencia;
    private String descricao;
    private String criticidade;
    private String status;
    private double tempoParadaHoras;

    public Falha(int id, int equipamentoID, Date dataHoraOcorrencia, String descricao, String criticidade, String status, double tempoParadaHoras) {
        this.id = id;
        this.equipamentoID = equipamentoID;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Falha(int equipamentoID, Date dataHoraOcorrencia, String descricao, String criticidade, String status, double tempoParadaHoras) {
        this.equipamentoID = equipamentoID;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipamentoID() {
        return equipamentoID;
    }

    public void setEquipamentoID(int equipamentoID) {
        this.equipamentoID = equipamentoID;
    }

    public Date getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(double tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }

    @Override
    public String toString() {
        return "Falha{" +
                "id=" + id +
                ", equipamentoID=" + equipamentoID +
                ", dataHoraOcorrencia=" + dataHoraOcorrencia +
                ", descricao='" + descricao + '\'' +
                ", criticidade='" + criticidade + '\'' +
                ", status='" + status + '\'' +
                ", tempoParadaHoras=" + tempoParadaHoras +
                '}';
    }
}
