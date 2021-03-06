package jdk8.annotation;

import java.lang.annotation.Repeatable;

@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}





