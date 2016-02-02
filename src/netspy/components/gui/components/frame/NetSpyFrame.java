/**
 * (c) Copyrights 2016 by Kevin Schorn, Markus Kluckow
 */
package netspy.components.gui.components.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netspy.components.gui.components.frame.components.LogBox;
import netspy.components.gui.components.listeners.NetSpyActionListener;

/**
 * The Class MyJFrame.
 */
public class NetSpyFrame extends JFrame {

	/** The Constant APPLICATION_TITLE. */
	public static final String APPLICATION_TITLE = "NetSpy 2";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2357381332647405895L;
	
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
    private static final String LABEL_BLACKWORD_PATH = "Blackword-Verzeichnis:";

	/** The Constant LABEL_MAIL_PATH. */
	private static final String LABEL_MAIL_PATH = "Mail-Verzeichnis:";
	
	/** The Constant BUTTON_LABEL_HIDE_LOG_BOX. */
	public static final String BUTTON_LABEL_HIDE_LOGBOX = "Log ausblenden";
	
	/** The Constant BUTTON_LABEL_CLEAR_LOGBOX. */
	private static final String BUTTON_LABEL_CLEAR_LOGBOX = "Log leeren";
	
	/** The Constant BUTTON_LABEL_SHOW_LOG_BOX. */
	public static final String BUTTON_LABEL_SHOW_LOGBOX = "Log einblenden";
	
	/** The Constant BUTTON_LABEL_START_SCAN. */
	private static final String BUTTON_LABEL_START_SCAN = "Start scan";
	
	/** The Constant BUTTON_LABEL_SEARCH_FILE. */
	private static final String BUTTON_LABEL_SEARCH_FILE = "Durchsuchen";

    /** The Constant BUTTON_ID_MAIL_PATH. */
    public static final String BUTTON_ID_MAIL_PATH = "button_mail_path";
    
    /** The Constant BUTTON_ID_BLACKWORD_PATH. */
    public static final String BUTTON_ID_BLACKWORD_PATH = "button_blackword_path";
    
    /** The Constant BUTTON_ID_QUARANTINE_PATH. */
    public static final String BUTTON_ID_QUARANTINE_PATH = "button_quarantine_path";
    
    /** The Constant BUTTON_ID_LOG_PATH. */
    public static final String BUTTON_ID_LOG_PATH = "button_log_path";

    /** The Constant BUTTON_ID_START_SCAN. */
    public static final String BUTTON_ID_START_SCAN = "button_start_scan";
    
    /** The Constant BUTTON_ID_TOGGLE_LOG_BOX. */
    public static final String BUTTON_ID_TOGGLE_LOGBOX = "toggle_logbox";
    
    /** The Constant BUTTON_ID_CLEAR_LOGBOX. */
    public static final String BUTTON_ID_CLEAR_LOGBOX = "clear_logbox";
    
    /** The Constant DIMENSION_TEXTFIELD_SIZE. */
    private static final Dimension DIMENSION_TEXTFIELD_SIZE = new Dimension(250, 25);

	/** The Constant DIMENSION_LABEL_SIZE. */
	private static final Dimension DIMENSION_LABEL_SIZE = new Dimension(50, 25);

	/** The Constant DIMENSION_BUTTON_SIZE. */
	private static final Dimension DIMENSION_BUTTON_SIZE = new Dimension(110, 25);

	
	/** The Input mail path. */
	private JTextField inputMailPath;
	
	/** The Input blackword path. */
	private JTextField inputBlackwordPath;
	
	/** The Input quarantine path. */
	private JTextField inputQuarantinePath;
	
	/** The Input log path. */
	private JTextField inputLogPath;
	
	/** The log box. */
	private LogBox logBox;
	
    /**
     * Instantiates a new my j frame.
     */
    public NetSpyFrame() {
        super();
        initialize();
    }

