package org.example.repository.impl;

import org.example.model.AcaoCorretiva;
import org.example.repository.AcaoCorretivaRepository;

import java.io.PipedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcaoCorretivaRepositoryImpl implements AcaoCorretivaRepository {

    public void cadastrarAcaoCorretiva(AcaoCorretiva a, Connection conn) throws SQLException {
        String query = """
                INSERT INTO AcaoCorretiva (falhaId, dataHoraInicio, dataHoraFim, responsavel, descricaoAcao) VALUES (?, ?, ?, ?, ?)
                """;
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, a.getFalhaID());
            stmt.setDate(2, a.getDataHoraInicio());
            stmt.setDate(3, a.getDataHoraFim());
            stmt.setString(4, a.getResponsavel());
            stmt.setString(5, a.getDescricaoAcao());
            stmt.executeUpdate();
        }
    }
}
