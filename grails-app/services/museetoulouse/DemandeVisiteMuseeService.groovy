package museetoulouse

import grails.transaction.Transactional

@Transactional
class DemandeVisiteMuseeService {

    DemandeVisiteMusee insertOrUpdateDemandeVisiteMuseeForMuseeAndDemandeVisite(DemandeVisiteMusee uneDemandeMusee,Musee unMusee,DemandeVisite uneDemandeVisite){
            uneDemandeVisite.save()
            uneDemandeMusee.musee = unMusee
            uneDemandeMusee.demandeVisite = uneDemandeVisite
            uneDemandeMusee.save()
            uneDemandeMusee
    }

   DemandeVisiteMusee deleteDemandeVisiteMusee(DemandeVisiteMusee uneDemandeMusee){
       uneDemandeMusee.delete()
       uneDemandeMusee
   }

}
