package museetoulouse

import grails.transaction.Transactional

@Transactional
class MuseeService {

   Musee insertOrUpdateMuseeForGestionnaireAndAdress(Musee unMusee,Gestionnaire unGestionnaire,Adresse uneAdresse){
       uneAdresse.save();
       unMusee.adresse = uneAdresse;
       unGestionnaire.addToListeMusees(unMusee);
       unGestionnaire.save(flush: true);
       return unMusee;
   }

   Musee deleteMusee(Musee unMusee){
       unMusee.getGestionnaire().listeMusees.clear();
       unMusee.delete();
   }

    def searchMusee(String inNomMusee, String inCodePostal, String inNomRue,def params) {
        def criteria = Musee.createCriteria()
        def res = criteria.list (max:params.max,offset:params.offset){
            if (inNomMusee) {
                like 'nom', "%${inNomMusee}%".toUpperCase()
            }
            if (inCodePostal) {
                adresse {
                    eq ('codePostal', inCodePostal.toInteger().intValue())
                }
            }
            if (inNomRue) {
                adresse {
                    like 'rue', "%${inNomRue}%".toUpperCase()
                }
            }
        }
        res
    }

    def searchFavoris(boolean inFavoris,def params){
        def criteria = Musee.createCriteria()
        def res = criteria.list (max:params.max,offset:params.offset){
                    eq ('favoris', inFavoris)
        }
        res
    }

    def searchFavoris(boolean inFavoris){
        def criteria = Musee.createCriteria()
        def res = criteria.list(){
            eq ('favoris', inFavoris)
        }
        res
    }
}
