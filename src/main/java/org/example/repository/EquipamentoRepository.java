package org.example.repository;

import org.example.model.Equipamento;

import java.sql.SQLException;

public interface EquipamentoRepository {

    boolean deletarEquipamento(String numeroSerie) throws SQLException;
    Equipamento criarEquipamento(Equipamento equipamento) throws SQLException;
    Equipamento buscarEquipamentoPorID(long id) throws SQLException;

}
