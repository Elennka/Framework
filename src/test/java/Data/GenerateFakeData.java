package Data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateFakeData {
    public Faker faker = new Faker (new Locale("ru"));

    public String firstName() {return faker.name().firstName();}

    public String lastName() {return faker.name().lastName();}

    public String emailAddress() {return faker.internet().emailAddress();}

    public String url() {return faker.internet().url();}

    public String phoneNumber() {return faker.phoneNumber().phoneNumber();}

    public String companyName() {return faker.company().name();}






}
