package com.bilbo.agendador_tarefas.bussines.mapper;

import com.bilbo.agendador_tarefas.dto.TarefasDTO;
import com.bilbo.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);
    TarefasDTO paraTarefasDto (TarefasEntity entity);
}
