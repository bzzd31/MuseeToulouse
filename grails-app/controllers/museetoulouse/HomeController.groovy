package museetoulouse

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class HomeController {
    MuseeService museeService;
    def nomMusee
    def codePostal
    def rue
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]



    def index(){
        def museeListParams
        def museeFavorisParams
        if(params.paginate == 'Favoris'){
            params.max = 3
            museeFavorisParams = [max: params.max, offset: params.offset]
            session.museeFavorisParams = museeFavorisParams
        } else if(params.paginate == 'Musee') {
            params.max = 5
            museeListParams = [max: params.max, offset: params.offset]
            session.museeListParams = museeListParams
        }
        def museeFavoris = museeService.searchFavoris(true, session.museeFavorisParams?: [max: 3, offset: 0])
        def museeList = museeService.searchMusee(nomMusee, codePostal, rue,session.museeListParams?: [max: 5, offset: 0])
        params.offset = null
        params.max = null
        [museeFavorisList:museeFavoris,museeFavorisCount:museeFavoris.totalCount,museeInstanceList: museeList, museeInstanceCount: museeList.totalCount]
    }
    def updateFavorisIndex(){
        def musee = Musee.get(params.id)
        if (musee){
            musee.favoris = !(musee.favoris)
            museeService.save()
           // museeService.insertOrUpdateMuseeForGestionnaireAndAdress(musee,musee.getGestionnaire(),musee.getAdresse());
        }
    }



    def search() {
        if(nomMusee == null || params.nomMusee != null){
            nomMusee = params.nomMusee
        }
        if(codePostal == null || params.codePostal != null){
            codePostal = params.codePostal
        }
        if(rue == null || params.nomRue != null){
            rue = params.nomRue
        }
        def museeListParams
        def museeFavorisParams
        if(params.paginate == 'Favoris'){
            params.max = 3
            museeFavorisParams = [max: params.max, offset: params.offset]
            session.museeFavorisParams = museeFavorisParams
        } else if(params.paginate == 'Musee') {
            params.max = 5
            museeListParams = [max: params.max, offset: params.offset]
            session.museeListParams = museeListParams
        }
        def museeList = museeService.searchMusee(nomMusee, codePostal, rue,session.museeListParams?: [max: 5, offset: 0])
        def museeFavoris = museeService.searchFavoris(true,session.museeFavorisParams?: [max: 3, offset: 0])
        params.offset = null
        params.max = null
        render(view: 'index', model: [museeFavorisList: museeFavoris, museeFavorisCount: museeFavoris.totalCount, museeInstanceList: museeList, museeInstanceCount: museeList.totalCount])
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
