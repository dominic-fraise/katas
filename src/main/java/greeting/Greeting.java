package greeting;

public class Greeting {

    static String greet(String... names) {
        if (names == null) {
            return concatenateString("my friend");
        }
        if (names.length == 1) {
            String name = names[0];
            if (isUpperCase(name)) {
                return concatenateShout(name);
            }

            return concatenateString(name);
        } else {
            return "Hello, " + String.join(" and ", names) + ".";
        }
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
