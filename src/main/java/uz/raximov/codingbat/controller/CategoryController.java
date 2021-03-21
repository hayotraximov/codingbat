package uz.raximov.codingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.raximov.codingbat.entity.Category;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.CategoryDto;
import uz.raximov.codingbat.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * BARCHA CATEGORIYALAR RO'YXATI QAYTARILADI
     * @return
     */
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.status(categories.size() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(categories);
    }

    /**
     * CATEGORIYANI ID BO'YICHA QAYTARADI, ID TOPILMASA NULL QAYTARADI
     * @param id
     * @return Category
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id){
        ApiResponse byId = categoryService.getById(id);
        return ResponseEntity.status(byId.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(byId);
    }

    /**
     * YANGI CATEGORIYA QO'SHISH
     * @param categoryDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * MAVJUD CATEGORIYANI O'ZGARTISH
     * @param categoryDto
     * @param id
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        ApiResponse apiResponse = categoryService.edit(categoryDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

//    @DeleteMapping("/{id}")
//    public HttpEntity<?> delete(@PathVariable String id){
////        return ResponseEntity.ok();
//    }
}
