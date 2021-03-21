package uz.raximov.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.raximov.codingbat.entity.StarBadge;
import uz.raximov.codingbat.entity.User;
import uz.raximov.codingbat.payload.ApiResponse;
import uz.raximov.codingbat.payload.UserDto;
import uz.raximov.codingbat.repository.StarBadgeRepository;
import uz.raximov.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StarBadgeRepository starBadgeRepository;

    public ApiResponse add(UserDto userDto){
        User user = new User();
        if (userDto.getStarBadge() != null){
            Optional<StarBadge> starBadgeRepositoryById = starBadgeRepository.findById(userDto.getStarBadge());
            if (!starBadgeRepositoryById.isPresent())
                return new ApiResponse("StarBadge topilmadi!",false, null);
            user.setStarBadge(starBadgeRepositoryById.get());
        }


        if (userRepository.existsByEmail(userDto.getEmail()))
            return new ApiResponse("Email allaqachon ro'yxatdan o'tgan!",false,null);

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return new ApiResponse("User qo'shildi!",true,savedUser);
    }

    public ApiResponse edit(UserDto userDto, Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return new ApiResponse("User topilmadi", false, null);

        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(),id))
            return new ApiResponse("Email allaqachon ro'yxatdan o'tgan!",false,null);
        User user = userOptional.get();
        if (userDto.getStarBadge() != null){
            Optional<StarBadge> starBadgeRepositoryById = starBadgeRepository.findById(userDto.getStarBadge());
            if (!starBadgeRepositoryById.isPresent())
                return new ApiResponse("StarBadge topilmadi!",false, null);
            user.setStarBadge(starBadgeRepositoryById.get());
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return new ApiResponse("User qo'shildi!",true,savedUser);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public ApiResponse getById(Integer id){
        Optional<User> byId = userRepository.findById(id);
        return byId.map(user -> new ApiResponse("Ma'lumot topildi", true, user)).orElseGet(() -> new ApiResponse("Ma'lumot topilmadi", false, null));
    }
}
