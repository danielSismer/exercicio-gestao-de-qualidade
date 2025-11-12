package org.example.service.impl;

import org.example.config.DatabaseConnection;
import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;
import org.example.repository.impl.EquipamentoRepositoryImpl;
import org.example.repository.impl.FalhaRepositoryImpl;
import org.example.service.FalhaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService {

    FalhaRepositoryImpl falhaRepository = new FalhaRepositoryImpl();
    EquipamentoRepositoryImpl equipamentoRepository = new EquipamentoRepositoryImpl();

    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {

        Equipamento e = equipamentoRepository.buscarEquipamentoPorID(falha.getEquipamentoID());
        Falha falhaRecebida;
        try {
            if (e.getId() == falha.getEquipamentoID()) {
                if (falha.getCriticidade().equalsIgnoreCase("CRITICA")) {
                    try (Connection conn = DatabaseConnection.getConenction()) {
                        conn.setAutoCommit(false);
                        try {
                            falhaRecebida = falhaRepository.registrarNovaFalha(falha,conn);
                            equipamentoRepository.setarStatusEquipamento(e.getId(),conn, "EM_MANUTENCAO");
                            conn.commit();
                            System.out.println("Transferência realizada com sucesso!");
                            return falhaRecebida;
                        } catch (SQLException ee) {
                            ee.printStackTrace();
                            conn.rollback();
                            System.out.println("Transferência revertida (rollback).");
                        } finally {
                            conn.setAutoCommit(true);
                        }
                    }
                }
            } else {
                System.out.println("Não foi possível encontrar o equipamento com esse ID");
            }
        } catch (RuntimeException r) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        return falhaRepository.buscarFalhasCriticasAbertas();
    }
}
