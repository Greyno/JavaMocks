import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class InlineMockTest {

    //Method 1 to create a mock: Use a static method in Mockito (inline)
    @Test
    void testInlineMock() {
        Map mapMock = mock(Map.class); //mock a Java Map class
        assertEquals(mapMock.size(), 0);
    }


}
