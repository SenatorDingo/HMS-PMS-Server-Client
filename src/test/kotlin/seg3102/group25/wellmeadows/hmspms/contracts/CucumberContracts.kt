package seg3102.group25.wellmeadows.hmspms.contracts

import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("seg3102/group25/wellmeadows/hmspms/contracts")
class CucumberContracts{

}