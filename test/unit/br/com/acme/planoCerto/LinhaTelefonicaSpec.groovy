package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinhaTelefonica)
class LinhaTelefonicaSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }


    void "teste de validação do campo DDD"(){
        
    	given: "Considerando o registro de uma nova linha telefônica"
		def linha = new LinhaTelefonica()
    	linha.ddd = ddd
        linha.validate()

        expect: "Expera-se o campo ddd válido"
        linha.errors.hasFieldErrors("ddd") != valido        	

        where: 
        ddd || valido
        1   || false
        10  || true
        99  || true
        100 || false
    }

    void "teste de validação do campo numero"(){
        
    	given: "Considerando o registro de uma nova linha telefônica"
		def linha = new LinhaTelefonica()
    	linha.numero = numero
        linha.validate()

        expect: "Expera-se o campo ddd válido"
        linha.errors.hasFieldErrors("numero") != valido        	

        where: 
        numero       || valido
        null         || false
        "0000000"    || false
        "9999-9999"  || true
        "99999-9999" || true
        "99999999"   || true
        "999999999"  || true        
    }

    void "teste de validação do campo operadora"(){
						
		given: "Considerando o registro de uma nova linha telefônica"
        def linha = new LinhaTelefonica()

		//Validação do campo operadora
		when: "Quando a linha não possui uma operadora"
		linha.operadora = null
		linha.validate()		

		then: "Então ocorrerá erros de propriedade nula"
		linha.errors.hasFieldErrors("operadora")
    }
}
