package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeting {

    static class Names {
        public Names(List<String> upperCaseNames, List<String> lowerCaseNames, List<String> names2, List<Name> allNames) {
            this.upperCaseNames = upperCaseNames;
            this.lowerCaseNames = lowerCaseNames;
            this.names = names2;
            this.allNames = allNames;
        }

        List<String> upperCaseNames;
        List<String> lowerCaseNames;
        List<String> names;
        List<Name> allNames;
        List<String> getLowercase(){
            return allNames.stream().filter(name -> !name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
        }
        List<String> getUpperCase(){
            return allNames.stream().filter(name -> name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
        }
        List<String> getAll(){
            return allNames.stream().map(name -> name.value).collect(Collectors.toList());
        }
    }
    static class Name {
        String value;
        boolean isUpperCase;

        public Name(String value) {
            this.value = value;
            this.isUpperCase = isUpperCase(value);
        }

    }

    static String greet(String... names) {
        if (names == null) {
            return "Hello, " + "my friend" + ".";
        }

        List<String> splitNames = Arrays.stream(names)
                .flatMap(Greeting::splitNames)
                .collect(Collectors.toList());

        List<Name> allNames = splitNames.stream().map(Name::new).collect(Collectors.toList());
        List<String> upperCaseNames = splitNames.stream().filter(Greeting::isUpperCase).collect(Collectors.toList());
        List<String> lowerCaseNames = splitNames.stream().filter(name -> !isUpperCase(name)).collect(Collectors.toList());

        Names namesObj = new Names(upperCaseNames, lowerCaseNames, splitNames, allNames);
        return buildSentence(namesObj);

    }

    private static String buildSentence(Names namesObj) {
        //mixed lower case and upper case
        if (namesObj.getLowercase().size() > 2 && !namesObj.getUpperCase().isEmpty())  {
            List<String> lowerCaseNamesWithoutLast = namesObj.getLowercase().subList(0, namesObj.getLowercase().size()-1);
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) +
                    ", and " + namesObj.getLowercase().get(namesObj.getLowercase().size() - 1) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.getUpperCase()) + "!";
            //probs only used for 2 lower case names? + upper case
        }
        if (!namesObj.getLowercase().isEmpty() && !namesObj.getUpperCase().isEmpty()){
            String lowerCaseGreeting = "Hello, " + String.join(" and ", namesObj.getLowercase()) + ".";
            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.getUpperCase()) + "!";

        }
        if (namesObj.getAll().size() == 0) {
            return "Hello nameless";
        }
        if (namesObj.getAll().size() == 1) {
            String name = namesObj.getAll().get(0);
            if (isUpperCase(name)) {
                return "HELLO " + name + "!";
            }
            return "Hello, " + name + ".";
        }
        if (namesObj.getAll().size() == 2) {
            return "Hello, " + namesObj.getAll().get(0) + " and " + namesObj.getAll().get(1) + ".";
        }
        //> 2 lower case only
        List<String> newNames = namesObj.getAll().subList(0, namesObj.getAll().size() - 1);
        return "Hello, " + String.join(", ", newNames) + ", and " + namesObj.getAll().get(namesObj.getAll().size() - 1) + '.';
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
