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

    List<Musee> searchMusee(String inNomMusee, String inCodePostal, String inNomRue) {
        System.out.println(inNomMusee + " " + inCodePostal + " " + inNomRue)
        def criteria = Musee.createCriteria()
        List<Musee> res = criteria.list {
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
        System.out.println(res)
        res
    }
}
