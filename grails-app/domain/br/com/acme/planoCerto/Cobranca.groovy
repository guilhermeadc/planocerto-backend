package br.com.acme.planoCerto

class Cobranca {

	LinhaTelefonica linha
	static hasMany = [itens: ItemCobranca]	

    static constraints = {
    	linha nullable: false
    }

    def total(){    	
    	return itens?.sum{ it.valor } ?: 0
    }
}
