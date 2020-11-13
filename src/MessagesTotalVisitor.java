public class MessagesTotalVisitor implements Visitor {
    @Override
    public int visitUser(User user) {
        return 0;
    }

    @Override
    public int visitSingleUser(User user) {
        return user.getMessageCount();
    }

    @Override
    public int visitGroupUser(User user) {
        return 0;
    }
}
