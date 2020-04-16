package ro.cyberit.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.cyberit.library.exceptions.ResourceNotFoundException;
import ro.cyberit.library.model.Publisher;
import ro.cyberit.library.repositories.PublisherRepository;

import java.util.Optional;

@Controller
public class PublisherController {

    @Autowired
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @RequestMapping(value = "/publishers")
    public String getPublishers(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        return "publishers/list";
    }

    @RequestMapping("publisher/details/{id}")
    public String getPublisherDetails(@PathVariable Long id, Model model){

        Optional<Publisher> optPublisher = publisherRepository.findById(id);

//        optPublisher.ifPresent(publisher -> model.addAttribute("publisher", publisher));
        if (optPublisher.isPresent()) {
            model.addAttribute("publisher", optPublisher.get());

            return "publishers/details";
        }

        throw new ResourceNotFoundException();
    }

    @RequestMapping("publishers/create")
    public String addPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "/publishers/create";
    }

    @RequestMapping(value = "publishers/create", method = RequestMethod.POST)
    public String createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
        return "redirect:/publishers";
    }

    @RequestMapping(value = "publisher/edit/{id}")
    public String showPublisherEditPage(@PathVariable Long id, Model model){

       Optional<Publisher> optPublisher = publisherRepository.findById(id);

       if (optPublisher.isPresent()){
           model.addAttribute("publisher", optPublisher.get());

           return "publishers/edit";
       }
        throw new ResourceNotFoundException();
    }

    @RequestMapping(value = "publishers/edit", method = RequestMethod.POST)
    public String updatePublisher (@ModelAttribute Publisher editedPublisher){

        Optional<Publisher> optPublisher = publisherRepository.findById(editedPublisher.getId());

        if (optPublisher.isPresent()){
            Publisher publisher = optPublisher.get();
            publisher.setName(editedPublisher.getName());
            publisher.setAddressLine1(editedPublisher.getAddressLine1());
            publisher.setCity(editedPublisher.getCity());
            publisher.setState(editedPublisher.getState());
            publisher.setZip(editedPublisher.getZip());

            publisherRepository.save(publisher);
        }

        return "redirect:/publisher/details/" + editedPublisher.getId();
    }

    @RequestMapping("publisher/delete/{id}")
    public String deletePublisher(@PathVariable Long id){

        publisherRepository.deleteById(id);

        return "redirect:/publishers";
    }
}
