/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.mrhouse.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realestate.mrhouse.Entities.Property;
import com.realestate.mrhouse.Enums.City;
import com.realestate.mrhouse.Enums.TypeProperty;
import com.realestate.mrhouse.Repositories.PropertyRepository;
import com.realestate.mrhouse.Services.PropertyService;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 2171584201008
 */
@Controller
@RequestMapping("/alquilar")

public class AlquilarController {

    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private PropertyRepository propertyRepository;

    /*
    @GetMapping("")
    public String alquilar(ModelMap modelo) {

        List<Property> properties = propertyService.listAlquiler();

        modelo.addAttribute("properties", properties);

        return "alquilar.html";
    }
     */
    @GetMapping("")
    public String list(@PageableDefault(size = 10, page = 0) Pageable pageable, ModelMap modelo, Model model) {

        List<Property> properties = propertyService.listAlquiler();
        Page<Property> page = propertyRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        modelo.addAttribute("properties", properties);
        model.addAttribute("page", page);
        int totalPages = page.getTotalPages();
        int currentPage = page.getNumber();
        int start = Math.max(1, currentPage);
        int end = Math.min(currentPage + 5, totalPages);

        if (totalPages > 0) {

            ArrayList<Integer> pageNumbers = new ArrayList();

            for (int i = start; i <= end; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<Integer> pageSizeOptions = Arrays.asList(10, 20, 50, 100);
        model.addAttribute("pageSizeOptions", pageSizeOptions);

        return "alquilar.html";
    }

    @GetMapping("/property/{id}")
    public String alquilarProperty(@PathVariable Long id, ModelMap modelo) {

        boolean hayCartas = false;

        List<Property> properties = propertyService.listAlquiler5(id);

        if (!properties.isEmpty()) {

            hayCartas = true;

        }

        modelo.addAttribute("hayCartas", hayCartas);

        modelo.put("property", propertyService.getOne(id));

        modelo.addAttribute("properties", properties);

        return "detail_property1.html";
    }

    @GetMapping("/search")
    public String searchProperties(
            @RequestParam(name = "city", required = false) City city,
            @RequestParam(name = "type", required = false) TypeProperty type,
            @RequestParam(name = "price", required = false) Double price,
            ModelMap model) {

        // Verifica si se han proporcionado parámetros de filtro
        if (city != null && city != City.TODOS || type != null && type != TypeProperty.TODOS || price != null) {
            // Aplica filtros solo si al menos uno de los parámetros no es nulo
            List<Property> properties = propertyService.findPropertiesByCityAndTypeAndPriceAlquiler(city, type, price);
                    

            model.addAttribute("properties", properties);
        } else {
            // Si ambos parámetros son nulos, obtén todas las propiedades sin filtros
            List<Property> allProperties = propertyService.listAlquiler();
            model.addAttribute("properties", allProperties);
        }

        return "alquilar.html";
    }

}
