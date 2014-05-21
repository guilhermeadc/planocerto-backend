package br.com.acme.planoCerto

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(CalculoLigacaoTimLibertyControle)
class CalculoLigacaoTimLibertyControleSpec extends Specification {

    static final CODIGO_AREA_SAO_PAULO = 11
    CalculoLigacaoTimLibertyControle calculo
    Calendar dataTest
    Cobranca cobrancaTest
    Ligacao ligacaoTest

    def setup() {
        calculo = new CalculoLigacaoTimLibertyControle()

        //TODO: Otimizar insumos com Mocks em vez de instanciação
        cobrancaTest = new Cobranca()
        dataTest = new GregorianCalendar(2014, Calendar.JANUARY, 01, 12, 00, 00)
        ligacaoTest = new Ligacao(duracao: 30, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO, data: dataTest.time)
    }

    def cleanup() {
    }

    void "Validade dos parâmetros de cálculo"() {

        when: "Quando é informado um parâmetro de cobranca nulo"
        calculo.calcularLigacao(null, new Ligacao())

        then: "Erro de parâmetro inválido deverá ser lançado"
        thrown(AssertionError)

        when: "Quando é informado um parâmetro de ligacao nulo"
        calculo.calcularLigacao(new Cobranca(), null)

        then: "Erro de parâmetro inválido deverá ser lançado"
        thrown(AssertionError)
    }

    void "Calculo para ligacao com duracao inferior a 3 segundos"() {

        given: "Dado um ligação com duração inferior/igual a 3 segundos"
        ligacaoTest.duracao = 3

        when: "Quando é calculado uma ligacao "
        def item = calculo.calcularLigacao(cobrancaTest, ligacaoTest)

        then: "Não deverá ser gerado cobrança"
        item.valor == 0
    }

    void "Calculo para ligacao com tarifação inferior a 30 segundos"() {

        given: "Dado um ligação com duração inferior/igual a 30 segundos"
        ligacaoTest.duracao = 20

        when: "Quando é calculado uma ligacao"
        def item = calculo.calcularLigacao(cobrancaTest, ligacaoTest)

        then: "O tarifa da ligação deverá ser referente à 30 segundos/50% do valor do minuto"
        item.valor == 0.50
    }

    void "Calculo de ligacao com tarifação VC (Móvel-Móvel) - Valor do Minuto 1.00"() {

        setup: "Dado uma ligação Móvel-Móvel"
        ligacaoTest.duracao = duracao_minutos
        ligacaoTest.tarifacao = Tarifacao.VC

        expect: "É esperado o seguinte valor"
        calculo.calcularLigacao(cobrancaTest, ligacaoTest)?.valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao_minutos || valorEsperado
        01              || 0.00   //Duração inferior a 3 segundos
        03              || 0.00   //Duração inferior a 3 segundos
        04              || 0.50   //Duração inferior a 30 segundos
        20              || 0.50   //Duração inferior a 30 segundos
        30              || 0.50
        60              || 1.00
        65              || 1.00   //Duração inferior à unidade de tempo de tarifação que é de 06 (seis) segundos
        66              || 1.10   //Duração superior/igual à unidade de tempo de tarifação de 06 (seis) segundos
        120             || 2.00
    }

    void "Calculo de ligacao com tarifação VC-1 (Móvel-Fixo) - Valor do Minuto 0.90"() {

        setup: "Dado uma ligação Móvel-Fixo"
        ligacaoTest.duracao = duracao_minutos
        ligacaoTest.tarifacao = Tarifacao.VC1

        expect: "É esperado p seguinte valor"
        calculo.calcularLigacao(cobrancaTest, ligacaoTest)?.valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao_minutos || valorEsperado
        01              || 0.00   //Duração inferior a 3 segundos
        03              || 0.00   //Duração inferior a 3 segundos
        04              || 0.45   //Duração inferior a 30 segundos1
        20              || 0.45   //Duração inferior a 30 segundos
        30              || 0.45
        60              || 0.90
        120             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
        600             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
        605             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
        606             || 0.99   //Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
        660             || 1.80   //Pagar apenas o primeiro minuto das chamadas VC-1 com duração menor ou igual a 10 minutos
    }

