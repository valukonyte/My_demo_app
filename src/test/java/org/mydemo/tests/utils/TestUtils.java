package org.mydemo.tests.utils;

import net.datafaker.Faker;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    private static final Faker faker = new Faker();

    public static String generateRandomFullName() {
        return faker.name().fullName();
    }

    public static String generateRandomStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String generateRandomCity() {
        return faker.address().city();
    }

    public static String generateRandomCountry() {
        return faker.address().country();
    }

    public static String generateRandomCreditCardNumber() {
        String card = faker.finance().creditCard();
        return card.replaceAll("-", "");
    }

    public static String generateCardExpirationDate() {
        LocalDate now = LocalDate.now();
        LocalDate futureDate = now.plusMonths(ThreadLocalRandom.current().nextInt(1, 60));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        return futureDate.format(formatter);
    }

    public static String generateCardSecurityCode() {
        return String.format("%03d", faker.number().numberBetween(1, 1000));
    }

    public static String generateRandomZipCode() {
        return faker.address().zipCode();
    }

    public static WebElement getRandomElement(List<WebElement> elements) {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Element list is empty, cannot select a random element");
        }
        return elements.get(ThreadLocalRandom.current().nextInt(elements.size()));
    }
}
