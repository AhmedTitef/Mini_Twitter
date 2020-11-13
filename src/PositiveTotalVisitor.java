public class PositiveTotalVisitor implements Visitor {
    @Override
    public int visitUser(User user) {
        return 0;
    }

    @Override
    public int visitSingleUser(User user) {

        return 0;
    }

    @Override
    public int visitGroupUser(User user) {
        return 0;
    }
}
