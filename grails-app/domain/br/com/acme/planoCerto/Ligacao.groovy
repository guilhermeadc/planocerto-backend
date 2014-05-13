package br.com.acme.planoCerto

import groovy.time.*
import groovy.time.TimeDuration

class Ligacao {
        
    Date data
    String numero
    //TimeDuration duracao
    Tarifacao tarifacao
    TipoLigacao tipo
    Boolean intraRede
    static belongsTo = [linha: LinhaTelefonica]
    
    static constraints = {        
        data nullable: false
        numero nullable: false
        //duracao nullable: false
        tarifacao nullable: false
        tipo nullable: false
        linha: nullable: false
        intraRede: nullable: false
    }
}
