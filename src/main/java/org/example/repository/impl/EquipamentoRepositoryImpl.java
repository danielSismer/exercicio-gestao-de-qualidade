package org.example.repository.impl;

import org.example.config.DatabaseConnection;
import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.*;

public class EquipamentoRepositoryImpl implements EquipamentoRepository{

    public Equipamento criarEquipamento(Equipamento e) throws SQLException {

        String query = """
                INSERT INTO Equipamento (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES (?, ?, ?, ?)
                """;
        try(Connection conn = DatabaseConnection.getConenction();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, e.getNome());
                stmt.setString(2, e.getNumeroDeSerie());
                stmt.setString(3, e.getAreaSetor());
                stmt.setString(4, e.getStatusOperacional().toUpperCase());
                stmt.executeUpdate();

            System.out.println("Equipamento Registrado com Sucesso!!!");

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                e.setId(rs.getLong(1));
            }
        }

        return e;
    }

    public Equipamento buscarEquipamentoPorID (long id) throws SQLException{
        String query = """
                SELECT id, nome, numeroDeSerie, areaSetor, statusOperacional
                FROM Equipamento WHERE id = ?
                """;
        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Equipamento(rs.getLong("id"), rs.getString("nome"), rs.getString("numeroDeSerie"), rs.getString("areaSetor"), rs.getString("statusOperacional"));
            }
        }

        return null;
    }


    @Override
    public boolean deletarEquipamento(String numeroSerie) {
        String query = """
                DELETE FROM Equipamento WHERE numeroDeSerie = ?
                """;
        try(Connection conn = DatabaseConnection.getConenction();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, numeroSerie);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e){
            return false;
        }
    }


    public void setarStatusEquipamento(long id, Connection conn, String status) throws SQLException{
        String query = """
                UPDATE Equipamento
                SET status = ?
                WHERE id = ?
                """;
        try(
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, status);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }
}
