package br.com.acme.planoCerto

class Ligacao {
        
    Date data
    String numero
    int codigoArea
    int duracao
    Tarifacao tarifacao = Tarifacao.NENHUM
    TipoLigacao tipo
    static belongsTo = [linha: LinhaTelefonica]
    
    static constraints = {        
        data nullable: false
        numero nullable: false
        codigoArea nullable: false
        duracao nullable: false
        tarifacao nullable: false
        tipo nullable: false
        linha nullable: false
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Ligacao ligacao = (Ligacao) o

        if (codigoArea != ligacao.codigoArea) return false
        if (data != ligacao.data) return false
        if (numero != ligacao.numero) return false

        return true
    }

    int hashCode() {
        int result
        result = (data != null ? data.hashCode() : 0)
        result = 31 * result + (numero != null ? numero.hashCode() : 0)
        result = 31 * result + codigoArea
        return result
    }


    @Override
    public String toString() {
        return "Ligacao{" +
                "codigoArea=" + codigoArea +
                ", numero='" + numero + '\'' +
                ", tarifacao=" + tarifacao +
                ", duracao=" + duracao +
                ", data=" + data +
                '}';
    }
}