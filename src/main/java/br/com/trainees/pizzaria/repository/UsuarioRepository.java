package br.com.trainees.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trainees.pizzaria.domain.entity.Usuario;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u"
			+ "LEFT JOIN u.")
	Optional<Usuario> buscaUsuarioPorId(@Param("id") Long id);
	
	Optional<Usuario> buscaUsuarioPorCpf(String cpf);
	
	@Query("UPDATE Usuario u SET u.email= :novoEmail "
			+ "WHERE u.id = :id ")
	Optional<Usuario> mudaEmailUsuario(@Param("novoEmail") String email, @Param("id") Long id);
	
	Optional<Usuario> mudaSenhaUsuario();

	Optional<Usuario> buscaUsuarioPorEndereco();
	
}
