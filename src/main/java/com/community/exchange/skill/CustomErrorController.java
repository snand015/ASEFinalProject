package com.community.exchange.skill;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletRequest request) {
        // Add your error handling logic here
        return "error"; // Return the name of the error view
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}


