package greeting;

public class Greeting {

    static String greet(String name) {

        if (name == null) {
            return "Hello, my friend.";
        }

        return "Hello, " + name + ".";
    }
}
