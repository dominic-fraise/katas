package greeting;

import java.util.Arrays;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }
        var upperCaseNames = Arrays.stream(names).filter(name -> isUpperCase(name)).toArray(String[]::new);
        var lowerCaseNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).toArray(String[]::new);

        if (lowerCaseNames.length > 0 && upperCaseNames.length > 0 && lowerCaseNames.length > 2)  {
            String lowerCaseGreeting;
            if (lowerCaseNames.length > 2) {
                String[] newLowerCaseNames = Arrays.copyOfRange(lowerCaseNames, 0, lowerCaseNames.length - 1);
                lowerCaseGreeting = "Hello, " + String.join(", ", newLowerCaseNames) + ", and " + lowerCaseNames[lowerCaseNames.length - 1] + '.';
                return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNames) + "!";
            }
        }

        if (lowerCaseNames.length > 0 && upperCaseNames.length > 0 && lowerCaseNames.length <= 2){
            String lowerCaseGreeting;
            lowerCaseGreeting = "Hello, " + String.join(" and ", lowerCaseNames) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNames) + "!";
        }

        switch(names.length) {
            case 0:
                return "Hello nameless";
            case 1:
                String name = names[0];
                return generateGreeting(name);
            case 2:
                return "Hello, " + String.join(" and ", names) + ".";
            default:
                String[] newNames = Arrays.copyOfRange(names, 0, names.length - 1);
                return "Hello, " + String.join(", ", newNames) + ", and " + names[names.length - 1] + '.';
        }
    }

    private static String generateGreeting(String name) {
        if (isUpperCase(name)) {
            return concatenateShout(name);
        }
        return concatenateString(name);
    }

    private static String concatenateShout(String name) {
        return "HELLO " + name + "!";
    }

    private static boolean isUpperCase(String name) {
        return name.equals(name.toUpperCase());
    }

    private static String concatenateString(String name) {
        return "Hello, " + name + ".";
    }
}
