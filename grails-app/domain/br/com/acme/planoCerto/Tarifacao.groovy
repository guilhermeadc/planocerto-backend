package br.com.acme.planoCerto

enum Tarifacao {

	/** No contexto de sistemas celulares no Brasil, VC1 é o valor pago pelo assinante, por minuto, quando a ligação 
	for feita para a um assinante fixo na área de tarifação em que está a área de registro do assinante. Na comunicação 
	Móvel-Móvel, a critério da Concessionária do SMC, poderá ser aplicado acréscimo de até 30% (trinta por cento) sobre o valor VC1. */
	VC1, 

	/** No contexto de sistemas celulares no Brasil, VC2 é o valor pago pelo assinante, por minuto, quando a ligação for feita 
	para a um assinante fixo ou móvel fora da área de tarifação em que está a área de registro do assinante, mas com o primeiro 
	dígito do código DDD igual ao da área de tarifação do assinante. */
	VC2,

	/** No contexto de sistemas celulares no Brasil, VC3 é o valor pago pelo assinante, por minuto, quando a ligação for feita 
	para a um assinante fixo ou móvel com outro código DDD. */
	VC3;

    static mapWith = 'none'
}
