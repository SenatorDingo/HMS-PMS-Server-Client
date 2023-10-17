package seg3102.group25.wellmeadows.hmspms.contracts.steps

import io.cucumber.java.en.*
import org.assertj.core.api.Assertions

class CucumberTestSteps{

        private var cucumberDetectedTest: Boolean = false

        @Given("Cucumber Detected The Test")
        fun cucumberDetectedTest(){
                cucumberDetectedTest = true
        }

        @Then("Cucumber Succeeded Test Detection")
        fun cucumberDetectedTestSuccessfully(){
                Assertions.assertThat(cucumberDetectedTest).isTrue
        }
}
