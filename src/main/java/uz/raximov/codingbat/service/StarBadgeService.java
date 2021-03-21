package uz.raximov.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.raximov.codingbat.entity.ProgLang;
import uz.raximov.codingbat.entity.StarBadge;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.StarBadgeDto;
import uz.raximov.codingbat.repository.ProgLangRepository;
import uz.raximov.codingbat.repository.StarBadgeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    StarBadgeRepository starBadgeRepository;

    @Autowired
    ProgLangRepository progLangRepository;

    public ApiResponse add(StarBadgeDto starBadgeDto){
        Optional<ProgLang> byId = progLangRepository.findById(starBadgeDto.getLangId());
        if (!byId.isPresent()) {
            return new ApiResponse("Dasturlash tili topilmadi", false, null);
        }

        if (starBadgeRepository.existsByScoreAndProgLang(starBadgeDto.getScore(), byId.get())) {
            return new ApiResponse("Bunday StarBadge Mavjud!",false, null);
        }

        StarBadge starBadge = new StarBadge();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(byId.get());
        StarBadge savedStarBadge = starBadgeRepository.save(starBadge);
        return new ApiResponse("Yangi StarBadge qo'shildi!", true, savedStarBadge);
    }

    public ApiResponse edit(StarBadgeDto starBadgeDto, Integer id){
        Optional<StarBadge> badgeRepositoryById = starBadgeRepository.findById(id);
        if (!badgeRepositoryById.isPresent()) {
            return new ApiResponse("Bunday StarBadge topilmadi!", false, null);
        }

        Optional<ProgLang> byId = progLangRepository.findById(starBadgeDto.getLangId());
        if (!byId.isPresent()) {
            return new ApiResponse("Dasturlash tili topilmadi", false, null);
        }

        if (starBadgeRepository.existsByScoreAndProgLangAndIdNot(starBadgeDto.getScore(), byId.get(),id)) {
            return new ApiResponse("Bunday StarBadge Mavjud!",false, null);
        }

        StarBadge starBadge = badgeRepositoryById.get();
        starBadge.setScore(starBadgeDto.getScore());
        starBadge.setProgLang(byId.get());
        StarBadge savedStarBadge = starBadgeRepository.save(starBadge);
        return new ApiResponse("Mavjud StarBadge o'xgartirildi!", true, savedStarBadge);
    }

    public List<StarBadge> getAll(){
        return starBadgeRepository.findAll();
    }

    public ApiResponse getById(Integer id){
        Optional<StarBadge> byId = starBadgeRepository.findById(id);
        return byId.map(starBadge -> new ApiResponse("StarBadge topildi", true, starBadge)).orElseGet(() -> new ApiResponse("StarBadge topilmadi", false, null));
    }

}
