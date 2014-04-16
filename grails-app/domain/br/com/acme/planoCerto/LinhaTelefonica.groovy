package br.com.acme.planoCerto

class LinhaTelefonica {

    Integer ddd
    String numero    
    Operadora operadora
    
    static constraints = {
        ddd range: 10..99
        numero nullable: false, matches: /^\d{4,5}-?\d{4}$/
        operadora nullable: false
    }
}
