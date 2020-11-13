public class GroupTotalVisitor implements Visitor {
    @Override
    public int visitUser(User user) {
        return user.getGroupUserCount();
    }

    @Override
    public int visitSingleUser(User user) {
        return 0;
    }

    @Override
    public int visitGroupUser(User user) {
        return user.getGroupUserCount();
    }
}
