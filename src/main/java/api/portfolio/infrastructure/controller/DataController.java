package api.portfolio.infrastructure.controller;

import api.portfolio.application.service.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class DataController {
    private final DataService dataService;
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        String result = dataService.readDataJson();
        return result.contains("Error")
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result)
                : ResponseEntity.ok(result);
    }
}