    /**
     * Initialize.
     */
    private void initialize() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(APPLICATION_TITLE);
        this.setBounds(400, 300, 400, 600);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.setTitlePanel();
        this.setInsets();
        this.setFormLayout();
        this.setInfoBox();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Sets the title panel.
     */
    private void setTitlePanel() {

        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel(APPLICATION_TITLE));

        this.add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Sets the form layout.
     */
    // TODO: FormPanel extends JPanel erstellen und jegliche configuration da reinpacken.
    private void setFormLayout() {

        final JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(600, 200));
        GridBagConstraints bgc = new GridBagConstraints();
        
        
//        MAIL PATH
        
//        LABEL
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 0;
        final JLabel labelMailPath = new JLabel(LABEL_MAIL_PATH);
        labelMailPath.setSize(DIMENSION_LABEL_SIZE);
        formPanel.add(labelMailPath, bgc);

        
//        INPUT
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 0;
        bgc.gridwidth = 4;
        this.inputMailPath = new JTextField();
        this.inputMailPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputMailPath.setEditable(false);
        this.inputMailPath.setName(INPUT_ID_MAIL_PATH);
        formPanel.add(this.inputMailPath, bgc);

//        BUTTON FOR CHOOSER
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 0;
        final JButton btnOpenMailPathChooser = new JButton(BUTTON_LABEL_SEARCH_FILE);
        btnOpenMailPathChooser.setName(BUTTON_ID_MAIL_PATH);
//        btnOpenMailPathChooser.setToolTipText("Wähle eine konkrete .eml-Datei oder ein"
//        		+ " Verzeichnis, in dem alle .eml-Dateien durchsucht werden sollen.");
        btnOpenMailPathChooser.addActionListener(new NetSpyActionListener(this));
        btnOpenMailPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btnOpenMailPathChooser, bgc);
        // Beispiel für bilder laden
//        Icon icon1 = new ImageIcon(this.getClass().getResource("../components/resources/img/info.png"));
//        JLabel bild = new JLabel(icon1);
//        bild.setToolTipText("Wähle eine konkrete .eml-Datei oder ein"
//        		+ " Verzeichnis, in dem alle .eml-Dateien durchsucht werden sollen.");
//        formPanel.add(bild);

        
//        BLACKWORD PATH
        
//        LABEL
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 1;
        final JLabel lblBlackword = new JLabel(LABEL_BLACKWORD_PATH);
        lblBlackword.setSize(DIMENSION_LABEL_SIZE);
        formPanel.add(lblBlackword, bgc);

//        INPUT
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 1;
        bgc.gridwidth = 4;
        this.inputBlackwordPath = new JTextField();
        this.inputBlackwordPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputBlackwordPath.setEditable(false);
        this.inputBlackwordPath.setName(INPUT_ID_BLACKWORD_PATH);
        formPanel.add(this.inputBlackwordPath, bgc);

//        BUTTON FOR CHOOSER
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 1;
        final JButton btnOpenBlackwordPathChooser = new JButton((BUTTON_LABEL_SEARCH_FILE));
        btnOpenBlackwordPathChooser.setName(BUTTON_ID_BLACKWORD_PATH);
        btnOpenBlackwordPathChooser.addActionListener(new NetSpyActionListener(this));
        btnOpenBlackwordPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btnOpenBlackwordPathChooser, bgc);

//        LOG PATH
        
//        LABEL
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 2;
        final JLabel lblLogPath = new JLabel(LABEL_LOG_PATH);
        lblLogPath.setSize(DIMENSION_LABEL_SIZE);
        formPanel.add(lblLogPath, bgc);

//        INPUT
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 2;
        bgc.gridwidth = 4;
        this.inputLogPath = new JTextField();
        this.inputLogPath.setEditable(false);
        this.inputLogPath.setName(INPUT_ID_LOG_PATH);
        this.inputLogPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        formPanel.add(getInputLogPath(), bgc);

