package org.example.repository;

import org.example.model.AcaoCorretiva;
import org.example.service.AcaoCorretivaService;

import java.sql.Connection;
import java.sql.SQLException;

public interface AcaoCorretivaRepository {

    void cadastrarAcaoCorretiva(AcaoCorretiva a, Connection conn) throws SQLException;

}