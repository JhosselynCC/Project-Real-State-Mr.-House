/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realestate.mrhouse.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 2171584201008
 */
@Controller
@RequestMapping("/comprar")
public class ComprarController {

    @GetMapping("")
    public String comprar() {

        return "comprar.html";
    }
}