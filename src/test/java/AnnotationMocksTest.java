import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.ws.soap.MTOM;
import java.util.Map;

public class AnnotationMocksTest {

    //Method 2 to create a mock: Create a mock using an annotation
    @Mock
    Map<String, Object> mapMock;

    //Get a new Map mock for each test method
    @BeforeEach
    void  setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMock() {
        mapMock.put("kayValue", "foo");
    }
}
