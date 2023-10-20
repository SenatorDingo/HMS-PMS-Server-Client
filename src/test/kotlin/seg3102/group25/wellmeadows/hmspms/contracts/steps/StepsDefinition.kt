package seg3102.group25.wellmeadows.hmspms.contracts.steps

import io.cucumber.java.en.And
import io.cucumber.java.en.But
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

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

    @Then("The HMS displays unauthorized access error message")
    fun throwUnauthorizedStaffError(){}


}
