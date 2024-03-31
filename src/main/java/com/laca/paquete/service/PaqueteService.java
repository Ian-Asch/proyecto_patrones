package com.laca.paquete.service;

import com.laca.paquete.Paquete;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaqueteService {
    private final DataSource dataSource;

    public PaqueteService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<Paquete> getAllPaquetes() {
        List<Paquete> paquetes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Packages";
        }
    }
}
