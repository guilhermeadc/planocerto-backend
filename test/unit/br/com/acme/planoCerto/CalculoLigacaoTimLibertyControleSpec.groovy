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

        given: "Dado um ligação com duração inferior/igual a 3 segundos"
        item.ligacao?.duracao = 3

        when: "Quando é calculado uma ligacao "
        calculo.calcularLigacao(item)

        then: "O tarifa da ligação deverá ser 0"
        item.valor == 0
        item.calculado == true
    }

    void "Calculo para ligacao com tarifação VC - (Móvel-Móvel) inferior a 30 segundos"() {

        given: "Dado um ligação com duração inferior/igual a 30 segundos"
        item.ligacao?.duracao = 3

        when: "Quando é calculado uma ligacao"
        calculo.calcularLigacao(item)

        then: "O tarifa da ligação deverá ser referente à 30 segundos/50% do valor do minuto"
        item.valor == 0.50
        item.calculado == true
    }

    void "Calculo de ligacao com tarifação VC - (Móvel-Móvel)"() {

        setup: "Dado uma ligação com tarifação VC - (Móvel-Móvel)"
        item.ligacao?.duracao = duracao
        item.ligacao?.tarifacao = Tarifacao.VC

        expect: "É esperado p seguinte valor"
        calculo.calcularLigacao(item).valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao | valorEsperado
        60      | 1.0             // 1 minuto
        120     | 2.0             // 2 minuto

    }
}