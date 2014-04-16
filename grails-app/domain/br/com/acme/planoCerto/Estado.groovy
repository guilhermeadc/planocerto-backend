package br.com.acme.planoCerto

class Estado {

	String nome
	String sigla
	Regiao regiao

	static constraints = {
		nome blank: false, nullable: false
		sigla nullable: false, size: 2..2
		regiao nullable: false
	}
}
