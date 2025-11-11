package org.example.model;

import java.sql.Date;

public class AcaoCorretiva {

    private int id;
    private int falhaID;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private String responsavel;
    private String descricaoAcao;

    public AcaoCorretiva(int id, int falhaID, Date dataHoraInicio, Date dataHoraFim, String responsavel, String descricaoAcao) {
        this.id = id;
        this.falhaID = falhaID;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoAcao = descricaoAcao;
    }

    public AcaoCorretiva(int falhaID, Date dataHoraInicio, Date dataHoraFim, String responsavel, String descricaoAcao) {
        this.falhaID = falhaID;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoAcao = descricaoAcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFalhaID() {
        return falhaID;
    }

    public void setFalhaID(int falhaID) {
        this.falhaID = falhaID;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }

    @Override
    public String toString() {
        return "AcaoCorretiva{" +
                "id=" + id +
                ", falhaID=" + falhaID +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", responsavel='" + responsavel + '\'' +
                ", descricaoAcao='" + descricaoAcao + '\'' +
                '}';
    }
}
