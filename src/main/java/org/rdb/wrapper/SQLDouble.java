package org.rdb.wrapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDouble {
	int Precision() default 0;
	int Scale() default 0;
	String name() default "";
	String caption() default "";
	Constrains constrains() default @Constrains;
}
