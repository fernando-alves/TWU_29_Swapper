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
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@SessionAttributes({"username"})
@Transactional
@RequestMapping("/offer")
public class OfferController {
    private String title;
    private String category;
    private String description;
    private String username;

    @Autowired
    private OfferServiceInterface offerService;

    private String id;

    public OfferController() {
    }

    public OfferController(OfferServiceInterface offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String goToCreatingOfferPage() {
        return "home/createOffer";
    }

    @RequestMapping(value = "/createAnOffer", method = RequestMethod.POST)
    public View createOffer(@RequestParam("title") String title, @RequestParam("category") String category,
                            @RequestParam("descriptionTxt") String description, HttpServletRequest request) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.username = request.getSession().getAttribute("username").toString();

        id = createOffer(this.title, this.category, this.description, username);
        setId(id);
        RedirectView redirectView = new RedirectView("viewAnOfferAfterCreating");
        redirectView.setExposeModelAttributes(false);

        return redirectView;
    }

    @RequestMapping(value = "/takedown", method = RequestMethod.GET)
    public RedirectView takeDownOffer(@ModelAttribute("offerId") String offerId) {

        offerService.takeDownOffer(offerId);

        RedirectView redirectView = new RedirectView("/twu/");
        redirectView.setExposeModelAttributes(false);

        return redirectView;
    }

    @RequestMapping("viewAnOfferAfterCreating")
    public String viewAnOfferAfterCreating(ModelMap modelMap) {
        Offer offer = offerService.getOfferById(getId());
        modelMap.addAttribute("theOffer", offer);
        return "home/viewAnOffer";
    }

    @RequestMapping("viewAnOfferFromBrowse")
    public String viewAnOfferFromBrowse(ModelMap modelMap, @RequestParam("offerId") String offerId) {
        setId(offerId);
        Offer offer = offerService.getOfferById(getId());
        modelMap.addAttribute("theOffer", offer);
        return "home/viewAnOffer";
    }

    @RequestMapping(value = "/browse", method = RequestMethod.GET)
    public ModelAndView browse(HttpServletRequest request, HttpSession session) {
        session.setAttribute("username", request.getRemoteUser());
        ModelAndView modelAndView = new ModelAndView("offer/browse");
        modelAndView.addObject("allOffers", offerService.getAll());
        return modelAndView;
    }

    private String createOffer(String title, String category, String description, String username) {
        Offer offer = new Offer(title, description, category, username, new Date(), false);
        return offerService.saveOffer(offer);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
