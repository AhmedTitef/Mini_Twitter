import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInfomationPanel extends JPanel {

    private JButton userTotalButton;
    private JButton groupTotalButton;
    private JButton messagesTotalButton;
    private JButton positivePercentageButton;

    private JPanel treePanel;

    /**
     * Create the panel.
     */
    public ShowInfomationPanel(JPanel treePanel) {
        super();

        this.treePanel = treePanel;
        initializeComponents();
        addComponents();
    }

    public static void addComponent(Container container, Component component, int gridx, int gridy,
                                    int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets( 0,0,0,0 ), 0, 0);
        container.add(component, gbc);
    }
    private void addComponents() {
        addComponent(this, userTotalButton, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, groupTotalButton, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, messagesTotalButton, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, positivePercentageButton, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        userTotalButton = new JButton("Show User Total");
        initializeUserTotalButtonActionListener();

        groupTotalButton = new JButton("Show Group Total");
        initializeGroupTotalButtonActionListener();

        messagesTotalButton = new JButton("Show Messages Total");
        initializeMessagesTotalButtonActionListener();

        positivePercentageButton = new JButton("Show Positive Percentage");
        initializePositivePercentageButtonActionListener();
    }

    /**
     * Returns the selected User in the TreePanel.
     */
    private DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((TreePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((TreePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }

    /*
     * Action Listeners
     */

    /**
     * Initializes action listener for UserTotalButton.  Opens message
     * dialog box for the specified User.
     *
     * Displays total number of SingleUsers contained within
     * the specified User.
     */
    private void initializeUserTotalButtonActionListener() {
        userTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                UserTotalVisitor visitor = new UserTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of inidividual users within "
                        + ((User) selectedNode).getId() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));


                JOptionPane.showMessageDialog( null, ((User) selectedNode).getId() + " information" , message , JOptionPane.INFORMATION_MESSAGE );
            }
        });
    }

    /**
     * Initializes action listener for GroupTotalButton.  Opens message
     * dialog box for the specified User.
     *
     * Displays total number of GroupUsers contained within the
     * specified User.  If a GroupUser is selected, the total excludes
     * the selected User.
     */
    private void initializeGroupTotalButtonActionListener() {
        groupTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                GroupTotalVisitor visitor = new GroupTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of groups within "
                        + ((User) selectedNode).getId() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));


                JOptionPane.showMessageDialog( null, ((User) selectedNode).getId() + " information" , message , JOptionPane.INFORMATION_MESSAGE );
            }
        });
    }

    /**
     * Initializes action listener for MessagesTotalButton.  Opens
     * message dialog box for the specified User.
     *
     * Displays the total number of messages sent by the specified
     * User.  If a GroupUser is selected, the total represents the total
     * number of messages sent by all SingleUsers that are descendants
     * of the specified User.
     */
    private void initializeMessagesTotalButtonActionListener() {
        messagesTotalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                MessagesTotalVisitor visitor = new MessagesTotalVisitor();
                ((User) selectedNode).accept(visitor);
                String message = "Total number of messages sent by "
                        + ((User) selectedNode).getId() + ": "
                        + Integer.toString(visitor.visitUser(((User) selectedNode)));

                JOptionPane.showMessageDialog( null, ((User) selectedNode).getId() + " information" , message , JOptionPane.INFORMATION_MESSAGE );
            }
        });
    }

    /**
     * Initializes action listener for PositivePercentageButton.  Opens
     * message dialog box for the specified User.
     *
     * Displays the percentage of positive messages sent by the specified
     * User.  If a GroupUser is selected, the total represents the total
     * number of positive messages sent by all SingleUsers that are descendants
     * of the specified User.
     */
    private void initializePositivePercentageButtonActionListener() {
        positivePercentageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // get User selected in TreePanel
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                PositiveTotalVisitor positiveCountVisitor = new PositiveTotalVisitor();
                ((User) selectedNode).accept(positiveCountVisitor);
                int positiveCount = positiveCountVisitor.visitUser(((User) selectedNode));

                MessagesTotalVisitor messageCountVisitor = new MessagesTotalVisitor();
                ((User) selectedNode).accept(messageCountVisitor);
                int messageCount = messageCountVisitor.visitUser(((User) selectedNode));

                // calculate percentage, set percentage to 0.00 if no messages have yet been sent
                double percentage = 0;
                if (messageCount > 0) {
                    percentage = ((double) positiveCount / messageCount) * 100;
                }
                String percentageString = String.format("%.2f", percentage);

                String message = "Percentage of positive messages sent by "
                        + ((User) selectedNode).getId() + ": "
                        + percentageString + "%";

                JOptionPane.showMessageDialog( null, ((User) selectedNode).getId() + " information" , message , JOptionPane.INFORMATION_MESSAGE );
            }
        });
    }
}
