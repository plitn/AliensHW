import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class CompositeAlien implements Component {
    int id;
    int fatherId;
    public int generation;
    static int lastId;
    static int maxGen;
    public ArrayList<Component> children;
    public CompositeAlien(int id, int fatherId, int generation) {
        this.id = id;
        this.fatherId = fatherId;
        this.generation = generation;
        children = new ArrayList<>();
    }

/*    public void generateChildren() {
        if (children.size() == 0 || children.get(children.size() - 1).getGeneration() < 5) {
            Random random = new Random();
            int childrenNumber = random.nextInt(5);
            for (int i = 0; i < childrenNumber; i++) {
                int childType = random.nextInt(2);
                if (childType == 0) {
                    children.add(new LeafAlien(lastId + 1, this.id, this.generation + 1));
                    lastId++;
                } else {
                    var child = new CompositeAlien(lastId + 1, this.id, this.generation + 1);
                    children.add(child);
                    child.generateChildren();
                    lastId++;
                }
            }
        }
    }*/

    public void generateChildren() {
        if (maxGen < 5) {
            Random random = new Random();
            int childrenNumber = random.nextInt(5);
            for (int i = 0; i < childrenNumber; i++) {
                int childType = random.nextInt(2);
                addChild(lastId + 1, this.generation + 1, this.id, childType == 0);
                lastId++;
            }
        }
    }

    private void addChild(int id, int generation, int fatherId, boolean isComposite) {
        if (generation > maxGen ) {
            maxGen = generation;
        }
        var connection = AliensCreator.connection;
        String query = "INSERT INTO aliens_data (ID, generation, father, isComposite) VALUES " + id + ", " +generation +", "+ fatherId+ ", " + isComposite;
        try {
            connection.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGeneration() {
        return generation;
    }
}
