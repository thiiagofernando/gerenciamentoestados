package com.ordemservico.application.services;

import com.ordemservico.application.commands.CreateEstadoCommand;
import com.ordemservico.application.commands.UpdateEstadoCommand;
import com.ordemservico.domain.Estado;
import com.ordemservico.infrastructure.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoCommandService {
    private final EstadoRepository repository;

    public Integer create(CreateEstadoCommand command) {
        Estado estado = new Estado();
        estado.setNome(command.getNome());
        estado.setUf(command.getUf());
        estado.setAtivo(command.isAtivo());

        return repository.save(estado).getId();
    }
    public List<Integer> createMultiple(List<CreateEstadoCommand> commands) {
        var estados = new ArrayList<Estado>();
        for (var commad : commands) {
            var estado = new Estado();
            estado.setNome(commad.getNome());
            estado.setUf(commad.getUf());
            estado.setAtivo(commad.isAtivo());
            estados.add(estado);
        }
        var all  = repository.saveAll(estados);
        return all.stream().map(est -> est.getId()).collect(Collectors.toList());
    }
    public void update(Integer id, UpdateEstadoCommand command) {
        Estado estado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        estado.setNome(command.getNome());
        estado.setUf(command.getUf());
        estado.setAtivo(command.isAtivo());

        repository.save(estado);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
