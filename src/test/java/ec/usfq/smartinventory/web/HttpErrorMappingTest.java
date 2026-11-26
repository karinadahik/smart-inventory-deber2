package ec.usfq.smartinventory.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.usfq.smartinventory.application.InventoryQueryService;
import ec.usfq.smartinventory.application.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** Regresion: errores del cliente no deben convertirse en HTTP 500. */
class HttpErrorMappingTest {

    private GlobalExceptionHandler advice() {
        return new GlobalExceptionHandler(new ProblemWriter(new ObjectMapper()));
    }

    private MockMvc mvc() {
        return MockMvcBuilders.standaloneSetup(
                        new InventoryController(mock(InventoryQueryService.class)),
                        new ReservationController(mock(ReservationService.class)))
                .setControllerAdvice(advice())
                .build();
    }

    @Test
    void missingStaticOrApiResourceMapsTo404ProblemJson() {
        var response = advice().notFound(
                new NoResourceFoundException(HttpMethod.GET, "api/v1/inventory/noexiste"));
        assertEquals(404, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("NOT_FOUND", response.getBody().get("code"));
        assertEquals(MediaType.parseMediaType(ProblemWriter.PROBLEM_JSON),
                response.getHeaders().getContentType());
    }

    @Test
    void unsupportedMethodMapsTo405WithAllowHeader() throws Exception {
        mvc().perform(patch("/api/v1/inventory"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(header().exists("Allow"))
                .andExpect(jsonPath("$.code").value("METHOD_NOT_ALLOWED"));
    }

    @Test
    void unsupportedContentTypeMapsTo415() throws Exception {
        mvc().perform(post("/api/v1/reservations")
                        .header("Idempotency-Key", "diagnostico-http")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("texto de prueba"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.code").value("UNSUPPORTED_MEDIA_TYPE"));
    }
}
