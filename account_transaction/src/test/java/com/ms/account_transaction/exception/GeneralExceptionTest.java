package com.ms.account_transaction.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Freddy Torres
 * file :  GeneralExceptionTest
 * @since : 12/3/2025, mié
 **/

class GeneralExceptionTest {
    @Test
    public void testGeneralException_Message() {
        // Given
        String expectedMessage = "This is a general exception";
        // When
        GeneralException exception = new GeneralException(expectedMessage);
        // Then
        assertNotNull(exception);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testGeneralException_WithNullMessage() {
        // Given
        GeneralException exception = new GeneralException(null);
        // Then
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    public void testGeneralException_ThrowsException() {
        // Then
        assertThrows(GeneralException.class, () -> {
            throw new GeneralException("Exception thrown");
        });
    }
}
