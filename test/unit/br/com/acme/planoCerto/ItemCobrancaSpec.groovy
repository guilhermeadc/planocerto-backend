package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ItemCobranca)
class ItemCobrancaSpec extends Specification {

    def cobranca
    def itemCobranca

    def setup() {
        cobranca = new Cobranca()
        itemCobranca = new ItemCobranca(cobranca)
    }

    def cleanup() {
    }

    void "Testar inicioLigacao() considerando uma chamada"() {
        setup:
        def calendar = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 00)
        itemCobranca.adicionarLigacao(new Ligacao(data: calendar.getTime()))

        expect:
        itemCobranca.inicioLigacao().equals(calendar.getTime())
    }

    void "Testar inicioLigacao() considerando várias chamadas"() {
        setup:
        def segundo = new GregorianCalendar(2014, Calendar.JANUARY, 02, 00, 00)
        itemCobranca.adicionarLigacao(new Ligacao(data: segundo.getTime()))

        // Primeira chamada efetuada
        def primeiro = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 00)
        itemCobranca.adicionarLigacao(new Ligacao(data: primeiro.getTime()))

        def terceiro = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 30)
        itemCobranca.adicionarLigacao(new Ligacao(data: terceiro.getTime()))

        expect:
        itemCobranca.inicioLigacao().equals(primeiro.getTime())
    }

    void "Testar finalLigacao() considerando uma chamada"() {
        setup:
        def calendar = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 00)
        def finalLigacaoEsperado = calendar.clone()
        finalLigacaoEsperado.add(Calendar.SECOND, 60)
        itemCobranca.adicionarLigacao(new Ligacao(data: calendar.getTime(), duracao: 60))

        expect:
        itemCobranca.finalLigacao().equals(finalLigacaoEsperado.getTime())
    }

    void "Testar finalLigacao() considerando várias chamadas"() {
        setup:
        def segundo = new GregorianCalendar(2014, Calendar.JANUARY, 02, 00, 00)
        itemCobranca.adicionarLigacao(new Ligacao(data: segundo.getTime(), duracao: 30))

        // Primeira chamada efetuada
        // O horário final da ligação será baseado no item correspondente à primeira chamada mais a sua duração
        def primeiro = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 00)
        def finalLigacaoEsperado = primeiro.clone()
        finalLigacaoEsperado.add(Calendar.SECOND, 30)
        itemCobranca.adicionarLigacao(new Ligacao(data: primeiro.getTime(), duracao: 30))

        def terceiro = new GregorianCalendar(2014, Calendar.JANUARY, 01, 00, 30)
        itemCobranca.adicionarLigacao(new Ligacao(data: terceiro.getTime(), duracao: 30))

        expect:
        itemCobranca.finalLigacao().equals(finalLigacaoEsperado.getTime())
    }
}
