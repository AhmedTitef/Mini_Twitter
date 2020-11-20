import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ToOpenUserViewPanel extends JPanel {
    private JButton openUserViewButton;
    private JPanel spacerPanel;

    private JPanel treePanel;
    private Map<String, Observer> allUsers;
    private Map<String, JPanel> openPanels;


    public ToOpenUserViewPanel(JPanel treePanel, Map<String, Observer> allUsers) {
        super();

        this.treePanel = treePanel;
        this.allUsers = allUsers;
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
        addComponent(this, openUserViewButton, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(this, spacerPanel, 1, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }



    private void initializeComponents() {
        openPanels = new HashMap<String, JPanel>();

        openUserViewButton = new JButton("Open User View");
        initializeOpenUserViewActionListener();

        // Empty spacer
        spacerPanel = new JPanel();
    }


    private DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((TreePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((TreePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        System.out.println(selectedNode);
        return selectedNode;
    }



    private void initializeOpenUserViewActionListener() {
        openUserViewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode selectedNode = getSelectedNode();


                if (!allUsers.containsKey(((User) selectedNode).getId())) {
                    System.out.println("Error, user doesnt exist");
                } else if (selectedNode.getClass() == GroupUser.class) {
                    System.out.println("Fatal Error");
                } else if (openPanels.containsKey(((User) selectedNode).getId())) {
                    System.out.println("Error this id has been used before");
                } else if (selectedNode.getClass() == SingleUser.class) {
                    SingleUserViewPanel userView = new SingleUserViewPanel(allUsers, openPanels, selectedNode);
                    openPanels.put(((User) selectedNode).getId(), userView);
                }
            }
        });
    }

}
