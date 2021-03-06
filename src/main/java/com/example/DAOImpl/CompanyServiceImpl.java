/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DAOImpl;

import com.example.DAOService.CompanyService;
import com.example.data.Company;
import com.example.repository.CompanyRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public Company insert(Company c) {
        Company comp = new Company();

        comp.setCompanyName(c.getCompanyName());
        return companyRepository.save(comp);

    }

    @Override
    @Transactional
    public Company getById(Long id) {
        Company comp = companyRepository.findOne(id);
        
        return comp;
    }

    public Company delete(Long id) {
        Company toDelete =  companyRepository.findOne(id);
        companyRepository.delete(toDelete);
        return toDelete;

    }

    @Override
    @Transactional
    public Company getByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional
    public Company update(Company c) {

        Company toUpdate = companyRepository.findOne(c.getId());
        toUpdate.setCompanyName(c.getCompanyName());
        return toUpdate;

    }

    

}
