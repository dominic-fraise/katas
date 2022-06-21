package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeting {

    static class Names {
        public Names(List<String> upperCaseNames, List<String> lowerCaseNames, String[] allNames) {
            this.upperCaseNames = upperCaseNames;
            this.lowerCaseNames = lowerCaseNames;
            this.names = allNames;
        }

        List<String> upperCaseNames;
        List<String> lowerCaseNames;
        String[] names;
    }

    static String greet(String... names) {
        if (names == null) {
            return "Hello, " + "my friend" + ".";
        }

        List<String> splitNames = Arrays.stream(names)
                .flatMap(Greeting::splitNames)
                .collect(Collectors.toList());

        names = splitNames.stream().toArray(String[]::new);

        List<String> upperCaseNames = Arrays.stream(names).filter(Greeting::isUpperCase).collect(Collectors.toList());
        List<String> lowerCaseNames = Arrays.stream(names).filter(name -> !isUpperCase(name)).collect(Collectors.toList());

        Names namesObj = new Names(upperCaseNames, lowerCaseNames, names);
        return buildSentence(namesObj);

    }

    private static String buildSentence(Names namesObj) {
        //mixed lower case and upper case
        if (namesObj.lowerCaseNames.size() > 2 && !namesObj.upperCaseNames.isEmpty())  {
            List<String> lowerCaseNamesWithoutLast = namesObj.lowerCaseNames.subList(0, namesObj.lowerCaseNames.size()-1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) +
                    ", and " + namesObj.lowerCaseNames.get(namesObj.lowerCaseNames.size() - 1) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.upperCaseNames) + "!";
            //probs only used for 2 lower case names? + upper case
        }
        if (!namesObj.lowerCaseNames.isEmpty() && !namesObj.upperCaseNames.isEmpty()){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", namesObj.lowerCaseNames) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.upperCaseNames) + "!";

        }
        if (namesObj.names.length == 0) {
            return "Hello nameless";
        }
        if (namesObj.names.length == 1) {
            String name = namesObj.names[0];
            if (isUpperCase(name)) {
                return "HELLO " + name + "!";
            }
            return "Hello, " + name + ".";
        }
        if (namesObj.names.length == 2) {
            return "Hello, " + String.join(" and ", namesObj.names) + ".";
        }
        //> 2 lower case only
        String[] newNames = Arrays.copyOfRange(namesObj.names, 0, namesObj.names.length - 1);
        return "Hello, " + String.join(", ", newNames) + ", and " + namesObj.names[namesObj.names.length - 1] + '.';
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
