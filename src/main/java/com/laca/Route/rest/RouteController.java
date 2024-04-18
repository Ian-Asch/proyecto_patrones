package com.laca.Route.rest;
import com.laca.Route.Route;
import com.laca.Route.decorator.RouteDecorator;
import com.laca.Route.decorator.concreteDecorator.LongRouteDecorator;
import com.laca.Route.decorator.concreteDecorator.MediumRouteDecorator;
import com.laca.Route.decorator.concreteDecorator.ShortRouteDecorator;
import com.laca.Route.prototype.RoutePrototype;
import com.laca.Route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController()
@RequestMapping("/routes")
@CrossOrigin(origins = "http://localhost:4200/")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        System.out.println(routes);
        return routes;
    }

    @PostMapping
    public RouteDecorator saveRoute(@RequestBody Route route) {
        RouteDecorator routeDecorated = null;

        // Aplicar los decoradores según el tipo de duración de la ruta
        switch (route.getDurationType()) {
            case SHORT:
                RouteDecorator shortRoute = new ShortRouteDecorator(route);
                System.out.println("Short route");
                routeDecorated = shortRoute;
                break;
            case MEDIUM:
                RouteDecorator mediumRoute = new MediumRouteDecorator(route);
                System.out.println("Medium route");
                routeDecorated = mediumRoute;

                break;
            case LONG:
                RouteDecorator longRoute = new LongRouteDecorator(route);
                System.out.println("Long route");
                routeDecorated = longRoute;
                break;
        }

        return routeService.saveRoute(routeDecorated);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<?> updateRoute(
            @PathVariable int routeId,
            @RequestBody Route updatedRoute) {
        try {
            Route updated = routeService.updateRoute(routeId, updatedRoute);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating route: " + e.getMessage());
        }
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<?> getRouteById(@PathVariable Long routeId) {
        try {
            Route route = routeService.getRouteById(routeId);
            return ResponseEntity.ok(route);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found: " + e.getMessage());
        }
    }

    @DeleteMapping("/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable Long routeId) {
        try {
            boolean isDeleted = routeService.deleteRoute(routeId);
            Route route= new Route();
            route.setRouteID(routeId);
            if (isDeleted) {
                return ResponseEntity.ok(route);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(routeId);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting route: " + e.getMessage());
        }

    }

    @PostMapping("/{routeId}/clone")
    public ResponseEntity<?> cloneRoute(@PathVariable Long routeId) {
        try {
            RoutePrototype clonedRoute = routeService.cloneRouteById(routeId);
            return ResponseEntity.ok(clonedRoute);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error cloning route: " + e.getMessage());
        }
    }

//    @PostMapping("/clone")
//    public ResponseEntity<?> cloneAndSaveRoute(@RequestBody Route route) {
//        try {
//            RoutePrototype clonedRoute = route.clone();
//            Route newRoute = routeService.saveRoute((Route) clonedRoute);
//
//            return ResponseEntity.ok(newRoute);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error cloning and saving route: " + e.getMessage());
//        }
//    }

}