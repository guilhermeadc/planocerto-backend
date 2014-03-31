package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Plano)
class PlanoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
    
    void "test validação do modelo de dados"() {
				
		//Validação de valores nulos
		given: "Considerando o cadastramento de um novo Plano"
		Plano plano = new Plano()
		
		when: "Quando o plano não possui nome"
		plano.validate()
		
		then: "Então ocorrerá erros de propriedade nula"
		plano.hasErrors()		
		
		//Validação de valores corretos
		when: "Quando a operadora possui nome"
		plano.nome = "TIM Liberty"
		plano.tipoPlano = Plano.TipoPlano.PREPAGO
		plano.operadora = new Operadora(nome: "TIM")
		plano.validate()
		
		then: "Então o registro será validado"
		!plano.hasErrors()		
    }
}
