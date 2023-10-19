package org.hejow.hexagonal.user;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class UserTest {
    private final FixtureMonkey SUT = FixtureMonkey.builder()
            .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .plugin(new JakartaValidationPlugin())
            .build();

    @ParameterizedTest
    @MethodSource(value = "emailRegexpSource")
    void createUser_Success_WithValidProperties(String email) {
        // given
        String name = "hejow";

        // when, then
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> new User(name, email));
    }

    @Test
    void createUser_Success_ByFixtureMonkey() {
        User user = SUT.giveMeOne(User.class);
        System.out.println(user);
    }

    @ParameterizedTest
    @MethodSource(value = "emailRegexpSource")
    void email_regexp_test(String email) {
        // given
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        // when
        boolean matches = pattern.matcher(email).matches();

        // then
        assertThat(matches).isFalse();
    }

    private static Stream<String> emailRegexpSource() {
        return Stream.of(
                "", "email", "email@", "email@gmail", "@gmail.com", "##@gmail.com", " 1@ya hoo.com"
        );
    }
}
