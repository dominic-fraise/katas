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

        private boolean hasOneUppercaseName() {
            return getUpperCase().size() == 1;
        }

        private  boolean hasOneLowercaseName() {
            return getLowercase().size() == 1;
        }

        private boolean hasNoNames() {
            return getAll().size() == 0;
        }

        private boolean hasTwoLowercaseNames() {
            return getLowercase().size() == 2;
        }

        private boolean hasManyMulticaseNames() {
            return getLowercase().size() > 2 && !getUpperCase().isEmpty();
        }

        private boolean hasManyMulticaseNamesWithLessThanTwoLower() {
            return getLowercase().size() > 0 && getLowercase().size() <= 2 && !getUpperCase().isEmpty();
        }

        private boolean hasMoreThanTwoLowercaseNames() {
            return getLowercase().size() > 2;
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

        Names namesObj = new Names(allNames);
        return buildSentence(namesObj);

    }

    private static String buildSentence(Names namesObj) {
        //mixed lower case and upper case
        if (namesObj.hasManyMulticaseNames())  {
            List<String> lowerCaseNamesWithoutLast = getAllButLastItem(namesObj.getLowercase());
            String lowerCaseGreeting =
                "Hello, " + String.join(", ", lowerCaseNamesWithoutLast) + ", and " + namesObj.getLowercase().get(namesObj.getLowercase().size() - 1) + '.';

            return lowerCaseGreeting + " AND HELLO " + String.join(" AND ", namesObj.getUpperCase()) + "!";
            //probs only used for 2 lower case names? + upper case
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
