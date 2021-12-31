package greeting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingTest {

    @Test
    void renameMe() {
        String greeting = Greeting.greet("");

        assertThat(greeting).isNotNull();
    }
}