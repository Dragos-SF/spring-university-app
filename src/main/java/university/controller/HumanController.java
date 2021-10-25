package university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import university.service.DTO.HumanDTO;
import university.service.HumanService;

import java.util.List;

@RestController
public class HumanController {

    private final HumanService humanService;

    @Autowired
    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }


@GetMapping("/api/humans")
public ResponseEntity<List<HumanDTO>> getAllHumans() {
    return ResponseEntity.ok(humanService.getAllHumans());
}

    @GetMapping("/api/humans/{id}")
    public ResponseEntity<HumanDTO> getHumanById(@PathVariable("id") Long humanId) {
        return ResponseEntity.ok(humanService.getHumanById(humanId));
    }

    @PostMapping("/api/humans")
    public ResponseEntity createOrUpdateHuman(@RequestBody HumanDTO humanRequest) {
        this.humanService.createOrUpdateHuman(humanRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/humans/{id}")
    public void deleteHumanById(@PathVariable("id") Long humanId) {
        this.humanService.deleteHumanById(humanId);
    }



}
