package museetoulouse

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class HomeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    MuseeService museeService;

    def index(){
        System.out.println("yea")
        params.max = 2
        def museeListFavoris = museeService.searchFavoris(true,params)
        params.max = 3
        def museeList  = museeService.searchFavoris(false,params)
       [museeList:museeList,museeListCount:3,museeInstanceList: museeListFavoris, museeInstanceCount: museeListFavoris.totalCount]
    }
    def updateFavorisIndex(){
        def musee = Musee.get(params.id)
        if (musee){
            musee.favoris = !(musee.favoris)
            museeService.insertOrUpdateMuseeForGestionnaireAndAdress(musee,musee.getGestionnaire(),musee.getAdresse());
        }
        params.max = 2
        def museeListFavoris = museeService.searchFavoris(true,params)
        params.max = 3
        def museeList  = museeService.searchFavoris(false,params)
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
