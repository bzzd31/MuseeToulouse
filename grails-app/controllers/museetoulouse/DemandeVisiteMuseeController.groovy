package museetoulouse

import java.text.ParseException
import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteMuseeController {
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    DemandeVisiteMuseeService demandeVisiteMuseeService
    MuseeService museeService
    DemandeVisiteService demandeVisiteService
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DemandeVisiteMusee.list(params), model:[demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
        //[demandeVisiteMuseeInstanceList:DemandeVisiteMusee.list(params),demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()]
    }

    def show(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        respond demandeVisiteMuseeInstance
    }

    def codeSearch(){
        String message
        DemandeVisiteMusee demandeVisiteMusee
        if(params.code != null && (((String)params.code).isNumber())){
            int code =  Integer.parseInt(params.code)
            DemandeVisite demandeVisites = demandeVisiteService.getDemandeVisiteMusee(code)
            if(demandeVisites != null){
                demandeVisiteMusee = demandeVisiteMuseeService.findsDemandeVisite(demandeVisites)
                if(demandeVisiteMusee != null){
                    message = "OK Demande Musee Valide"
                } else {
                     message = "Erreur impossible de récupérer la liste des demandes"
                }
            } else {
                message = "Erreur Pas de Code Valide"
            }
        } else {
            message = "Erreur Il faut remplir/modifier le champs code"
        }
        flash.message = message;
        render(view: 'index', model: [demandeVisiteMuseeInstanceList:demandeVisiteMusee,demandeVisiteMuseeInstanceCount: DemandeVisiteMusee.count()])
    }

    def create() {
        respond new DemandeVisiteMusee(params)
        /*List<Musee> musee = museeService.searchFavoris(true)
        render(view:'create',model:[museeInstance:musee]);*/
    }

    @Transactional
    def save(DemandeVisiteMusee demandeVisiteMuseeInstance) {
        if (demandeVisiteMuseeInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteMuseeInstance.hasErrors()) {
            respond demandeVisiteMuseeInstance.errors, view:'create'
            return
        }
        demandeVisiteMuseeInstance.save()

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musee.label', default: 'Musee'), demandeVisiteMuseeInstance.id])
                redirect demandeVisiteMuseeInstance
            }
            '*' { respond demandeVisiteMuseeInstance, [status: CREATED] }
        }

        /*Musee musee = Musee.get(params.museeInstance)
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
        String statut
        Random rn = new Random();
        switch (rn.nextInt()%4){
            case 0:
                statut ="En Attente"
                break;
            case 1:
                statut ="Confirme"
                break;
            default:
                statut ="Refusee"
                break;
        }
        DemandeVisite demandeVisite = new DemandeVisite(dateDebutPeriode:dateDebut,dateFinPeriode:dateFin,nbPersonnes:nbPersonne,statut:statut)
        int code = demandeVisiteService.getLastedCode(demandeVisite)
        demandeVisite.setCode(code)
        DemandeVisiteMusee demandeVisiteMuseeInstance = demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(demandeVisiteMusee,musee,demandeVisite)
        request.withFormat {
            form multipartForm {
                flash.message = "Votre Demande de visite a ete enregistré le code de celle ci est "+demandeVisiteMusee.demandeVisite.code
                redirect controller: "home", action: "index"
            }
            '*' { respond demandeVisiteMuseeInstance, [status: CREATED] }
        }*/
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
        demandeVisiteMuseeInstance.save()
        //demandeVisiteMuseeService.insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(demandeVisiteMuseeInstance,demandeVisiteMuseeInstance.getMusee(),demandeVisiteMuseeInstance.getDemandeVisite())


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
        demandeVisiteMuseeInstance.delete()
        //demandeVisiteMuseeService.deleteDemandeVisiteMusee(demandeVisiteMuseeInstance)


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
