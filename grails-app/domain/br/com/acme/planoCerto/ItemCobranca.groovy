package br.com.acme.planoCerto

class ItemCobranca {

	static belongsTo = [cobranca: Cobranca]
	Ligacao ligacao
	BigDecimal valor

    static constraints = {
    	cobranca nullable: false
    	ligacao nullable: false
    }
}
