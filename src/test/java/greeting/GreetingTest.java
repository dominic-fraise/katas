package greeting;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingTest {

    @Test
    void shouldReturnHelloName() {
        String greetResult = Greeting.greet("Bob");

        assertThat(greetResult).isEqualTo("Hello, Bob.");

    }

    @Test
    void shouldReturnNullCase() {
        String name = null;
        String actual = Greeting.greet(name);

        assertThat(actual).isEqualTo("Hello, my friend.");

    }

    @Test
    void shouldShoutBack() {
        String actual = Greeting.greet("JERRY");

        assertThat(actual).isEqualTo("HELLO JERRY!");

    }

    @Test
    void shouldHandleTwoNames() {
        String actual = Greeting.greet(List.of("Jill", "Jane"));

        assertThat(actual).isEqualTo("Hello, Jill and Jane.");
    }
}
