import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.taekwondoscheduler.model.business.AllBusinessTests;
import com.taekwondoscheduler.model.domain.AllDomainTests;
import com.taekwondoscheduler.model.services.AllServiceTests;


/**
 * Application Test Suite tests all tests
 */
@Suite
@SelectClasses({AllBusinessTests.class, AllServiceTests.class, AllDomainTests.class})
public class AllTests {

}
