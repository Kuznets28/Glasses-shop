package org.example;

import org.example.model.Role;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Role role = Role.valueOf("USER");
        System.out.println(role);
        switch (role){
            case USER -> System.out.println("это прользователь");
            case ADMIN -> System.out.println("Это админ");
        }
    }

}
