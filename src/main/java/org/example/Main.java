package org.example;

import org.example.model.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Role role = Role.valueOf("USER");
        System.out.println(role);
        switch (role){
            case USER -> System.out.println("это прользователь");
            case ADMIN -> System.out.println("Это админ");
        }

        List<String> str = new ArrayList<>();
        Collections.addAll(str, "Hello", "World66");
        List<String> a = str.stream().filter(b -> b.length() < 6).collect(Collectors.toList());
        System.out.println(a);
    }

}
