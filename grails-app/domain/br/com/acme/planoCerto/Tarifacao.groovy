package br.com.acme.planoCerto

enum Tarifacao {

    /** Regra de tarifaçao desconhecida */
    NENHUM,

	/** No contexto de sistemas celulares no Brasil, VC1 é o valor pago pelo assinante, por minuto, quando a ligação 
	for feita para a um assinante fixo na área de tarifação em que está a área de registro do assinante. */
	VC1, //(Móvel-Fixo)

    /** Valor devido pelo usuário, por unidade de tempo, pela realização de comunicação
     destinada a usuário do SMP ou SME, que esteja associado a mesma Área de Registro de origem da chamada. */
    VC, //(Móvel-Móvel)

    /** Valor devido pelo usuário, por unidade de tempo, pela realização de
     comunicação originada e terminada na rede desta prestadora, porém dentro da mesma Área de Registro. */
    VC_IR, //(Móvel-Móvel Intra-Rede

    /** Valor devido pelo usuário, por unidade de tempo, pela realização de
     chamada fora de sua Área de Mobilidade, destinada a código de acesso do STFC, associado à Área Geográfica interna à Área
     de Registro de origem da chamada. */
    VC_1R, //(Móvel-Fixo em Roaming)

    /** Valor devido pelo usuário, por unidade de tempo, pela realização de
     chamada fora de sua Área de Mobilidade, destinada a usuário do SMP e SME, que habilitou sua estação móvel na Área de
     Registro de origem da chamada*/
    VC_R, // (Móvel-Móvel em Roaming)

	/** No contexto de sistemas celulares no Brasil, VC2 é o valor pago pelo assinante, por minuto, quando a ligação for feita 
	para a um assinante fixo ou móvel fora da área de tarifação em que está a área de registro do assinante, mas com o primeiro 
	dígito do código DDD igual ao da área de tarifação do assinante. */
	VC2,

	/** No contexto de sistemas celulares no Brasil, VC3 é o valor pago pelo assinante, por minuto, quando a ligação for feita 
	para a um assinante fixo ou móvel com outro código DDD. */
	VC3;

    static mapWith = 'none'
}
