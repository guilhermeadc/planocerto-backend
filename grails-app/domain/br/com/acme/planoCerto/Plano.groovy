package br.com.acme.planoCerto

class Plano {

	enum TipoPlano{
		PREPAGO, POSPAGO
	}

	String nome
	TipoPlano tipoPlano	
	static belongsTo = [operadora: Operadora]

    static constraints = {
    	nome blank: false, nullable: false
    	tipoPlano nullable: false
    	operadora nullable: false
    }
}
