package com.laca.paquete.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:4200/")
public class PaqueteController {

}
