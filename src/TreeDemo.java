import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class TreeDemo extends JPanel implements TreeSelectionListener{
    private JTree tree;
    private JEditorPane html = new JEditorPane();

    public TreeDemo(Node node) {
        super(new GridLayout(1,0));
        tree = new JTree(createNodes(node));

        ImageIcon leafIcon = new ImageIcon(Homework1.class.getResource("middle.gif"));
        if (leafIcon != null) {
            DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
            renderer.setOpenIcon(leafIcon);
            renderer.setClosedIcon(leafIcon);
            tree.setCellRenderer(renderer);
        }
        tree.putClientProperty("JTree.lineStyle", "None");

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        JScrollPane htmlView = new JScrollPane(html);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
    }

    private DefaultMutableTreeNode createNodes(Node node) {
        DefaultMutableTreeNode head = null;
        head = new DefaultMutableTreeNode(node);
        if(node.left != null) {
            head.add(createNodes(node.left));
            head.add(createNodes(node.right));
        }
        return head;
    }

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        Node n_node =(Node) node.getUserObject();
        String tmp = Homework1.inorder(n_node);
        if (n_node.left !=null) {
            if(Homework1.isOperator(n_node.value_node)) {
                html.setText(tmp.substring(1,tmp.length()-1) + "=" + Homework1.calculate(n_node));
            }
        }
        else {
            html.setText(Character.toString(n_node.value_node));
        }
    }

    public static void ShowGUI(Node n) {
        //Create and set up the window.
        JFrame frame = new JFrame("Binary Tree Calculator");//name win
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new TreeDemo(n));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
