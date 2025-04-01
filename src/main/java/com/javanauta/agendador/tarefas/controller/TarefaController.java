package com.javanauta.agendador.tarefas.controller;


import com.javanauta.agendador.tarefas.business.TarefaService;
import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> salvarTarefa(@RequestBody TarefaDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefaService.salvarTarefa(token, dto));
    }
}
