import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class admin_control_panel {
    private static  admin_control_panel INSTANCE  ;
    private JTree tree1;
    private JButton openUserViewButton;
    private JButton showUserTotalButton;
    private JButton showGroupTotalButton;
    private JButton addUser;
    private JButton addGroup;
    private JTextArea textArea1;
    private JList list1;
    private JTextArea textArea2;
    private JList list2;
    private JButton showMessagesTotalButton;
    private JButton showPositivePercentageButton;
    private static JFrame frame;
    private JPanel treePanel;
    private JPanel addUserPanel;
    private JPanel openUserViewPanel;
    private JPanel showInfoPanel;

    private DefaultMutableTreeNode root;
    private Map<String, Observer> allUsers;


    public static admin_control_panel getInstance() {
        if (INSTANCE == null) {
            synchronized (Driver.class) {
                if (INSTANCE == null) {
                    INSTANCE = new admin_control_panel();
                }
            }
        }
        return INSTANCE;
    }

    private admin_control_panel() {
        super();

        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, openUserViewPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    public static void addComponent(Container container, Component component, int gridx, int gridy,
                                    int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
                anchor, fill, new Insets( 0,0,0,0 ), 0, 0);
        container.add(component, gbc);
    }
    private void initializeComponents() {
        frame = new JFrame("Mini Twitter App");
        formatFrame();

        allUsers = new HashMap<String, Observer>();
        root = new GroupUser("Root");
        allUsers.put(((User) root).getId(), (Observer) this.root);

        treePanel = new TreePanel(root);
        addUserPanel = new AddUserPanel(treePanel, allUsers);
        openUserViewPanel = new ToOpenUserViewPanel(treePanel, allUsers);
        showInfoPanel = new ShowInfomationPanel(treePanel);


    }

    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

}
