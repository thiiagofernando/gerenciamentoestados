package com.ordemservico.api;

import com.ordemservico.application.commands.CreateEstadoCommand;
import com.ordemservico.application.commands.UpdateEstadoCommand;
import com.ordemservico.application.queries.EstadoQuery;
import com.ordemservico.application.services.EstadoCommandService;
import com.ordemservico.application.services.EstadoQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
@RequiredArgsConstructor
@Tag(name = "Estados", description = "API de gerenciamento de estados")
public class EstadoController {
    private final EstadoCommandService commandService;
    private final EstadoQueryService queryService;

    @PostMapping
    @Operation(summary = "Criar novo estado")
    public ResponseEntity<Integer> create(@RequestBody CreateEstadoCommand command) {
        Integer id = commandService.create(command);
        return ResponseEntity.created(URI.create("/api/estados/" + id)).body(id);
    }

    @PostMapping("createMultiple")
    @Operation(summary = "Criar lista de estados")
    public ResponseEntity<List<Integer>> createMultiple(@RequestBody List<CreateEstadoCommand> commands) {
        var ids = commandService.createMultiple(commands);
        return ResponseEntity.ok(ids);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estado existente")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody UpdateEstadoCommand command) {
        commandService.update(id, command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir estado")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commandService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar todos os estados")
    public ResponseEntity<List<EstadoQuery>> findAll() {
        return ResponseEntity.ok(queryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado por ID")
    public ResponseEntity<EstadoQuery> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(queryService.findById(id));
    }
}