package effective_java.item03;

class UniqueSuperCar {
    public static final UniqueSuperCar UNIQUE_SUPER_CAR = new UniqueSuperCar();

    private UniqueSuperCar() { }

    public void run() {
        System.out.println("run~~~");
    }
}

public class RacingGame {
    private final UniqueSuperCar uniqueSuperCar;

    public RacingGame(UniqueSuperCar uniqueSuperCar) {
        this.uniqueSuperCar = uniqueSuperCar;
    }

    public void runGame() {
        readyToRace();
        uniqueSuperCar.run();
        carMaintenance();
    }

    private void readyToRace() {
        System.out.println("클라이언트 : ready~~");
    }

    private void carMaintenance() {
        System.out.println("클라이언트 : under maintenance~~");
    }
}
