package seg3102.group25.wellmeadows.hmspms.contracts.steps

import org.springframework.beans.factory.annotation.Autowire
import seg3102.group25.wellmeadows.hmspms.application.dtos.*
import seg3102.group25.wellmeadows.hmspms.application.services.*
import seg3102.group25.wellmeadows.hmspms.application.usecases.*
import seg3102.group25.wellmeadows.hmspms.contracts.testStubs.*
import seg3102.group25.wellmeadows.hmspms.domain.*


import io.cucumber.java.en.And
import io.cucumber.java.en.But
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions
import seg3102.group25.wellmeadows.hmspms.contracts.testStubs.factories.*
import seg3102.group25.wellmeadows.hmspms.contracts.testStubs.repositories.*
import seg3102.group25.wellmeadows.hmspms.contracts.testStubs.services.EventEmitterStub
import seg3102.group25.wellmeadows.hmspms.domain.facility.factories.FacilityFactory

class StepsDefinition{

    private var constituentFileFactory = ConstituentFileFactoryStub()
    private var constituentFileRepository = ConstituentFileRepositoryStub()
    private var facilityAdmissionWaitListFactory = FacilityAdmissionWaitListFactoryStub()
    private var facilityAdmissionWaitListRepository = FacilityAdmissionWaitListRepositoryStub()
    private var facilityFactory = FacilityFactoryStub()
    private var facilityRepository = FacilityRepositoryStub()
    private var patientFileFactory = PatientFileFactoryStub()
    private var patientFileRepository = PatientFileRepositoryStub()
    private var patientPrescriptionFactory = PatientPrescriptionFactoryStub()
    private var patientPrescriptionRepository = PatientPrescriptionRepositoryStub()
    private var shiftFactory = ShiftFactoryStub()
    private var shiftRepository = ShiftRepositoryStub()
    private var eventEmitter = EventEmitterStub()

    @Given("The HMS is ON")
    fun checkHMSActive(){
        Assertions.assertTrue(true)
    }

    @Then("The Staff Member selects to register")
    fun initializeStaffRegistration(){

    }

    @And("The HMS asks for Staff Member information")
    fun requestStaffInformation(){}

    @And("The Staff Member provides all the required information")
    fun submitStaffInformation(){}

    @Then("The HMS displays an acknowledgement message")
    fun displayStaffRegistrationAcknowledgement(){}

    @And("The Staff Member is registered")
    fun checkStaffRegistration(){}

    @Then("The HMS displays an incomplete staff information error message")
    fun throwIncompleteStaffInformationError(){}

    @But("The user is not found in the system")
    fun checkUserStaffExists(){}

    @And("The Staff Member is not already registered")
    fun checkUserStaffNotExist(){}

    @And("The HMS displays an invalid uer error message")
    fun throwInvalidStaffUserError(){}

    @And("The Staff Member is not logged in")
    fun checkStaffNotLoggedIn(){}

    @And("The Staff Member is logged in")
    fun checkStaffLoggedIn(){}

    @When("The Staff Member submits login information")
    fun submitStaffLoginInformation(){}

    @And("The HMS checks Staff Member for authorization")
    fun checkStaffAuthorization(){}

    @Then("The HMS displays Staff Member operations")
    fun displayStaffOperations(){}

    @And("The Staff Member operation menu is displayed")
    fun checkStaffOperationsDisplayed(){}

    @And("The Staff Member is not authorized")
    fun staffNotAuthorized(){}

    @And("The Staff Member is authorized")
    fun staffAuthorized(){}

    @Then("The HMS displays unauthorized access error message")
    fun throwUnauthorizedStaffError(){}

    @When("The Staff Member initiates Log Out")
    fun initiateStaffLogOut(){}

    @Then("The HMS displays a Log Out acknowledgment")
    fun displayLogOutAcknowledgment(){}

    @When("The Staff Member initiates Patient Registration")
    fun initiatePatientRegistration(){}

    @Then("The HMS registers the Staff Member")
    fun registerStaff(){}

    @And("The HMS request Patient information")
    fun requestPatientInformation(){}

    @And("The Staff Member submits patient information")
    fun submitPatientInformation(){}

    @And("The information provided is complete")
    fun patientInformationValid(){}

    @And("The information provided is incomplete")
    fun patientInformationInvalid(){}

    @Then("The HMS issues new identification for Patient")
    fun issuePatientIdentification(){}

    @And("The HMS registers the Patient")
    fun registerPatient(){}

    @And("The HMS displays an incomplete patient information error message")
    fun throwIncompletePatientInformationError(){}

    @When("The Staff Member initiates viewing Patient File")
    fun initiateViewingPatientFile(){}

    @And("The HMS requests Patient identification number")
    fun requestPatientIdentificationNumber(){}

    @And("The Staff Member inputs a valid Patient identification number")
    fun checkValidPatientIdentificationNumber(){}

    @And("The Staff Member inputs a invalid Patient identification number")
    fun checkInvalidPatientIdentificationNumber(){}

    @Then("The HMS displays the Patients File")
    fun displayPatientFile(){}

    @Then("The HMS displays a Patient not found error message")
    fun throwInvalidPatientIdentificationError(){}

    @When("Consult Patient File")
    fun consultPatientFile(){}

    @And("The Staff Member modifies the Patient information")
    fun attemptModifyPatientInformation(){}

