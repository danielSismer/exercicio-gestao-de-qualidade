package org.example.service.impl;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.repository.impl.RelatorioRepositoryImpl;
import org.example.service.RelatorioService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RelatorioServiceImpl implements RelatorioService {

    RelatorioRepositoryImpl relatorioRepository = new RelatorioRepositoryImpl();

    @Override
    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return relatorioRepository.listRelatorioParadaDTO();
    }

    @Override
    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate datafim) throws SQLException {
        return relatorioRepository.listbuscarEquipamentosSemFalhasPorPeriodo(dataInicio, datafim);
    }

    @Override
    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(long falhaId) throws SQLException {
        return relatorioRepository.listFalhaDetalhadaDTO();
    }

    @Override
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException {
        return relatorioRepository.listEquipamentoContagemFalhasDTO(contagemMinimaFalhas);
    }
}
