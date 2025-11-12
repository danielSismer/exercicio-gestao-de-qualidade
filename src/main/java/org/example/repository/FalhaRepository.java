package org.example.repository;

import org.example.model.Falha;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface FalhaRepository {

    Falha registrarNovaFalha(Falha falha, Connection conn) throws SQLException;
    List<Falha> buscarFalhasCriticasAbertas() throws SQLException;


}
