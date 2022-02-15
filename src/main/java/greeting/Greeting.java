package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }
        var upperCaseNames = Arrays.stream(names).filter(name -> isUpperCase(name)).toArray(String[]::new);
        var lowerCaseNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).toArray(String[]::new);

        var upperCaseNamesList = Arrays.stream(upperCaseNames).collect(Collectors.toList());
        var lowerCaseNamesList = Arrays.stream(lowerCaseNames).collect(Collectors.toList());

        if (atLeastOneUpperAndThreeLower(upperCaseNamesList, lowerCaseNamesList))  {
            String[] newLowerCaseNames = Arrays.copyOfRange(lowerCaseNames, 0, lowerCaseNamesList.size() - 1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", newLowerCaseNames) + ", and " + lowerCaseNames[
                    lowerCaseNamesList.size() - 1] + '.';
            return mixedCaseGreeting(upperCaseNames, lowerCaseGreeting);
        } else if (atLeastOneUpperAndOneLower(upperCaseNames, lowerCaseNames)){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", lowerCaseNames) + ".";
            return mixedCaseGreeting(upperCaseNames, lowerCaseGreeting);
        } else if (names.length == 0) {
            return "Hello nameless";
        } else if (names.length == 1) {
            String name = names[0];
            return generateGreeting(name);
        } else if (names.length == 2) {
            return "Hello, " + String.join(" and ", names) + ".";
        } else {
            String[] newNames = Arrays.copyOfRange(names, 0, names.length - 1);
            return "Hello, " + String.join(", ", newNames) + ", and " + names[names.length - 1]
                + '.';
        }
    }

    private static boolean atLeastOneUpperAndThreeLower(List<String> upperCaseNamesList,
        List<String> lowerCaseNamesList) {
        return lowerCaseNamesList.size() > 2 && upperCaseNamesList.size() > 0;
    }

    private static boolean atLeastOneUpperAndOneLower(String[] upperCaseNames, String[] lowerCaseNames) {
        return lowerCaseNames.length > 0 && upperCaseNames.length > 0;
    }

    private static String mixedCaseGreeting(String[] upperCaseNames, String lowerCaseGreeting) {
        return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNames) + "!";
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
