package me.markakis.demo.statementvalidator.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Declares exception handler to be shared across multiple @Controller classes.
 * 
 * @author marqui
 */
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * Handles MaxUploadSizeExceeded exceptions.
     * 
     * @param model
     *            contains attributes to be displayed on the view.
     * @param exception
     *            contains info about the exception occurred.
     * @return the name of the view to be rendered.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(Model model, MaxUploadSizeExceededException exception) {
        model.addAttribute("warn", "File exceeds size limit. Please select a file less than 2MB.");
        return "home";
    }

}
