public class Program {
    public static void main(String[] args) {
        ToyManager toyManager = new ToyManager();

        toyManager.addNewToy(1, "toy1", 10, 20.0);
        toyManager.addNewToy(2, "toy2", 5, 30.0);
        toyManager.addNewToy(3, "toy3", 7, 50.0);

        toyManager.changeToyWeight(2, 40.0);

        System.out.println("Result:");
        for (int i = 0; i < 5; i++) {
            Toy selectedToy = toyManager.selectPrizeToy();
            System.out.println("Choise: " + selectedToy);
        }

        System.out.println("Prize:");
        for (int i = 0; i < 5; i++) {
            Toy prizeToy = toyManager.getPrizeToy();
            System.out.println("Prize: " + prizeToy);
        }
    }
}