//        BUTTON FOR CHOOSER
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 2;
        final JButton btnOpenLogPathChooser = new JButton(BUTTON_LABEL_SEARCH_FILE);
        btnOpenLogPathChooser.setName(BUTTON_ID_LOG_PATH);
        btnOpenLogPathChooser.addActionListener(new NetSpyActionListener(this));
        btnOpenLogPathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btnOpenLogPathChooser, bgc);

//        QUARANTINE PATH
        
//        LABEL
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 3;
        final JLabel lblQuarantine = new JLabel(LABEL_QUARANTAENE_PATH);
        lblQuarantine.setSize(DIMENSION_LABEL_SIZE);
        formPanel.add(lblQuarantine, bgc);

//        INPUT
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 3;
        bgc.gridwidth = 4;
        this.inputQuarantinePath = new JTextField();
        this.inputQuarantinePath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        this.inputQuarantinePath.setEditable(false);
        this.inputQuarantinePath.setName(INPUT_ID_QUARANTINE_PATH);
        formPanel.add(this.inputQuarantinePath, bgc);

//        BUTTON FOR CHOOSER
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 3;
        final JButton btnOpenQuarantinePathChooser = new JButton((BUTTON_LABEL_SEARCH_FILE));
        btnOpenQuarantinePathChooser.setName(BUTTON_ID_QUARANTINE_PATH);
        btnOpenQuarantinePathChooser.addActionListener(new NetSpyActionListener(this));
        btnOpenQuarantinePathChooser.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btnOpenQuarantinePathChooser, bgc);

//        BUTTON START SCAN
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.gridx = 2;
        bgc.gridy = 4;
        final JButton btnStartScan = new JButton(BUTTON_LABEL_START_SCAN);
        btnStartScan.setName(BUTTON_ID_START_SCAN);
        btnStartScan.setPreferredSize(DIMENSION_BUTTON_SIZE);
        btnStartScan.addActionListener(new NetSpyActionListener(this));
        formPanel.add(btnStartScan, bgc);
        
//        bgc = new GridBagConstraints();
//        bgc.fill = GridBagConstraints.HORIZONTAL;
//        bgc.gridx = 5;
//        bgc.gridy = 4;
//        final JButton btnToggleLogBox = new JButton(BUTTON_LABEL_HIDE_LOGBOX);
//        btnToggleLogBox.setName(BUTTON_ID_TOGGLE_LOGBOX);
//        btnToggleLogBox.addActionListener(new NetSpyActionListener(this));
//        formPanel.add(btnToggleLogBox, bgc);
        
//        BUTTON CLEAR LOGBOX
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.gridx = 6;
        bgc.gridy = 4;
        bgc.gridwidth = 1;
        final JButton btnClearLogBox = new JButton(BUTTON_LABEL_CLEAR_LOGBOX);
        btnClearLogBox.setName(BUTTON_ID_CLEAR_LOGBOX);
        btnClearLogBox.setPreferredSize(DIMENSION_BUTTON_SIZE);
        btnClearLogBox.addActionListener(new NetSpyActionListener(this));
        formPanel.add(btnClearLogBox, bgc);

        this.add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the insets.
     */
    private void setInsets() {

        final JPanel insetEast = new JPanel();
        final JPanel insetWest = new JPanel();
        this.add(insetWest, BorderLayout.WEST);
        this.add(insetEast, BorderLayout.EAST);
    }

    /**
     * Sets the info box.
     */
    private void setInfoBox() {

        final JPanel InfoBox = new JPanel();
        InfoBox.setLayout(new FlowLayout());
        setLogBox(new LogBox());

        
        InfoBox.add(getLogBox());

        this.add(InfoBox, BorderLayout.SOUTH);
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
		return logBox;
	}

	/**
	 * Sets the log box.
	 *
	 * @param logBox the new log box
	 */
	public void setLogBox(LogBox logBox) {
		this.logBox = logBox;
	}
}