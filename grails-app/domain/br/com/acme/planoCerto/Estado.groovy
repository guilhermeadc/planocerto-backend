package br.com.acme.planoCerto

class Estado {

	String nome
	String sigla
	Regiao regiao

	static constraints = {
		nome blank: false, nullable: false
		sigla nullable: false, size: 2..2
		regiao nullable: false
	}

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Estado estado = (Estado) o

        if (nome != estado.nome) return false

        return true
    }

    int hashCode() {
        return (nome != null ? nome.hashCode() : 0)
    }


    @Override
    public String toString() {
        return "Estado{" +
                "sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
