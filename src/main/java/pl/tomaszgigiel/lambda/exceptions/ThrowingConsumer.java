package pl.tomaszgigiel.lambda.exceptions;

//https://www.baeldung.com/java-lambda-exceptions
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {

       void accept(T t) throws E;

}
