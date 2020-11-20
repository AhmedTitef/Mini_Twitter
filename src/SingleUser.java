
import java.util.*;

public class SingleUser extends User implements Subject {
//    public void showUserDetails();
//    public void showFollowers();

    private String name;
    private String userID;
    private Map<String, Observer> followers;
    private  Map <String , Subject> followings;
    private List<Integer> uniqueID;
    private int positiveMessageCount;

    public List<String> getTwitterMessages() {
        return twitterMessages;
    }

    private List<String> twitterMessages; //newsfeed


    public SingleUser(String id){
        super(id);
        followers = new HashMap<>(  );
        followings = new HashMap<>(  );
        twitterMessages = new ArrayList<>(  );

    }

    public boolean checkUserID(Integer id){


        return false;
    }



    public void sendMessage(String message){
//        this.latestMessage = message;
        this.setMessageCount(this.getMessageCount() + 1);
        twitterMessages.add( message );



        notifyObservers();
    }


    private void addFollower(Observer user){

        User userInformation = (User) user;
        this.getFollowers().put( (userInformation.getId()) , user );



    }
    public int getPositiveMessageCount() {
        return positiveMessageCount;
    }

    private void addUserToFollow(Subject personToFollow){


        if (personToFollow.getClass() == SingleUser.class)
            getFollowing().put(((User) personToFollow).getId(), personToFollow);


    }

    public  Map<String , Observer> getFollowers(){
        return followers;
    }

    public Map<String, Subject> getFollowing() {
        return this.followings;
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

        twitterMessages.add( ((SingleUser) subject).getId() + ": " + ((SingleUser) subject).twitterMessages.get( twitterMessages.size()-1 ) );

    }

    @Override
    public void attach(Observer observer) {

        addFollower( observer );
    }

    @Override
    public void notifyObservers() {

        for (Observer oneObserver : followers.values()){
            oneObserver.update( this );
        }
    }
}
