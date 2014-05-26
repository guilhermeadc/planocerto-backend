package br.com.acme.planoCerto

interface CalculoLigacao {
	static mapWith = "none"
	ItemCobranca calcularLigacao(Cobranca cobranca, Ligacao ligacao)
}
