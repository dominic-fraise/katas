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
        return concatenateString(names.get(0) + " and " + names.get(1));
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
