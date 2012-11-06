package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"username"})
@Transactional
@RequestMapping("/offer")
public class CreateOfferController {
    private String title;
    private String category;
    private String description;
    private String username;

    private String id;

    @Autowired
    private OfferServiceInterface offerService;

    public CreateOfferController() {
    }

    public CreateOfferController(OfferServiceInterface offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String goToCreatingOfferPage() {
        return "home/createOffer";
    }

    @RequestMapping(value = "/createAnOffer", method = RequestMethod.POST)
    public View createOffer(@RequestParam("title") String title, @RequestParam("category") String category,
                            @RequestParam("description") String description, HttpServletRequest request) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.username = request.getSession().getAttribute("username").toString();

        id = createOffer(this.title, this.category, this.description, username);

        return new RedirectView("viewAnOffer");
    }

    @RequestMapping("viewAnOffer")
    public String viewAnOffer(ModelMap modelMap) {
        Offer offer = offerService.getOfferById(id);
        modelMap.addAttribute("theOffer", offer);
        return "home/viewAnOffer";
    }

    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    public ModelAndView browse() {
        ModelAndView modelAndView = new ModelAndView("offer/browse");
        modelAndView.addObject("allOffers", offerService.getAll());
        return modelAndView;
    }

    private String createOffer(String title, String category, String description, String username) {
        Offer offer = new Offer(title, description, category, username);
        return offerService.saveOffer(offer);
    }
}
