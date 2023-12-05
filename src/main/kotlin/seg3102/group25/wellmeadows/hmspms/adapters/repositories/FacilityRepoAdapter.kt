package seg3102.group25.wellmeadows.hmspms.adapters.repositories

import com.google.firebase.database.*
import kotlinx.coroutines.*
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admission.Admission
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.admissionWaitList.FacilityAdmissionWaitList
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.division.FacilityDivision
import seg3102.group25.wellmeadows.hmspms.domain.facility.entities.shift.FacilityShift
import seg3102.group25.wellmeadows.hmspms.domain.facility.repositories.FacilityRepository
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityStatus
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.FacilityType
import seg3102.group25.wellmeadows.hmspms.domain.facility.valueObjects.ShiftType
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseFacility
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseFacilityAdministrationWaitList
import seg3102.group25.wellmeadows.hmspms.infrastructure.database.mapper.DatabaseFacilityShift

class FacilityRepoAdapter: FacilityRepository {

    private val dataBase: FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun findSync(facilityIdentity: FacilityType): FacilityDivision? {
        val existFile: FacilityDivision?
        runBlocking { existFile = find(facilityIdentity) }
        return existFile
    }

    override suspend fun find(facilityIdentity: FacilityType): FacilityDivision? {
        val ref: DatabaseReference = dataBase.reference
        val uidRef = ref.child("facilities").orderByChild("divisionId").equalTo(facilityIdentity.facilityDivisionID)

        val errorAccount = FacilityDivision("", "", "", "",
            "", "", "", -1, "")
        val deferred = CompletableDeferred<FacilityDivision?>()

        val timeoutJob = CoroutineScope(Dispatchers.Default).launch {
            delay(10000) // Timeout after 5 seconds (adjust as needed)
            if (!deferred.isCompleted) { // Check if the deferred is not completed
                deferred.complete(errorAccount) // Complete with errorAccount in case of timeout
            }
        }

        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                timeoutJob.cancel()
                deferred.complete(errorAccount)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    //val firebaseStaffAccount = snapshot.getValue(DatabaseStaffAccount::class.java)
                    // Workaround - WORKS -
                    val firebaseFacilityDivision = snapshot.children.firstOrNull()?.let { facilitySnapshot ->
                        val facilityDivision = DatabaseFacility()
                        facilitySnapshot.children.forEach { dataD ->
                            when (dataD.key) {
                                "divisionId"-> facilityDivision.divisionId = dataD.value as? String
                                "divisionName"-> facilityDivision.divisionName = dataD.value as? String
                                "chargeNurseFirstName"-> facilityDivision.chargeNurseFirstName = dataD.value as? String
                                "chargeNurseLastName"-> facilityDivision.chargeNurseLastName = dataD.value as? String
                                "chargeNurseTelExtension"-> facilityDivision.chargeNurseTelExtension = dataD.value as? String
                                "chargeNurseBipExtension"-> facilityDivision.chargeNurseBipExtension = dataD.value as? String
                                "location"-> facilityDivision.location = dataD.value as? String
                                "numberBeds"-> facilityDivision.numberBeds = dataD.getValue(Int::class.java)
                                "telephoneExtension"-> facilityDivision.telephoneExtension = dataD.value as? String
                                "facilityType"-> facilityDivision.facilityType = dataD.getValue(FacilityType::class.java)
                                "numberBedsAvailable"-> facilityDivision.numberBedsAvailable = dataD.getValue(Int::class.java)
                                "status"-> facilityDivision.status = dataD.getValue(FacilityStatus::class.java)
                                "shifts"->{
                                    dataD.children.forEach { dataS ->
                                        val shiftS = DatabaseFacilityShift()
                                        dataS.children.forEach { dataSS ->
                                            when (dataSS.key) {
                                                "createdOn" -> shiftS.staffNumber = dataSS.value as? String
                                                "updatedLast" -> shiftS.shiftType = dataSS.getValue(ShiftType::class.java)
                                                "prescriptionID" -> shiftS.division = dataSS.value as? String
                                            }
                                        }
                                        facilityDivision.shifts?.add(
                                            FacilityShift(
                                                shiftS.staffNumber ?: "",
                                                shiftS.shiftType ?: ShiftType.Early,
                                                shiftS.division ?: ""
                                            )
                                        )
                                    }
                                }
                                "admissionWaitList"->{
                                    dataD.children.forEach { dataWL ->
                                        val admissionWaitList = DatabaseFacilityAdministrationWaitList()
                                        dataWL.children.forEach { dataWLC ->
                                            when (dataWLC.key) {
                                                "patientId" -> admissionWaitList.patientId = dataWLC.value as? String
                                                "chargeNurseId" -> admissionWaitList.chargeNurseId = dataWLC.value as? String
                                                "division" -> admissionWaitList.division = dataWLC.value as? String
                                                "admissionStatus" -> admissionWaitList.admissionStatus = dataWLC.value as? String
                                                "priority" -> admissionWaitList.priority = dataWLC.getValue(Int::class.java)
                                                "createdOn" -> admissionWaitList.createdOn = dataWLC.value as? String
                                            }
                                        }
                                        facilityDivision.admissionWaitList?.add(
                                            FacilityAdmissionWaitList(
                                                admissionWaitList.patientId ?: "",
                                                admissionWaitList.chargeNurseId ?: "",
                                                admissionWaitList.division ?: "",
                                                admissionWaitList.admissionStatus ?: "",
                                                admissionWaitList.priority ?: -1,
                                                admissionWaitList.createdOn ?: "",
                                            )
                                        )
                                    }
                                }
                                "admissions"->{
                                    dataD.children.forEach { dataWL ->
                                        val admission = Admission("", "", "", "", "")
                                        dataWL.children.forEach { dataWLC ->
                                            when (dataWLC.key) {
                                                "patientNumber" -> admission.patientNumber = dataWLC.value as? String ?: ""
                                                "localDoctor" -> admission.localDoctor = dataWLC.value as? String ?: ""
                                                "roomNumber" -> admission.roomNumber = dataWLC.value as? String ?: ""
                                                "bedNumber" -> admission.bedNumber = dataWLC.value as? String ?: ""
                                                "privateInsuranceNumber" -> admission.privateInsuranceNumber = dataWLC.value as? String ?: ""
                                            }
                                        }
                                        facilityDivision.admissions?.add(admission)
                                    }
                                }
                            }
                        }

                        facilityDivision
                    }
                    val facilityDivision = FacilityDivision(
                        firebaseFacilityDivision?.divisionId ?: "",
                        firebaseFacilityDivision?.divisionName ?: "",
                        firebaseFacilityDivision?.chargeNurseFirstName ?: "",
                        firebaseFacilityDivision?.chargeNurseLastName ?: "",
                        firebaseFacilityDivision?.chargeNurseTelExtension ?: "",
                        firebaseFacilityDivision?.chargeNurseBipExtension ?: "",
                        firebaseFacilityDivision?.location ?: "",
                        firebaseFacilityDivision?.numberBeds ?: -1,
                        firebaseFacilityDivision?.telephoneExtension ?: "",
                    )
                    facilityDivision.facilityType = firebaseFacilityDivision?.facilityType ?: FacilityType.None
                    facilityDivision.numberBedsAvailable = firebaseFacilityDivision?.numberBedsAvailable ?: -1
                    facilityDivision.status = firebaseFacilityDivision?.status ?: FacilityStatus.Incomplete
                    facilityDivision.shifts = firebaseFacilityDivision?.shifts ?: mutableListOf()
                    facilityDivision.admissionWaitList = firebaseFacilityDivision?.admissionWaitList ?: mutableListOf()
                    facilityDivision.admissions = firebaseFacilityDivision?.admissions ?: mutableListOf()
                    timeoutJob.cancel()
                    deferred.complete(facilityDivision)
                } else {
                    timeoutJob.cancel()
                    deferred.complete(null)
                }
            }
        }
        uidRef.addListenerForSingleValueEvent(valueEventListener)

        val result = deferred.await()

        // Remove the listener after getting the result or timeout
        uidRef.removeEventListener(valueEventListener)

        return result
    }

    override fun save(division: FacilityDivision): FacilityDivision {
        val newNode: DatabaseReference = FirebaseDatabase.getInstance().reference
            .child("facilities").child(division.divisionId)
        newNode.setValueAsync(division)
        return division
    }

}