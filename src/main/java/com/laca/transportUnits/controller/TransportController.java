package com.laca.transportUnits.controller;

import com.laca.transportUnits.Transport;
import com.laca.transportUnits.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/transport")
@CrossOrigin(origins = "http://localhost:4200/")
public class TransportController {

    private final TransportService transportService;

    @Autowired
    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping
    public List<Transport> getAllTransports() {
        List<Transport> transports = transportService.getAllTransports();
        return transports;
    }

    @PostMapping
    public Transport saveTransport(@RequestBody Transport transport) {
        return transportService.saveTransport(transport);
    }

    @PutMapping("/{UnitID}")
    public ResponseEntity<?> updateTransport(
            @PathVariable int UnitID,
            @RequestBody Transport updatedTransport) {
        try {
            Transport updated = transportService.updateTransport(UnitID, updatedTransport);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating transport: " + e.getMessage());
        }
    }

    @GetMapping("/{UnitID}")
    public ResponseEntity<?> getTransportById(@PathVariable int UnitID) {
        try {
            Transport transport = transportService.getTransportById(UnitID);
            return ResponseEntity.ok(transport);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transport not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/{UnitID}")
    public ResponseEntity<?> deleteTransport(@PathVariable int UnitID) {
        try {
            boolean isDeleted = transportService.deleteTransport(UnitID);
            Transport transport= new Transport();
            transport.setUnitID(UnitID);
            if (isDeleted) {
                return ResponseEntity.ok(transport);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(UnitID);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting transport: " + e.getMessage());
        }

    }

}