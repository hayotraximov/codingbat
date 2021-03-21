package uz.raximov.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.ProgLangDto;
import uz.raximov.codingbat.service.ProgLangService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/proglang")
public class ProgLangController {

    @Autowired
    ProgLangService progLangService;

    @GetMapping()
    public HttpEntity<?> getAll() {
        return progLangService.getAll();
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse byId = progLangService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(byId);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        return progLangService.delete(id);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody ProgLangDto progLangDto) {
        return progLangService.add(progLangDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody ProgLangDto progLangDto, @PathVariable Integer id) {
        return progLangService.edit(progLangDto, id);
    }
}
