package net.canadensys.api.narwhal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle HTTP error (like 404) in a template.
 * @author canadensys
 *
 */
@Controller
public class HttpErrorController {
	@RequestMapping(value="/errors/404.html")
    public String handle404(HttpServletRequest request) {
        return "error/404";
    }
}
