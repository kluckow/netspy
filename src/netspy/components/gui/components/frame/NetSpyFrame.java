/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import netspy.components.config.ConfigPropertiesManager;
import netspy.components.filehandling.manager.FileManager;
import netspy.components.gui.components.frame.components.LogBox;
import netspy.components.gui.components.listeners.BlacklistActionListener;
import netspy.components.gui.components.listeners.NetSpyActionListener;
import netspy.components.gui.components.listeners.NetSpyListSelectionListener;

/**
 * The Class MyJFrame.
 */
public class NetSpyFrame extends JFrame {

	/** The Constant FRAME_INSETS. */
	private static final Insets GBC_INSETS = new Insets(5, 5, 5, 5);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2357381332647405895L;
	
	/** The Constant APPLICATION_TITLE. */
	public static final String APPLICATION_TITLE = "NetSpy 2";
	
	/** The Constant INPUT_ID_LOG_PATH. */
	private static final String INPUT_ID_LOG_PATH = "input_log_path";
	
	/** The Constant INPUT_ID_MAIL_PATH. */
	private static final String INPUT_ID_MAIL_PATH = "input_mail_path";
	
	/** The Constant INPUT_ID_BLACKWORD_PATH. */
	private static final String INPUT_ID_BLACKWORD_PATH = "input_blackword_path";
	
	/** The Constant INPUT_ID_QUARANTINE_PATH. */
	private static final String INPUT_ID_QUARANTINE_PATH = "input_quarantine_path";

    /** The Constant LABEL_QUARANTAENE_PATH. */
    private static final String LABEL_QUARANTAENE_PATH = "Quarantäne-Verzeichnis:";

	/** The Constant LABEL_LOG_PATH. */
	private static final String LABEL_LOG_PATH = "Log-Verzeichnis:";

	/** The Constant LABEL_BLACKWORD_PATH. */
    private static final String LABEL_BLACKWORD_PATH = "Blackword-Datei:";

	/** The Constant LABEL_MAIL_PATH. */
	private static final String LABEL_MAIL_PATH = "Mail-Verzeichnis:";
	
	/** The Constant BUTTON_LABEL_HIDE_LOG_BOX. */
	public static final String BUTTON_LABEL_HIDE_LOGBOX = "Log ausblenden";
	
	/** The Constant BUTTON_LABEL_CLEAR_LOGBOX. */
	private static final String BUTTON_LABEL_CLEAR_LOGBOX = "Log leeren";
	
	/** The Constant BUTTON_LABEL_SHOW_LOG_BOX. */
	public static final String BUTTON_LABEL_SHOW_LOGBOX = "Log einblenden";
	
	/** The Constant BUTTON_LABEL_START_SCAN. */
	private static final String BUTTON_LABEL_START_SCAN = "Starte Scan";

	/** The Constant BUTTON_LABEL_SHOW_LOG. */
	private static final String BUTTON_LABEL_SHOW_LOG = "Zeige Logdatei";
	
	/** The Constant BUTTON_LABEL_SEARCH_FILE. */
	private static final String BUTTON_LABEL_SEARCH_FILE = "Durchsuchen";
	
	/** The Constant BUTTON_LABEL_BLACKWORD_ADD. */
	private static final String BUTTON_LABEL_BLACKWORD_ADD = "Hinzufügen";
	
	/** The Constant BUTTON_LABEL_BLACKWORD_ADD. */
	private static final String BUTTON_LABEL_BLACKWORD_DELETE = "Löschen";

	/** The Constant BUTTON_LABEL_BLACKWORD_DELETE_ALL. */
	private static final String BUTTON_LABEL_BLACKWORD_DELETE_ALL = "Alle löschen";
	
	/** The Constant BUTTON_LABEL_BLACKWORD_ADD. */
	private static final String BUTTON_LABEL_BLACKWORD_EDIT = "Ändern";

    /** The Constant BUTTON_ID_MAIL_PATH. */
    public static final String BUTTON_ID_MAIL_PATH = "button_mail_path";
    
