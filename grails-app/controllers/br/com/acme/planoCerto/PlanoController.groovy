package br.com.acme.planoCerto



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlanoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Plano.list(params), model:[planoInstanceCount: Plano.count()]
    }

    def show(Plano planoInstance) {
        respond planoInstance
    }

    def create() {
        respond bindData(new Plano(), params)
    }

    @Transactional
    def save(Plano planoInstance) {
        if (planoInstance == null) {
            notFound()
            return
        }

        if (planoInstance.hasErrors()) {
            respond planoInstance.errors, view:'create'
            return
        }

        planoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'planoInstance.label', default: 'Plano'), planoInstance.id])
                redirect planoInstance
            }
            '*' { respond planoInstance, [status: CREATED] }
        }
    }

    def edit(Plano planoInstance) {
        respond planoInstance
    }

    @Transactional
    def update(Plano planoInstance) {
        if (planoInstance == null) {
            notFound()
            return
        }

        if (planoInstance.hasErrors()) {
            respond planoInstance.errors, view:'edit'
            return
        }

        planoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Plano.label', default: 'Plano'), planoInstance.id])
                redirect planoInstance
            }
            '*'{ respond planoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Plano planoInstance) {

        if (planoInstance == null) {
            notFound()
            return
        }

        planoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Plano.label', default: 'Plano'), planoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'planoInstance.label', default: 'Plano'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
