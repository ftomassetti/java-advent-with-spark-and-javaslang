package me.tomassetti.javaadvent.calculators;

import javaslang.Function2;
import javaslang.Tuple2;
import javaslang.collection.Map;
import javaslang.collection.HashMap;
import javaslang.control.Either;
import spark.Request;
import spark.Response;
import spark.Route;

public class ReallyTestableCalculator implements Route {

    private static final int NOT_FOUND = 404;

    private Map<String, Function2<Long, Long, Long>> functions = HashMap.ofAll(
            new Tuple2<>("add", (a, b) -> a + b),
            new Tuple2<>("mul", (a, b) -> a * b),
            new Tuple2<>("div", (a, b) -> a / b),
            new Tuple2<>("sub", (a, b) -> a - b));

    public Either<Error, Long> calculate(String operatorName, long left, long right) {
        Either<Error, Long> unknownOp = Either.<Error, Long>left(new Error(NOT_FOUND, "Unknown math operation"));
        return functions.get(operatorName).map(f -> Either.<Error, Long>right(f.apply(left, right)))
                .orElse(unknownOp);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        long left = Long.parseLong(request.params(":left"));
        String operatorName = request.params(":operator");
        long right = Long.parseLong(request.params(":right"));
        Either<Error, Long> res =  calculate(operatorName, left, right);
        if (res.isRight()) {
            return res.get();
        } else {
            response.status(res.left().get().getHttpCode());
            return null;
        }
    }
}

