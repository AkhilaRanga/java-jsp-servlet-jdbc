package com.learn.javaweb.util;

import java.util.ArrayList;
import java.util.List;

public class EmployeeValidator {
    private final List<String> errors = new ArrayList<>();

    public EmployeeValidator validateRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            errors.add(fieldName + " is required");
        }
        return this;
    }

    public EmployeeValidator validateCharLength(String value, int limit, String fieldName) {
        if (value != null && value.length() > limit) {
            errors.add(fieldName + " should not be more than " + limit + " characters");
        }
        return this;
    }

    public EmployeeValidator validateIntInRange(String value, int min, int max, String fieldName) {
        try {
            int intValue = Integer.parseInt(value);
            if (intValue < min || intValue > max) {
                errors.add(fieldName + " should be between " + min + " and " + max);
            }
        } catch (NumberFormatException e) {
            errors.add(fieldName + " should be a number");
        }
        return this;
    }

    public EmployeeValidator validateEmployeeJob(String jobValue) {
        if (jobValue == null || !(jobValue.equals("DEVELOPER") || jobValue.equals("MANAGER") || jobValue.equals("DIRECTOR"))) {
            errors.add("Job must be DEVELOPER, MANAGER, or DIRECTOR.");
        }
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
