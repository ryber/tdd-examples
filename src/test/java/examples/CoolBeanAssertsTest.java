package examples;

import org.junit.jupiter.api.Test;

import static examples.CoolBeanAsserts.assertThat;

class CoolBeanAssertsTest {

    @Test
    void testName() {
        assertThat(new CoolBean("Elmo"))
                .hasName("Elmo")
                .hasSignature("21c004c9cf00b5b6189f63e8a2162d434fc32b49b62a72ffd1645560b8e270ab");
    }
}