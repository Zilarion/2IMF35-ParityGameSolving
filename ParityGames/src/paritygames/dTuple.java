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
        if (max == null) {
            throw new IllegalTupleException("Max tuple value is not set");
        }
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
    
    public int size() {
        return tupleSize;
    }

    public void set(int position, int value) throws IllegalTupleException {
        if (max != null) {
            if (max.get(position) < value) {
                throw new IllegalTupleException("Cannot set tuple value higher then max");
            }
        }
        tuple[position] = value;
    }

    public void increment(int position) throws IllegalTupleException {
        if (max != null) {
            if (max.get(position) < tuple[position] + 1) {
                throw new IllegalTupleException("Cannot increment tuple value higher then max");
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

    public boolean eq(int position, dTuple dtuple) {
        if (isTop) {
            // Only equal if the other is also top
            return dtuple.isTop();
        } else {
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

    public boolean lt(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, everything is less then us except for top
            return !dtuple.isTop();
        } else {
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
            // If we are top, everything is less or equal to us
            return true;
        } else {
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

    public boolean gt(int position, dTuple dtuple) {
        if (isTop) {
            // If we are top, nothing is greater then us
            return false;
        } else {
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
            // If we are top, only top is equal to us
            return dtuple.isTop();
        } else { 
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
