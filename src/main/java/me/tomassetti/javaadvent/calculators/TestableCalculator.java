package me.tomassetti.javaadvent.calculators;

import com.google.common.collect.ImmutableMap;
import javaslang.Function2;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

public class TestableCalculator implements Route {

    private Map<String, Function2<Long, Long, Long>> functions = ImmutableMap.of(
            "add", (a, b) -> a + b,
            "mul", (a, b) -> a * b,
            "div", (a, b) -> a / b,
            "sub", (a, b) -> a - b);

    public long calculate(String operatorName, long left, long right) {
        return functions.get(operatorName).apply(left, right);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        long left = Long.parseLong(request.params(":left"));
        String operatorName = request.params(":operator");
        long right = Long.parseLong(request.params(":right"));
        return calculate(operatorName, left, right);
    }
}

