package me.markakis.demo.statementvalidator.web.controllers;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.markakis.demo.statementvalidator.pojos.FailedRecord;
import me.markakis.demo.statementvalidator.services.ValidationService;
import me.markakis.demo.statementvalidator.utils.MultipartParser;

/**
 * The controller to render the home view of the application.
 * 
 * @author marqui
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MultipartParser     multipartParser;

    @Autowired
    private ValidationService   validationService;

    /**
     * Renders home page.
     * 
     * @param model
     *            contains attributes to be displayed on the view.
     * @return the name of the view to be rendered.
     */
    @GetMapping
    public String home(Model model) {
        return "home";
    }

    /**
     * Receives file upload.
     * 
     * @param file
     *            contains info and data of the uploaded file.
     * @param redirectAttributes
     *            contains attributes to be displayed on the view.
     * @return the name of the view to be rendered.
     */
    @PostMapping()
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        // if no file has been uploaded, redirects to the upload page
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("warn", "Please select a file to upload.");
            return "redirect:/";
        }

        try {
            List<FailedRecord> failedRecords = validationService
                    .validateTransactions(multipartParser.geTransactions(file));
            logger.debug("Found {} failed records.", failedRecords.size());
            redirectAttributes.addFlashAttribute("failedRecords", failedRecords);
            return "redirect:/results";
        } catch (InvalidFileNameException ifne) {
            redirectAttributes.addFlashAttribute("error",
                    "Please bear in mind that only .csv and .xml files are supported.");
            return "redirect:/";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getCause().getMessage());
            return "redirect:/";
        }

    }

    /**
     * Renders results page.
     * 
     * @param model
     *            contains attributes to be displayed on the view.
     * @return the name of the view to be rendered.
     */
    @GetMapping("results")
    public String results(Model model) {
        return "results";
    }

}
