package br.com.acme.planoCerto

class Ligacao {
        
    Date data
    String numero
    int codigoArea
    int duracao
    Tarifacao tarifacao = Tarifacao.NENHUM
    TipoLigacao tipo
    static belongsTo = [linha: LinhaTelefonica]
    
    static constraints = {        
        data nullable: false
        numero nullable: false
        codigoArea nullable: false
        duracao nullable: false
        tarifacao nullable: false
        tipo nullable: false
        linha nullable: false
    }
}