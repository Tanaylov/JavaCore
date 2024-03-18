package HW3;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

public class EmployeeGenerator {

    private static final String[] SURNAME = {"Ivanov", "Petrov", "Sidorov", "Petrenko", "Solovej", "Dubov", "Karasev"};
    private static final String[] NAME = {"Ivan", "Petr", "Sergej", "Victor", "Egor", "Maksim"};
    private static final String[] MIDDLENAME = {"Ivanovich", "Nikolaevich", "Fedorovich", "Gennadievich", "Petrovich"};

    private static final Random random = new Random();

    public static void generate(EmployeeStatus employeeStatus, int employeeNumber) {
        for (int i = 0; i < employeeNumber; i++) {
            String[] fullName = {SURNAME[random.nextInt(SURNAME.length)],
                                NAME[random.nextInt(NAME.length)],
                                MIDDLENAME[random.nextInt(MIDDLENAME.length)]};
            int year = random.nextInt(1970, 2020);
            int month = random.nextInt(1, 13);
            int day = random.nextInt(1, 29);
            LocalDate birthData = LocalDate.of(year, month, day);
            if (birthData.isAfter(LocalDate.now().minusYears(14))){
                System.out.println(Arrays.toString(fullName) + birthData + "is too young for work.");
                i--;
                continue;
            }
            long telephoneNumber = random.nextLong(900_000_00_00L, 1_000_000_00_00L);
            byte workExperience = (byte) random.nextInt(1, 10);
            switch (employeeStatus) {
                case WORKER -> new Worker(fullName, random.nextInt(50_000, 100_000), workExperience,
                            birthData, telephoneNumber);
                case MANAGER -> new Manager(fullName, random.nextInt(100_000, 150_000), workExperience,
                        birthData, telephoneNumber);
            }

        }
    }
}
