package museetoulouse

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class HomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    MuseeService museeService;

    def index(){
        def museeFavoris = museeService.searchFavoris(true)
        params.max = 3
        def museeList  = museeService.searchFavoris(false, params)
        [museeFavorisList:museeFavoris,museeInstanceList: museeList, museeInstanceCount: museeList.totalCount]
    }
    def updateFavorisIndex(){
        def musee = Musee.get(params.id)
        if (musee){
            musee.favoris = !(musee.favoris)
            museeService.insertOrUpdateMuseeForGestionnaireAndAdress(musee,musee.getGestionnaire(),musee.getAdresse());
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
