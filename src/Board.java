class Board {

    /** Number of squares on a side of the board. */
    static final int SIDE = 20;

    /** Length of a side + an artificial 1-deep border region. */
    static final int EXTENDED_SIDE = 22;

    /** Board constructor, which is a char array, linearized indexed. */
    Board() {
        _board = new char[EXTENDED_SIDE * EXTENDED_SIDE];
        _realBoard = new int[400];
        setInitialBoard();
    }

    /** Helper method to set the initial board. */
    private void setInitialBoard() {
        for (int i = 0; i < EXTENDED_SIDE; i++) {
            _board[i] = 'B';
            _board[EXTENDED_SIDE * EXTENDED_SIDE - 1 - i] = 'B';
        }
        int j = 0;
        for (int i = EXTENDED_SIDE; i < EXTENDED_SIDE * (EXTENDED_SIDE - 1); i++) {
            if (i % EXTENDED_SIDE == 0
                    || i % EXTENDED_SIDE == EXTENDED_SIDE - 1) {
                _board[i] = 'B';
            } else {
                _board[i] = '-';
                _realBoard[j] = i;
                j++;
            }
        }
    }

    /** Public method get the char of the board at POSITION. Used to check
     * what thing is currently in that square. */
    public char getBoard(int position) {
        return _board[position];
    }

    /** Change the char in that (linear) POSITION of the Board to THING. */
    public void changeBoard(int position, char thing) {
        _board[position] = thing;
    }

    public int[] getRealBoard() {
        return _realBoard;
    }

    /** Instance variable Board. */
    private final char[] _board;


    private static int[] _realBoard;
}
