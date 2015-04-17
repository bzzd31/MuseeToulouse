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

    DemandeVisiteMusee findsDemandeVisite(DemandeVisite demande){
        DemandeVisiteMusee demandeVisiteMusee = DemandeVisiteMusee.find("from DemandeVisiteMusee as d where d.demandeVisite.id =? ",[demande.id])
        demandeVisiteMusee
    }


   DemandeVisiteMusee deleteDemandeVisiteMusee(DemandeVisiteMusee uneDemandeMusee){
       uneDemandeMusee.delete()
       uneDemandeMusee
   }

}
