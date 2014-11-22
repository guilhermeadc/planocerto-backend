package br.com.acme.planoCerto

import spock.lang.*

@TestFor(PlanoController)
@Mock(Plano)
class PlanoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["nome"] = 'TIM'
        params["tipoPlano"] = Plano.TipoPlano.POSPAGO
        params["operadora"] = new Operadora();
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.planoInstanceList
            model.planoInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.planoInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def plano = new Plano()
            plano.validate()
            controller.save(plano)

        then:"The create view is rendered again with the correct model"
            model.planoInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            plano = new Plano(params)

            controller.save(plano)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/plano/show/1'
            controller.flash.message != null
            Plano.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def plano = new Plano(params)
            controller.show(plano)

        then:"A model is populated containing the domain instance"
            model.planoInstance == plano
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def plano = new Plano(params)
            controller.edit(plano)

        then:"A model is populated containing the domain instance"
            model.planoInstance == plano
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/plano/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def plano = new Plano()
            plano.validate()
            controller.update(plano)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.planoInstance == plano

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            plano = new Plano(params).save(flush: true)
            controller.update(plano)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/plano/show/$plano.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/plano/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def plano = new Plano(params).save(flush: true)

        then:"It exists"
            Plano.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(plano)

        then:"The instance is deleted"
            Plano.count() == 0
            response.redirectedUrl == '/plano/index'
            flash.message != null
    }
}
