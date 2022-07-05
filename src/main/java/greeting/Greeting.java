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

        List<Name> allNames = Arrays.stream(names)
                .flatMap(Greeting::splitNames)
                .map(Name::new).collect(Collectors.toList());

        Names namesObj = new Names(allNames);
        return buildSentence(namesObj);

    }

    private static String buildSentence(Names namesObj) {
        if (namesObj.hasManyMulticaseNames())  {
            List<String> lowerCaseNamesWithoutLast = getAllButLastItem(namesObj.getLowercase());
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) + ", and " + namesObj.getLowercase().get(namesObj.getLowercase().size() - 1) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.getUpperCase()) + "!";
        }
        if (namesObj.hasManyMulticaseNamesWithLessThanTwoLower()){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", namesObj.getLowercase()) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.getUpperCase()) + "!";

        }
        if (namesObj.hasOneLowercaseName()) {
            return "Hello, " + namesObj.getLowercase().get(0) + ".";
        }
        if (namesObj.hasOneUppercaseName()) {
            return "HELLO " + namesObj.getUpperCase().get(0) + "!";
        }
        if (namesObj.hasTwoLowercaseNames()) {
            return "Hello, " + namesObj.getAll().get(0) + " and " + namesObj.getAll().get(1) + ".";
        }
        if (namesObj.hasMoreThanTwoLowercaseNames()) {
            List<String> newNames = getAllButLastItem(namesObj.getAll());
            return "Hello, " + String.join(", ", newNames) + ", and " + namesObj.getAll().get(namesObj.getAll().size() - 1) + '.';
        }
        return "Hello nameless";
    }

    private static List<String> getAllButLastItem(List<String> namesObj) {
        return namesObj.subList(0, namesObj.size() - 1);
    }

    private static Stream<String> splitNames(String name) {
        if (name.startsWith("\"") && name.endsWith("\"") && name.length() > 1) {
            return Stream.of(name.replace("\"", ""));
        }
        return Arrays.stream(name.split(", "));
    }

}
