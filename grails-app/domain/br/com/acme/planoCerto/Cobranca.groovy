package br.com.acme.planoCerto

class Cobranca {

	LinhaTelefonica linha
	static hasMany = [itens: ItemCobranca]	

    static constraints = {
    	linha nullable: false
    }

    Cobranca(){
        this.itens = []
    }

    Cobranca(LinhaTelefonica linha){
        this()
    	assert linha != null, "Parâmetro [linha] não pode ser nulo."
    	this.linha = linha
    }

    def total() {
    	return itens.sum{ it.valor } ?: 0
    }

    def buscarLigacoesSucessivas(Date dataReferencia, Tarifacao tarifacao, int codigoArea) {
        assert dataReferencia != null, "Parâmetro [dataReferencia] não pode ser nulo."

        def refFim = Calendar.getInstance()
        refFim.setTime(dataReferencia)

        def refInicio = Calendar.getInstance()
        refInicio.setTime(dataReferencia)
        refInicio.add(Calendar.SECOND, -120)

        def itensLocalizados = this.itens.findAll({
            it.tarifacao == tarifacao &&
            it.codigoArea == codigoArea &&
            refInicio.getTime().compareTo(it.finalLigacao()) < 0 &&
            refFim.getTime().compareTo(it.finalLigacao()) > 0
        })

        return itensLocalizados.max({ it.finalLigacao() })
    }

    def buscarLigacoesSucessivas(Ligacao ligacao) {
        assert ligacao != null, "Parâmetro [ligacao] não pode ser nulo."
        buscarLigacoesSucessivas(ligacao.data, ligacao.tarifacao, ligacao.codigoArea)
    }
}
