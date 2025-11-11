package org.example.service.impl;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;
import org.example.repository.impl.EquipamentoRepositoryImpl;
import org.example.service.EquipamentoService;

import java.sql.SQLException;
import java.sql.Wrapper;

public class EquipamentoServiceImpl implements EquipamentoService {

    EquipamentoRepositoryImpl equipamentoRepository = new EquipamentoRepositoryImpl();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {

        if (equipamento == null ){
            throw new IllegalArgumentException();
        }

        return equipamentoRepository.criarEquipamento(equipamento);

    }

    @Override
    public Equipamento buscarEquipamentoPorId(long id) throws SQLException {
        return equipamentoRepository.buscarEquipamentoPorID(id);
    }

    @Override
    public boolean deletarEquipamento(String numeroSerie) throws SQLException {
        return equipamentoRepository.deletarEquipamento(numeroSerie);
    }
}
