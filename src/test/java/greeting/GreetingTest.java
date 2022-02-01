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

    @Test
    void shouldGreetTwoNames() {
        String greetResult = Greeting.greet("Jill", "Jane");

        assertThat(greetResult).isEqualTo("Hello, Jill and Jane.");
    }

    @Test
    void shouldGreetMultipleNames() {
        String greetResult = Greeting.greet("Amy", "Brian", "Charlotte");

        assertThat(greetResult).isEqualTo("Hello, Amy, Brian, and Charlotte.");
    }

    @Test
    void shouldGreetWithNoName() {
        String greetResult = Greeting.greet();

        assertThat(greetResult).isEqualTo("Hello nameless");
    }

    @Test
    void shouldGreetWithMixedCasing() {
        String greetResult = Greeting.greet("Amy", "BRIAN", "Charlotte");

        assertThat(greetResult).isEqualTo("Hello, Amy and Charlotte. AND HELLO BRIAN!");
    }

    @Test
    void shouldGreetWithMixedCasingWithTwoShoutNames() {
        String greetResult = Greeting.greet("AMY", "BRIAN", "Charlotte");

        assertThat(greetResult).isEqualTo("Hello, Charlotte. AND HELLO AMY AND BRIAN!");
    }

    @Test
    void shouldGreetWithMixedCasingWithThreeLowerCaseNames() {
        String greetResult = Greeting.greet("AMY", "Brian", "Charlotte", "Jill");

        assertThat(greetResult).isEqualTo("Hello, Brian, Charlotte, and Jill. AND HELLO AMY!");
    }
}
