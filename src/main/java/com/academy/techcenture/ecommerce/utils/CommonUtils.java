package com.academy.techcenture.ecommerce.utils;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CommonUtils {

    private Faker faker;

    public CommonUtils() {
        this.faker = new Faker();
    }

    public String randomZipCode() {
        return faker.address().zipCode().substring(0, 5);
    }

    public String randomPhoneNumber() {
        return String.format("(%03d) %03d-%04d",
                (int) Math.floor(999 * Math.random()),
                (int) Math.floor(999 * Math.random()),
                (int) Math.floor(9999 * Math.random()));
    }

    public String generateRandomString(int limit) {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toLowerCase();
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < limit) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public String randomCompanyName() {
        return faker.company().name();
    }

    public String randomState() {
        return faker.address().state();
    }

    public String randomCity() {
        return faker.address().cityName();
    }

    public String randomStreetAddress() {
        return faker.address().streetAddress();
    }

    public int randomNumber(int from, int to) {
        return (int) (Math.random() * (to - from + 1) + from);

    }

    public String randomEmail() {
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String[] domain = {"gmail", "yahoo", "hotmail", "icloud"};
        String email = lastName + "." + firstName + "@" + domain[(int) (Math.random() * (4))] + ".com";
        return email.toLowerCase(Locale.ROOT);

    }

    public String randomDOBAbove18() {
        LocalDate startDate = LocalDate.of(1950, 1, 1);//start date
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.of(LocalDate.now().getYear() - 18, 1, 1);
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay).toString();

    }

    public String randomPassword() {
        String SALTCHARS = "ABCDEFGHIaqwertyuioplkjhgfdszxcvbnmJKLMNO$%*&#@!()PQRSTUVWXYZ1234567890".toLowerCase();
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String password = salt.toString();
        return password;
    }



}
