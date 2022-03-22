package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }

        var upperCaseNamesList = Arrays.stream(names).filter(Greeting::isUpperCase).collect(Collectors.toList());
        var lowerCaseNamesList = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());

        if (atLeastOneUpperAndThreeLower(upperCaseNamesList, lowerCaseNamesList))  {
            List<String> lowerCaseNamesWithoutLast = lowerCaseNamesList.subList(0, lowerCaseNamesList.size()-1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) +
                    ", and " + lowerCaseNamesList.get(lowerCaseNamesList.size() - 1) + '.';

            return mixedCaseGreeting(lowerCaseGreeting, upperCaseNamesList);

        } else if (atLeastOneUpperAndOneLower(upperCaseNamesList, lowerCaseNamesList)){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", lowerCaseNamesList) + ".";
            return mixedCaseGreeting(lowerCaseGreeting, upperCaseNamesList);

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

    private static boolean atLeastOneUpperAndOneLower(List<String> upperCaseNamesList,
        List<String> lowerCaseNamesList) {
        return lowerCaseNamesList.size() > 0 && upperCaseNamesList.size() > 0;
    }

    private static String mixedCaseGreeting(String lowerCaseGreeting, List<String> upperCaseNamesList) {
        return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNamesList) + "!";
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
