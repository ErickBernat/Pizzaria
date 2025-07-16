package br.com.trainees.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trainees.pizzaria.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
