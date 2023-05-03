import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ToyManager {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyManager() {
        this.toys = new ArrayList<>();
        this.prizeToys = new ArrayList<>();
    }

    public void addNewToy(int id, String name, int quantity, double weight) {
        Toy newToy = new Toy(id, name, quantity, weight);
        toys.add(newToy);
    }

    public void changeToyWeight(int id, double newWeight) {
        Optional<Toy> toyOptional = toys.stream().filter(toy -> toy.getId() == id).findFirst();
        toyOptional.ifPresent(toy -> toy.setWeight(newWeight));
    }

    public Toy selectPrizeToy() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomWeight = new Random().nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;

        for (Toy toy : toys) {
            cumulativeWeight += toy.getWeight();
            if (randomWeight <= cumulativeWeight && toy.getQuantity() > 0) {
                prizeToys.add(toy);
                toy.setQuantity(toy.getQuantity() - 1);
                return toy;
            }
        }

        return null;
    }

    public Toy getPrizeToy() {
        if (prizeToys.isEmpty()) {
            return null;
        }

        Toy prizeToy = prizeToys.remove(0);
        writePrizeToyToFile(prizeToy);
        return prizeToy;
    }

    private void writePrizeToyToFile(Toy prizeToy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("toys.txt", true))) {
            writer.write(prizeToy.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
