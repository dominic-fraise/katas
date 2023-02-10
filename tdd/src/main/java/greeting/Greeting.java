package greeting;

import java.util.List;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return "Hello, " + "my friend" + ".";
        }

        return buildSentence(new Names(names));

    }

    private static String buildSentence(Names names) {
        if (names.hasManyMulticaseNames())  {
            List<String> lowerCaseNamesWithoutLast = getAllButLastItem(names.getLowercase());
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) + ", and " + getLastItem(names.getLowercase()) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", names.getUpperCase()) + "!";
        }
        if (names.hasManyMulticaseNamesWithLessThanTwoLower()){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", names.getLowercase()) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", names.getUpperCase()) + "!";

        }
        if (names.hasOneLowercaseName()) {
            return "Hello, " + names.getLowercase().get(0) + ".";
        }
        if (names.hasOneUppercaseName()) {
            return "HELLO " + names.getUpperCase().get(0) + "!";
        }
        if (names.hasTwoLowercaseNames()) {
            return "Hello, " + names.getAll().get(0) + " and " + names.getAll().get(1) + ".";
        }
        if (names.hasMoreThanTwoLowercaseNames()) {
            List<String> newNames = getAllButLastItem(names.getAll());
            return "Hello, " + String.join(", ", newNames) + ", and " + getLastItem(names.getAll()) + '.';
        }
        return "Hello nameless";
    }

    private static String getLastItem(List<String> list) {
        return list.get(list.size() - 1);
    }

    private static List<String> getAllButLastItem(List<String> namesObj) {
        return namesObj.subList(0, namesObj.size() - 1);
    }

}
