package netspy;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * The Class MyJFrame.
 */
public class MyJFrame extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2357381332647405895L;
	
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
	
	/** The Text field size. */
	private Dimension textFieldSize = new Dimension(250,25);
	
	
	/**
	 * Instantiates a new my j frame.
	 */
	public MyJFrame(){
		super();
		initialize();
	}
	
	/**
	 * Initialize.
	 */
	private void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("NetSpy 2.0v");
		this.setBounds(400, 300, 400, 600);
		this.setLayout(new BorderLayout());
		

		this.setTitlePanel();
		this.setExtraSpace();
		this.setFormLayout();
//		this.setStart();
		this.setInfoBox();
				
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Sets the title panel.
	 */
	private void setTitlePanel(){
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout (new FlowLayout(FlowLayout.CENTER));
		titlePanel.add(new JLabel("NetSpy 2.0v"));
		
		this.add(titlePanel, BorderLayout.NORTH);
	}
	
	/**
	 * Sets the form layout.
	 */
	private void setFormLayout(){
		
		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints bgc = new GridBagConstraints();
		Dimension btn_size = new Dimension(30,25);
		Dimension lbl_size = new Dimension(50,25);
		formPanel.setPreferredSize(new Dimension(600,200));
		
		
		
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = 0.1;
		bgc.weighty = 0.1;
		bgc.gridx = 1;
		bgc.gridy = 0;
		JLabel lbl_mail = new JLabel("Mail Verzeichnis:");
		lbl_mail.setSize(lbl_size);
		formPanel.add(lbl_mail, bgc);
		
		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = 0.5;
		bgc.gridx = 2;
		bgc.gridy = 0;
		bgc.gridwidth = 4;
		setInputMailPath(new JTextField());
		getInputMailPath().setName("InputMailPath");
		formPanel.add(getInputMailPath(), bgc);
	
		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = -0.5;
		bgc.gridx = 6;
		bgc.gridy = 0;
		JButton btn_MailPath = new JButton(("..."));
		btn_MailPath.setPreferredSize(btn_size);
		formPanel.add(btn_MailPath, bgc);

		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = 0.1;
		bgc.weighty = 0.1; 
		bgc.gridx = 1;
		bgc.gridy = 1;		
		JLabel lbl_blackword = new JLabel("Blackword:");
		lbl_blackword.setSize(lbl_size);
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
		JButton btn_BlackwordPath = new JButton(("..."));
		btn_BlackwordPath.setName("Blackword Datei");
		btn_BlackwordPath.setPreferredSize(btn_size);
		formPanel.add(btn_BlackwordPath,bgc);

		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = 0.1;
		bgc.weighty = 0.1;
		bgc.gridx = 1;
		bgc.gridy = 2;
		JLabel lbl_logPath = new JLabel("Log Ordner:");
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
		JButton btn_LogPath = new JButton("...");
		btn_LogPath.setName("Log Pfad");
		btn_LogPath.setPreferredSize(btn_size);
		formPanel.add(btn_LogPath, bgc);
		
		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.weightx = 0.1;
		bgc.weighty = 0.1;
		bgc.gridx = 1;
		bgc.gridy = 3;
		JLabel lbl_Quarantine = new JLabel("Quarantäne Ordner:");
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
		JButton btn_QuarantinePath = new JButton(("..."));
		btn_QuarantinePath.setName("Quarantäne Ordner");
		btn_QuarantinePath.setPreferredSize(btn_size);
		formPanel.add(btn_QuarantinePath, bgc);
		
		bgc = new GridBagConstraints();
		bgc.fill = GridBagConstraints.HORIZONTAL;
		bgc.gridx = 2;
		bgc.gridy = 4;
		bgc.weighty = 0.1;
		JButton btn_Start = new JButton("Start");
		btn_Start.setName("Start");
		formPanel.add(btn_Start, bgc);
		
		this.add(formPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Sets the extra space.
	 */
	private void setExtraSpace(){
		JPanel Space1 = new JPanel();
		Space1.setLayout(new GridLayout());
		
		JLabel lbl_Space1 = new JLabel("     ");
		Space1.add(lbl_Space1);

		this.add(Space1, BorderLayout.EAST);
		
		JPanel Space2 = new JPanel();
		Space2.setLayout(new GridLayout());
		
		JLabel lbl_Space2 = new JLabel("     ");
		
		Space2.add(lbl_Space2);
		
		this.add(Space2, BorderLayout.WEST);
	}
	
	/**
	 * Sets the info box.
	 */
	private void setInfoBox(){
		JPanel InfoBox = new JPanel();
		InfoBox.setLayout(new GridLayout(1,7));
		
		setInfoBoxText(new JTextField());
		getInfoBoxText().setName("Infobox");
		
		InfoBox.setPreferredSize(new Dimension(600,200));
		InfoBox.add(getInfoBoxText());
		
		this.add(InfoBox, BorderLayout.SOUTH);
	}
	
	/**
	 * Sets the input mail path.
	 *
	 * @param InputMailPath the new input mail path
	 */
	public void setInputMailPath(JTextField inputMailPath){
		this.inputMailPath = inputMailPath;
	}
	
	/**
	 * Gets the input mail path.
	 *
	 * @return the input mail path
	 */
	public JTextField getInputMailPath(){
		inputMailPath.setPreferredSize(textFieldSize);
		return inputMailPath;
	}
	
	/**
	 * Sets the input blackword path.
	 *
	 * @param InputBlackwordPath the new input blackword path
	 */
	public void setInputBlackwordPath(JTextField inputBlackwordPath){
		this.inputBlackwordPath = inputBlackwordPath;
	}
	
	/**
	 * Gets the input blackword path.
	 *
	 * @return the input blackword path
	 */
	public JTextField getInputBlackwordPath(){
		inputBlackwordPath.setPreferredSize(textFieldSize);
		return inputBlackwordPath;
	}
	
	/**
	 * Sets the input log path.
	 *
	 * @param InputLogPath the new input log path
	 */
	public void setInputLogPath(JTextField inputLogPath){
		this.inputLogPath = inputLogPath;
	}
	
	/**
	 * Gets the input log path.
	 *
	 * @return the input log path
	 */
	public JTextField getInputLogPath() {
		inputLogPath.setPreferredSize(textFieldSize);
		return inputLogPath;
	}
		
	/**
	 * Sets the input quarantine path.
	 *
	 * @param InputQuarantinePath the new input quarantine path
	 */
	public void setInputQuarantinePath(JTextField inputQuarantinePath){
		this.inputQuarantinePath = inputQuarantinePath;
	}
	
	/**
	 * Gets the input quarantine path.
	 *
	 * @return the input quarantine path
	 */
	public JTextField getInputQuarantinePath(){
		inputQuarantinePath.setPreferredSize(textFieldSize);
		return inputQuarantinePath;
	}
	
	/**
	 * Sets the info box text.
	 *
	 * @param OutputInfoBox the new info box text
	 */
	public void setInfoBoxText(JTextField outputInfoBox){
		this.outputInfoBox = outputInfoBox;
	}
	
	/**
	 * Gets the info box text.
	 *
	 * @return the info box text
	 */
	public JTextField getInfoBoxText(){
		return outputInfoBox;
	}
}
