package netspy.components.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Class MyJFrame.
 */
public class MyJFrame extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2357381332647405895L;

    /** The Constant LABEL_QUARANTAENE_PATH. */
    private static final String LABEL_QUARANTAENE_PATH = "Quarantäne-Verzeichnis:";

	/** The Constant LABEL_LOG_PATH. */
	private static final String LABEL_LOG_PATH = "Log-Verzeichnis:";

	/** The Constant LABEL_BLACKWORD_PATH. */
    private static final String LABEL_BLACKWORD_PATH = "Blackword-Verzeichnis:";

	/** The Constant LABEL_MAIL_PATH. */
	private static final String LABEL_MAIL_PATH = "Mail-Verzeichnis:";

    /** The Constant BUTTON_ID_MAIL_PATH. */
    public static final String BUTTON_ID_MAIL_PATH = "button_mail_path";
    
    /** The Constant BUTTON_ID_BLACKWORD_PATH. */
    public static final String BUTTON_ID_BLACKWORD_PATH = "button_blackword_path";
    
    /** The Constant BUTTON_ID_QUARANTINE_PATH. */
    public static final String BUTTON_ID_QUARANTINE_PATH = "button_quarantine_path";
    
    /** The Constant BUTTON_ID_LOG_PATH. */
    public static final String BUTTON_ID_LOG_PATH = "button_log_path";

    /** The Constant DIMENSION_TEXTFIELD_SIZE. */
    private static final Dimension DIMENSION_TEXTFIELD_SIZE = new Dimension(250, 25);

	/** The Constant DIMENSION_LABEL_SIZE. */
	private static final Dimension DIMENSION_LABEL_SIZE = new Dimension(50, 25);

	/** The Constant DIMENSION_BUTTON_SIZE. */
	private static final Dimension DIMENSION_BUTTON_SIZE = new Dimension(30, 25);
	
	/** The Input mail path. */
	private JTextField inputMailPath;
	
	/** The Input blackword path. */
	private JTextField inputBlackwordPath;
	
	/** The Input quarantine path. */
	private JTextField inputQuarantinePath;
	
	/** The Input log path. */
	private JTextField inputLogPath;
	
	/** The Output info box. */
	private JTextField outputInfoBox;
	
    /**
     * Instantiates a new my j frame.
     */
    public MyJFrame() {
        super();
        initialize();
    }

    /**
     * Initialize.
     */
    private void initialize() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("NetSpy 2.0");
        this.setBounds(400, 300, 400, 600);
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
        titlePanel.add(new JLabel("NetSpy 2.0v"));

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
        
        final JLabel labelMailPath = new JLabel(LABEL_MAIL_PATH);
        labelMailPath.setSize(DIMENSION_LABEL_SIZE);
        
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 0;
        
        formPanel.add(labelMailPath, bgc);

        setInputMailPath(new JTextField());
        getInputMailPath().setName("InputMailPath");
        
        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 0;
        bgc.gridwidth = 4;
        
        formPanel.add(getInputMailPath(), bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 0;
        final JButton btn_MailPath = new JButton(("..."));
        btn_MailPath.setName(BUTTON_ID_MAIL_PATH);
        // TODO: Wir brauchen unseren eigenen MyActionListener der auf button clicks reagiert, je nach button in switch
        // case unterschiedlich, am besten die btn names benutzen, public static und so
        btn_MailPath.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                final JFileChooser mailPathChooser = new JFileChooser();
                // TODO: Vielleicht nicht definieren, sonst muss man fehler abfangen,
                // ohne definition greifen ja die default werte
//                mailPathChooser.setCurrentDirectory(new File("C:/"));
                // TODO: Das wäre schon die optionale anforderung! HÄFTIG!
                mailPathChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                final int returnVal = mailPathChooser.showOpenDialog(null);
                final File file = mailPathChooser.getSelectedFile();
                // Verhindert exception wenn keine file/dir ausgewählt wird
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println(file.getPath());
                } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                    System.out.println("Es wurde leider kein File ausgewählt!");
                } else if (returnVal == JFileChooser.ERROR_OPTION) {
                    System.out.println("Error!");
                }
            }
        });
        btn_MailPath.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btn_MailPath, bgc);
        // Beispiel für bilder laden
        // TODO: Lass mal paar lustige bildchen mit rein tun :D
