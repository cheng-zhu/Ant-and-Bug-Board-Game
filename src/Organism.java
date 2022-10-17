class Organism {

    /** The Organism constructor. Takes in its position when initializing.*/
    Organism(int position) {
        _position = position;
        _cycles = 0;
    }

    /** The default move method. The Organism move to position + delta. */
    public void move(int delta) {
        _position = _position + delta;
    }

    /** Get the cycles of the Organism. */
    public int getCycles() {
        return _cycles;
    }

    /** Get the position of the Organism. */
    public int getPosition() {
        return _position;
    }

    /** Add one cycle. */
    public void addCycle() {
        _cycles++;
    }

    /** Set the cycle to 0 again. */
    public void resetCycle() {
        _cycles = 0;
    }

    /** Instance variable _CYCLES, to record how many cycles have the organism
     * live through. It starts from 0 when a creature is born.*/
    private int _cycles;

    /** Instance variable _POSITION. The position of the organism on the board.
     * Use the same index as the Board class. */
    private int _position;
}
