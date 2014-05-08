package br.com.acme.planoCerto

class Cobranca {

	LinhaTelefonica linha
	static hasMany = [itens: ItemCobranca]	

    static constraints = {
    	linha nullable: false
    }

    Cobranca(LinhaTelefonica linha, List<Ligacao> ligacoes){
    	assert linha != null, "Parâmetro [linha] não pode ser nulo"
    	assert ligacoes != null, "Parâmetro [ligacoes] não pode ser nulo"    	

    	this.linha = linha
    	ligacoes.each { this.adicionarItem(it, null) }
    }

    def total(){    	
    	return itens?.sum{ it.valor } ?: 0
    }

    def adicionarItem(Ligacao ligacao, BigDecimal valor){
    	assert ligacao != null, "Parâmetro [linha] não pode ser nulo"

    	def item = new ItemCobranca(cobranca: this, ligacao: ligacao, valor: valor)
    	return item
    }

    def adicionarItem(Ligacao ligacao){
    	adicionarItem(ligacao, null)
    }
}
