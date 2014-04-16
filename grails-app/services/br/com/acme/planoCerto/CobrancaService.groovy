package br.com.acme.planoCerto

import grails.transaction.Transactional

@Transactional
class CobrancaService {

    def serviceMethod(LinhaTelefonica linha, List ligacoes) {
    	assert linha != null, "Parâmetro [linha] não pode ser nulo"
    	assert ligacoes != null, "Parâmetro [ligacoes] não pode ser nulo"

    	def cobranca = new Cobranca(linha: linha)
    	for(lig in ligacoes) {
    		
    	}

    	return cobranca
    }
}
