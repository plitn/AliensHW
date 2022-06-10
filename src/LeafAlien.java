public class LeafAlien implements Component {
    int id;
    int fatherId;
    private final int generation;
    public LeafAlien(int id, int fatherId, int generation) {
        this.fatherId = fatherId;
        this.id = id;
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return generation;
    }
}
