import java.util.List;

public class Production {
    private String key;
    private List<String> values;

    public Production() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return key + " -> " + values;
    }
}
