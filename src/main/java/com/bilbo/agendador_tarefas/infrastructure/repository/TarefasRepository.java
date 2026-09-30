package com.bilbo.agendador_tarefas.infrastructure.repository;

import com.bilbo.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
