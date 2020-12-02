import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class VerificationPanel extends JPanel {


    private JButton showVerification;
    private Map<String, Observer> allUsers;
    private Map<String, Observer> allGroups;
    private Map<String, JPanel> openPanels;
    private JPanel treePanel;


    public VerificationPanel(JPanel treePanel, Map<String, Observer> allUsers) {
        super();
        this.treePanel = treePanel;
        this.allGroups = allGroups;
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
        addComponent( this, showVerification, 0, 0, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH );


    }

    private void initializeComponents() {
        showVerification = new JButton( "Verify" );
        intializeShowVerificationActionListener();
    }

    private boolean checkDuplicates() {

        Set<Observer> set = new HashSet<>( allUsers.values() );

        if (set.size()!= allUsers.size()){
            return false;
        }

//
//
        return true;
    }


    private void intializeShowVerificationActionListener() {
        showVerification.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean unique = checkDuplicates();
                String message;
                if (unique) {
                    message = "Unique" ;
                    System.out.println( "Unique" );
                } else {
                    System.out.println( "Not Unique" );
                    message = " Not Unique / Duplicate Users/Groups found" ;
                }

                JOptionPane.showMessageDialog( null, message, "Unique or no", JOptionPane.INFORMATION_MESSAGE );
            }
        } );
    }
}

