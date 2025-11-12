package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.model.Falha;
import org.example.repository.FalhaRepository;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/*
* CREATE TABLE IF NOT EXISTS Falha (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    equipamentoId BIGINT NOT NULL,
    dataHoraOcorrencia DATETIME NOT NULL,
    descricao TEXT NOT NULL,
    criticidade VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    tempoParadaHoras DECIMAL(10, 2) DEFAULT 0.00,

* */


public class FalhaRepositoryImpl implements FalhaRepository {

    public Falha registrarNovaFalha(Falha falha, Connection conn) throws SQLException {
        String query = """
                INSERT INTO Falha (
                equipamento_id, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras
                ) VALUES (?, ?, ?, ?, ?, ?)
                """;
        try(
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, falha.getEquipamentoID());
            stmt.setDate(2, falha.getDataHoraOcorrencia());
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setDouble(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();
        }
        return falha;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        List<Falha> falhas = new ArrayList<>();
        String query = """
                SELECT id, equipamento_id, dataHoraOcorrencia, descricao, criticidade, status, tempoParadaHoras
                FROM Falha
                WHERE criticidade = 'CRITICA' AND status = 'ABERTA'
                """;
        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int idFalha = rs.getInt("id");
                int equipamentoId = rs.getInt("equipamento_id");
                Date dataOcorrencia = rs.getDate("dataHoraOcorrencia");
                String descricao = rs.getString("descricao");
                String criticidade = rs.getString("criticidade");
                String status = rs.getString("status");
                double tempoParadaHoras = rs.getDouble("tempoParadaHoras");
                falhas.add(new Falha(idFalha, equipamentoId, dataOcorrencia, descricao,criticidade, status, tempoParadaHoras));
            }
        }
        return falhas;
    }


    public void atualizarStatusFalha(long id, Connection conn){
        String query = """
                UPFATE Falha SET status = ? WHERE id = ?
                """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, "RESOLVIDA");
            stmt.setLong(2, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Falha buscarFalhaPorID(int id) {
        String query = """
                SELECT * FROM Falha WHERE id = ?
                """;
        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int idv2 = rs.getInt("id");
                int equipamentoId = rs.getInt("equipamentoId");
                Date dataOcorrencia = rs.getDate("dataHoraOcorrencia");
                String descricao = rs.getString("descricao");
                String criticidade = rs.getString("criticidade");
                String status = rs.getString("status");
                double tempo = rs.getDouble("tempoParadaHoras");
                return new Falha(idv2, equipamentoId, dataOcorrencia, descricao, criticidade, status, tempo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
