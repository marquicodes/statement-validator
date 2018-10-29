package me.markakis.demo.statementvalidator.web.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Replaces default ErrorController in order to add custom logic for error
 * pages.
 * 
 * @author marqui
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Determines the suitable error page to display, depended on the status
     * code.
     * 
     * @param request
     *            provides request information for HTTP servlets.
     * @return the suitable error page to display.
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (null != status) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (HttpStatus.NOT_FOUND.value() == statusCode) {
                return "errors/404";
            }
        }
        return "errors/error";
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
