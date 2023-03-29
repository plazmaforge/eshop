package com.ohapon.eshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static class Employ {

        private String firstName;
        private String lastName;
        private int age;

        public Employ(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Employ{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    //public Com

    public static void main(String[] args) {

        System.out.println("\uF3ED");
        System.out.println("\u62445");

        /*
        List<Employ> list = new ArrayList<>();
        list.add(new Employ("Jon", "Smit", 33));
        list.add(new Employ("Dmitry", "Boiko", 34));
        list.add(new Employ("Oleh", "Boiko", 55));
        list.add(new Employ("Anna", "Boiko", 30));
        list.add(new Employ("Maxim", "Ivanov", 20));

        Map<String, Employ> familyList = new HashMap<>();

        for (Employ employ : list) {

            String family = employ.getLastName();
            Employ employOld = familyList.get(family);

            if (employOld == null) {
                employOld = employ;
                familyList.put(family, employOld);
            } else {
                if (employ.getAge() > employOld.getAge()) {
                    familyList.put(family, employ);
                }
            }

            //familyList.put(family, employOld);
        }

        //list.sort();
        //System.out.println(familyList);

        String str = "\u0904\u0905\u0906\u0907\u0908";
        System.out.println(str);

         */

    }



}
