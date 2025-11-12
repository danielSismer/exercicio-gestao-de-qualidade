package org.example.service.impl;

import org.example.config.DatabaseConnection;
import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.impl.AcaoCorretivaRepositoryImpl;
import org.example.repository.impl.EquipamentoRepositoryImpl;
import org.example.repository.impl.FalhaRepositoryImpl;
import org.example.service.AcaoCorretivaService;
import java.sql.Connection;
import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService {

    AcaoCorretivaRepositoryImpl acaoCorretivaRepository = new AcaoCorretivaRepositoryImpl();
    FalhaRepositoryImpl falhaRepository = new FalhaRepositoryImpl();
    EquipamentoRepositoryImpl equipamentoRepository = new EquipamentoRepositoryImpl();

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {

        try (Connection conn = DatabaseConnection.getConenction()) {
            conn.setAutoCommit(false);
            try {
                Falha falha = falhaRepository.buscarFalhaPorID(acao.getFalhaID());
                acaoCorretivaRepository.cadastrarAcaoCorretiva(acao, conn);
                falhaRepository.atualizarStatusFalha(acao.getFalhaID(), conn);
                if(falha.getStatus().equalsIgnoreCase("critica"));{
                    equipamentoRepository.setarStatusEquipamento(falha.getEquipamentoID(), conn, "OPERACIONAL");
                }
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {

        }
        return null;

    }
}

