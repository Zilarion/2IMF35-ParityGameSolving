package paritygames;

/**
 *
 * @author ruudandriessen
 */
public class dTuple {
    int[] tuple;
    
    public dTuple(int size) {
        tuple = new int[size];
    }
    
    public dTuple(int[] tuple) {
        this.tuple = tuple;
    }
    
    public void set(int position, int value) {
        tuple[position] = value;
    }
    
    public int get(int position) {
        return tuple[position];
    }
    
    public boolean eq(int position, dTuple dtuple) {
        // Check if our equals operator holds up to the final position
        for (int i = 0; i < position; i++) {
            if (dtuple.get(i) != tuple[i]) {
                return false;
            }
        }
        // Check if our equals operator holds at position
        return dtuple.get(position) == tuple[position];
    }
    
    public boolean lt(int position, dTuple dtuple) {
        // Check that all previous tuple values are at least the same or less
        for (int i = 0; i < position; i++) {
            if (!(dtuple.get(i) <= tuple[i])) {
                return false;
            }
        }
        // Check at our final position if < holds
        return dtuple.get(position) < tuple[position];
    }
    
    public boolean lte(int position, dTuple dtuple) {
        // Check that all previous tuple values are at least the same or less
        for (int i = 0; i < position; i++) {
            if (!(dtuple.get(i) <= tuple[i])) {
                return false;
            }
        }
        // Check at our final position if <= holds
        return dtuple.get(position) <= tuple[position];
    }
    
    public boolean gt(int position, dTuple dtuple) {
        // Check that all previous tuple values are bigger or more
        for (int i = 0; i < position; i++) {
            if (!(dtuple.get(i) >= tuple[i])) {
                return false;
            }
        }
        // Check at our final position if > holds
        return dtuple.get(position) > tuple[position];
    }
    
    public boolean gte(int position, dTuple dtuple) {
        // Check that all previous tuple values are bigger or more
        for (int i = 0; i < position; i++) {
            if (!(dtuple.get(i) >= tuple[i])) {
                return false;
            }
        }
        // Check at our final position if >= holds
        return dtuple.get(position) >= tuple[position];
    }
    
}
