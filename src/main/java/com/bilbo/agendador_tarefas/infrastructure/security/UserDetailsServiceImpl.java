package com.bilbo.agendador_tarefas.infrastructure.security;



import com.bilbo.agendador_tarefas.dto.UsuarioDTO;
import com.bilbo.agendador_tarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  {

    @Autowired
    private UsuarioClient client;



    public UserDetails carregarDadosUsuario (String email, String token){

        UsuarioDTO usuarioDTO = client.BuscarUsuarioPorEmail(email,token);
        return User
                .withUsername(usuarioDTO.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDTO.getPassword())
                .build();// Define a senha do usuário


    }
}
