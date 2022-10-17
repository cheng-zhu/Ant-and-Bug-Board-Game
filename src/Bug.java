

class Bug extends Organism {

    Bug(int position) {
        super(position);
        _numEat = 0;
    }

    /** The bug has eaten! */
    public void bugEat() {
        _numEat = 0;
    }

    /** This cycle the bug didn't eat. */
    public void bugNotEat() {
        _numEat++;
    }

    /** This cycle the bug didn't eat. */
    public int numEat() {
        return _numEat;
    }

    /** How many cycles did the bug not eat. */
    private int _numEat;
}
