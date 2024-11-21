package com.marek.web;

import com.marek.domain.sectors.GetSectorsUseCase;
import com.marek.model.sectors.Sector;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.marek.web.SectorsController.REQUEST_MAPPING;


@RestController
@RequiredArgsConstructor
@RequestMapping(REQUEST_MAPPING)
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class SectorsController {
    public static final String REQUEST_MAPPING = "/api/sectors";
    private final GetSectorsUseCase getSectors;

    @GetMapping()
    public ResponseEntity<List<Sector>> getSectors() {
        var mockData = getSectors.execute();

        return ResponseEntity.ok(mockData);
    }
}
