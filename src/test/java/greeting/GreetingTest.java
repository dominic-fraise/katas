package greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingTest {

    @Test
    void shouldReturnHelloName() {
        String expected = "Hello, Bob.";
        String actual = Greeting.greet("Bob");
        assertThat(actual).isEqualTo(expected);

    }
}
