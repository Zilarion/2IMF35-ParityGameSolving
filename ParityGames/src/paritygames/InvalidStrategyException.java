package paritygames;

/**
 *
 * @author ruudandriessen
 */
class InvalidStrategyException extends Exception {
    public InvalidStrategyException() {
    }

    //Constructor that accepts a message
    public InvalidStrategyException(String message) {
        super(message);
    }

}
