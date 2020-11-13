public class UserTotalVisitor implements Visitor {
    @Override
    public int visitUser(User user) {
        return 0;
    }

    @Override
    public int visitSingleUser(User user) {
        return 1;
    }

    @Override
    public int visitGroupUser(User user) {
        return 0;
    }
}
