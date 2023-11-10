package seg3102.group25.wellmeadows.hmspms.domain.patient.entities.prescription

import seg3102.group25.wellmeadows.hmspms.domain.common.DomainEvent
import java.util.*

class PatientPrescription(
    val id: UUID,
    val occurredOn: Date,
    val patientNumber: String
): DomainEvent