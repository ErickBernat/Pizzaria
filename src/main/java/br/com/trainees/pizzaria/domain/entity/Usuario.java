package br.com.trainees.pizzaria.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {
	
	@Id
	@Column
	private Long id;
}
