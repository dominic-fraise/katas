package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return "Hello, " + "my friend" + ".";
        }
        if (names.length == 0) {
            return "Hello nameless";

        }

        List<String> splitNames = Arrays.stream(names)
                .flatMap(Greeting::splitNames)
                .collect(Collectors.toList());

        names = splitNames.stream().toArray(String[]::new);

        List<String> upperCaseNames = Arrays.stream(names).filter(Greeting::isUpperCase).collect(Collectors.toList());
        List<String> lowerCaseNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());

        if (lowerCaseNames.size() > 2 && !upperCaseNames.isEmpty())  {
            List<String> lowerCaseNamesWithoutLast = lowerCaseNames.subList(0, lowerCaseNames.size()-1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) +
                    ", and " + lowerCaseNames.get(lowerCaseNames.size() - 1) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNames) + "!";

        } else if (!lowerCaseNames.isEmpty() && !upperCaseNames.isEmpty()){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", lowerCaseNames) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", upperCaseNames) + "!";

        } else if (upperCaseNames.size() == 1) {
            return "HELLO " + names[0] + "!";

        } else if (upperCaseNames.size() == 0 && lowerCaseNames.size() == 1) {
            return "Hello, " + names[0] + ".";

        } else if (names.length == 2) {
            return "Hello, " + String.join(" and ", names) + "."; //same as below?

        } else {
            String[] newNames = Arrays.copyOfRange(names, 0, names.length - 1);
            return "Hello, " + String.join(", ", newNames) + ", and " + names[names.length - 1]
                + '.';

        }
    }

    private static Stream<String> splitNames(String name) {
        if (name.startsWith("\"") && name.endsWith("\"") && name.length() > 1) {
            return Stream.of(name.replace("\"", ""));
        }
        return Arrays.stream(name.split(", "));
    }

    private static boolean isUpperCase(String name) {
        if (isNonLetterCharacter(name)) {
            return false;
        }
        return name.equals(name.toUpperCase());
    }

    private static boolean isNonLetterCharacter(String name) {
        return name.equals(name.toLowerCase()) && name.equals(name.toUpperCase());
    }

}
