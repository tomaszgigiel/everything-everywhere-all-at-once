package pl.tomaszgigiel.lambda.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// https://www.baeldung.com/java-lambda-exceptions
class LambdaExceptionWrappersTest {

	@Test
	void handlingConsumerWrapper() {
		List<Integer> integers = Arrays.asList(1, 2, 3);
		List<Integer> expected = Arrays.asList(1, 3);
		List<Integer> performed = new ArrayList<Integer>();

		integers.forEach(LambdaExceptionWrappers.handlingConsumerWrapper(i -> possibleIOException(i, performed), IOException.class));
		Assertions.assertEquals(expected, performed);
	}

	@Test
	void handlingConsumerWrapperException() {
		List<Integer> integers = IntStream.rangeClosed(1, 100).boxed().toList();
		List<Integer> performed = new ArrayList<Integer>();

		Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
			integers.forEach(LambdaExceptionWrappers.handlingConsumerWrapper(i -> possibleIOException(i, performed), IOException.class));
		});

		Assertions.assertEquals("java.lang.RuntimeException: Intentionally 'throw new RuntimeException' when a == 11", exception.getMessage());
	}

	private void possibleIOException(Integer a, List<Integer> list) throws IOException {
		if (a % 2 == 0) {
			throw new IOException("Intentionally 'throw new IOException' when a == " + a);
		}
		if (a > 10) {
			throw new RuntimeException("Intentionally 'throw new RuntimeException' when a == " + a);
		}
		list.add(a);
	}

}
