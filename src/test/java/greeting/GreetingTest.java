package greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingTest {

    @Test
    void shouldReturnHelloName() {
        String greetResult = Greeting.greet("Bob");

        assertThat(greetResult).isEqualTo("Hello, Bob.");

    }

    @Test
    void shouldReturnNullCase() {
        String actual = Greeting.greet(null);

        assertThat(actual).isEqualTo("Hello, my friend.");

    }

    @Test
    void shouldShoutBack() {
        String actual = Greeting.greet("JERRY");

        assertThat(actual).isEqualTo("HELLO JERRY!");

    }
}
