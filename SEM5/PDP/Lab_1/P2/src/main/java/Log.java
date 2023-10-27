class Log {
    private static int count = 1;
    private int id;
    private Account to;
    private int value;

    public Log(Account to, int value) {
        this.id = count;
        count += 1;
        this.to = to;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("log ")
                .append(id)
                .append(" transferred to ")
                .append(to)
                .append(" amount: ")
                .append(value);
        return stringBuilder.toString();
    }
}
