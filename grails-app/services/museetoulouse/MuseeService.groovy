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

    List<Musee> searchMusee(LinkedHashMap<String, Object> stringObjectLinkedHashMap) {
        def criteria = Musee.createCriteria()
        String nomMusee = stringObjectLinkedHashMap.get("nomMusee")
        String codePostal = stringObjectLinkedHashMap.get("codePostal")
        String nomRue = stringObjectLinkedHashMap.get("nomRue")

        List<Musee> res = criteria.list {
            if (nomMusee) {
                like 'nom', "%${nomMusee}%"
            }
            if (codePostal) {
                adresse {
                    eq ('codePostal', codePostal.toInteger().intValue())
                }
            }
            if (nomRue) {
                adresse {
                    like 'rue', "%${nomRue}%"
                }
            }
        }
        res
    }
}
