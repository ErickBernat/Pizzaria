package br.com.trainees.pizzaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.trainees.pizzaria.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u LEFT JOIN u.endereco e WHERE u.id = :id")
	Optional<Usuario> buscaUsuarioPorId(@Param("id") Long id);

	
	@Query("SELECT u FROM Usuario u " +
		     "LEFT JOIN FETCH u.endereco " +
		     "WHERE u.cpf = :cpf")
	Optional<Usuario> buscaUsuarioPorCpf(@Param("cpf") String cpf);
	
	@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.endereco WHERE u.email = :email")
	Optional<Usuario> buscaUsuarioPorEmail(@Param("email") String email);
	
    @Modifying
    @Transactional
	@Query("UPDATE Usuario u SET u.email= :novoEmail "
			+ "WHERE u.id = :id ")
	void mudaEmailUsuario(@Param("novoEmail") String email, @Param("id") Long id);
	
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.senha = :novaSenha " + 
            "WHERE u.id = :id")
    void mudaSenhaUsuario(@Param("novaSenha") String senha, @Param("id") Long id);


    @Query("SELECT u FROM Usuario u " +
    	    "JOIN u.endereco e " +
    	    "WHERE e.bairro = :bairro")
    List<Usuario> buscaUsuarioPorBairro(@Param("bairro") String bairro);
    

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.status = false " 
            + "WHERE u.id = :id")
    void deixaUsuarioInativo(@Param("id") Long id);	
}
