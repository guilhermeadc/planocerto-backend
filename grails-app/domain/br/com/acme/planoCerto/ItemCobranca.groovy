package br.com.acme.planoCerto

class ItemCobranca {

	Ligacao ligacao
	BigDecimal valor
	transient Boolean calculado
    static belongsTo = [cobranca: Cobranca]

    static constraints = {
    	cobranca nullable: false
    	ligacao nullable: false    	
    }
}