    void "Calculo de ligacao com tarifação VC-IR (Móvel-Movel Intra-Rede) - Valor do Minuto 0.90"() {

        setup: "Dado uma ligação Móvel-Fixo"
        ligacaoTest.duracao = duracao_minutos
        ligacaoTest.tarifacao = Tarifacao.VC_IR

        expect: "É esperado o seguinte valor"
        calculo.calcularLigacao(cobrancaTest, ligacaoTest)?.valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao_minutos || valorEsperado
        01              || 0.00   //Duração inferior a 3 segundos
        03              || 0.00   //Duração inferior a 3 segundos
        04              || 0.45   //Duração inferior a 30 segundos
        20              || 0.45   //Duração inferior a 30 segundos
        30              || 0.45
        60              || 0.90
        120             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-IR com duração menor ou igual a 10 minutos
        600             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-IR com duração menor ou igual a 10 minutos
        605             || 0.90   //Pagar apenas o primeiro minuto das chamadas VC-IR com duração menor ou igual a 10 minutos
        606             || 0.99   //Pagar apenas o primeiro minuto das chamadas VC-IR com duração menor ou igual a 10 minutos
        660             || 1.80   //Pagar apenas o primeiro minuto das chamadas VC-iR com duração menor ou igual a 10 minutos
    }

    void "Calculo de ligacao com tarifação VC-1-R (Móvel-Fixo em Roaming) - Valor do Minuto 0.90"() {

        setup: "Dado uma ligação Móvel-Móvel"
        ligacaoTest.duracao = duracao_minutos
        ligacaoTest.tarifacao = Tarifacao.VC_1R

        expect: "É esperado p seguinte valor"
        calculo.calcularLigacao(cobrancaTest, ligacaoTest)?.valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao_minutos || valorEsperado
        01              || 0.00   //Duração inferior a 3 segundos
        03              || 0.00   //Duração inferior a 3 segundos
        04              || 0.45   //Duração inferior a 30 segundos
        20              || 0.45   //Duração inferior a 30 segundos
        30              || 0.45
        60              || 0.9
        65              || 0.90   //Duração inferior à unidade de tempo de tarifação é de 06 (seis) segundos
        66              || 0.99   //Duração inferior à unidade de tempo de tarifação é de 06 (seis) segundos
        120             || 1.80
    }

    void "Calculo de ligacao com tarifação VC-R (Móvel-Móvel em Roaming) - Valor do Minuto 1.00"() {

        setup: "Dado uma ligação Móvel-Móvel em Roaming"
        ligacaoTest.duracao = duracao_minutos
        ligacaoTest.tarifacao = Tarifacao.VC_R

        expect: "É esperado p seguinte valor"
        calculo.calcularLigacao(cobrancaTest, ligacaoTest)?.valor == valorEsperado

        where: "Considerando os seguintes parâmetros de cálculo"
        duracao_minutos || valorEsperado
        01              || 0.00   //Duração inferior a 3 segundos
        03              || 0.00   //Duração inferior a 3 segundo
        04              || 0.50   //Duração inferior a 30 segundos
        20              || 0.50   //Duração inferior a 30 segundos
        30              || 0.50
        60              || 1.00
        65              || 1.00   //Duração inferior à unidade de tempo de tarifação é de 06 (seis) segundos
        66              || 1.10   //Duração inferior à unidade de tempo de tarifação é de 06 (seis) segundos
        120             || 2.00
    }

