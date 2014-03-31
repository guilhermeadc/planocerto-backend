package br.com.acme.planoCerto

import org.spockframework.compiler.model.WhenBlock;

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Operadora)
class OperadoraSpec extends Specification {

    void "test validação do modelo de dados"() {
				
		//Validação de valores nulos
		given: "Considerando o cadastramento de uma nova operadora"
		Operadora operadora = new Operadora()
		
		when: "Quando a operadora não possui nome"
		operadora.validate()
		
		then: "Então ocorrerá erros de propriedade nula"
		operadora.hasErrors()		
		
		//Validação de valores corretos
		when: "Quando a operadora possui nome"
		operadora.nome = "TIM"
		operadora.validate()
		
		then: "Então o registro será validado"
		!operadora.hasErrors()		
    }
}
