import br.com.acme.planoCerto.*

class BootStrap {

    def init = { servletContext ->
        def tim

    	//Carga das operadoras disponíveis atualmente no país
    	if(!Operadora.count()){
    		tim = new Operadora(nome: "TIM")
                .addToPlanos(new Plano(nome: "TIM Infinity", tipoPlano: Plano.TipoPlano.POSPAGO))
                .addToPlanos(new Plano(nome: "Liberty Express", tipoPlano: Plano.TipoPlano.POSPAGO))
                .addToPlanos(new Plano(nome: "Liberty Controle Express", tipoPlano: Plano.TipoPlano.POSPAGO))
                .addToPlanos(new Plano(nome: "Liberty Controle", tipoPlano: Plano.TipoPlano.POSPAGO))
                .addToPlanos(new Plano(nome: "Da Vinci", tipoPlano: Plano.TipoPlano.POSPAGO))
                .addToPlanos(new Plano(nome: "Infinity Pré", tipoPlano: Plano.TipoPlano.PREPAGO))
                .save(failOnError: true)

    		new Operadora(nome: "Claro").save()
    		new Operadora(nome: "Vivo").save()
    		new Operadora(nome: "OI").save()
    	} 

        //Carga inicial de estados e regiões
        if(!Estado.count()){
            //Estados da Região I
            new Estado(nome: "Rio de Janeiro", sigla: "RJ", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Minas Gerais", sigla: "MG", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Espírito Santo", sigla: "ES", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Bahia", sigla: "BA", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Alagoas", sigla: "AL", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Sergipe", sigla: "SE", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Pernambuco", sigla: "PE", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Paraíba", sigla: "PB", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Rio Grande do Norte", sigla: "RN", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Ceará", sigla: "CE", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Piauí", sigla: "PI", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Maranhão", sigla: "MA", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Pará", sigla: "PA", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Amazonas", sigla: "AM", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Amapá", sigla: "AP", regiao: Regiao.I).save(failOnError: true)
            new Estado(nome: "Roraima", sigla: "RO", regiao: Regiao.I).save(failOnError: true)

            //Estados da Região II
            new Estado(nome: "Acre", sigla: "AC", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Rondônio", sigla: "RO", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Mato Grosso", sigla: "MT", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Mato Grosso do Sul", sigla: "MS", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Tocantins", sigla: "TO", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Goiás", sigla: "GO", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Paraná", sigla: "PR", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Santa Catarina", sigla: "SC", regiao: Regiao.II).save(failOnError: true)
            new Estado(nome: "Rio Grande Sul", sigla: "RS", regiao: Regiao.II).save(failOnError: true)

            //Estados da Região III
            new Estado(nome: "São Paulo", sigla: "SP", regiao: Regiao.III).save(failOnError: true)
        }

        //Carga de linha telefônica para testes
        if(!LinhaTelefonica.count()){
            new LinhaTelefonica(ddd: 61, numero: "9243-5973", operadora: tim).save(failOnError: true)
        }
    }

    def destroy = {
    }
}
