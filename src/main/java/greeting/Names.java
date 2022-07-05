package greeting;

import java.util.List;
import java.util.stream.Collectors;

class Names {
    public Names(List<Name> allNames) {
        this.allNames = allNames;
    }

    private final List<Name> allNames;

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
