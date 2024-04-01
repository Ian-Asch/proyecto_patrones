package com.laca.paquete.controller;

import com.laca.paquete.Paquete;
import com.laca.paquete.PaqueteFactory;
import com.laca.paquete.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/package")
@CrossOrigin(origins = "http://localhost:4200/")
public class PaqueteController {
    private PaqueteService paqueteService;

    @Autowired()
    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @GetMapping
    public List<Paquete> getAllPaquetes() {
        List<Paquete> paquetes = paqueteService.getAllPaquetes();
        System.out.println(paquetes);
        return paquetes;
    }

    @PostMapping
    public Paquete savePaquete(@RequestBody Paquete paquete) {
        return paqueteService.savePaquete(paquete);
    }

    @PutMapping("/{packageId}")
    public void updatePaquete(@PathVariable int packageId, @RequestBody Paquete updatedPaquete) {
        try {
            paqueteService.updatePaquete(packageId, updatedPaquete);
        } catch (Exception e) {
            throw new RuntimeException("(Controller) error updating package: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<?> getPaqueteById(@PathVariable int packageId) {
        try {
            Paquete paquete = paqueteService.getPaqueteId(packageId);

            return ResponseEntity.ok(paquete);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(Controller) package not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<?> deletePaquete(@PathVariable int packageId) {
        try {
            Boolean isDeleted = paqueteService.deletePaquete(packageId);

            Paquete paquete = PaqueteFactory.crearPaquete();
            paquete.setPackageID(packageId);

            System.out.println(isDeleted);

            if (isDeleted) {
                return ResponseEntity.ok(paquete);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(packageId);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(Controller) error deleting package: " + e.getMessage());
        }
    }
}
