
import java.util.*;

public class SingleUser extends User implements Subject {
//    public void showUserDetails();
//    public void showFollowers();

    private String name;
    private String userID;
    private Map<String, Observer> followers;
    private Map<String, Subject> following;
    private List<Integer> uniqueID;
    private int positiveMessageCount;
    private long lastUpdatedTime;
    private String latestMessage;
    private long lastUpdateTime;


    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long time) {

        this.lastUpdateTime = time;

//        notifyObservers();
    }

    public String getLatestMessage() {
        return this.latestMessage;
    }

    public List<String> getTwitterMessages() {
        return twitterMessages;
    }

    private List<String> twitterMessages;

    //newsfeed


    public SingleUser(String id) {
        super( id );
        followers = new HashMap<>();
        following = new HashMap<>();
        twitterMessages = new ArrayList<>();

    }


    public void sendMessage(String message) {
        this.latestMessage = message;
        this.setMessageCount( this.getMessageCount() + 1 );
        twitterMessages.add( message );


        notifyObservers();
    }


    private void addFollower(Observer user) {

        User userInformation = (User) user;
        this.getFollowers().put( (userInformation.getId()), user );
        ((SingleUser) user).addUserToFollow( this );


    }

    public int getPositiveMessageCount() {
        return positiveMessageCount;
    }

    private void addUserToFollow(Subject personToFollow) {


        if (personToFollow.getClass() == SingleUser.class)
            getFollowing().put( ((User) personToFollow).getId(), personToFollow );


    }

    public Map<String, Observer> getFollowers() {
        return followers;
    }

    public Map<String, Subject> getFollowing() {
        return following;
    }


    @Override
    public boolean contain(String id) {
        return false;
    }

    @Override
    public int getSingleUserCount() {
        return 1;
    }

    @Override
    public int getGroupUserCount() {
        return 0;
    }


    @Override
    public void accept(Visitor visitor) {

        visitor.visitSingleUser( this );
    }


    @Override
    public void update(Subject subject) {






        twitterMessages.add( 0, (((SingleUser) subject).getId() + ": " + ((SingleUser) subject).getLatestMessage() ) + ". Sent at " +  ((SingleUser) subject).getLastUpdateTime()  );

//        setLastUpdateTime( System.currentTimeMillis() );
//        System.out.println("Last Update User is: " + ((SingleUser) subject).getId());
//        setLastUpdateTime( ( ((SingleUser) subject).getLastUpdateTime()) );
//        ((SingleUser) subject).setLastUpdateTime(System.currentTimeMillis() );

    }

    @Override
    public void attach(Observer observer) {

        addFollower( observer );
    }

    @Override
    public void notifyObservers() {

        for (Observer oneObserver : followers.values()) {
            oneObserver.update( this );
        }
    }
}
