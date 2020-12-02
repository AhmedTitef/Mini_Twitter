import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class GroupUser extends User {
    private String userGroupID;
    private List<SingleUser> singleUSerList;
    private Map<String, User> groupUsers;
    private List <Integer> ids;
     private long creationTime;




    @Override
    public boolean contain(String id) {
        boolean contains = false;
        for (User user : groupUsers.values()) {
            if (user.contain(id)) {
                contains = true;
            }
        }
        return contains;
    }

    @Override
    public int getSingleUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            count += user.getSingleUserCount();
        }
        return count;
    }

    @Override
    public int getGroupUserCount() {
        int count = 0;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                ++count;
                count += user.getGroupUserCount();
            }
        }
        return count;
    }



    GroupUser(String uniqueID) {
        super(uniqueID);
        this.creationTime = System.currentTimeMillis();
        groupUsers = new HashMap<String, User>();
    }

    @Override
    public void accept(Visitor visitor) {
        for (User user : groupUsers.values()) {
            user.accept(visitor);
        }
        visitor.visitGroupUser(this);
    }

    public Map<String, User> getGroupUsers() {
        return groupUsers;
    }


    public int getCount(){
        int count = 0;
        return count;
    }

    public boolean idCheck(String id){

        return false;

    }




    public User addUserInGroup(User user) {
        if (!this.contain(user.getId())) {
            this.groupUsers.put(user.getId(), user);
        }
        return this;
    }




    private boolean containsMoreDescendantGroupUser(){
        boolean containsMoreGroups = false;
        for (User user : this.groupUsers.values()) {
            if (user.getClass() == GroupUser.class) {
                containsMoreGroups = true;
            }
        }
        return containsMoreGroups;
    }



    @Override
    public void update(Subject subject) {
        for (User user : groupUsers.values()) {
            ((Observer) user).update(subject);
        }
    }

    @Override
    public int getMessageCount() {
        int msgCount = 0;
        for (User user : this.groupUsers.values()) {
            msgCount += user.getMessageCount();
        }
        return msgCount;
    }
}