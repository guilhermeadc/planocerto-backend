package br.com.acme.planoCerto

import spock.lang.*

@TestFor(OperadoraController)
@Mock(Operadora)
class OperadoraControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null        
        params["nome"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.operadoraInstanceList
            model.operadoraInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.operadoraInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def operadora = new Operadora()
            operadora.validate()
            controller.save(operadora)

        then:"The create view is rendered again with the correct model"
            model.operadoraInstance != null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            operadora = new Operadora(params)

            controller.save(operadora)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/operadora/show/1'
            controller.flash.message != null
            Operadora.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def operadora = new Operadora(params)
            controller.show(operadora)

        then:"A model is populated containing the domain instance"
            model.operadoraInstance == operadora
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def operadora = new Operadora(params)
            controller.edit(operadora)

        then:"A model is populated containing the domain instance"
            model.operadoraInstance == operadora
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/operadora/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def operadora = new Operadora()
            operadora.validate()
            controller.update(operadora)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.operadoraInstance == operadora

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            operadora = new Operadora(params).save(flush: true)
            controller.update(operadora)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/operadora/show/$operadora.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/operadora/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def operadora = new Operadora(params).save(flush: true)

        then:"It exists"
            Operadora.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(operadora)

        then:"The instance is deleted"
            Operadora.count() == 0
            response.redirectedUrl == '/operadora/index'
            flash.message != null
    }
}