    /** The Constant BUTTON_ID_BLACKWORD_ADD . */
    public static final String BUTTON_ID_BLACKWORD_ADD = "button_blackword_add";
    
    /** The Constant BUTTON_ID_BLACKWORD_DELETE . */
    public static final String BUTTON_ID_BLACKWORD_DELETE = "button_blackword_delete";
    
    /** The Constant BUTTON_ID_BLACKWORD_DELETE_ALL. */
    public static final String BUTTON_ID_BLACKWORD_DELETE_ALL = "button_blackword_delete_all";
    
    /** The Constant BUTTON_ID_BLACKWORD_DELETE . */
    public static final String BUTTON_ID_BLACKWORD_EDIT = "button_blackword_edit";
    
    /** The Constant BUTTON_ID_BLACKWORD_PATH. */
    public static final String BUTTON_ID_BLACKWORD_PATH = "button_blackword_path";
    
    /** The Constant BUTTON_ID_QUARANTINE_PATH. */
    public static final String BUTTON_ID_QUARANTINE_PATH = "button_quarantine_path";
    
    /** The Constant BUTTON_ID_LOG_PATH. */
    public static final String BUTTON_ID_LOG_PATH = "button_log_path";

    /** The Constant BUTTON_ID_START_SCAN. */
    public static final String BUTTON_ID_START_SCAN = "button_start_scan";    
    
    /** The Constant BUTTON_ID_SHOW_LOG. */
    public static final String BUTTON_ID_SHOW_LOG = "button_show_log";
    
    /** The Constant BUTTON_ID_TOGGLE_LOG_BOX. */
    public static final String BUTTON_ID_TOGGLE_LOGBOX = "toggle_logbox";
    
    /** The Constant BUTTON_ID_CLEAR_LOGBOX. */
    public static final String BUTTON_ID_CLEAR_LOGBOX = "clear_logbox";
    
    /** The Constant DIMENSION_TEXTFIELD_SIZE. */
    private static final Dimension DIMENSION_TEXTFIELD_SIZE = new Dimension(250, 25);

	/** The Constant DIMENSION_LABEL_SIZE. */
	private static final Dimension DIMENSION_LABEL_SIZE = new Dimension(50, 25);

	/** The Constant DIMENSION_BUTTON_SIZE. */
	private static final Dimension DIMENSION_BUTTON_SIZE = new Dimension(120, 25);

	/** The dlm action listener. */
	private BlacklistActionListener dlmActionListener;
	
	/** The action listener. */
	private NetSpyActionListener actionListener = new NetSpyActionListener(this);
	
	/** The list selection listener. */
	private NetSpyListSelectionListener listSelectionListener = new NetSpyListSelectionListener(this);
	
	/** The Input mail path. */
	private JTextField inputMailPath;
	
	/** The Input blackword path. */
	private JTextField inputBlackwordPath;
	
	/** The Input quarantine path. */
	private JTextField inputQuarantinePath;
	
	/** The Input log path. */
	private JTextField inputLogPath;
	
	/** The black word list. */
	private JScrollPane blackwordScrollPane;
		
	/** The log box. */
	private LogBox logBox = new LogBox();
	
	/** The main panel. */
	private JPanel mainPanel = new JPanel();
	
	/** The empty row. */
	private JPanel emptyRow = new JPanel();
	
	/** The gbc. */
	private GridBagConstraints gbc = new GridBagConstraints();
	
	/** The prop conf. */
	ConfigPropertiesManager propConf;

	/** The blacklist scroll pane. */
	private JScrollPane blacklistScrollPane;

	/** The btn add black word. */
	private JButton btnAddBlackWord;

	/** The btn edit black word. */
	private JButton btnEditBlackWord;

	/** The btn delete black word. */
	private JButton btnDeleteBlackword;

	/** The btn delete all blackwords. */
	private JButton btnDeleteAllBlackwords;
	
	/** The blackword list. */
	private JList<String> blackwordList;

