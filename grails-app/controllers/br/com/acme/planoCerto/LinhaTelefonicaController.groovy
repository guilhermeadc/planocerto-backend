package br.com.acme.planoCerto

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LinhaTelefonicaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LinhaTelefonica.list(params), model:[linhaTelefonicaInstanceCount: LinhaTelefonica.count()]
    }

    def show(LinhaTelefonica linhaTelefonicaInstance) {
        respond linhaTelefonicaInstance
    }

    def create() {
        respond bindData(new LinhaTelefonica(), params)
    }

    @Transactional
    def save(LinhaTelefonica linhaTelefonicaInstance) {
        if (linhaTelefonicaInstance == null) {
            notFound()
            return
        }

        if (linhaTelefonicaInstance.hasErrors()) {
            respond linhaTelefonicaInstance.errors, view:'create'
            return
        }

        linhaTelefonicaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'linhaTelefonicaInstance.label', default: 'LinhaTelefonica'), linhaTelefonicaInstance.id])
                redirect linhaTelefonicaInstance
            }
            '*' { respond linhaTelefonicaInstance, [status: CREATED] }
        }
    }

    def edit(LinhaTelefonica linhaTelefonicaInstance) {
        respond linhaTelefonicaInstance
    }

    @Transactional
    def update(LinhaTelefonica linhaTelefonicaInstance) {
        if (linhaTelefonicaInstance == null) {
            notFound()
            return
        }

        if (linhaTelefonicaInstance.hasErrors()) {
            respond linhaTelefonicaInstance.errors, view:'edit'
            return
        }

        linhaTelefonicaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'LinhaTelefonica.label', default: 'LinhaTelefonica'), linhaTelefonicaInstance.id])
                redirect linhaTelefonicaInstance
            }
            '*'{ respond linhaTelefonicaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(LinhaTelefonica linhaTelefonicaInstance) {

        if (linhaTelefonicaInstance == null) {
            notFound()
            return
        }

        linhaTelefonicaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'LinhaTelefonica.label', default: 'LinhaTelefonica'), linhaTelefonicaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linhaTelefonicaInstance.label', default: 'LinhaTelefonica'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
