package greeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }
        List<String> splitNames = new ArrayList<>();

        for (String name : names) {
            String[] splitName = name.split(", ");
            splitNames.addAll(Arrays.stream(splitName).collect(Collectors.toList()));
        }
        names = splitNames.stream().toArray(String[]::new);

        List<String> upperCaseNames = Arrays.stream(names).filter(Greeting::isUpperCase).collect(Collectors.toList());
        List<String> lowerCaseNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());

        if (atLeastOneUpperAndAtLeastThreeLower(upperCaseNames, lowerCaseNames))  {
            List<String> lowerCaseNamesWithoutLast = lowerCaseNames.subList(0, lowerCaseNames.size()-1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) +
                    ", and " + lowerCaseNames.get(lowerCaseNames.size() - 1) + '.';

            return mixedCaseGreeting(lowerCaseGreeting, upperCaseNames);

        } else if (atLeastOneUpperAndOneLower(upperCaseNames, lowerCaseNames)){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", lowerCaseNames) + ".";
            return mixedCaseGreeting(lowerCaseGreeting, upperCaseNames);

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

    private static boolean atLeastOneUpperAndAtLeastThreeLower(List<String> upperCaseNamesList,
                                                               List<String> lowerCaseNamesList) {
        return lowerCaseNamesList.size() > 2 && !upperCaseNamesList.isEmpty();
    }

    private static boolean atLeastOneUpperAndOneLower(List<String> upperCaseNamesList,
        List<String> lowerCaseNamesList) {
        return !lowerCaseNamesList.isEmpty() && !upperCaseNamesList.isEmpty();
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
