package br.com.acme.planoCerto

import java.math.*

class CalculoLigacaoTimLibertyControle implements CalculoLigacao {
    static mapWith = "none"

	@Override
    ItemCobranca calcularLigacao(Cobranca cobranca, Ligacao ligacao) {
		assert cobranca != null, "Parâmetro [cobranca] não pode ser nulo"
        assert ligacao != null, "Parâmetro [ligacao] não pode ser nulo"

        ItemCobranca itemCobranca = new ItemCobranca(cobranca, ligacao)

        // Ligação com duração inferior a 3 segundos não é computado
        if (ligacao.duracao <= 3)
            return itemCobranca

        /* Caso haja chamadas sucessivas com duração superior a 3 (três) segundos e inferior a 30 segundos, efetuadas entre o
        mesmo Código de Acesso de origem e destino, e o tempo compreendido entre o final de uma chamada e o início da chamada
        seguinte for inferior ou igual a 120 (cento e vinte) segundos, os tempos das chamadas devem ser somados, considerando-se o
        somatório do tempo das chamadas como sendo uma única ligação. */
        if(ligacao.duracao < 30){
            def itemSucessivo = cobranca.buscarLigacoesSucessivas(ligacao)
            itemSucessivo?.adicionarLigacao(ligacao)
            itemSucessivo?.recalculado = true
            itemCobranca = itemSucessivo ?: itemCobranca
        }

        // Cálculo do tempo de tarifação considerando unidades de 6 segundos
        def fracao = ((itemCobranca.duracao() / 6.0).divide(10, 1, RoundingMode.DOWN))
        fracao = (itemCobranca.duracao() > 30) ? fracao : 0.5 //Fração de 30 segundos

        switch (itemCobranca.tarifacao){

            case Tarifacao.VC:
                itemCobranca.valor = 1.00 * fracao
                break

            case Tarifacao.VC_IR:
                if (itemCobranca.duracao() > 60)
                    // Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
                    fracao = (itemCobranca.duracao() <= 600) ? 1.0 : (fracao - 10 + 1)

                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC1:
                if (itemCobranca.duracao() > 60)
                    // Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
                    fracao = (itemCobranca.duracao() <= 600) ? 1.0 : (fracao - 10 + 1)

                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC_1R:
                itemCobranca.valor = 0.90 * fracao
                break

            case Tarifacao.VC_R:
                itemCobranca.valor = 1.00 * fracao
                break
        }

        return itemCobranca
	}
}
