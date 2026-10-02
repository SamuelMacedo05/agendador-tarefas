package com.bilbo.agendador_tarefas.infrastructure.client;


import com.bilbo.agendador_tarefas.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient (name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTO BuscarUsuarioPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization")String token);
}
