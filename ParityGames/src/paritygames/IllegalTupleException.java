package paritygames;

/**
 *
 * @author ruudandriessen
 */
class IllegalTupleException extends Exception {

    public IllegalTupleException() {
    }
    
    //Constructor that accepts a message
    public IllegalTupleException(String message) {
        super(message);
    }

}
