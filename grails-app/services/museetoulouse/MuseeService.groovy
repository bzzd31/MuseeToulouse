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


}
