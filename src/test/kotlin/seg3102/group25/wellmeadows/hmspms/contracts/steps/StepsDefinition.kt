package seg3102.group25.wellmeadows.hmspms.contracts.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.But
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class StepsDefinition{

    @Given("The HMS is ON")
    fun checkHMSActive(){}

    @Then("The Staff Member selects to register")
    fun initializeStaffRegistration(){}

    @And("The HMS asks for Staff Member information")
    fun requestStaffInformation(){}

    @And("The Staff Member provides all the required information")
    fun submitStaffInformation(){}

    @Then("The HMS displays an acknowledgement message")
    fun displayStaffRegistrationAcknowledgement(){}

    @And("The Staff Member is registered")
    fun checkStaffRegistration(){}

    @Then("The HMS displays an incomplete information error message")
    fun throwIncompleteStaffInformationError(){}

    @But("The user is not found in the system")
    fun checkUserStaffExists(){}

    @And("The Staff Member is not already registered")
    fun checkUserStaffNotExist(){}

    @And("The HMS displays an invalid uer error message")
    fun throwInvalidStaffUserError(){}


}
