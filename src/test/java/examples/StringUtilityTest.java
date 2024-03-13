package examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilityTest {

    @Test
    void canJoinWithSpaces() {
        var stringUtil = new StringUtility();
        assertEquals("Hello World",
                stringUtil.joinWithSpace("Hello", "World"));
    }

}