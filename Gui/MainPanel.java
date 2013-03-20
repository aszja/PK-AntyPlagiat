package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITLE_FRAME = "tytul";
	private static final String TITLE_LEFTPANEL = "RIGHTtytul";
	private static final String TITLE_RIGHTPANEL = "LEFTtytul";
	private static final String MENU_MAIN = "Menu";
	private static final String MENU_ITEM_ADD = "Add new file";
	private static final String MENU_ITEM_START = "Start research";
	private static final String MENU_ITEM_CLOSE = "Close";
	private static final String RESULT_LABEL = "Result:";

	private JPanel leftPanel = null;
	private JPanel rightPanel = null;
	private JPanel resultPanel = null;

	private JTextArea rightTextArea = null;
	private JTextArea leftTextArea = null;
	private JTextField resultValue = null;

	public MainPanel() {
		super();
		this.init();

	}

	private void init() {

		//left panel
		this.leftTextArea = new JTextArea();
		this.leftPanel = new JPanel(new BorderLayout());
		leftPanel.setBorder(BorderFactory.createTitledBorder(TITLE_LEFTPANEL));
		leftPanel.add(this.leftTextArea, BorderLayout.CENTER);
		
		

		//right panel
		this.rightTextArea = new JTextArea();
		this.rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createTitledBorder(TITLE_RIGHTPANEL));
		rightPanel.add(this.rightTextArea, BorderLayout.CENTER);
		
		
		//split panel
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(leftPanel), new JScrollPane(rightPanel));
		splitPanel.setResizeWeight(0.5);
		
		
		// add JmenuBar
		this.setLayout(new BorderLayout());
		this.setJMenuBar(createMenu());

		// add to frame
		this.add(splitPanel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// center the jframe on screen and set size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		this.setSize(width * 3 / 4, height * 3 / 4);

		this.setLocationRelativeTo(null);
		this.setTitle(TITLE_FRAME);
		this.setVisible(true);

	}

	private JMenuBar createMenu() {
		JMenu mainMenu = new JMenu(MENU_MAIN);
		JMenuBar menuBar = new JMenuBar();
		JMenuItem addItem = new JMenuItem(MENU_ITEM_ADD);
		addItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addAction();
			}
		});
		JMenuItem startItem = new JMenuItem(MENU_ITEM_START);
		startItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startAction();
			}
		});
		JMenuItem closeItem = new JMenuItem(MENU_ITEM_CLOSE);
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeFrame();
			}
		});
		mainMenu.add(addItem);
		mainMenu.add(startItem);
		mainMenu.addSeparator();
		mainMenu.add(closeItem);

		menuBar.add(mainMenu);
		return menuBar;
	}

	private void addAction() {
		// TODO Auto-generated method stub
	}

	private void startAction() {
		// TODO Auto-generated method stub
		//when first time 
		if (this.resultPanel == null) {
			addResultPanel();
		}
		refresh();
	}

	private void addResultPanel() {
		FlowLayout flowlayout = new FlowLayout();
		flowlayout.setAlignment(FlowLayout.LEFT);
		flowlayout.setHgap(10);
		flowlayout.setVgap(10);

		this.resultValue = new JTextField();
		this.resultValue.setPreferredSize(new Dimension(100, 20));

		this.resultPanel = new JPanel(flowlayout);
		this.resultPanel.add(new JLabel(RESULT_LABEL));
		this.resultPanel.add(resultValue);
		this.add(resultPanel, BorderLayout.SOUTH);
	}

	private void refresh() {
		this.invalidate();
		this.validate();
		this.repaint();
	}

	private void closeFrame() {
		this.dispose();
	}
}
