package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue{
    private int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(this.value);
    }
}
