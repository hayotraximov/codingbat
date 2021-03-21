package uz.raximov.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.raximov.codingbat.entity.Task;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.TaskDto;
import uz.raximov.codingbat.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * BARCHA TASKLAR RO'YXATI QAYTARILADI
     * @return List<ApiResponse>
     */
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.status(tasks.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(tasks);
    }

    /**
     * TASKNI ID BO'YICHA QAYTARADI, ID TOPILMASA NULL QAYTARADI
     * @param id
     * @return Task
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse byId = taskService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(byId);
    }

    /**
     * YANGI TASK QO'SHISH
     * @param taskDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.add(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * MAVJUD TASKNI O'ZGARTISH
     * @param taskDto
     * @param id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody TaskDto taskDto, @PathVariable Integer id){
        ApiResponse apiResponse = taskService.edit(taskDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

//    @DeleteMapping("/{id}")
//    public HttpEntity<?> delete(@PathVariable String id){
////        return ResponseEntity.ok();
//    }
}
