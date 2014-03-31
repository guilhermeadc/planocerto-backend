package br.com.acme.planoCerto

class Operadora {

	String nome
	static hasMany = [planos: Plano]

    static constraints = {
		nome blank:false, nullable: false  
    }

    public String toString() { nome }
}
