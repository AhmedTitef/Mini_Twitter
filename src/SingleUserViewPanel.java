import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class SingleUserViewPanel extends JPanel {
    private static JFrame frame;
    private GridBagConstraints constraints;

    private JTextField toFollowTextField;

    private JTextArea tweetMessageTextArea;
    private JTextArea currentFollowingTextArea;
    private JTextArea newsFeedTextArea;

    private JScrollPane tweetMessageScrollPane;
    private JScrollPane currentFollowingScrollPane;
    private JScrollPane newsFeedScrollPane;
    private  JTextArea creationTime;
    private  JTextArea lastUpdateTime;

    private JButton followUserButton;
    private JButton postTweetButton;

    private Subject user;
    private Map<String, Observer> allUsers;
    private Map<String, JPanel> openPanels;


    public SingleUserViewPanel(Map<String, Observer> allUsers, Map<String, JPanel> allPanels, DefaultMutableTreeNode user) {
        super();

        this.user = (Subject) user;
        this.allUsers = allUsers;
        this.openPanels = allPanels;
        initializeComponents();
        addComponents();
    }

    /*
     * Private methods
     */


    public static void addComponent(Container container, Component component, int gridx, int gridy,
                                    int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets( 0,0,0,0 ), 0, 0);
        container.add(component, gbc);
    }
    private void addComponents() {

        addComponent(frame, toFollowTextField, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, followUserButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, currentFollowingTextArea, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, tweetMessageScrollPane, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, postTweetButton, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, newsFeedScrollPane, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent( frame , creationTime , 2,0,1,1, GridBagConstraints.CENTER , GridBagConstraints.BOTH );
        addComponent( frame, lastUpdateTime , 2,2,5,5 , GridBagConstraints.CENTER , GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        frame = new JFrame("User View");
        formatFrame();

        constraints = new GridBagConstraints();
        constraints.ipady = 100;

        toFollowTextField = new JTextField("User ID");
        followUserButton = new JButton("Follow User");
        initializeFollowUserButtonActionListener();

        currentFollowingTextArea = new JTextArea("Current Following: ");
        formatTextArea(currentFollowingTextArea);
        currentFollowingScrollPane = new JScrollPane(currentFollowingTextArea);
        currentFollowingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        tweetMessageTextArea = new JTextArea("Tweet Message");
        tweetMessageScrollPane = new JScrollPane(tweetMessageTextArea);
        tweetMessageScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        postTweetButton = new JButton("Post Tweet");
        initializePostTweetButtonActionListener();

        newsFeedTextArea = new JTextArea("Twitter Messages: ");
        formatTextArea(newsFeedTextArea);
        newsFeedScrollPane = new JScrollPane(newsFeedTextArea);
        newsFeedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        creationTime = new JTextArea( "Creation Time: " + ((User) user).getCreationTime() );
        formatTextArea( creationTime );

        lastUpdateTime = new JTextArea( "Last Update Time: " + ((SingleUser) user).getLastUpdateTime() );
        formatTextArea( lastUpdateTime );




        updateCurrentFollowingTextArea();


        updateTwitterMessagesTextArea();
    }

    private void formatTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(8);
        textArea.setEditable(false);
    }

    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(800, 400);
        frame.setVisible(true);
        frame.setTitle(((User) user).getId());


        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                openPanels.remove(((User) user).getId());
            }
        });
    }


    private void updateTwitterMessagesTextArea() {
        String list = "Twitter News: \n";

        for (String news : ((SingleUser) user).getTwitterMessages()) {
            list += " - " + news + "\n"  ;
//            list += " at " + ((SingleUser) user).getLastUpdateTime() +  "\n";
        }




        newsFeedTextArea.setText(list);
        newsFeedTextArea.setCaretPosition(0);


    }


    private void updateCurrentFollowingTextArea() {
        String list = "Current Following: \n";
        for (String following : ((SingleUser) user).getFollowing().keySet()) {
            list += " - " + following + "\n";
        }
        currentFollowingTextArea.setText(list);
        currentFollowingTextArea.setCaretPosition(0);
    }


    private void updateLastUpdateTime(){
        long time = ((SingleUser)user).getLastUpdateTime();
        System.out.println("time: "  + time);
        lastUpdateTime.setText( String.valueOf( time ) );
    }

    private  void updateLatestButton(){
//        LastUpdatePanel lastUpdatePanel = new LastUpdatePanel(  );
    }


    private void initializePostTweetButtonActionListener() {
        postTweetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((SingleUser) user).setLastUpdateTime(  System.currentTimeMillis() );
                ((SingleUser) user).sendMessage(tweetMessageTextArea.getText());
                System.out.println("Latest update from : " +  ((SingleUser) user).getId() ) ;

//                ((SingleUser) user).setLastUpdateTime( System.currentTimeMillis() );
//                lastUpdateTime.setText("Last Update Time: " + ((SingleUser) user).getLastUpdateTime() );




                for (JPanel panel : openPanels.values()) {


                    ((SingleUserViewPanel) panel).updateTwitterMessagesTextArea();
                    ((SingleUserViewPanel) panel).updateLastUpdateTime();
//                    ((SingleUserViewPanel) panel).lastUpdateTime.setText(  "Last Update Time: " + ((SingleUser) user).getLastUpdateTime() );

                }
            }
        });
    }


    private void initializeFollowUserButtonActionListener() {
        followUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                User toFollow = (User) allUsers.get(toFollowTextField.getText());



                if (!allUsers.containsKey(toFollowTextField.getText())) {
                    System.out.println("Fatal Error");

                } else if (toFollow.getClass() == GroupUser.class) {
                    System.out.println("Fatal Error");
                } else if (allUsers.containsKey(toFollowTextField.getText())) {
                    ((Subject) toFollow).attach((Observer) user);
                }

                updateCurrentFollowingTextArea();
            }
        });
    }
}
