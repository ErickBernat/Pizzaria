package br.com.trainees.pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trainees.pizzaria.domain.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
