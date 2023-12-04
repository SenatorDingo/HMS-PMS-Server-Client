package seg3102.group25.wellmeadows.hmspms

import com.google.cloud.storage.Acl.Domain
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableAsync
import seg3102.group25.wellmeadows.hmspms.adapters.factories.*
import seg3102.group25.wellmeadows.hmspms.adapters.repositories.*
import seg3102.group25.wellmeadows.hmspms.adapters.services.implementation.application.DomainEventEmitterAdapter
import seg3102.group25.wellmeadows.hmspms.adapters.services.implementation.application.DomainEventListenerAdapter
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventEmitter
import seg3102.group25.wellmeadows.hmspms.application.services.DomainEventListener
import seg3102.group25.wellmeadows.hmspms.application.usecases.*
import seg3102.group25.wellmeadows.hmspms.application.usecases.implementation.*
import seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.ConstituentFacade
import seg3102.group25.wellmeadows.hmspms.domain.constituent.facade.implementation.ConstituentFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.constituent.factories.ConstituentFileFactory
import seg3102.group25.wellmeadows.hmspms.domain.constituent.repositories.ConstituentFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.FacilityFacade
import seg3102.group25.wellmeadows.hmspms.domain.facility.facade.implementation.FacilityFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.AdmissionFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityAdmissionWaitListFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.ShiftFactory
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionWaitListRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.ShiftRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.PatientFacade
import seg3102.group25.wellmeadows.hmspms.domain.patient.facade.implementation.PatientFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientFileFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.factories.PatientPrescriptionFactory
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientFileRepository
import seg3102.group25.wellmeadows.hmspms.domain.patient.repositories.PatientPrescriptionRepository
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.PatientManagementFacade
import seg3102.group25.wellmeadows.hmspms.domain.patientManagement.facade.implementation.PatientManagementFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.SecurityFacade
import seg3102.group25.wellmeadows.hmspms.domain.security.facade.implementation.SecurityFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.StaffFacade
import seg3102.group25.wellmeadows.hmspms.domain.staff.facade.implementation.StaffFacadeImpl
import seg3102.group25.wellmeadows.hmspms.domain.staff.factories.StaffAccountFactory
import seg3102.group25.wellmeadows.hmspms.domain.staff.repositories.StaffAccountRepository
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.services.FireBaseInitialize


@SpringBootApplication
@EnableAsync
class HmspmsApplication

fun main(args: Array<String>) {
	runApplication<HmspmsApplication>(*args)
}
