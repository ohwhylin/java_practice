public enum Prices {
    ECONOMY(2500),
    STANDARD(4000),
    LUX(10000),
    ULTRALUX(15000);

    private final int amount;
    Prices(int amount) { this.amount = amount; }
    public int amount() { return amount; }
}