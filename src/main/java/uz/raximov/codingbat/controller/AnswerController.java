package uz.raximov.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.raximov.codingbat.entity.Answer;
import uz.raximov.codingbat.payload.AnswerDto;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Answer> answerList = answerService.getAll();
        return ResponseEntity.status(answerList.size() != 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(answerList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse answerServiceById = answerService.getById(id);
        return ResponseEntity.status(answerServiceById.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(answerServiceById);
    }

    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.add(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody AnswerDto answerDto, @PathVariable Integer id){
        ApiResponse apiResponse = answerService.edit(answerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
