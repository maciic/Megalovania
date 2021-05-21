package hu.unideb.inf.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    private Validate validate;

    @BeforeEach
    public void setUp() throws Exception {
        validate = new Validate();
    }

    @Test
    @DisplayName("Email validation")
    public void testEmailValidation() {
        assertEquals(false,validate.validate(""),"Empty email address.");
        assertEquals(false,validate.validate("test"),"Not a valid email address.");
        assertEquals(false,validate.validate("test@."),"Not a valid email address.");
        assertEquals(false,validate.validate("test@gmail.c"),"Not a valid email address");
        assertEquals(true,validate.validate("test@gmail.com"),"Valid email address.");
    }
}