package org.safecircle.backend.controllers;

import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.C;
import org.safecircle.backend.dto.CircleDTO;
import org.safecircle.backend.models.Circle;
import org.safecircle.backend.services.CircleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/circle")
public class CircleController {
    private final CircleService circleService;

    public CircleController(CircleService circleService) {
        this.circleService = circleService;
    }

    @CrossOrigin
    @GetMapping(value = "/{circleId}")
    public ResponseEntity<Circle> getCircleById(@PathVariable long circleId) {
        Circle circle = circleService.getCircleById(circleId);
        if(circle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else{
            return ResponseEntity.ok(circle);
        }
    }

    @CrossOrigin
    @GetMapping(value = "/getAll/{userId}")
    public ResponseEntity<List<Circle>> getCirclesByUserId(@PathVariable long userId) {
        List<Circle> listCircles = circleService.getCirclesByUserId(userId);
        if(listCircles == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.ok(listCircles);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/{userId}/create")
    public ResponseEntity<String> createCircle(@PathVariable long userId, @Valid @RequestBody CircleDTO circle) {
        return circleService.createCircle(userId, circle);
    }

    @CrossOrigin
    @PutMapping(value = "/{circleId}/update")
    public ResponseEntity<String> updateCircle(@Valid @RequestBody CircleDTO circleDTO, @PathVariable long circleId) {
        return circleService.updateCircle(circleId, circleDTO);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{circleId}/delete")
    public ResponseEntity<String> deleteCircle(@PathVariable long circleId) {
        return circleService.deleteCircle(circleId);
    }

    @CrossOrigin
    @PutMapping(value = "{circleId}/remove/{userId}")
    public ResponseEntity<String> removeUserById(@PathVariable long circleId, @PathVariable long userId) {
        return circleService.removeUserById(circleId, userId);
    }

    @CrossOrigin
    @PostMapping(value = "/{circleId}/add/{userIds}")
    public ResponseEntity<String> addUserByIds(@PathVariable long circleId, @PathVariable List<Long> userIds) {
        return circleService.addUsersToCircle(circleId, userIds);
    }
}
