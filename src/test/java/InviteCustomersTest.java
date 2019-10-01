import org.json.simple.parser.ParseException;
import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InviteCustomersTest {

    @Test
    public void testCalculateDistance(){
        double latitude = Math.toRadians(52.986375);
        double longitude = Math.toRadians(-6.043701);

        double actualDistance = InviteCustomers.calculateDistance(latitude,longitude);
        double expectedDistance = 41.676839095769566;
        int result = Double.compare(actualDistance,expectedDistance);
        assertEquals(result,0);
    }

    @Test
    public void testSelectCustomers() throws IOException, ParseException {
        String path = "/Users/fmalagrino/Documents/GPSProblem/src/main/resources/output.json";
        List<Customer> actualCustomers=  InviteCustomers.selectCustomers(path);
        List<Customer> expectedCustomers = new ArrayList<Customer>();
        actualCustomers.add(new Customer("Ian Kehoe",4));
        assertThat(actualCustomers).containsAllIn(expectedCustomers).inOrder();
    }
}
