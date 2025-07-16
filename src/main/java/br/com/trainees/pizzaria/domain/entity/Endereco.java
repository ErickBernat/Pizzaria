package br.com.trainees.pizzaria.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_endereco")
public class Endereco {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_endereco")
	Long id;
	String logradouro;
	String cep;
	String bairro;
	String complemento;
	
	
	public Endereco(String logradouro, String cep, String bairro, String complemento) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.bairro = bairro;
		this.complemento = complemento;
	}

	public Endereco() {
		super();
	}

	public Long getId() {
		return id;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
}
