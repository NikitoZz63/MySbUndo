package test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyStringBuilder {

    private final Deque<StringBuilderMemento> history = new ArrayDeque<>();
    private char[] value;
    private int count;

    public MyStringBuilder() {
        this.value = new char[16];
    }

    public MyStringBuilder append(String str) {
        save();
        if (str == null) {
            str = "null";
        }

        int len = str.length();
        ensureCapacity(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    public MyStringBuilder append(char c) {
        save();
        ensureCapacity(count + 1);
        value[count] = c;
        count++;
        return this;
    }


    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= value.length) {
            return;
        }

        int newCapacity = value.length * 2 + 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }

        value = Arrays.copyOf(value, newCapacity);
    }

    public void save() {
        history.push(new StringBuilderMemento(this.count, this.value));
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        StringBuilderMemento stringBuilderMemento = history.pop();
        this.value = stringBuilderMemento.getValue();
        this.count = stringBuilderMemento.getCount();
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }

    public static class StringBuilderMemento {
        private final char[] value;
        private final int count;

        private StringBuilderMemento(int count, char[] value) {
            this.count = count;
            this.value = value;
        }

        public char[] getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }
    }

}
