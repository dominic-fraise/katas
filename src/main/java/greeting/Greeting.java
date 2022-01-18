package greeting;

public class Greeting {

    static String greet(String name) {

        if (name == null) {
            return concatenateString("my friend");
        }

        if (name.equals(name.toUpperCase())) {
            return "HELLO " + name + "!";
        }

        return concatenateString(name);
    }

    private static String concatenateString(String name) {
        return "Hello, " + name + ".";
    }
}
