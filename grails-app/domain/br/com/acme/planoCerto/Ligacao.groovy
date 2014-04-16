package br.com.acme.planoCerto

import groovy.time.*
import groovy.time.TimeDuration

class Ligacao {
        
    Date data
    String numero
    //TimeDuration duracao
    TipoLigacao tipo
    static belongsTo = [linha: LinhaTelefonica]
    
    static constraints = {        
        data nullable: false
        numero nullable: false
        //duracao nullable: false
        tipo nullable: false
        linha: nullable: false
    }
}
