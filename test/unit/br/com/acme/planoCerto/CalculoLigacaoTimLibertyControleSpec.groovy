package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CalculoLigacaoTimLibertyControle)
class CalculoLigacaoTimLibertyControleSpec extends Specification {

	def calculo
	def cobranca
	def item

    def setup() {
    	calculo = new CalculoLigacaoTimLibertyControle()

    	//TODO: Otimizar insumos com Mocks em vez de instanciação
    	cobranca = new Cobranca()
    	item = cobranca.adicionarItem(new Ligacao())
    }

    def cleanup() {
    }

    void "Validade dos parâmetros de cálculo"() {

    	when: "Quando é informado um parâmetro nulo"
    	calculo.calcularLigacao(null)

    	then: "Erro de parâmetro inválido deverá ser lançado"
    	thrown(AssertionError)
    }

     void "Calculo para ligacao com duracao inferior a 3 segundos"() {

    	when: "Quando é informado uma ligacao com duracao de 3 segundo"
    	calculo.calcularLigacao(item)

    	then: "Erro de parâmetro inválido deverá ser lançado"
    	item.valor == 0
    	item.calculado == true
    }
}