    void "Calculo de ligacao chamadas sucessivas"() {

        // CÁLCULO DA PRIMEIRA LIGAÇÃO ==============================================
        when: "Quando é realizado o cálculo inicial de uma cobrança"
        def horarioLigacao = dataTest.clone()
        horarioLigacao.set(Calendar.HOUR, 12); horarioLigacao.set(Calendar.SECOND, 00);
        ligacaoTest = new Ligacao(duracao: 05, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO, data: horarioLigacao.time)
        def itemCalculado = calculo.calcularLigacao(cobrancaTest, ligacaoTest)
        if(!itemCalculado.recalculado)
            cobrancaTest.itens.add(itemCalculado)

        then: "Então um novo item de cobrança é calculado"
        itemCalculado.duracao() == 05
        itemCalculado.recalculado == false
        itemCalculado.ligacoes.size() == 1
        cobrancaTest.itens.size() == 1

        and: "E o valor calculado será correspondente à uma única ligação"
        itemCalculado.valor == 0.5

        // CÁLCULO DA SEGUNDA LIGAÇÃO ==============================================
        when: "Quando é calculado uma segunda ligação sucessiva"
        horarioLigacao = dataTest.clone()
        horarioLigacao.set(Calendar.HOUR, 12); horarioLigacao.set(Calendar.SECOND, 10);
        ligacaoTest = new Ligacao(duracao: 10, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO, data: horarioLigacao.time)
        itemCalculado = calculo.calcularLigacao(cobrancaTest, ligacaoTest)
        if(!itemCalculado.recalculado)
            cobrancaTest.itens.add(itemCalculado)

        then: "Então a duracao dela será somada à ligação anterior"
        itemCalculado.duracao() == 05 + 10
        itemCalculado.ligacoes.size() == 2
        cobrancaTest.itens.size() == 1

        and: "E a cobranca da ligação será recalculada"
        itemCalculado.recalculado == true

        and: "E o valor calculado será correspondente às duas ligações"
        itemCalculado.valor == 0.5


        // CÁLCULO DA TERCEIRA LIGAÇÃO ==============================================
        when: "Quando é calculado a terceira ligação sucessiva"
        horarioLigacao = dataTest.clone()
        horarioLigacao.set(Calendar.HOUR, 12); horarioLigacao.set(Calendar.SECOND, 50);
        ligacaoTest = new Ligacao(duracao: 27, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO, data: horarioLigacao.time)
        itemCalculado = calculo.calcularLigacao(cobrancaTest, ligacaoTest)
        if(!itemCalculado.recalculado)
            cobrancaTest.itens.add(itemCalculado)

        then: "Então a duracao dela será somada à ligação anterior"
        itemCalculado.duracao() == 05 + 10 + 27
        itemCalculado.ligacoes.size() == 3
        cobrancaTest.itens.size() == 1

        and: "E a cobranca da ligação será recalculada"
        itemCalculado.recalculado == true

        and: "E o valor calculado será correspondente às duas ligações"
        itemCalculado.valor == 0.7

        // CÁLCULO DA QUARTA LIGAÇÃO ==============================================
        when: "Quando é calculado a quarta ligação com intervalo de 3 minutos"
        horarioLigacao = dataTest.clone()
        horarioLigacao.set(Calendar.HOUR, 12); horarioLigacao.set(Calendar.MINUTE, 03);
        ligacaoTest = new Ligacao(duracao: 25, tarifacao: Tarifacao.VC, codigoArea: CODIGO_AREA_SAO_PAULO, data: horarioLigacao.time)
        itemCalculado = calculo.calcularLigacao(cobrancaTest, ligacaoTest)
        if(!itemCalculado.recalculado)
            cobrancaTest.itens.add(itemCalculado)

        then: "Então uma nova cobrança será gerada"
        itemCalculado.duracao() == 25
        itemCalculado.ligacoes.size() == 1
        cobrancaTest.itens.size() == 2
        itemCalculado.recalculado == false

        and: "E o valor calculado será correspondente à somente a nova ligação"
        itemCalculado.valor == 0.5
    }
}