	/** The dlm black word. */
	private DefaultListModel<String> dlmBlackWord;

	/** The btn show log. */
	private JButton btnShowLog;
	
    /**
     * Instantiates a new my j frame.
     */
    public NetSpyFrame() {
        super();
        initConf();
        initialize();
    }

    /**
     * Inits the conf.
     */
    private void initConf() {
    	
    	propConf = new ConfigPropertiesManager(this.logBox);
    	propConf.init();
	}

	/**
     * Initialize.
     */
    private void initialize() {

    	// general configuration of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(APPLICATION_TITLE);
        this.setResizable(false);
        
        // Application Icon
        ImageIcon appIcon = new ImageIcon(System.getProperty("user.dir") + "/resources/img/system_search.png");        
        this.setIconImage(appIcon.getImage());
        
        // Layout
        this.mainPanel.setLayout(new GridBagLayout());
        gbc.insets = GBC_INSETS;
        
        // Background color
        this.mainPanel.setBackground(Color.WHITE);
        
        // create content
        this.setTitlePanel();
        this.setFormLayout();
        this.setBlackWordBox();
        this.setInfoBox();
        
        this.add(this.mainPanel);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Sets the title panel.
     */
    private void setTitlePanel() {

    	// y = 0, x = 0-16, centered
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 17;
    	gbc.anchor = GridBagConstraints.CENTER;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	
        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(new JLabel(APPLICATION_TITLE));
        
        this.mainPanel.add(titlePanel, gbc);
    	gbc.fill = GridBagConstraints.NONE;

    }

    /**
     * Sets the form layout.
     */
    
	private void setFormLayout() {

//        MAIL PATH
        
//        LABEL
    	// y = 1, x = 0-1, fill none
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        final JLabel labelMailPath = new JLabel(LABEL_MAIL_PATH);
        labelMailPath.setSize(DIMENSION_LABEL_SIZE);
        this.mainPanel.add(labelMailPath, gbc);

        
//        INPUT
        // y = 1, x = 2-5, fill horizontal
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        this.inputMailPath = new JTextField();
        this.inputMailPath.setText(propConf.getInboxPath());
        this.inputMailPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputMailPath.setEditable(false);
        this.inputMailPath.setName(INPUT_ID_MAIL_PATH);
        this.inputMailPath.setToolTipText("Wähle eine konkrete .eml-Datei oder ein\n"
        		+ " Verzeichnis, in dem alle .eml-Dateien durchsucht werden sollen.");
        this.mainPanel.add(this.inputMailPath, gbc);

//        BUTTON FOR CHOOSER
        // y = 1, x = 6-7, fill horizontal
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        final JButton btnOpenMailPathChooser = new JButton(BUTTON_LABEL_SEARCH_FILE);
        btnOpenMailPathChooser.setName(BUTTON_ID_MAIL_PATH);
        btnOpenMailPathChooser.addActionListener(actionListener);
        btnOpenMailPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(btnOpenMailPathChooser, gbc);

//        BLACKWORD PATH
        
//        LABEL
        // y = 2, x = 0-1, fill none
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        final JLabel lblBlackword = new JLabel(LABEL_BLACKWORD_PATH);
        lblBlackword.setSize(DIMENSION_LABEL_SIZE);
        this.mainPanel.add(lblBlackword, gbc);

//        INPUT
        // y = 2, x = 2-5, fill horizontal
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        this.inputBlackwordPath = new JTextField();
        this.inputBlackwordPath.setText(propConf.getBlackwordPath());
        this.inputBlackwordPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputBlackwordPath.setEditable(false);
        this.inputBlackwordPath.setName(INPUT_ID_BLACKWORD_PATH);
        this.inputBlackwordPath.setToolTipText("Wähle die blacklist.txt-Datei aus, anhand "
        		+ "welcher die Emails überprüft werden sollen.");
        this.mainPanel.add(this.inputBlackwordPath, gbc);

//        BUTTON FOR CHOOSER
        // y = 2, x = 6-7, fill horizontal
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        final JButton btnOpenBlackwordPathChooser = new JButton((BUTTON_LABEL_SEARCH_FILE));
        btnOpenBlackwordPathChooser.setName(BUTTON_ID_BLACKWORD_PATH);
        btnOpenBlackwordPathChooser.addActionListener(actionListener);
        btnOpenBlackwordPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(btnOpenBlackwordPathChooser, gbc);

//        LOG PATH
        
//        LABEL
        // y = 3, x = 0-1, fill none
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        final JLabel lblLogPath = new JLabel(LABEL_LOG_PATH);
        lblLogPath.setSize(DIMENSION_LABEL_SIZE);
        this.mainPanel.add(lblLogPath, gbc);

//        INPUT
        // y = 3, x = 2-5, fill horizontal
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        this.inputLogPath = new JTextField();
        this.inputLogPath.setText(propConf.getLogPath());
        this.inputLogPath.setEditable(false);
        this.inputLogPath.setName(INPUT_ID_LOG_PATH);
        this.inputLogPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputLogPath.setToolTipText("Wähle das Log-Verzeichnis aus. "
        		+ "Dort werden die Informationen über verdächtige Emails gespeichert.");
        this.mainPanel.add(this.inputLogPath, gbc);

//        BUTTON FOR CHOOSER
        // y = 3, x = 6-7, fill horizontal
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        final JButton btnOpenLogPathChooser = new JButton(BUTTON_LABEL_SEARCH_FILE);
        btnOpenLogPathChooser.setName(BUTTON_ID_LOG_PATH);
        btnOpenLogPathChooser.addActionListener(actionListener);
        btnOpenLogPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(btnOpenLogPathChooser, gbc);

//        QUARANTINE PATH
        
//        LABEL
    	// y = 4, x = 0-1, fill none
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.BASELINE;
        final JLabel lblQuarantine = new JLabel(LABEL_QUARANTAENE_PATH);
        lblQuarantine.setSize(DIMENSION_LABEL_SIZE);
        this.mainPanel.add(lblQuarantine, gbc);

//        INPUT
        // y = 4, x = 2-5, fill horizontal
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        this.inputQuarantinePath = new JTextField();
        this.inputQuarantinePath.setText(propConf.getQuarantinePath());
        this.inputQuarantinePath.setEditable(false);
        this.inputQuarantinePath.setName(INPUT_ID_QUARANTINE_PATH);
        this.inputQuarantinePath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputQuarantinePath.setToolTipText("Wähle das Quarantäne-Verzeichnis aus, "
        		+ "in welches die verdächtigen Emails gespeichert werden.");
        this.mainPanel.add(this.inputQuarantinePath, gbc);

//        BUTTON FOR CHOOSER
        // y = 4, x = 6-7, fill horizontal
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        final JButton btnOpenQuarantinePathChooser = new JButton((BUTTON_LABEL_SEARCH_FILE));
        btnOpenQuarantinePathChooser.setName(BUTTON_ID_QUARANTINE_PATH);
        btnOpenQuarantinePathChooser.addActionListener(actionListener);
        btnOpenQuarantinePathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(btnOpenQuarantinePathChooser, gbc);
        
        // EMPTY ROW
        // let row with index 5 empty: workaround
        // y = 5, x = 0-9, fill none
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.emptyRow = new JPanel();
        emptyRow.setSize(new Dimension(0, 10));
        emptyRow.setBackground(Color.WHITE);
        this.mainPanel.add(emptyRow, gbc);
        
//        BUTTON CLEAR LOGBOX
        // y = 6, x = 0-1, fill horizontal
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        final JButton btnClearLogBox = new JButton(BUTTON_LABEL_CLEAR_LOGBOX);
        btnClearLogBox.setName(BUTTON_ID_CLEAR_LOGBOX);
        btnClearLogBox.setPreferredSize(DIMENSION_BUTTON_SIZE);
        btnClearLogBox.addActionListener(actionListener);
        this.mainPanel.add(btnClearLogBox, gbc);
        
        // y = 6, x = 2-3, free space
        
//        BUTTON START SCAN
        // y = 6, x = 6-7, fill horizontal
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        final JButton btnStartScan = new JButton(BUTTON_LABEL_START_SCAN);
        btnStartScan.setName(BUTTON_ID_START_SCAN);
        btnStartScan.addActionListener(actionListener);
        btnStartScan.setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(btnStartScan, gbc);

        
//        BUTTON SHOW LOG
        // y = 6, x = 8-9, fill none
        gbc.gridx = 8;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        btnShowLog = new JButton(BUTTON_LABEL_SHOW_LOG);
        btnShowLog.setName(BUTTON_ID_SHOW_LOG);
        btnShowLog.setPreferredSize(DIMENSION_BUTTON_SIZE);
        btnShowLog.addActionListener(actionListener);
        btnShowLog.setToolTipText("Sofern eine Logdatei von dem aktuellen Tag existiert,"
        		+ " wird diese mit Ihrem Standard-Text-Editor geöffnet.");
        this.mainPanel.add(btnShowLog, gbc);
        
    }
    
	
    /**
     * Sets the info box.
     */
    private void setInfoBox() {

    	// y = 7-12, x = 0-11, fill both
    	gbc.gridx = 0;
    	gbc.gridy = 7;
    	gbc.gridwidth = 12;
    	gbc.gridheight = 6;
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.anchor = GridBagConstraints.CENTER;
        final JScrollPane infoBoxScrollable = new JScrollPane(this.logBox);
        infoBoxScrollable.setPreferredSize(new Dimension(0, 150));
        infoBoxScrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoBoxScrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        this.mainPanel.add(infoBoxScrollable, gbc);
    }
    
	/**
	 * Sets the black word box.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setBlackWordBox(){
		
		// DEFAULT LIST MODEL
		
		setDlmBlackWord(new DefaultListModel<String>());
		setBlackwordList(new JList(getDlmBlackWord()));
		getBlackwordList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadBlackwordBox(getDlmBlackWord());
		
		// TODO: add customized data listener for removed/added/changed events
		// and print to logbox
//		dlmBlackWord.addListDataListener(new ListDataListener() {
//			@Override
//			public void intervalRemoved(ListDataEvent e) {
//			}
//			@Override
//			public void intervalAdded(ListDataEvent e) {
//			}
//			@Override
//			public void contentsChanged(ListDataEvent e) {
//			}
//		});
		
		// y = 1-4, x = 10-11, fill both
		gbc.gridx = 10;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		getBlackwordList().setBackground(Color.WHITE);
		blacklistScrollPane = new JScrollPane(this.getBlackwordScrollPane());
		blacklistScrollPane.setPreferredSize(new Dimension(150, 190));
		blacklistScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		blacklistScrollPane.setViewportView(getBlackwordList());
		
		// initialize action listener for default list model
		dlmActionListener = new BlacklistActionListener(getBlackwordList(), getDlmBlackWord());
		
		mainPanel.add(blacklistScrollPane, gbc);

//		BUTTON ADD BLACKWORD
		// y = 2, x = 8-9, fill horizontal
        gbc.gridx = 8;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.BASELINE;
        
        setBtnAddBlackWord(new JButton(BUTTON_LABEL_BLACKWORD_ADD));
        getBtnAddBlackWord().setName(BUTTON_ID_BLACKWORD_ADD);
        getBtnAddBlackWord().addActionListener(dlmActionListener);
        getBtnAddBlackWord().setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(getBtnAddBlackWord(), gbc);
        
//        BUTTON EDIT BLACKWORD
        // y = 2, x = 8-9, fill horizontal
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        
        setBtnEditBlackWord(new JButton(BUTTON_LABEL_BLACKWORD_EDIT));
        getBtnEditBlackWord().setName(BUTTON_ID_BLACKWORD_EDIT);
        getBtnEditBlackWord().addActionListener(dlmActionListener);
        getBtnEditBlackWord().setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(getBtnEditBlackWord(), gbc);
        
//        BUTTON DELETE BLACKWORD
        // y = 3, x = 8-9, fill horizontal
        gbc.gridx = 8;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        
        setBtnDeleteBlackWord(new JButton(BUTTON_LABEL_BLACKWORD_DELETE));
        getBtnDeleteBlackWord().setName(BUTTON_ID_BLACKWORD_DELETE);
        getBtnDeleteBlackWord().addActionListener(dlmActionListener);
        getBtnDeleteBlackWord().setPreferredSize(DIMENSION_BUTTON_SIZE);
        this.mainPanel.add(getBtnDeleteBlackWord(), gbc);
        
		
//        BUTTON DELETE ALL BLACKWORDS
		// y = 4, x = 8-9, fill horizontal
		gbc.gridx = 8;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		
		setBtnDeleteAllBlackwords(new JButton(BUTTON_LABEL_BLACKWORD_DELETE_ALL));
		getBtnDeleteAllBlackwords().setName(BUTTON_ID_BLACKWORD_DELETE_ALL);
		getBtnDeleteAllBlackwords().addActionListener(dlmActionListener);
		getBtnDeleteAllBlackwords().setPreferredSize(DIMENSION_BUTTON_SIZE);
		this.mainPanel.add(getBtnDeleteAllBlackwords(), gbc);
		
		// add selection listener to JList and select first entry
		getBlackwordList().addListSelectionListener(listSelectionListener);
		if (getDlmBlackWord().size() > 0) {
			getBlackwordList().setSelectedIndex(0);
		} else {
			this.btnEditBlackWord.setEnabled(false);
			this.btnDeleteBlackword.setEnabled(false);
			this.btnDeleteAllBlackwords.setEnabled(false);
		}
        
    }
	
	
    /**
     * Fill black word box.
     *
     * @param dlm the dlm_ black word
     * @return the array list
     */
	public void loadBlackwordBox(DefaultListModel<String> dlm) {
		
		List<String> blacklist = new FileManager().getBlacklist();
		if (blacklist != null) {
			
			Collections.sort(blacklist, String.CASE_INSENSITIVE_ORDER);
			
			for(String blackword: blacklist){
				dlm.addElement(blackword.toLowerCase());
			}
		}
	}
 
	/**
     * Sets the input mail path.
     *
     * @param inputMailPath the new input mail path
     */
    public void setInputMailPath(final JTextField inputMailPath) {

        this.inputMailPath = inputMailPath;
    }

    /**
     * Gets the input mail path.
     *
     * @return the input mail path
     */
    public JTextField getInputMailPath() {

        return this.inputMailPath;
    }

    /**
     * Sets the input blackword path.
     *
     * @param inputBlackwordPath the new input blackword path
     */
    public void setInputBlackwordPath(final JTextField inputBlackwordPath) {

        this.inputBlackwordPath = inputBlackwordPath;
    }

    /**
     * Gets the input blackword path.
     *
     * @return the input blackword path
     */
    public JTextField getInputBlackwordPath() {

        return this.inputBlackwordPath;
    }

    /**
     * Sets the input log path.
     *
     * @param inputLogPath the new input log path
     */
    public void setInputLogPath(final JTextField inputLogPath) {

        this.inputLogPath = inputLogPath;
    }

    /**
     * Gets the input log path.
     *
     * @return the input log path
     */
    public JTextField getInputLogPath() {

        return this.inputLogPath;
    }

    /**
     * Sets the input quarantine path.
     *
     * @param inputQuarantinePath the new input quarantine path
     */
    public void setInputQuarantinePath(final JTextField inputQuarantinePath) {

        this.inputQuarantinePath = inputQuarantinePath;
    }

    /**
     * Gets the input quarantine path.
     *
     * @return the input quarantine path
     */
    public JTextField getInputQuarantinePath() {

        return this.inputQuarantinePath;
    }

	/**
	 * Gets the log box.
	 *
	 * @return the log box
	 */
	public LogBox getLogBox() {
		return this.logBox;
	}

	/**
	 * Sets the log box.
	 *
	 * @param logBox the new log box
	 */
	public void setLogBox(LogBox logBox) {
		this.logBox = logBox;
	}

	/**
	 * Gets the main panel.
	 *
	 * @return the main panel
	 */
	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	/**
	 * Sets the main panel.
	 *
	 * @param mainPanel the new main panel
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * Gets the gbc.
	 *
	 * @return the gbc
	 */
	public GridBagConstraints getGbc() {
		return this.gbc;
	}

	/**
	 * Sets the gbc.
	 *
	 * @param gbc the new gbc
	 */
	public void setGbc(GridBagConstraints gbc) {
		this.gbc = gbc;
	}

	/**
	 * Gets the black word list.
	 *
	 * @return the black word list
	 */
	public JScrollPane getBlackwordScrollPane() {
		return blackwordScrollPane;
	}
	
	/**
	 * Sets the blackword scroll pane.
	 *
	 * @param blackwordScrollPane the new blackword scroll pane
	 */
	public void setBlackwordScrollPane(JScrollPane blackwordScrollPane) {
		this.blackwordScrollPane = blackwordScrollPane;
	}

	/**
	 * Gets the btn delete black word.
	 *
	 * @return the btn delete black word
	 */
	public JButton getBtnDeleteBlackWord() {
		return btnDeleteBlackword;
	}

	/**
	 * Sets the btn delete black word.
	 *
	 * @param btnDeleteBlackWord the new btn delete black word
	 */
	public void setBtnDeleteBlackWord(JButton btnDeleteBlackWord) {
		this.btnDeleteBlackword = btnDeleteBlackWord;
	}

	/**
	 * Gets the btn edit black word.
	 *
	 * @return the btn edit black word
	 */
	public JButton getBtnEditBlackWord() {
		return btnEditBlackWord;
	}

	/**
	 * Sets the btn edit black word.
	 *
	 * @param btnEditBlackWord the new btn edit black word
	 */
	public void setBtnEditBlackWord(JButton btnEditBlackWord) {
		this.btnEditBlackWord = btnEditBlackWord;
	}

	/**
	 * Gets the btn add black word.
	 *
	 * @return the btn add black word
	 */
	public JButton getBtnAddBlackWord() {
		return btnAddBlackWord;
	}

	/**
	 * Sets the btn add black word.
	 *
	 * @param btnAddBlackWord the new btn add black word
	 */
	public void setBtnAddBlackWord(JButton btnAddBlackWord) {
		this.btnAddBlackWord = btnAddBlackWord;
	}

	/**
	 * Gets the blackword list.
	 *
	 * @return the blackword list
	 */
	public JList<String> getBlackwordList() {
		return blackwordList;
	}

	/**
	 * Sets the blackword list.
	 *
	 * @param blackwordList the new blackword list
	 */
	public void setBlackwordList(JList<String> blackwordList) {
		this.blackwordList = blackwordList;
	}

	/**
	 * Gets the btn delete all blackwords.
	 *
	 * @return the btn delete all blackwords
	 */
	public JButton getBtnDeleteAllBlackwords() {
		return btnDeleteAllBlackwords;
	}

	/**
	 * Sets the btn delete all blackwords.
	 *
	 * @param btnDeleteAllBlackwords the new btn delete all blackwords
	 */
	public void setBtnDeleteAllBlackwords(JButton btnDeleteAllBlackwords) {
		this.btnDeleteAllBlackwords = btnDeleteAllBlackwords;
	}

	/**
	 * Gets the dlm black word.
	 *
	 * @return the dlm black word
	 */
	public DefaultListModel<String> getDlmBlackWord() {
		return dlmBlackWord;
	}

	/**
	 * Sets the dlm black word.
	 *
	 * @param dlmBlackWord the new dlm black word
	 */
	public void setDlmBlackWord(DefaultListModel<String> dlmBlackWord) {
		this.dlmBlackWord = dlmBlackWord;
	}
}
