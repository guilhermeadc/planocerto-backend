package br.com.acme.planoCerto

class ItemCobranca {

	BigDecimal valor = 0.0
    Tarifacao tarifacao = Tarifacao.NENHUM
    int codigoArea
    transient Boolean recalculado = false

    static belongsTo = [cobranca: Cobranca]
    static hasMany = [ligacoes: Ligacao]

    static constraints = {
        cobranca nullable: false
        valor nullable: false
        tarifacao nullable: false
        codigoArea nullable: false
    }

    ItemCobranca(Cobranca cobranca) {
        ligacoes = new ArrayList<Ligacao>()
        cobranca = cobranca
    }

    ItemCobranca(Cobranca cobranca, Ligacao ligacao, BigDecimal valor) {
        this(cobranca)
        ligacoes.add(ligacao)
        tarifacao = ligacao.tarifacao
        codigoArea = ligacao.codigoArea
        valor = valor
    }

    ItemCobranca(Cobranca cobranca, Ligacao ligacao) {
        this(cobranca, ligacao, 0.0)
    }

    def adicionarLigacao(Ligacao ligacao){
        assert ligacao != null, "Parâmetro [ligacao] não pode ser nulo"

        if(this.ligacoes.isEmpty()) {
            this.tarifacao = ligacao.tarifacao
            this.codigoArea = ligacao.codigoArea
        }

        assert this.tarifacao == ligacao.tarifacao, "Ligação com diferente tipo de tarifação não pode ser adicionada"
        assert this.codigoArea == ligacao.codigoArea, "Ligação com diferente código de acesso não pode ser adicionada"

        this.ligacoes.add(ligacao)
    }

    def duracao(){
        return ligacoes.sum({ it.duracao })
    }

    def inicioLigacao(){
        return ligacoes.min({ it.data })?.data
    }

    def finalLigacao(){
        def ligacaoInicial = ligacoes.min({ it.data })
        def calFinalLigacao = Calendar.getInstance()
        calFinalLigacao.setTime(ligacaoInicial.data)
        calFinalLigacao.add(Calendar.SECOND, ligacaoInicial.duracao)
        return calFinalLigacao.time
    }


}
