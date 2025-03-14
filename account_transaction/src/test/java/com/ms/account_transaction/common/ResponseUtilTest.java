package com.ms.account_transaction.common;

import com.ms.account_transaction.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.ms.account_transaction.dto.response.ErrorResponse;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 * @author : Freddy Torres
 * file :  ResponseUtilTest
 * @since : 13/3/2025, jue
 *
**/

class ResponseUtilTest {
    @Test
    public void testCreateSuccessResponse() {
        // Given
        String message = "Success!";
        String data = "Some data";
        // When
        ApiResponse<String> response = ResponseUtil.createSuccessResponse(message, data);
        // Then
        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(message, response.getMessage());
        assertEquals(data, response.getData());
        assertNotNull(response.getTimestamp());
    }
    @Test
    public void testCreateErrorResponse() {
        // Given
        String message = "Error occurred!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        // Then
        Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/error-path");
        ServletWebRequest webRequest = Mockito.mock(ServletWebRequest.class);
        Mockito.when(webRequest.getRequest()).thenReturn(httpServletRequest);
        ResponseEntity<ErrorResponse> responseEntity = ResponseUtil.createErrorResponse(status, message, webRequest);
        ErrorResponse errorResponse = responseEntity.getBody();
        // Then
        assertNotNull(errorResponse);
        assertEquals(message, errorResponse.getMessage());
        assertEquals("/error-path", errorResponse.getPath());
        assertNotNull(errorResponse.getTimestamp());
        assertEquals(status, responseEntity.getStatusCode());
    }
}
