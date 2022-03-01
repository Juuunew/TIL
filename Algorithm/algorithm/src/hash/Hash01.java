package hash;

public class Hash01 {
    public Slot[] hashTable;


    public Hash01(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String value;

        Slot(String value) {
            this.value = value;
        }
    }

    public int hashFunc(String key) {
        return (int) (key.charAt(0)) % this.hashTable.length;
    }

    public static void main(String[] args) {
    }

}
