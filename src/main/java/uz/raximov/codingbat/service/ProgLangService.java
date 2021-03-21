package uz.raximov.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.raximov.codingbat.entity.ProgLang;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.ProgLangDto;
import uz.raximov.codingbat.repository.ProgLangRepository;

import java.util.Optional;

@Service
public class ProgLangService {

    @Autowired
    ProgLangRepository progLangRepository;

    /**
     * BU METOD YANGI DASTURLASH TILINI QO'SHADI
     * ApiResponse TOIFALI MA'LUMOT QAYTARIDA
     */
    public HttpEntity<ApiResponse> add(ProgLangDto progLangDto){
        boolean existsByName = progLangRepository.existsByName(progLangDto.getName());
        if (existsByName)
            return ResponseEntity.status(409).body(new ApiResponse("Bunday til mavjud!", false, null));

        ProgLang progLang = new ProgLang();
        progLang.setName(progLangDto.getName());
        progLang.setDescription(progLangDto.getDescription());
        ProgLang savedProglang = progLangRepository.save(progLang);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Til muvaffaqqiyatli qo'shildi!",true,savedProglang));
    }

    /**
     * BU METOD MAVJUD TILNI TAXRIRLAYDI
     * @return ApiResponse
     */
    public HttpEntity<?> edit(ProgLangDto progLangDto, Integer id){
        boolean existsByNameAndIdNot = progLangRepository.existsByNameAndIdNot(progLangDto.getName(), id);
        if (existsByNameAndIdNot)
            return ResponseEntity.status(409).body(new ApiResponse("Bunday til mavjud!", false, null));

        Optional<ProgLang> byId = progLangRepository.findById(id);
        if (byId.isPresent()) {
            ProgLang progLang = byId.get();
            progLang.setName(progLangDto.getName());
            progLang.setDescription(progLangDto.getDescription());
            ProgLang savedProglang = progLangRepository.save(progLang);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Til o'zgartirildi", true, savedProglang));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Bunday idli til topilmadi", false, null));
    }

    /**
     * BU METOD MAVJUD TILNI O'CHIRADI
     * @return ApiResponse
     * tugallanmagan
     */
    public HttpEntity<?> delete(Integer id){
        return ResponseEntity.ok(new ApiResponse());
    }


    /**
     * BU METOD MAVJUD TILLARNI QAYTARADI
     * @return List<ApiResponse>
     */
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(progLangRepository.findAll());
    }

    /**
     * BU METOD ID ORQALI TILNI QAYTARADI
     * @return HttpEntity<ProgLang>
     */
    public ApiResponse getById(Integer id){
        Optional<ProgLang> byId = progLangRepository.findById(id);
        return byId.map(progLang -> new ApiResponse("Dasturlash tili topildi!", true, progLang)).orElseGet(() -> new ApiResponse("Dasturlash tili topilmadi!", false, null));
    }
}
