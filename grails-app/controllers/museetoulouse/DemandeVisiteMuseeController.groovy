package museetoulouse

import java.text.ParseException
import java.text.SimpleDateFormat

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
    def save() {

        Musee musee = Musee.get(params.museeId)
        System.out.println("musee"+params.museeId)
        String dateDebutStr = params.dateDebut
        String dateFinStr = params.dateFin
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy")
        Date dateDebut
        Date dateFin
        DemandeVisiteMusee demandeVisiteMusee = new DemandeVisiteMusee(dateDemande: new Date())
        try {

            dateDebut = formatter.parse(dateDebutStr)
            dateFin = formatter.parse(dateFinStr)
            dateFin = formatter.parse(dateFinStr)


        } catch (ParseException e) {
            e.printStackTrace();
        }
        int nbPersonne = params.nbPersonnes.toInteger().intValue()

        DemandeVisite demandeVisite = new DemandeVisite(code:0,dateDebutPeriode:dateDebut,dateFinPeriode:dateFin,nbPersonnes:nbPersonne,statut:"En Attente")


        DemandeVisiteMusee demandeVisiteMuseeInstance = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(demandeVisiteMusee,musee,demandeVisite)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee'), demandeVisiteMuseeInstance.id])
                redirect controller: "home", action: "index"
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
