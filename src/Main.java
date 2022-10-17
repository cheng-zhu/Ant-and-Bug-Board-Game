import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** The main class, where prompt the users to type ENTER to make another move. */
public class Main {
    public static void main(String[] args0) {
        _b = new Board();
        _realBoard = _b.getRealBoard();
        _antArr = new ArrayList<>();
        _bugArr = new ArrayList<>();
        _neighborArr = new int[]{-22, -1, 1, 22};
        setInitialAntBug();
        _numBugs = 5;
        _numAnts = 100;
        System.out.println("Welcome to the game! This is the starting status.");
        display();
        while (true) {
            if (_numBugs == 0) {
                System.out.println("Game over! There is no bug.");
                System.exit(0);
            } else if (_numAnts == 0) {
                System.out.println("Game over! There is no ant.");
                System.exit(0);
            } else {
                promptEnterKey();
                oneStep();
                display();
            }
        }
    }

    private static void setInitialAntBug() {
        final int[] antBugArr = new Random().
                ints(0, 399).
                distinct().limit(105).toArray();
        for (int i = 0; i < 100; i++) {
            _b.changeBoard(_realBoard[antBugArr[i]], 'o');
            _antArr.add(new Ant(_realBoard[antBugArr[i]]));
        }
        for (int i = 100; i < 105; i++) {
            _b.changeBoard(_realBoard[antBugArr[i]], 'X');
            _bugArr.add(new Bug(_realBoard[antBugArr[i]]));
        }
    }

    private static void oneStep() {
        ArrayList<Ant> newAnts = new ArrayList<>();
        ArrayList<Bug> newBugs = new ArrayList<>();
        ArrayList<Ant> deadAnts = new ArrayList<>();
        ArrayList<Bug> deadBugs = new ArrayList<>();
        for (Bug bug : _bugArr) {
            shuffleArray(_neighborArr);
            int trial = 0;
            for (int j : _neighborArr) {
                if (_b.getBoard(bug.getPosition() + j) == 'o') {
                    for (Ant deadAnt : _antArr) {
                        if (deadAnt.getPosition() == bug.getPosition() + j) {
                            deadAnts.add(deadAnt);
                            _numAnts--;
                        }
                    }
                    _b.changeBoard(bug.getPosition(), '-');
                    bug.move(j);
                    bug.bugEat();
                    _b.changeBoard(bug.getPosition(), 'X');
                    break;
                }
                trial++;
                if (trial == 4) {
                    if (_b.getBoard(bug.getPosition() + j) == '-') {
                        _b.changeBoard(bug.getPosition(), '-');
                        bug.move(j);
                        _b.changeBoard(bug.getPosition(), 'X');
                    }
                    bug.bugNotEat();
                    if (bug.numEat() == 3) {
                        _numBugs--;
                        for (Bug deadBug : _bugArr) {
                            if (deadBug.getPosition() == bug.getPosition()) {
                                deadBugs.add(deadBug);
                                _b.changeBoard(bug.getPosition(), '-');
                            }
                        }
                    }
                }
            }
            bug.addCycle();
            if (!deadBugs.contains(bug) && bug.getCycles() == 8) {
                shuffleArray(_neighborArr);
                for (int j : _neighborArr) {
                    if (_b.getBoard(bug.getPosition() + j) == '-') {
                        _b.changeBoard(bug.getPosition() + j, 'X');
                        newBugs.add(new Bug(bug.getPosition() + j));
                        bug.resetCycle();
                        _numBugs++;
                        break;
                    }
                }
            }
        }
        _bugArr.removeAll(deadBugs);
        _bugArr.addAll(newBugs);
        _antArr.removeAll(deadAnts);

        for (Ant ant : _antArr) {
            int i = new Random().nextInt(4);
            int deltaI = _neighborArr[i];
            if (_b.getBoard(ant.getPosition() + deltaI) == '-') {
                _b.changeBoard(ant.getPosition(), '-');
                ant.move(deltaI);
                _b.changeBoard(ant.getPosition(), 'o');
            }
            ant.addCycle();
            if (ant.getCycles() == 3) {
                shuffleArray(_neighborArr);
                for (int k : _neighborArr) {
                    if (_b.getBoard(ant.getPosition() + k) == '-') {
                        _b.changeBoard(ant.getPosition() + k, 'o');
                        newAnts.add(new Ant(ant.getPosition() + k));
                        _numAnts++;
                        ant.resetCycle();
                        break;
                    }
                }
            }
        }
        _antArr.addAll(newAnts);
    }

    private static void display() {
        int j = 0;
        for (int i : _realBoard) {
            System.out.print(_b.getBoard(i) + " ");
            j++;
            if (j == 20) {
                j = 0;
                System.out.println();
            }
        }
        System.out.println();
    }

    private static Board _b;

    private static int[] _realBoard;

    private static ArrayList<Ant> _antArr;

    private static ArrayList<Bug> _bugArr;

    private static int[] _neighborArr;

    private static int _numBugs;

    private static int _numAnts;

    private static void shuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    private static void promptEnterKey(){
        System.out.print("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
