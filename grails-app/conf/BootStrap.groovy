import museetoulouse.RemplirBaseDeDonneesService

class BootStrap {

    RemplirBaseDeDonneesService remplirBaseDeDonneesService
    def init = { servletContext ->
        remplirBaseDeDonneesService.creationDesAdresses()
        remplirBaseDeDonneesService.creationDesGestionnaires()
        remplirBaseDeDonneesService.creationDesMusees()
    }
    def destroy = {
    }
}
