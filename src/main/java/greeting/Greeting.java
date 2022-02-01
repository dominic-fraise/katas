package greeting;

import java.util.Arrays;
import java.util.List;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }
        if (names.length == 0) return "";
        if (names.length == 1) {
            String name = names[0];
            return generateGreeting(name);
        } if (names.length == 2) {
            return "Hello, " + String.join(" and ", names) + ".";
        } else {
            String[] newNames = Arrays.copyOfRange(names, 0, names.length - 1);
            return "Hello, " + String.join(", ", newNames) + ", and " + names[names.length - 1] + '.';
        }
    }

    private static String generateGreeting(String name) {
        if (isUpperCase(name)) {
            return concatenateShout(name);
        }
        return concatenateString(name);
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
