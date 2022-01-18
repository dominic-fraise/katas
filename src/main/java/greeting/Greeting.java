package greeting;

public class Greeting {

    static String greet(String name) {

        if (name == null) {
            return concatenateString("my friend");
        }

        if (isUpperCase(name)) {
            return "HELLO " + name + "!";
        }

        return concatenateString(name);
    }

    private static boolean isUpperCase(String name) {
        return name.equals(name.toUpperCase());
    }

    private static String concatenateString(String name) {
        return "Hello, " + name + ".";
    }
}
