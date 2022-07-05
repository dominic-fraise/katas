package greeting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Names {
    public Names(String... names) {
        this.allNames = Arrays.stream(names)
                .flatMap(this::splitNames)
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private final List<Name> allNames;

    private Stream<String> splitNames(String name) {
        if (name.startsWith("\"") && name.endsWith("\"") && name.length() > 1) {
            return Stream.of(name.replace("\"", ""));
        }
        return Arrays.stream(name.split(", "));
    }

    List<String> getLowercase() {
        return allNames.stream().filter(name -> !name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
    }

    List<String> getUpperCase() {
        return allNames.stream().filter(name -> name.isUpperCase).map(name -> name.value).collect(Collectors.toList());
    }

    List<String> getAll() {
        return allNames.stream().map(name -> name.value).collect(Collectors.toList());
    }

    boolean hasOneUppercaseName() {
        return getUpperCase().size() == 1;
    }

    boolean hasOneLowercaseName() {
        return getLowercase().size() == 1;
    }

    boolean hasTwoLowercaseNames() {
        return getLowercase().size() == 2;
    }

    boolean hasManyMulticaseNames() {
        return getLowercase().size() > 2 && !getUpperCase().isEmpty();
    }

    boolean hasManyMulticaseNamesWithLessThanTwoLower() {
        return getLowercase().size() > 0 && getLowercase().size() <= 2 && !getUpperCase().isEmpty();
    }

    boolean hasMoreThanTwoLowercaseNames() {
        return getLowercase().size() > 2;
    }
}
