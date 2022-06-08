package ru.netology.data;
import lombok.experimental.UtilityClass;
import com.github.javafaker.Faker;

import java.util.Locale;

@UtilityClass
public class DataGenerator {
    public static RegistrationDto generateInfo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return new RegistrationDto(
                faker.address().city(),
                faker.name().fullName(),
                faker.phoneNumber().phoneNumber());
    }
}
