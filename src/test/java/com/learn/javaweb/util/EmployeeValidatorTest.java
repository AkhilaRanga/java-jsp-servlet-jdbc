package com.learn.javaweb.util;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeValidatorTest {

    @Test
    void testValidateRequired_withNullOrBlank_shouldHaveError() {
        EmployeeValidator validator =
                new EmployeeValidator()
                        .validateRequired(null, "First name")
                        .validateRequired("   ", "Last name");

        Assertions.assertTrue(validator.hasErrors());
        List<String> errors = validator.getErrors();
        Assertions.assertEquals(2, errors.size());
        Assertions.assertEquals("First name is required", errors.get(0));
        Assertions.assertEquals("Last name is required", errors.get(1));
    }

    @Test
    void testValidateRequired_withValidValue_shouldHaveNoErrors() {
        EmployeeValidator validator =
                new EmployeeValidator().validateRequired("Jake", "First name");

        Assertions.assertFalse(validator.hasErrors());
    }

    @Test
    void testValidateCharLength_withExceedingChars_shouldHaveError() {
        EmployeeValidator validator =
                new EmployeeValidator().validateCharLength("abcdef", 5, "First name");

        Assertions.assertTrue(validator.hasErrors());
        Assertions.assertEquals(
                List.of("First name should not be more than 5 characters"), validator.getErrors());
    }

    @Test
    void testValidateCharLength_withValidLength_shouldHaveNoErrors() {
        EmployeeValidator validator =
                new EmployeeValidator().validateCharLength("Jake", 20, "First name");

        Assertions.assertFalse(validator.hasErrors());
    }

    @Test
    void validateIntInRange_withInValidValue_shouldHaveError() {
        EmployeeValidator validator =
                new EmployeeValidator().validateIntInRange("String", 18, 50, "Age");

        Assertions.assertTrue(validator.hasErrors());
        Assertions.assertEquals(List.of("Age should be a number"), validator.getErrors());
    }

    @Test
    void validateIntInRange_notWithinRange_shouldHaveError() {
        EmployeeValidator validator =
                new EmployeeValidator().validateIntInRange("15", 18, 50, "Age");

        Assertions.assertTrue(validator.hasErrors());
        Assertions.assertEquals(List.of("Age should be between 18 and 50"), validator.getErrors());
    }

    @Test
    void validateEmployeeJob_withInvalidValue_shouldHaveError() {
        EmployeeValidator validator = new EmployeeValidator().validateEmployeeJob("ARCHITECT");

        Assertions.assertTrue(validator.hasErrors());
        Assertions.assertEquals(
                List.of("Job must be DEVELOPER, MANAGER, or DIRECTOR"), validator.getErrors());
    }

    @Test
    void validateEmployeeJob_withValidValue_shouldHaveNoErrors() {
        EmployeeValidator validator =
                new EmployeeValidator()
                        .validateEmployeeJob("DEVELOPER")
                        .validateEmployeeJob("MANAGER")
                        .validateEmployeeJob("DIRECTOR");

        Assertions.assertFalse(validator.hasErrors());
    }

    @Test
    void testMultipleFieldValidation() {
        EmployeeValidator validator =
                new EmployeeValidator()
                        .validateRequired("John", "First Name")
                        .validateRequired("", "Last Name")
                        .validateCharLength("Smith", 10, "Last Name");

        Assertions.assertTrue(validator.hasErrors());
        List<String> errors = validator.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("Last Name is required", errors.get(0));
    }
}
