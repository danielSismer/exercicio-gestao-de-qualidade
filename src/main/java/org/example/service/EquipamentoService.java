package org.example.service;

import org.example.model.Equipamento;

import java.sql.SQLException;

public interface EquipamentoService {

    Equipamento criarEquipamento(Equipamento equipamento) throws SQLException;
    Equipamento buscarEquipamentoPorId(long id) throws SQLException;
    boolean deletarEquipamento(String numeroSerie) throws SQLException;



}