    @And("The Staff Member submits the changes")
    fun submitModifyPatientInformation(){}

    @Then("The HMS updates the Patient File")
    fun modifyPatientInformation(){}

    @Then("The HMS displays a modification not allowed error message")
    fun throwNotAllowedModificationError(){}

    @And("The Staff Member is a Charge Nurse")
    fun checkStaffTypeChargeNurse(){}

    @When("The Staff Member selects to Visualize Division")
    fun initializeVisualizeDivision(){}

    @And("The HMS requests Division Identifier")
    fun requestDivisionIdentifier(){}

    @And("The Staff Member submits correct division identifier")
    fun submitAndCheckValidDivisionIdentifier(){}

    @Then("The HMS displays information about division")
    fun displayDivisionInformation(){}

    @And("The Staff Member submits incorrect division identifier")
    fun submitAndCheckInvalidDivisionIdentifier(){}

    @Then("The HMS displays a division not found error message")
    fun throwUnknownDivisionIdentifierError(){}

    @When("The Staff Member initiates Patient Admittance")
    @Then("initiate Admit Patient")
    fun initiatePatientAdmission(){}

    @And("The HMS request room and bed number")
    fun requestRoomAndBedNumber(){}

    @And("The Staff browses and selects or enters room and bed number")
    fun displaySelectionRoomAndBedNumber(){}

    @And("The Staff Member submits room and bed number")
    fun submitRoomAndBedNumber(){}

    @And("The HMS asks for remaining admission information")
    fun requestOtherAdmittanceInformation(){}

    @And("The Staff Member submits requested information")
    fun submitOtherAdmittanceInformation(){}

    @And("The HMS admits the Patient")
    fun admitPatient(){}

    @And("The Division is complete")
    fun divisionIsComplete(){}

    @Then("The HMS notifies the Staff Member that the division is complete")
    fun displayDivisionIsComplete(){}

    @And("The HMS displays Request Patient Admission")
    fun requestPatientAdmission(){}

    @When("The Staff Member initiates request patient admission")
    fun initiateRequestPatientAdmission(){}

    @Then("The HMS assigns patient to ward for awaiting admission")
    fun assignPatientToWardAdmissionWaitList(){}

    @And("The HMS notifies Ward Charge Nurse")
    fun notifyWardChargeNursePatientAdmissionWaitList(){}

    @And("The Staff Member submits correct request admission information")
    fun submitRequestPatientAdmissionValid(){}

    @And("The Staff Member submits incorrect request admission information")
    fun submitRequestPatientAdmissionInvalid(){}

    @Then("The HMS displays an incorrect request patient admission message")
    fun throwRequestPatientAdmissionInformationError(){}

    @And("The Patient is already admitted to division")
    fun checkPatientAlreadyAdmittedDivision(){}

    @Then("The HMS displays a patient already in ward error message")
    fun throwPatientAlreadyAdmittedToWardError(){}

    @And("There is a Patient on the Admission Request List")
    fun checkPatientAdmissionRequestListNotEmpty(){}

    @When("The Staff Member views Admission Request List")
    fun viewPatientAdmissionRequestList(){}

    @And("The Staff Member selects Patient from Admission Request List")
    fun submitSelectedPatientFromAdmissionRequestList(){}

    @And("The HMS displays the Patient Registration")
    fun displayPatientRegistration(){}

    @And("The Staff Member invokes admission of the Patient")
    fun invokeAdmissionOfPatient(){}

    @And("The Patient Admission not possible")
    fun patientAdmissionNotPossible(){}

    @Then("The Staff Member denies Patient Admission")
    fun deniesPatientAdmission(){}

    @And("The HMS notifies Charge Nurse who requested admission")
    fun notifyRequestedAdmissionChargeNurse(){}

    @When("The Staff Member selects Patient discharge")
    fun selectPatientDischarge(){}

    @And("The Staff Member submits discharge for Patient")
    fun submitPatientDischarge(){}

    @Then("The HMS updated bed availability")
    fun updateBedAvailability(){}

    @And("The HMS displays discharge information")
    fun displayPatientDischargeInformation(){}

    @And("The Patient is no longer admitted")
    fun setPatientToNoLongerAdmitted(){}

    @And("The Patients bed is added to the division availability")
    fun updateDivisionAvailability(){}

    @And("discharge information slated to be sent to Patient")
    fun prepareDischargeDocumentsForPatient(){}

    @And("The Staff Member is a Doctor")
    fun checkStaffTypeDoctor(){}

    @When("The Staff Member requests to add prescription to Patient")
    fun requestAddPrescriptionPatient(){}

    @And("The HMS requests for required prescription information")
    fun requestPrescriptionPatientInformation(){}

    @And("The Staff Member submits required prescription information")
    fun submitPrescriptionPatientInformation(){}

    @And("The HMS records the prescription in Patient File")
    fun updatePrescriptionPatientFile(){}

    @And("The Selected Patient not assigned to current Doctor")
    fun isPatientUnderDoctorCareInvalid(){}

    @Then("The HMS notifies Doctor that the patient is not under their case")
    fun notifyDoctorOfPatientNotUnderTheirCare(){}

    @And("The Staff Member submits incorrect prescription information")
    fun submitIncorrectPrescriptionInformation(){}

    @Then("The HMS displays an incorrect prescription information error message")
    fun throwIncorrectPrescriptionInformationError(){}
}
