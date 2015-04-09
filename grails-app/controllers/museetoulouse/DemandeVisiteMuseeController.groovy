package museetoulouse


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    DemandeVisiteMuseeService demandeVisiteMuseeService
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DemandeVisiteMusee.list(params), model: [demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
    }

    def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    def create() {
        respond new DemandeVisiteMusee(params)
    }

    @Transactional
    def save(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'create'
            return
        }

        demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(demandeVisiteMuseeInstance,demandeVisiteMuseeInstance.getMusee(),demandeVisiteMuseeInstance.getDemandeVisite())

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: CREATED] }
        }
    }

    def edit(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    @Transactional
    def update(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view: 'edit'
            return
        }

        demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(demandeVisiteMuseeInstance,demandeVisiteMuseeInstance.getMusee(),demandeVisiteMuseeInstance.getDemandeVisite())


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DemandeVisiteMusee demandeVisiteMuseeInstance) {

        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        demandeVisiteMuseeService.deleteDemandeVisiteMusee(demandeVisiteMuseeInstance)


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DemandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
