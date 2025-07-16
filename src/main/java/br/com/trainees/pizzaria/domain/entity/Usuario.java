package br.com.trainees.pizzaria.domain.entity;

import br.com.trainees.pizzaria.domain.dto.UsuarioCadastroDTO;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_id_usuario")
	private Long id;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	
	@Column(name = "ativo")
	private boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_id_endereco")
	Endereco endereco;
	
	public Usuario() {}
	
	public Usuario(UsuarioCadastroDTO usuarioCadastroDto) {
		setCpf(usuarioCadastroDto.cpf());
		setNome(usuarioCadastroDto.nome());
		setTelefone(usuarioCadastroDto.telefone());
		setEmail(usuarioCadastroDto.email());
		setSenha(usuarioCadastroDto.senha());
		setStatus(true);
	}

	public Usuario(String cpf, String nome, String telefone, String email, String senha, boolean status, Endereco endereco) {
		setCpf(cpf);
		setNome(nome);
		setTelefone(telefone);
		setEmail(email);
		setSenha(senha);
		setEndereco(endereco);
		setStatus(status);
	}

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
