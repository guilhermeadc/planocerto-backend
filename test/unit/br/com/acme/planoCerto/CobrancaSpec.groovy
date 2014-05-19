package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cobranca)
class CobrancaSpec extends Specification {

    def cobranca
    static final CODIGO_AREA_SAO_PAULO = 11
    static final CODIGO_AREA_BRASILIA = 61

    def setup() {
        cobranca = new Cobranca()
    }

    def cleanup() {
    }

    void "Testar a buscar de ligacoes sucessivas"() {
        setup:
        //(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
        def horaLigacao1 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 00)
        def ligacao = new Ligacao(data: horaLigacao1.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca1 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca1

        def horaLigacao2 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 10)
        ligacao = new Ligacao(data: horaLigacao2.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca2 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca2

        def horaLigacao3 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 20)
        ligacao = new Ligacao(data: horaLigacao3.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca3 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca3

        def horaLigacao4 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 01, 00)
        ligacao = new Ligacao(data: horaLigacao4.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca4 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca4

        def horaLigacao5 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 02, 00)
        ligacao = new Ligacao(data: horaLigacao5.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca5 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca5

        def horaLigacao6 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 05, 00)
        ligacao = new Ligacao(data: horaLigacao6.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca6 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca6

        expect:
        // Ligacao 6 não é considerada ligação sucessiva - 300min
        cobranca.buscarLigacoesSucessivas(horaLigacao6.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO) == null
        cobranca.buscarLigacoesSucessivas(horaLigacao5.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca4)
        cobranca.buscarLigacoesSucessivas(horaLigacao4.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca3)
        cobranca.buscarLigacoesSucessivas(horaLigacao3.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca2)
        cobranca.buscarLigacoesSucessivas(horaLigacao2.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca1)
    }

    void "Testar a buscar de ligacoes sucessivas com códigos de área diferentes"() {
        setup:
        //(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
        def horaLigacao1 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 00)
        def ligacao = new Ligacao(data: horaLigacao1.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca1 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca1

        def horaLigacao2 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 10)
        ligacao = new Ligacao(data: horaLigacao2.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_BRASILIA)
        ItemCobranca itemCobranca2 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca2

        def horaLigacao3 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 20)
        ligacao = new Ligacao(data: horaLigacao3.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca3 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca3

        def horaLigacao4 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 01, 00)
        ligacao = new Ligacao(data: horaLigacao4.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_BRASILIA)
        ItemCobranca itemCobranca4 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca4

        def horaLigacao5 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 02, 00)
        ligacao = new Ligacao(data: horaLigacao5.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca5 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca5

        def horaLigacao6 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 05, 00)
        ligacao = new Ligacao(data: horaLigacao6.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca6 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca6

        expect:
        // Ligacao 6 não é considerada ligação sucessiva - 300min
        cobranca.buscarLigacoesSucessivas(horaLigacao6.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO) == null
        cobranca.buscarLigacoesSucessivas(horaLigacao5.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca3)
        cobranca.buscarLigacoesSucessivas(horaLigacao4.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca3)
        cobranca.buscarLigacoesSucessivas(horaLigacao3.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca1)
        cobranca.buscarLigacoesSucessivas(horaLigacao2.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca1)
    }

    void "Testar a buscar de ligacoes sucessivas com tarifacões diferentes"() {
        setup:
        //(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)
        def horaLigacao1 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 00)
        def ligacao = new Ligacao(data: horaLigacao1.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca1 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca1

        // Registro com tipo de tarifação diferenciado
        def horaLigacao2 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 10)
        ligacao = new Ligacao(data: horaLigacao2.time, tarifacao: Tarifacao.VC_IR, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca2 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca2

        def horaLigacao3 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 20)
        ligacao = new Ligacao(data: horaLigacao3.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca3 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca3

        // Registro com tipo de tarifação diferenciado
        def horaLigacao4 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 01, 00)
        ligacao = new Ligacao(data: horaLigacao4.time, tarifacao: Tarifacao.VC_IR, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca4 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca4

        def horaLigacao5 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 02, 00)
        ligacao = new Ligacao(data: horaLigacao5.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca5 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca5

        def horaLigacao6 = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 05, 00)
        ligacao = new Ligacao(data: horaLigacao6.time, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO)
        ItemCobranca itemCobranca6 = new ItemCobranca(cobranca, ligacao)
        cobranca.itens << itemCobranca6

        expect:
        // Ligacao 6 não é considerada ligação sucessiva - 300min
        cobranca.buscarLigacoesSucessivas(horaLigacao6.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO) == null
        cobranca.buscarLigacoesSucessivas(horaLigacao5.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca3)
        cobranca.buscarLigacoesSucessivas(horaLigacao4.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca3)
        cobranca.buscarLigacoesSucessivas(horaLigacao3.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca1)
        cobranca.buscarLigacoesSucessivas(horaLigacao2.time, Tarifacao.VC, CODIGO_AREA_SAO_PAULO).equals(itemCobranca1)
    }
}