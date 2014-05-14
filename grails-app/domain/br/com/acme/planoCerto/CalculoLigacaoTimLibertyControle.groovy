package br.com.acme.planoCerto

class CalculoLigacaoTimLibertyControle implements CalculoLigacao {
    static mapWith = "none"

	@Override
	def calcularLigacao(ItemCobranca itemCobranca) { 
		assert itemCobranca != null, "Parâmetro [itemCobranca] não pode ser nulo"

        itemCobranca.valor = 0
        itemCobranca.calculado = true

        // Ligação com duração inferior a 3 segundos não é computado
        if (itemCobranca.ligacao?.duracao <= 3)
            return itemCobranca

        // Cálculo do tempo de tarifação considerando unidades de 6 segundos
        def fracao = (itemCobranca.ligacao?.duracao / 6.0) / 10.0
        fracao = (itemCobranca.ligacao?.duracao > 30) ? fracao : 0.5 //Fração de 30 segundos

        switch (itemCobranca.ligacao){

            case Tarifacao.VC:
                itemCobranca.valor = 1.00 * fracao
                break

            case Tarifacao.VC_IR:
                // Pagar apenas o primeiro minuto das chamadas VC-IR com duração menor ou igual a 10 minutos
                fracao = (itemCobranca.ligacao?.duracao <= 600) ? 1.0 : (fracao - 10 +1)
                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC1:
                // Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
                fracao = (itemCobranca.ligacao?.duracao <= 600) ? 1.0 : (fracao - 10 +1)
                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC_1R:
                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC_R:
                itemCobranca.valor = 0.90 * fracao
                break
        }

		return itemCobranca
	}
}
