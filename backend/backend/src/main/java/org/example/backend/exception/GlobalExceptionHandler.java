package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les erreurs de validation déclenchées par @Valid.
     * Renvoie une réponse HTTP 400 (Bad Request) avec un corps JSON
     * contenant les champs en erreur et leurs messages respectifs.
     *
     * @param ex L'exception levée lors de la validation.
     * @return Une map des erreurs de validation.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * Gère toutes les autres exceptions non capturées.
     * Renvoie une réponse HTTP 500 (Internal Server Error) avec un message générique.
     *
     * @param ex L'exception inattendue.
     * @return Une map contenant le message d'erreur.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAllExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Une erreur inattendue est survenue sur le serveur.");
        // Il est conseillé de logger l'exception ici pour le débogage
        // ex.printStackTrace();
        return error;
    }
}
