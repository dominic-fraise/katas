package greeting;

public class Greeting {

    static String greet(String name) {

        if (name == null) {
            return concatenateString("my friend");
        }

        return concatenateString(name);
    }

    private static String concatenateString(String name) {
        return "Hello, " + name + ".";
    }
}
