package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;
import org.example.repository.RelatorioRepository;

import javax.xml.crypto.Data;
import java.io.PipedInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioRepositoryImpl {


    public List<RelatorioParadaDTO> listRelatorioParadaDTO() throws SQLException {
        List<RelatorioParadaDTO> relatorio = new ArrayList<>();

        String query = """
                SELECT f.id as falhaId, f.tempoParadaHoras as tempoParado, e.id as equipamentoId
                FROM Falha f
                JOIN Equipamento e
                ON e.id = f.equipamentoId
                """;
        try(Connection conn = DatabaseConnection.getConenction(); PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int falhaID = rs.getInt("falhaId");
                int equipamentoID = rs.getInt("tempoParado");
                float tempo = rs.getFloat("equipamentoId");
                relatorio.add(new RelatorioParadaDTO(falhaID, equipamentoID, tempo));
            }
        }
        return relatorio;
    }

    public List<Equipamento> listbuscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate datafim) throws SQLException{

        List<Equipamento> e = new ArrayList<>();
        String query = """
                select e.id as id, e.nome as nome
                from Equipamento e
                JOIN Falha f
                ON e.id = f.equipamentoId
                JOIN AcaoCorretiva ac
                ON f.id = ac.falhaId
                WHERE ac.dataHoraFim or ac.dataHoraInicio BETWEEN ? AND ?;
                """;

        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(dataInicio));
            stmt.setDate(2, Date.valueOf(datafim));

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                e.add(new Equipamento(id, nome));
            }
        }

        return e;
    }

    public List<EquipamentoContagemFalhasDTO> listEquipamentoContagemFalhasDTO(int quant) throws SQLException{

        List<EquipamentoContagemFalhasDTO> equipamentos = new ArrayList<>();
        String query = """
                    SELECT e.id as equipamentoId, count(*) as contagemFalhas
                    FROM Falha f
                    JOIN Equipamento e
                    ON e.id = f.equipamentoId
                    GROUP BY e.id;
                """;
        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                int idEquipamentp = rs.getInt("equipamentoId");
                int quantidadeFalhas = rs.getInt("contagemFalhas");
                equipamentos.add(new EquipamentoContagemFalhasDTO(idEquipamentp, quantidadeFalhas));
            }
        }

        return equipamentos;
    }

    public List<FalhaDetalhadaDTO> listFalhaDetalhadaDTO() throws SQLException{

        List<FalhaDetalhadaDTO> relatorio = new ArrayList<>();
        String query = """
                select e.id as id, e.nome as nome, e.numeroDeSerie as numSerie, e.areaSetor as areaSetor, e.statusOperacional as statusOperacional
                from Falha f
                JOIN Equipamento e
                ON e.id = f.equipamentoId
                """;

        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                 int id = rs.getInt("id");
                 Date dataHoraOcorrencia = rs.getDate("");
                 String descricao;
                 String criticidade;
                 String status;
                 double tempopParadaHoras;
            }
        }

        return relatorio;
    }


}
