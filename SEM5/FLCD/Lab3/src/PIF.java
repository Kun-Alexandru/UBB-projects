import java.util.AbstractMap;
import java.util.ArrayList;

public class PIF {

    private final ArrayList<AbstractMap.SimpleEntry<AbstractMap.SimpleEntry<Integer, Integer>,String>> elements;

    public PIF() {
        this.elements = new ArrayList<>();
    }

    public void add(AbstractMap.SimpleEntry position, String symbol) throws Exception {
        elements.add(new AbstractMap.SimpleEntry<>(position,symbol));
    }

    @Override
    public String toString() {
        String stString = "PIF\n";
        for (int i=0; i<elements.size(); i++) {
            stString += "( ";
            stString += "("+elements.get(i).getKey().getKey()+", "+elements.get(i).getKey().getValue()+"), ";
            stString += elements.get(i).getValue();
            stString += " )\n";
        }
        return stString;
    }
}
