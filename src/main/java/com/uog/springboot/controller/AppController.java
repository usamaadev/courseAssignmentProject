package com.uog.springboot.controller;

import com.uog.springboot.model.Product;
import com.uog.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService service;


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/viewAll")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/viewById/{id}")
    public String get(Model model , @PathVariable Integer id) {
            Product product = service.get(id);
            model.addAttribute("listProducts", product);
            return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);
        return "redirect:/";
    }



    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @RequestMapping("/")
    public String viewMainPage(Model model){

        return "main_page";
    }

    @RequestMapping("/EnterId")
    public String viewById(Model model){

        return "viewById";
    }


    @RequestMapping(value = "/active" ,method = RequestMethod.GET)
    public String get(Model model) {
        List<Product> products = new ArrayList<Product>();
        products = service.findActive();
        model.addAttribute("listProducts", products);
        return "index";
    }



}
