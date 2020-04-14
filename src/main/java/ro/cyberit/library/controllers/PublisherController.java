package ro.cyberit.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.cyberit.library.repositories.PublisherRepository;

@Controller
public class PublisherController {

    @Autowired
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @RequestMapping(value = "/publishers")
    public String getPublishers(Model model){

        model.addAttribute("publishers", publisherRepository.findAll());

        return "publishers/list";
    }
}
