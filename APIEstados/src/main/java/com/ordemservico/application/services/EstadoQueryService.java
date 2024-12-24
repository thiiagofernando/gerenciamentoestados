package com.ordemservico.application.services;

import com.ordemservico.application.queries.EstadoQuery;
import com.ordemservico.domain.Estado;
import com.ordemservico.infrastructure.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoQueryService {
    private final EstadoRepository repository;

    public List<EstadoQuery> findAll() {
        return repository.findAll().stream()
                .map(this::toQuery)
                .collect(Collectors.toList());
    }

    public EstadoQuery findById(Integer id) {
        return repository.findById(id)
                .map(this::toQuery)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
    }

    private EstadoQuery toQuery(Estado estado) {
        EstadoQuery query = new EstadoQuery();
        query.setId(estado.getId());
        query.setNome(estado.getNome());
        query.setUf(estado.getUf());
        query.setAtivo(estado.isAtivo());
        return query;
    }
}