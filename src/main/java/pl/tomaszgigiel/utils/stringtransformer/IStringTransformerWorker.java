package pl.tomaszgigiel.utils.stringtransformer;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface IStringTransformerWorker extends UnaryOperator<String> {
}
