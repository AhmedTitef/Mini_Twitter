import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class LastUpdatePanel extends JPanel {
    private JButton lastUpdateUser;
    private Map<String, Observer> allUsers;
    private Map<String, Observer> allGroups;
    private Map<String, JPanel> openPanels;
    private DefaultMutableTreeNode root;
    private String userID;

    public LastUpdatePanel(DefaultMutableTreeNode root, Map<String, Observer> allUsers) {
        super();

        this.root = root;
        //        this.userID = user

        this.allUsers = allUsers;

        initializeComponents();
        addComponents();
    }

    public static void addComponent(Container container, Component component, int gridx, int gridy,
                                    int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints( gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets( 0, 0, 0, 0 ), 0, 0 );
        container.add( component, gbc );
    }

    private void addComponents() {
        addComponent( this, lastUpdateUser, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH );


    }

    private void initializeComponents() {
        lastUpdateUser = new JButton( "Show Last Updated User" );
        intializeLastUpdateActionListener();
    }


    private String getLastUpdateUser() {

        HashMap<String, Long> arrayList = new HashMap<String, Long>();


        for (Observer observer : allUsers.values()) {
            if (observer.getClass() != GroupUser.class  ) {
                System.out.println( ((SingleUser) observer).getLastUpdateTime() );
                arrayList.put(  observer.toString() , ((SingleUser) observer).getLastUpdateTime());



            }

        }

        if (arrayList.size()!= 0) {
            Long lastUpdateUSer = (Collections.max( arrayList.values() ));
            for (Map.Entry<String, Long> entry : arrayList.entrySet()) {
                if (entry.getValue().equals( lastUpdateUSer )) {
                    System.out.println( "Last Updated User: " + entry.getKey() );
                    return entry.getKey();
                }
            }
        }else {
            System.out.println("Error, no users were added");
        }



        return "Error";

    }


    private void intializeLastUpdateActionListener() {
        lastUpdateUser.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = getLastUpdateUser();

                JOptionPane.showMessageDialog( null, message, "", JOptionPane.INFORMATION_MESSAGE );
            }
        } );
    }
}
