package pl.tomaszgigiel.lambda.exceptions;

import java.util.function.Consumer;

import lombok.extern.log4j.Log4j2;

// https://www.baeldung.com/java-lambda-exceptions
@Log4j2
public class LambdaExceptionWrappers {

       public static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
               return i -> {
                       try {
                               throwingConsumer.accept(i);
                       } catch (Exception ex) {
                               try {
                                       E exCast = exceptionClass.cast(ex);
                                       log.error(ex.getLocalizedMessage(), exCast);
                               } catch (ClassCastException ccEx) {
                                       throw new RuntimeException(ex);
                               }
                       }
               };
       }

}
