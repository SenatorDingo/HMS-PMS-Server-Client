package seg3102.group25.wellmeadows.hmspms.infrastructure.web.services

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
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
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityAdmissionRepository
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

@Service
class ApplicationContextProvider {

    /* Emitters */
    final val domainEventEmitter: DomainEventEmitter = DomainEventEmitterAdapter()

    /* Listener */
    val domainEventListener: DomainEventListener = DomainEventListenerAdapter()

    /* Repositories */
    final val constituentFileRepository: ConstituentFileRepository = ConstituentFileRepoAdapter()
    final val facilityAdmissionWaitListRepository: FacilityAdmissionWaitListRepository = FacilityAdmissionWaitListRepoAdapter()
    final val facilityRepository: FacilityRepository = FacilityRepoAdapter()
    final val facilityShiftRepository: ShiftRepository = FacilityShiftRepoAdapter()
    final val patientFileRepository: PatientFileRepository = PatientFileRepoAdapter()
    final val patientPrescriptionRepository: PatientPrescriptionRepository = PatientPrescriptionRepoAdapter()
    final val staffAccountRepository: StaffAccountRepository = StaffAccountRepoAdapter()
    final val facilityAdmissionRepository: FacilityAdmissionRepository = FacilityAdmissionRepoAdapter()

    val admissionDtoFactory: AdmissionFactory = AdmissionDtoFactory()
    final val constituentFileFactory: ConstituentFileFactory = ConstituentFileDtoFactory()
    final val facilityAdmissionWaitListFactory: FacilityAdmissionWaitListFactory = FacilityAdmissionWaitListDtoFactory()
    final val facilityFactory: FacilityFactory = FacilityDtoFactory()
    final val patientFileFactory: PatientFileFactory = PatientFileDtoFactory()
    final val patientPrescriptionFactory: PatientPrescriptionFactory = PatientPrescriptionDtoFactory()
    final val shiftFactory: ShiftFactory = ShiftDtoFactory()
    final val staffAccountFactory: StaffAccountFactory = StaffAccountDtoFactory()

    /* Facades */
    final val constituentFacade: ConstituentFacade = ConstituentFacadeImpl(
        constituentFileRepository, constituentFileFactory, domainEventEmitter
    )
    final val facilityFacade: FacilityFacade = FacilityFacadeImpl(
        facilityRepository, facilityShiftRepository, facilityAdmissionWaitListRepository,
        facilityFactory, shiftFactory, facilityAdmissionWaitListFactory, domainEventEmitter
    )
    final val patientFacade: PatientFacade = PatientFacadeImpl(
        patientFileRepository, patientPrescriptionRepository, patientFileFactory,
        patientPrescriptionFactory, facilityAdmissionRepository, domainEventEmitter
    )
    final val securityFacade: SecurityFacade = SecurityFacadeImpl(
        staffAccountRepository, domainEventEmitter
    )
    final val staffFacade: StaffFacade = StaffFacadeImpl(
        staffAccountRepository, staffAccountFactory, domainEventEmitter
    )

    /* Use Cases */
    final val admitPatientUseCase: AdmitPatient = AdmitPatientImpl(
        securityFacade, patientFacade, staffFacade, facilityFacade
    )
    final val admitPatientRequestListUseCase: AdmitPatientRequestList = AdmitPatientRequestListImpl(
        securityFacade, facilityFacade, patientFacade
    )
    final val dischargePatientUseCase: DischargePatient = DischargePatientImpl(
        securityFacade, facilityFacade, patientFacade
    )
    final val prescribeMedicationUseCase: PrescribeMedication = PrescribeMedicationImpl(
        securityFacade, patientFacade
    )
    final val registerPatientUseCase: RegisterPatient = RegisterPatientImpl(
        securityFacade, patientFacade, constituentFacade
    )
    final val registerStaffUseCase: RegisterStaff = RegisterStaffImpl(
        securityFacade, staffFacade, facilityFacade
    )
    final val requestPatientAdmissionUseCase: RequestPatientAdmission = RequestPatientAdmissionImpl(
        securityFacade, patientFacade, facilityFacade
    )
    final val updatePatientFileUseCase: UpdatePatientFile = UpdatePatientFileImpl(
        securityFacade, patientFacade, constituentFacade
    )


    /* System */
    val patientManagementFacade: PatientManagementFacade = PatientManagementFacadeImpl(
        staffAccountRepository, domainEventEmitter, admitPatientUseCase,
        admitPatientRequestListUseCase, dischargePatientUseCase, prescribeMedicationUseCase,
        registerPatientUseCase, registerStaffUseCase, requestPatientAdmissionUseCase, updatePatientFileUseCase
    )


    @Bean
    fun providePatientFacade(): PatientFacade{
        return patientFacade
    }

    @Bean
    fun provideStaffRepository(): StaffAccountRepository {
        return staffAccountRepository
    }

    @Bean
    fun providePatientManagementFacade(): PatientManagementFacade {
        return patientManagementFacade
    }

    @Bean
    fun provideFacilityFacade(): FacilityFacade{
        return facilityFacade
    }
}