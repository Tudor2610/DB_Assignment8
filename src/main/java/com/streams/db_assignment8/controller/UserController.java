package com.streams.db_assignment8.controller;

import com.streams.db_assignment8.model.User;
import com.streams.db_assignment8.model.UserStream;
import com.streams.db_assignment8.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    public static UserStream userStream = new UserStream();

    private final UserService userService;

    @PostMapping("/add")
    public List<User> addToStream(@RequestBody User user){
        userService.addtoStream(user, userStream);
        return userStream.getUserStream().collect(Collectors.toList());
    }

    @GetMapping("/under18")
    public List<User> filterBelow18(){
        return userService.filterBelow(userStream);
    }

    @GetMapping("/agex2")
    public List<User> doubleAge(){
        return userService.doubleAge(userStream);
    }

    @GetMapping("/last")
    public Optional<User> getLast(){
        return userService.getLast(userStream);
    }

    @GetMapping("/extremes")
    public List<Optional<User>> getExtremes(){
        return userService.getExtremes(userStream);
    }

    @DeleteMapping("/remove")
    public List<User> removeDuplicates(){
        return userService.removeDuplicates(userStream);
    }

    @GetMapping("/grownups")
    public List<User> filterUpperAndSort(){
        return userService.filterUpperAndSort(userStream);
    }

}
