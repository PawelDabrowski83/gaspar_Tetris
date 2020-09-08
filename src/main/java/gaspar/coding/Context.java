package gaspar.coding;

public class Context {

    private ShapeStrategy strategy;

    public Context(ShapeStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Square a, Square b, Square c, Square d) {
        strategy.setUp(a, b, c, d);
    }
}
