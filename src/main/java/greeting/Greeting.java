package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greeting {

    static class Names {
        public Names(List<Name> allNames) {
            this.allNames = allNames;
        }

        List<Name> allNames;

        private Name getLastName() {
            return this.allNames.get(this.allNames.size() - 1);
        }

        List<String> getLowercase(){
            return allNames.stream().filter(name -> !name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
        }
        List<String> getUpperCase(){
            return allNames.stream().filter(name -> name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
        }
        List<String> getAll(){
            return allNames.stream().map(name -> name.value).collect(Collectors.toList());
        }

        public List<Name> getAllNames() {
            return this.allNames;
        }

    }
    static class Name {
        String value;
        boolean isUpperCase;

        public Name(String value) {
            this.value = value;
            this.isUpperCase = isUpperCase(value);
        }

        public String toString() {
            return this.value;
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

        Names namesObj = new Names(allNames);
        return buildSentence(namesObj);

    }

    private static String buildSentence(Names namesObj) {
        //mixed lower case and upper case
        if (namesObj.getLowercase().size() > 2 && !namesObj.getUpperCase().isEmpty())  {
            List<String> lowerCaseNamesWithoutLast = getLowerCaseNamesWithoutLast(namesObj.getLowercase());
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
        if (namesObj.getAll().isEmpty()) {
            return "Hello nameless";
        }

        Name name = namesObj.getAllNames().get(0);
        if (namesObj.getAll().size() == 1 && name.isUpperCase) {
                return "HELLO " + name.value + "!";
        }
        if (namesObj.getAll().size() == 1 && !name.isUpperCase) {
            return "Hello, " + name.value + ".";
        }
        if (namesObj.getAll().size() == 2) {
            return "Hello, " + namesObj.getAll().get(0) + " and " + namesObj.getAll().get(1) + ".";
        }
        //> 2 lower case only
        List<String> newNames = getLowerCaseNamesWithoutLast(namesObj.getAll());
        return "Hello, " + String.join(", ", newNames) + ", and " + namesObj.getLastName() + '.';
    }

    private static List<String> getLowerCaseNamesWithoutLast(List<String> list) {
        return list.subList(0, list.size() - 1);
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
