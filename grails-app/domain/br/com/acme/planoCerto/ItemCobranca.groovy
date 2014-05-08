package br.com.acme.planoCerto

class ItemCobranca {

	static belongsTo = [cobranca: Cobranca]
	Ligacao ligacao
	BigDecimal valor
	transient Boolean calculado

    static constraints = {
    	cobranca nullable: false
    	ligacao nullable: false    	
    }
}
