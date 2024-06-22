package effectiveJava.item03;

// RacingCar 인터페이스를 구현
class UniqueSuperCar implements RacingCar{
    public static final UniqueSuperCar UNIQUE_SUPER_CAR = new UniqueSuperCar();

    private UniqueSuperCar() { }

    @Override
    public void run() {
        System.out.println("싱글턴: run~~~");
    }
}

// Mock Class
class MockRacingCar implements RacingCar {
    @Override
    public void run() {
        System.out.println("가상 객체: test run ~~~~");
    }
}

public class RacingGame {
//    private final UniqueSuperCar uniqueSuperCar;
    private final RacingCar racingCar;

//    public RacingGame(UniqueSuperCar uniqueSuperCar) {
//        this.uniqueSuperCar = uniqueSuperCar;
//    }
    public RacingGame(RacingCar racingCar) {
        this.racingCar = racingCar;
    }

    public void runGame() {
        readyToRace();
//        uniqueSuperCar.run(); // 테스트 할 클라이언트 메서드가 해당 싱글톤을 사용 중
        racingCar.run();
        carMaintenance();
    }

    private void readyToRace() {
        System.out.println("클라이언트 : ready~~");
    }

    private void carMaintenance() {
        System.out.println("클라이언트 : under maintenance~~");
    }

    public static void main(String[] args) {
//        RacingGame racingGame = new RacingGame(UniqueSuperCar.UNIQUE_SUPER_CAR);

        // 실제 객체 대신 가짜 객체 주입
        RacingGame racingGame = new RacingGame(new MockRacingCar());

        try {
            racingGame.runGame();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}