//        final JLabel bild;
//        final Icon icon1;
//        final Icon icon2;
//        icon1 = new ImageIcon(getClass().getResource("img/de.png"));
//        bild = new JLabel(icon1);
//        formPanel.add(bild);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 1;
        final JLabel lbl_blackword = new JLabel(LABEL_BLACKWORD_PATH);
        lbl_blackword.setSize(DIMENSION_LABEL_SIZE);
        formPanel.add(lbl_blackword, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 1;
        bgc.gridwidth = 4;
        setInputBlackwordPath(new JTextField());
        getInputBlackwordPath().setName("InputBlackwordPath");
        formPanel.add(getInputBlackwordPath(), bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 1;
        // TODO: add JFileChooser
        final JButton btn_BlackwordPath = new JButton(("..."));
        btn_BlackwordPath.setName(BUTTON_ID_BLACKWORD_PATH);
        btn_BlackwordPath.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btn_BlackwordPath, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 2;
        final JLabel lbl_logPath = new JLabel(LABEL_LOG_PATH);
        formPanel.add(lbl_logPath, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 2;
        bgc.gridwidth = 4;
        setInputLogPath(new JTextField());
        getInputLogPath().setName("InputLogPath");
        formPanel.add(getInputLogPath(), bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 2;
        // TODO: add JFileChooser
        final JButton btn_LogPath = new JButton("...");
        btn_LogPath.setName(BUTTON_ID_LOG_PATH);
        btn_LogPath.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btn_LogPath, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.1;
        bgc.weighty = 0.1;
        bgc.gridx = 1;
        bgc.gridy = 3;
        final JLabel lbl_Quarantine = new JLabel(LABEL_QUARANTAENE_PATH);
        formPanel.add(lbl_Quarantine, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = 0.5;
        bgc.gridx = 2;
        bgc.gridy = 3;
        bgc.gridwidth = 4;
        setInputQuarantinePath(new JTextField());
        getInputQuarantinePath().setName("InputQuarantinePath");
        formPanel.add(getInputQuarantinePath(), bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.weightx = -0.5;
        bgc.gridx = 6;
        bgc.gridy = 3;
        // TODO: add JFileChooser
        final JButton btn_QuarantinePath = new JButton(("..."));
        btn_QuarantinePath.setName(BUTTON_ID_QUARANTINE_PATH);
        btn_QuarantinePath.setPreferredSize(DIMENSION_BUTTON_SIZE);
        formPanel.add(btn_QuarantinePath, bgc);

        bgc = new GridBagConstraints();
        bgc.fill = GridBagConstraints.HORIZONTAL;
        bgc.gridx = 2;
        bgc.gridy = 4;
        bgc.weighty = 0.1;
        final JButton btn_Start = new JButton("Start");
        btn_Start.setName("Start");
        formPanel.add(btn_Start, bgc);

        this.add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the extra space.
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
    // TODO: Neu implementieren, sodass hier jeglicher Output gepostet werden kann.
    // Zum Beispiel: new Reporter(Reporter.INFO/DEBUG/WARNING/ERROR).report(SOME_STATIC_FINAL_MESSAGE)
    // Wäre auch nicht schlecht einen clear-Button zu haben
    private void setInfoBox() {

        final JPanel InfoBox = new JPanel();
        InfoBox.setLayout(new GridLayout(1, 7));
        setInfoBoxText(new JTextField());
        getInfoBoxText().setName("Infobox");

        InfoBox.setPreferredSize(new Dimension(600, 200));
        InfoBox.add(getInfoBoxText());

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

        inputMailPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        return inputMailPath;
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

        inputBlackwordPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        return inputBlackwordPath;
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

        inputLogPath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        return inputLogPath;
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

        inputQuarantinePath.setPreferredSize(DIMENSION_TEXTFIELD_SIZE);
        return inputQuarantinePath;
    }

    /**
     * Sets the info box text.
     *
     * @param outputInfoBox the new info box text
     */
    public void setInfoBoxText(final JTextField outputInfoBox) {

        this.outputInfoBox = outputInfoBox;
    }

    /**
     * Gets the info box text.
     *
     * @return the info box text
     */
    public JTextField getInfoBoxText() {

        return outputInfoBox;
    }
}
