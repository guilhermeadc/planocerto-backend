package br.com.acme.planoCerto

import grails.transaction.Transactional

@Transactional
class CobrancaService {

    def serviceMethod(LinhaTelefonica linha, List<Ligacao> ligacoes) {
    	assert linha != null, "Parâmetro [linha] não pode ser nulo"
    	assert ligacoes != null, "Parâmetro [ligacoes] não pode ser nulo"

        //Obtém regra de cálculo relacionado ao plano
        CalculoLigacao calculo = obterEstrategiaCalculo(linha)

        // Calcular custo dos serviços de telefonias
    	def cobranca = new Cobranca(linha, ligacoes)
        cobranca.itens.each { calculo.calcularLigacao(it) }

    	return cobranca
    }

    def obterEstrategiaCalculo(LinhaTelefonica linha){
        new CalculoLigacaoTimLibertyControle()
    }
}