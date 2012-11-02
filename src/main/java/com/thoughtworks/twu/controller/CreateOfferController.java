package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("/offer")
public class CreateOfferController {
    String title;
    String category;
    String description;
    String username;

    @Autowired
    private OfferServiceInterface offerService;

    public CreateOfferController() {
    }

    public CreateOfferController(OfferServiceInterface offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String goToHomepageAfterCreatingOffer() {
        return "home/createOffer";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createOffer(@RequestParam("title") String title, @RequestParam("category") String category,
                                    @RequestParam("description") String description, HttpServletRequest request) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.username = request.getSession().getAttribute("username").toString();

        ModelAndView modelAndView = createOffer(title, category, description, username);

        return modelAndView;
    }

    private ModelAndView createOffer(String title, String category, String description, String username) {
        Offer offer = new Offer(title, description, category, username);
        offerService.saveOffer(offer);
        return new ModelAndView("redirect:/home");
    }
}
