package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CobrancaService)
class CobrancaServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "testar validade dos parâmetros"() {
    	when: "Quando o parâmetro [ligacao] for nulo"
    	service.serviceMethod(new LinhaTelefonica(), null)

    	then: "Então uma excessão deverá ser lancada"
    	thrown(AssertionError)

        when: "Quando o parâmetro [linha] for nulo"
        service.serviceMethod(null, [])

        then: "Então uma excessão deverá ser lancada"
        thrown(AssertionError)
    }


    void "testar calculo sem registro de ligacoes"() {
        when: "Quando não existir ligações no período"
        def result = service.serviceMethod(new LinhaTelefonica(), [])

        then: "Então o resultado será 0.00"
        result instanceof Cobranca
        result.total() == 0.00
    }
}
