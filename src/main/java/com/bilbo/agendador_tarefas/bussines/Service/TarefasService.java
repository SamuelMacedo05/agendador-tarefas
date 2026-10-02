package com.bilbo.agendador_tarefas.bussines.Service;


import com.bilbo.agendador_tarefas.bussines.mapper.TarefasConverter;
import com.bilbo.agendador_tarefas.bussines.mapper.TarefasUpdateConverter;
import com.bilbo.agendador_tarefas.dto.TarefasDTO;
import com.bilbo.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.bilbo.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.bilbo.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.bilbo.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.bilbo.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConveter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;


    public TarefasDTO gravarTarefa(String toker, TarefasDTO dto) {
        String email = jwtUtil.extrairEmailToken(toker.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConveter.paraTarefasEntity(dto);


        return tarefaConveter.paraTarefasDto(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {


        return tarefaConveter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String toker) {
        String email = jwtUtil.extrairEmailToken(toker.substring(7));
        return tarefaConveter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefasPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente" + id, e.getCause());
        }

    }


    public TarefasDTO alterarStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encrontrada" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConveter.paraTarefasDto(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }


    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encrontrada" + id));
            tarefasUpdateConverter.updateTarefas(dto, entity);
            return tarefaConveter.paraTarefasDto(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + e.getCause());
        }
    }
}
