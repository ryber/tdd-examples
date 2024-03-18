package examples;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoolBeanAsserts extends AbstractAssert<CoolBeanAsserts, CoolBean> {
    public CoolBeanAsserts(CoolBean o) {
        super(o, CoolBeanAsserts.class);
    }

    public static CoolBeanAsserts assertThat(CoolBean bean){
        return new CoolBeanAsserts(bean);
    }

    public CoolBeanAsserts hasName(String expectedName){
        isNotNull();
        if(!Objects.equals(actual.name(), expectedName)){
            failWithMessage("Expected name of %s but got %s", expectedName, actual.name());
        }
        return this;
    }

    public CoolBeanAsserts hasSignature(String s) {
        assertEquals(actual.sig(), s);
        return this;
    }
}
