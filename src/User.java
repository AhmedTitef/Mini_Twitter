import javax.swing.tree.DefaultMutableTreeNode;


public abstract class User extends DefaultMutableTreeNode implements Observer {
    // composite pattern is used here
    // we use this class to determine for example if the userid given exists or no, or or to get message count of a specific user

    public abstract int getSingleUserCount();

    public abstract int getGroupUserCount();

    public abstract boolean contain(String id);

    private String id;
    private int messageCount;


    public User(String id) {
        super(id);

        this.setMessageCount( 0 );

        this.id = id;
    }


    public int getMessageCount() {

        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }


    public abstract void accept(Visitor visitor);


    public String getId() {
        return id;
    }


}
