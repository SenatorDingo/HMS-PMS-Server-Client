package seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.implementation

import seg3102.group25.wellmeadows.hmspms.application.dtos.queries.RegisterPatientDTO
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.domain.constituent.entities.file.ConstituentFile
import seg3102.group25.wellmeadows.hmspms.domain.constituent.events.ConstituentFileCreated
import seg3102.group25.wellmeadows.hmspms.domain.constituent.events.ConstituentFileUpdated
import seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.ConstituentFacade
import seg3102.group25.wellmeadows.hmspms.domain.constituent.factories.ConstituentFileFactory
import seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories.ConstituentFileRepository
import java.util.*

class ConstituentFacadeImpl(
    private val constituentFileRepository: ConstituentFileRepository,
    private val constituentFileFactory: ConstituentFileFactory,
    private val eventEmitter: DomainEventEmitter
): ConstituentFacade {
    override fun createConstituentNOKFile(accountInfo: RegisterPatientDTO): Boolean {
        val constituentID = accountInfo.nextOfKinFirstName + accountInfo.nextOfKinLastName
        val existAccount = constituentFileRepository.findSync(constituentID)
        if (existAccount != null){
            return false
        }
        val constituentFile = constituentFileFactory.createConstituentFile(accountInfo)
        constituentFileRepository.save(constituentFile)
        eventEmitter.emit(
            ConstituentFileCreated(
            UUID.randomUUID(),
            Date(),
            constituentID
        )
        )
        return true
    }

    override fun updateConstituentNOKFile(constituentID: String, accountInfo: RegisterPatientDTO): Boolean {
        val constituentFile = constituentFileRepository.findSync(constituentID)
        if (constituentFile != null) {
            val updated = constituentFileFactory.createConstituentFile(accountInfo)
            constituentFile.update(updated)
            constituentFileRepository.save(constituentFile)
            eventEmitter.emit(
                ConstituentFileUpdated(
                UUID.randomUUID(),
                Date(),
                constituentID
                )
            )
            return true
        }
        return false
    }

    override fun getConstituentNOKFile(constituentID: String): ConstituentFile? {
        return constituentFileRepository.findSync(constituentID)
    }


}