package uz.raximov.codingbat.service;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.raximov.codingbat.entity.Category;
import uz.raximov.codingbat.entity.Task;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.TaskDto;
import uz.raximov.codingbat.repository.CategoryRepository;
import uz.raximov.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;


    /**
     * BARCHA TASKLARNI QAYTARADI
      * @return List<Task>
     */
    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    /**
     * ID BO'YICHA MAVJUD TASKNI QIDIRADI
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getById(Integer id){
        Optional<Task> byId = taskRepository.findById(id);
        return byId.map(task -> new ApiResponse("Topildi", true, task)).orElseGet(() -> new ApiResponse("Bunday Idli Task topilmadi", false, null));
    }

    /**
     * YANGI TASK QO'SHADI
     * @param taskDto
     * @return ApiResponse
     */
    public ApiResponse add(TaskDto taskDto){
        Optional<Category> categoryOptional = categoryRepository.findById(taskDto.getCategoryId());
        if (!categoryOptional.isPresent())
            return new ApiResponse("Kategoriya topilmadi", false, null);

        if (taskRepository.existsByNameAndCategory(taskDto.getName(), categoryOptional.get())){
            return new ApiResponse("Bunday nomli masala mavjud",false,null);
        }

        Task task = new Task();
        task.setCategory(categoryOptional.get());
        task.setExamples(taskDto.getExamples());
        task.setHasStar(taskDto.isHasStar());
        task.setName(taskDto.getName());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());
        Task savedTask = taskRepository.save(task);
        return new ApiResponse("Task saqlandi!",true,savedTask);
    }

    /**
     * MAVJUD TASKNI ID BO'YICHA TOPIB O'ZGARTIRADI
     * @param taskDto
     * @param id
     * @return ApiResponse
     */
    public ApiResponse edit(TaskDto taskDto, Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(taskDto.getCategoryId());
        if (!categoryOptional.isPresent())
            return new ApiResponse("Kategoriya topilmadi", false, null);

        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent())
            return new ApiResponse("Task topilmadi!", false, null);

        Task task = taskOptional.get();
        task.setExamples(taskDto.getExamples());
        task.setCategory(categoryOptional.get());
        task.setName(taskDto.getName());
        task.setHasStar(taskDto.isHasStar());
        task.setSolution(taskDto.getSolution());
        task.setText(taskDto.getText());
        Task savedTask = taskRepository.save(task);
        return new ApiResponse("Task o'zgartirildi!",true,savedTask);
    }

    public ApiResponse delete(Integer id){
        return new ApiResponse();
    }
}

