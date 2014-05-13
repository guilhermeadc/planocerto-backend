package br.com.acme.planoCerto

class CalculoLigacaoTimLibertyControle implements CalculoLigacao {
    static mapWith = "none"

	@Override
	def calcularLigacao(ItemCobranca itemCobranca) { 
		assert itemCobranca != null, "Parâmetro [itemCobranca] não pode ser nulo"

		itemCobranca.valor = 0
		itemCobranca.calculado = true

		return itemCobranca
	}
}
