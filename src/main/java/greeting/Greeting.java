package greeting;

import java.util.List;

public class Greeting {
    static String greet(String name) {

        if (name == null) {
            return concatenateString("my friend");
        }

        if (isUpperCase(name)) {
            return concatenateShout(name);
        }

        return concatenateString(name);
    }

    static String greet(List<String> names) {
        if(names.isEmpty()) {
            return greet((String) null);
        }
        if(names.size() == 1) {
            return greet(names.get(0));
        }
        String concat = names.size() > 2 ? ", and " : " and ";
        String namesList = String.join(", ", names.subList(0, names.size() - 1)) + concat + names.get(names.size() - 1);

        return concatenateString(namesList);
    }

    private static String concatenateShout(String name) {
        return "HELLO " + name + "!";
    }

    private static boolean isUpperCase(String name) {
        return name.equals(name.toUpperCase());
    }

    private static String concatenateString(String name) {
        return "Hello, " + name + ".";
    }
}
