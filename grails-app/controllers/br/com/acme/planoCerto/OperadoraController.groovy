package br.com.acme.planoCerto

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OperadoraController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Operadora.list(params), model:[operadoraInstanceCount: Operadora.count()]
    }

    def show(Operadora operadoraInstance) {
        respond operadoraInstance
    }

    def create() {
        respond bindData(new Operadora(), params)
    }

    @Transactional
    def save(Operadora operadoraInstance) {
        if (operadoraInstance == null) {
            notFound()
            return
        }

        if (operadoraInstance.hasErrors()) {
            respond operadoraInstance.errors, view:'create'
            return
        }

        operadoraInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'operadoraInstance.label', default: 'Operadora'), operadoraInstance.id])
                redirect operadoraInstance
            }
            '*' { respond operadoraInstance, [status: CREATED] }
        }
    }

    def edit(Operadora operadoraInstance) {
        respond operadoraInstance
    }

    @Transactional
    def update(Operadora operadoraInstance) {
        if (operadoraInstance == null) {
            notFound()
            return
        }

        if (operadoraInstance.hasErrors()) {
            respond operadoraInstance.errors, view:'edit'
            return
        }

        operadoraInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Operadora.label', default: 'Operadora'), operadoraInstance.id])
                redirect operadoraInstance
            }
            '*'{ respond operadoraInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Operadora operadoraInstance) {

        if (operadoraInstance == null) {
            notFound()
            return
        }

        operadoraInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Operadora.label', default: 'Operadora'), operadoraInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'operadoraInstance.label', default: 'Operadora'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
