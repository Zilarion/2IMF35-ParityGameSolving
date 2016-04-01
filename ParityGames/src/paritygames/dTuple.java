package paritygames;

/**
 *
 * @author ruudandriessen
 */
public class dTuple {
    private static int tupleSize= -1;
    private static dTuple max;
    private int[] tuple;
    private boolean isTop;

    public dTuple() throws IllegalTupleException {
        this(new int[tupleSize]);
    }

    public dTuple(int[] tuple) throws IllegalTupleException {
        if (tupleSize == -1) {
            throw new IllegalTupleException("Tuple size is not set");
        }
        if (tuple.length != tupleSize) {
            throw new IllegalTupleException("Tuple size is equal to tuple size");
        }
        this.tuple = tuple;
    }
    
    public static void setTupleSize(int size) {
        tupleSize = size;
    }
    
    public static void setMaxTuple(dTuple maxTuple) throws IllegalTupleException {
        // Check that max tuple is not top
        if (maxTuple.isTop()) {
            throw new IllegalTupleException("Max tuple cannot have value T");
        }
        
        // Check if all even values are zero
        for (int i = 0; i < tupleSize; i++) {
            if (i % 2 == 0 && maxTuple.get(i) != 0) {
                throw new IllegalTupleException("Max tuple must always have even values == 0");
            }
        }
        
        // Set our max tuple
        max = maxTuple;
    }
    
    public static int size() {
        return tupleSize;
    }

    public void set(int position, int value) throws IllegalTupleException {
        if (max != null) {
            if (max.get(position) < value) {
                setTop(true);
            }
        }
        tuple[position] = value;
    }

    public void increment(int position) throws IllegalTupleException {
        if (max != null) {
            if (max.get(position) < tuple[position] + 1) {
                setTop(true);
            }
        }
        tuple[position]++;
    } 
    
    public int get(int position) {
        return tuple[position];
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isTop() {
        return isTop;
    }

    public boolean eq(dTuple dtuple) {
        return eq(tupleSize - 1, dtuple);
    }
    
    public boolean eq(int position, dTuple dtuple) {
        if (isTop) {
            // Only equal if the other is also top
            return dtuple.isTop();
        } else {
            if (dtuple.isTop()) {
                // We would never be equal to top
                return false;
            }
            // Check if our equals operator holds up to the final position
            for (int i = 0; i < position; i++) {
                if (dtuple.get(i) != tuple[i]) {
                    return false;
                }
            }
            // Check if our equals operator holds at position
            return dtuple.get(position) == tuple[position];
        }
    }

    public boolean lt(dTuple dtuple) {
        return lt(tupleSize - 1, dtuple);
    }
    
    public boolean lt(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, dtuple is never less then us
            return false;
        } else {
            if (dtuple.isTop()) {
                // If dtuple is top and we are not, dtuple is less then us
                return true;
            }
            // Check that all previous tuple values are at least the same or less
            for (int i = 0; i < position; i++) {
                if (!(dtuple.get(i) <= tuple[i])) {
                    return false;
                }
            }
            // Check at our final position if < holds
            return dtuple.get(position) < tuple[position];
        }
    }

    public boolean lte(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, then dtuple is only equal if it is also top
            return dtuple.isTop();
        } else {
            if (dtuple.isTop()) {
                // if we are not top, but dtuple is we are always less then dtuple
                return true;
            }
            
            // Check that all previous tuple values are at least the same or less
            for (int i = 0; i < position; i++) {
                if (!(dtuple.get(i) <= tuple[i])) {
                    return false;
                }
            }
            // Check at our final position if <= holds
            return dtuple.get(position) <= tuple[position];
        }
    }

    public boolean gt(dTuple dtuple) {
        return gt(tupleSize - 1, dtuple);
    }
    
    public boolean gt(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, we are definitly bigger, except when dtuple is T
            return !dtuple.isTop();
        } else {
            if (dtuple.isTop()) {
                // If we are not top, but dtuple is, we are never greater
                return false;
            }
            // Check that all previous tuple values are bigger or more
            for (int i = 0; i < position; i++) {
                if (!(dtuple.get(i) >= tuple[i])) {
                    return false;
                }
            }
            // Check at our final position if > holds
            return dtuple.get(position) > tuple[position];
        }
    }

    public boolean gte(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, only everyhint is gt or equal to us
            return true;
        } else { 
            if (dtuple.isTop()) {
                // If we are not top, but dtuple is, we are never greater nor equal
                return false;
            }
            
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

    @Override
    public String toString() {
        if (isTop) {
            return "T";
        } else {
            String val = "(";
            for (int i = 0; i < tuple.length - 1; i++) {
                val += tuple[i] + ",";
            }
            val += tuple[tuple.length - 1] + ")";
            return val;
        }
    }

}
