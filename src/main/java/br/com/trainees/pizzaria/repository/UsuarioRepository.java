package br.com.trainees.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import br.com.trainees.pizzaria.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u "
			+ "LEFT JOIN u.endereco e "
			+ "WHERE u.id = :id")
	Optional<Usuario> buscaUsuarioPorId(@Param("id") Long id);

	
	@Query("SELECT u FROM Usuario u " +
		     "LEFT JOIN FETCH u.endereco " +
		     "WHERE u.cpf = :cpf")
	Optional<Usuario> buscaUsuarioPorCpf(@Param("cpf") String cpf);
	
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
    
    @Query("SELECT CASE WHEN COUNT(u.id) > 0 THEN true ELSE false END " //
            + "FROM Usuario u " 
            + "WHERE u.id != :id " 
            + "AND u.email = :email")
    boolean existeOutroUsuarioComMesmoEmail(@Param("id") Long id, @Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u.id) > 0 THEN true ELSE false END " //
            + "FROM Usuario u " 
            + "WHERE u.id != :id " 
            + "AND u.cpf = :cpf")
    boolean existeOutroUsuarioComMesmoCpf(@Param("id") Long id, @Param("cpf") String email);
}
