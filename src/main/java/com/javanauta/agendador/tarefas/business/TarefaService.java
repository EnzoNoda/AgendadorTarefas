package com.javanauta.agendador.tarefas.business;

import com.javanauta.agendador.tarefas.business.dto.TarefaDTO;
import com.javanauta.agendador.tarefas.business.mapper.TarefaConverter;
import com.javanauta.agendador.tarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.agendador.tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendador.tarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.agendador.tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO salvarTarefa(String token, TarefaDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return  tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefaConverter.paraListaTarefaDto(tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefaEntity> listaTarefas = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefaDto(listaTarefas);
    }
}
