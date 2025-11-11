package org.example.repository;

import org.example.model.Equipamento;

import java.sql.SQLException;

public interface EquipamentoRepository {

    boolean deletarEquipamento(String numeroSerie);

}
