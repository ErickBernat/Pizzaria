package br.com.trainees.pizzaria.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import br.com.trainees.pizzaria.domain.dto.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk_id_usuario")
	private Long id;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	private boolean ativo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_id_endereco")
	Endereco endereco;
	
	public Usuario() {
		
	}
	
	public Usuario(UsuarioDTO dto, Endereco endereco) {
		this.id = dto.id();
		this.cpf = dto.cpf();
		this.nome = dto.nome();
		this.telefone = dto.telefone();
		this.email = dto.email();
		this.senha = dto.senha();
		this.ativo = dto.ativo();
	}
	
	public Usuario(String cpf, String nome, String telefone, String email, String senha, Endereco endereco, Boolean status) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.ativo = status;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean status) {
		this.ativo = status;
	}
	
	
	
}
