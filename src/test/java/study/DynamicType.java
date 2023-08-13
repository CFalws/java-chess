package study;

import org.junit.jupiter.api.Test;

public class DynamicType {

    @Test
    void test() {
        chooseToPrintAtRuntime(1, "b", 3L, 'f');
    }

    <T> void chooseToPrintAtRuntime(T... varargs) {
        for (final T vararg : varargs) {
            System.out.println(vararg);
        }
    }
}
