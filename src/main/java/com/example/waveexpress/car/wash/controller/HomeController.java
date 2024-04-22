package com.example.waveexpress.car.wash.controller;

import java.io.IOException;
import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.waveexpress.car.wash.beans.*;
import com.example.waveexpress.car.wash.repository.*;
import com.example.waveexpress.car.wash.service.*;

/**
 * Controller class for handling requests related to home page, CRUD operations, and weather data.
 */
@Controller
public class HomeController {

    @Autowired
    private SaleRepository salesRepository;

    @Autowired
    WeatherService wService;

    /**
     * Handles GET requests to the home page ("/").
     * Populates model with sales data and weather information from API for display on the home page.
     * 
     * @param model the model to be populated with data
     * @return the name of the view to be rendered
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sales", salesRepository.findAll());
        model.addAttribute("sale", new Sale(LocalDate.now().toString(), 0.00, 0, ""));

        // To populate data on Home page for Weather API
        Weather weather;
        try {
            weather = this.wService.getWeatherDataCity("North York", "CA");
            model.addAttribute("weather", weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }

    /**
     * Handles POST requests to save sales data.
     * If the sale already exists, it updates the existing entry; otherwise, it adds a new entry.
     * 
     * @param sale the Sale object to be saved
     * @return redirect to the home page after saving
     */
    @PostMapping("/save")
    public String saveSales(@ModelAttribute Sale sale) {
        Optional<Sale> existingSales = salesRepository.findById(sale.getId());
        if (existingSales.isPresent()) {
            Sale updatedSales = existingSales.get();
            updatedSales.setDate(sale.getDate());
            updatedSales.setSalesAmt(sale.getSalesAmt());
            updatedSales.setWashes(sale.getWashes());
            updatedSales.setEnteredBy(sale.getEnteredBy());
            salesRepository.save(updatedSales);
        } else {
            salesRepository.save(sale);
        }
        return "redirect:/";
    }

    /**
     * Handles GET requests to edit sales data.
     * Retrieves the sale by its ID and populates the model with sales data and weather information for display on the home page.
     * 
     * @param id the ID of the sale to be edited
     * @param model the model to be populated with data
     * @return the name of the view to be rendered
     */
    @GetMapping("/edit/{id}")
    public String editSales(@PathVariable int id, Model model) {
        Sale sale = salesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Sales ID"));
        model.addAttribute("sales", salesRepository.findAll());
        model.addAttribute("sale", sale);

        // To populate data on Home page for Weather API
        Weather weather;
        try {
            weather = this.wService.getWeatherDataCity("North York", "CA");
            model.addAttribute("weather", weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }

    /**
     * Handles GET requests to delete sales data.
     * Deletes the sale by its ID and populates the model with a success message, sales data, and weather information for display on the home page.
     * 
     * @param id the ID of the sale to be deleted
     * @param model the model to be populated with data
     * @return the name of the view to be rendered
     */
    @GetMapping("/delete/{id}")
    public String deleteSales(@PathVariable int id, Model model) {
        salesRepository.deleteById(id);
        model.addAttribute("msg", "Row was Deleted");

        model.addAttribute("sales", salesRepository.findAll());
        model.addAttribute("sale", new Sale(LocalDate.now().toString(), 0.00, 0, ""));

        // To populate data on Home page for Weather API
        Weather weather;
        try {
            weather = this.wService.getWeatherDataCity("North York", "CA");
            model.addAttribute("weather", weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "home";
    }
}