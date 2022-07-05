package greeting;

import java.util.List;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return "Hello, " + "my friend" + ".";
        }

        Names namesObj = new Names(names);
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

}
