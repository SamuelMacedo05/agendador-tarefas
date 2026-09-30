package com.bilbo.agendador_tarefas.bussines.mapper;

import com.bilbo.agendador_tarefas.dto.TarefasDTO;
import com.bilbo.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    @Mapping(source = "dataEvento", target = "dataEvento")

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDto (TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> dtos);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> entities);



}
