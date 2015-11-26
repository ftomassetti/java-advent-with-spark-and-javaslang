package me.tomassetti.javaadvent;

import me.tomassetti.javaadvent.calculators.ReallyTestableCalculator;
import me.tomassetti.javaadvent.calculators.TestableCalculator;

import static spark.Spark.exception;
import static spark.Spark.get;

public class SparkService {
    public static void main(String[] args) {
        exception(NumberFormatException.class, (e, req, res) -> res.status(404));
        exception(ArithmeticException.class, (e, req, res) -> {
            res.status(400);
            res.body("This does not seem a good idea to me");
        });
        get("/:left/:operator/:right", new ReallyTestableCalculator());
    }
}
