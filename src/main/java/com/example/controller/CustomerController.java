/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controller;

import com.example.DAOService.CompanyService;
import com.example.data.Company;
import com.example.data.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.DAOService.CustomerDAOService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerDAOService customerDAOService;
    
    @Autowired
    private CompanyService companyService;
    
    
    @RequestMapping(value="/form",method = RequestMethod.GET)
    public ModelAndView form()
    {
        ModelAndView mav = new ModelAndView("/admin/customer/customer-form");
    
        return mav;
        }
   

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Customer customer, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
       
        customerDAOService.create(customer);

        String message = "Customer  " + customer.getFirstName() + "  was succesfully created";
       
        mav.setViewName("redirect:/customer/list");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("list");
        List<Customer> customerList = customerDAOService.findAll();

        mav.addObject("customerList", customerList);
        return mav;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getById(final RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        Customer customer = customerDAOService.findById(id);
        String message = "Customer " + customer.getFirstName() + " is listed";
        mav.setViewName("redirect:/");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteById(final RedirectAttributes redirectAttributes, @PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        Customer customer = customerDAOService.delete(id);
        String message = "Customer " + customer.getFirstName() + " is deleted";
        mav.setViewName("redirect:/customer/list");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;

    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editById(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("customer-update");
        Customer customer = customerDAOService.findById(id);
        List<Company> companyList = companyService.getAll();
        mav.addObject("customer", customer);
        mav.addObject("companyList", companyList);
        return mav;

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editById(@PathVariable("id") Long id,@ModelAttribute Customer customer,final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        customerDAOService.update(customer);
        
        String message = "Customer " + customer.getFirstName() + " is updated";
        redirectAttributes.addFlashAttribute("message", message);
        mav.setViewName("redirect:/customer/list");
        
        return mav;

    }

}