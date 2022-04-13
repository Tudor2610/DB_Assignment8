package com.streams.db_assignment8.service;

import com.streams.db_assignment8.model.User;
import com.streams.db_assignment8.model.UserStream;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    public void addtoStream(User user, UserStream aux){
        user.setId(aux.getUserList().size() + 1);
        aux.setUserStream(Stream.concat(aux.getUserStream(), Stream.of(user)));
    }

    public List<User> filterBelow(UserStream aux){
        return aux.getUserStream().filter((User u) -> u.getAge() < 18).collect(Collectors.toList());
    }

    public List<User> doubleAge(UserStream aux){
        Stream<User> result = aux.getUserStream();
        result.forEach(u -> u.setAge(u.getAge() * 2));
        return result.collect(Collectors.toList());
    }

    public Optional<User> getLast(UserStream aux){
        Stream<User> result = aux.getUserStream();
        result.reduce((first, second) -> second).orElse(null);
        return result.findFirst();
    }

    public List<Optional<User>> getExtremes(UserStream aux){
        Stream<User> result = aux.getUserStream();
        result.sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        List<Optional<User>> extremes = new ArrayList<>();
        extremes.add(result.findFirst());
        result.reduce((first, second) -> second).orElse(null);
        extremes.add(result.findFirst());
        return extremes;
    }

    public List<User> removeDuplicates(UserStream aux){
        return aux.getUserStream().distinct().collect(Collectors.toList());
    }

    public List<User> filterUpperAndSort(UserStream aux){
        Stream<User> result = aux.getUserStream();
        result.filter((u) -> u.getAge() > 30);
        result.forEach((u) -> u.setName(u.getName().toUpperCase()));
        result.sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        return result.collect(Collectors.toList());
    }
}
