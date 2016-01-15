import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth einai h klash pou dhmiourgei ena Menu Bar kai Buttons panw sto frame ths klashs
 * TMachine, kai ka8ista dunath thn epikoinwnia tou xrhsth me thn Mhxanh Turing,
 * mesw tou Menu Bar kai twn Buttons. Kaleitai mono apo thn klash TMachine.
 */
public class Actions extends Controller implements ActionListener, ItemListener, WindowListener
	{
	private MenuBar menu;
	private CheckboxMenuItem	slow;
	private CheckboxMenuItem	fast;
	private CheckboxMenuItem	vfast;
	private CheckboxMenuItem	comp;
	private JFrame Jtmp;
	private JFrame shot2;
	private JButton b;
	private JPanel EPanel;
	private JTextArea strings;
	private TextArea AreaOpen;
	private TextArea AreaSaveAs;
	private TextArea Container;
	private TextArea AddRem;
	private TextArea Initial; 
	private TextArea Final; 

	private int FlagModeN=1;
	private int FlagSpeedN=2;
	private String StringOpen="";
	private String StringOpenFix="";
	private String StringSave="";
	private String StringSaveFix="";
	private String MachStr="";
	private String MachStrFix="";
	private String MachPos="";
	private String MachPosFix="";

	static CheckboxMenuItem basic;
	static CheckboxMenuItem general;

/**
 * Kataskeuazei sugekrimenou tupou Menu Bar kai Buttons gia to interface mias Mhxanhs Turing.
 */
	public Actions(JFrame shot)
		{
// Menu Bar....
		menu=new MenuBar();
		Menu file=new Menu("File");
		Menu oper=new Menu("Operations");
		Menu settngs=new Menu("Settings");
		Menu about=new Menu("About");
		MenuItem new_=new MenuItem("New");

		new_.setActionCommand("New");
		new_.addActionListener(this);
		file.add(new_);
		MenuItem open=new MenuItem("Open");
		open.setActionCommand("Open");
		open.addActionListener(this);
		file.add(open);
		MenuItem save=new MenuItem("Save");
		save.setActionCommand("Save");
		save.addActionListener(this);
		file.add(save);
		MenuItem savetape=new MenuItem("Save Tape");
		savetape.setActionCommand("SaveTape");
		savetape.addActionListener(this);
		file.add(savetape);
		MenuItem saveAs=new MenuItem("Save As...");
		saveAs.setActionCommand("SaveAs");
		saveAs.addActionListener(this);
		file.add(saveAs);
		MenuItem exit=new MenuItem("Exit");
		exit.setActionCommand("Exit");
		exit.addActionListener(this);
		file.add(exit);

		MenuItem step=new MenuItem("Step");
		step.setActionCommand("Step");
		step.addActionListener(this);
		oper.add(step);
		MenuItem start=new MenuItem("Start");
		start.setActionCommand("Start");
		start.addActionListener(this);
		oper.add(start);
		MenuItem stop=new MenuItem("Stop");
		stop.setActionCommand("Stop");
		stop.addActionListener(this);
		oper.add(stop);
		MenuItem reset=new MenuItem("Reset");
		reset.setActionCommand("Reset");
		reset.addActionListener(this);
		oper.add(reset);

		MenuItem alphabet=new MenuItem("Alphabet");
		alphabet.setActionCommand("ChangeAlphabet");
		alphabet.addActionListener(this);
		settngs.add(alphabet);

		MenuItem states=new MenuItem("States");
		states.setActionCommand("ChangeStates");
		states.addActionListener(this);
		settngs.add(states);

// For 'TM Mode' sub-menu...
		Menu tmmode = new Menu("TM Mode");

		basic = new CheckboxMenuItem("Basic");
		basic.addItemListener(this);
		tmmode.add(basic);

		general = new CheckboxMenuItem("General");
		general.addItemListener(this);
		tmmode.add(general);

		basic.setState(true);
		general.setState(false);

		settngs.add(tmmode);
//--------------------------

// For 'Speed' sub-menu...
		Menu speed = new Menu("Speed");

		slow = new CheckboxMenuItem("Slow");
		slow.addItemListener(this);
		speed.add(slow);

		fast = new CheckboxMenuItem("Medium");
		fast.addItemListener(this);
		speed.add(fast);

		vfast = new CheckboxMenuItem("Fast");
		vfast.addItemListener(this);
		speed.add(vfast);

		comp = new CheckboxMenuItem("Very Fast");
		comp.addItemListener(this);
		speed.add(comp);

		slow.setState(true);
		fast.setState(false);
		vfast.setState(false);
		comp.setState(false);

		settngs.add(speed);
//------------------------

		MenuItem creator=new MenuItem("Creators");
		creator.setActionCommand("Creators");
		creator.addActionListener(this);
		about.add(creator);

		menu.add(file);
		menu.add(oper);
		menu.add(settngs);
		menu.add(about);

		shot.setMenuBar(menu);
		Jtmp = shot;

// Buttons....
		EPanel=new JPanel(new GridLayout(0,1));
		EPanel.setBackground(Color.black);

		b=new JButton("Start");
		b.setActionCommand("Start");
		b.setBackground(Color.GREEN);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		EPanel.add(b);

		b=new JButton("Stop");
		b.setActionCommand("Stop");
		b.setBackground(Color.YELLOW);
		b.addActionListener(this);
		EPanel.add(b);

		b=new JButton("Reset");
		b.setActionCommand("Reset");
		b.setBackground(Color.RED);
		b.addActionListener(this);
		EPanel.add(b);
		EPanel.setBounds(725,0,70,90);
		shot.getContentPane().add(EPanel, BorderLayout.EAST);

		b=new JButton("Step");
		b.setActionCommand("Step");
		b.setBounds(365,80,70,30);
		b.setBackground(Color.GREEN);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		shot.getContentPane().add(b);
		
		b=new JButton("Load");
		b.setActionCommand("Load");
		b.setBounds(560,170,70,25);
		b.setBackground(Color.ORANGE);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		shot.getContentPane().add(b);

		b=new JButton("Clear Message Box");
		b.setActionCommand("Clear");
		b.setBounds(450,510,150,30);
		b.setBackground(Color.RED);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		shot.getContentPane().add(b);

		b=new JButton("<");
		b.setActionCommand("GoLeft");
		b.setBounds(190-20,35,22+20,25);
		b.setBackground(Color.GREEN);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		shot.getContentPane().add(b);

		b=new JButton(">");
		b.setActionCommand("GoRight");
		b.setBounds(190+400,35,22+20,25);
		b.setBackground(Color.GREEN);
		b.setForeground(Color.BLACK);
		b.addActionListener(this);
		shot.getContentPane().add(b);
		}

	public Tape TakeCurrTape()
		{
		return tape;
		}

/**
 * Xeirizetai ta events ths klashs kalontas me8odous apo thn klash Controller.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_actionPerformed_ActionEvent
 * <BR>
 * <B>Parameter:</B> ev To event pou exoume
 */
	public void actionPerformed(ActionEvent ev)
		{
		String label;
		label = ev.getActionCommand();
		int fileLoaded, fileSaved;
		int i, flag, FileTMmode=0;
		int j, pow10, temp, MachPosINT;

// Doing all events...
		if (label=="New")
			{
			Jtmp.setEnabled(false);

			shot2= new JFrame("New Machine");
			JPanel WPanel=new JPanel(new GridLayout(1,2));
			JPanel WPanel2=new JPanel(new GridLayout(1,2));
			JPanel WPanel3=new JPanel(new GridLayout(1,2));
			JPanel WPanel4=new JPanel(new GridLayout(1,2));		


			shot2.getContentPane().setLayout(new GridLayout(5, 2));
			GridBagConstraints forbutt = new GridBagConstraints();

			strings = new JTextArea("Select Machine Type:");
			strings.setFont(new Font("Helvetica", Font.BOLD,14));
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			shot2.getContentPane().add(strings);

			strings = new JTextArea("Select Machine Speed:");
			strings.setFont(new Font("Helvetica", Font.BOLD,14));
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			shot2.getContentPane().add(strings);

			CheckboxGroup checkp;
			checkp = new CheckboxGroup();
			
			Checkbox chbox1 = new Checkbox("Basic Turing Machine", checkp, true);
			chbox1.addItemListener(this);

			chbox1.setBackground(Color.LIGHT_GRAY); 
			shot2.getContentPane().add(chbox1);

			CheckboxGroup checkp2;
			checkp2 = new CheckboxGroup();
			
			Checkbox chboxSL = new Checkbox("Slow Speed", checkp2, false);
			chboxSL.addItemListener(this);
			chboxSL.setBackground(Color.LIGHT_GRAY);
			WPanel3.add(chboxSL);

			Checkbox chboxFS = new Checkbox("Medium Speed", checkp2, true);
			chboxFS.addItemListener(this);
			chboxFS.setBackground(Color.LIGHT_GRAY);
			WPanel3.add(chboxFS);

			shot2.getContentPane().add(WPanel3);

			Checkbox chbox2 = new Checkbox("General Turing Machine", checkp, false);
			chbox2.addItemListener(this);
			chbox2.setBackground(Color.LIGHT_GRAY); 
			shot2.getContentPane().add(chbox2);

			Checkbox chboxVS = new Checkbox("Fast Speed", checkp2, false);
			chboxVS.addItemListener(this);
			chboxVS.setBackground(Color.LIGHT_GRAY);
			WPanel4.add(chboxVS);

			Checkbox chboxCM = new Checkbox("Very Fast Speed", checkp2, false);
			chboxCM.addItemListener(this);
			chboxCM.setBackground(Color.LIGHT_GRAY);
			WPanel4.add(chboxCM);

			shot2.getContentPane().add(WPanel4);

			strings = new JTextArea("");
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			shot2.getContentPane().add(strings);

			strings = new JTextArea("");
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			shot2.getContentPane().add(strings);

			strings = new JTextArea("");
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			WPanel.add(strings);


			b=new JButton("OK");
			b.setActionCommand("OkN");
			b.addActionListener(this);
			WPanel.add(b);

			shot2.getContentPane().add(WPanel, BorderLayout.NORTH);

			b=new JButton("Cancel");
			b.setActionCommand("Cancel");
			b.addActionListener(this);
			WPanel2.add(b);

			strings = new JTextArea("");
			strings.setBackground(Color.LIGHT_GRAY);
			strings.setEditable(false); 
			WPanel2.add(strings);

			shot2.getContentPane().add(WPanel2, BorderLayout.NORTH);

			shot2.setSize(450,150);
			shot2.setResizable(false);

			shot2.setVisible(true);

			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+180,bounds.y+105);

			shot2.addWindowListener(this);
			}
		if (label=="Open")
			{
			Jtmp.setEnabled(false);

			shot2 = new JFrame("Open File");
			shot2.getContentPane().setBackground(Color.GRAY);
			shot2.getContentPane().setLayout(null);

			strings = new JTextArea("File Name :");
			strings.setFont(new Font("Helvetica", Font.BOLD,14));
			strings.setBounds(100,10,100,30);
			strings.setBackground(Color.GRAY);
			strings.setEditable(false);
			shot2.getContentPane().add(strings);

			AreaOpen = new TextArea(StringOpen,1,1,TextArea.SCROLLBARS_NONE);
			AreaOpen.setFont(new Font("Helvetica", Font.BOLD,14));
			AreaOpen.setBounds(5,40,290-8,60);
			AreaOpen.setBackground(Color.WHITE);
			shot2.getContentPane().add(AreaOpen);

			b=new JButton("OK");
			b.setActionCommand("OkO");
			b.setBounds(60,105,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);

			b=new JButton("Cancel");
			b.setActionCommand("Cancel");
			b.setBounds(146,105,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);


			shot2.setSize(300,185);
			shot2.setResizable(false);
			shot2.setVisible(true);
			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+67,bounds.y+104);
		
			shot2.addWindowListener(this);
			}
		if (label=="Save")
			{
			if (StringOpen.length()==0)
				{
//	Executing Save As...
				Jtmp.setEnabled(false);

				shot2 = new JFrame("Save As");
				shot2.getContentPane().setBackground(Color.GRAY);
				shot2.getContentPane().setLayout(null);

				strings = new JTextArea("File Name :");
				strings.setFont(new Font("Helvetica", Font.BOLD,14));
				strings.setBounds(100,10,100,30);
				strings.setBackground(Color.GRAY);
				strings.setEditable(false);
				shot2.getContentPane().add(strings);

				AreaSaveAs = new TextArea(StringSave,1,1,TextArea.SCROLLBARS_NONE);
				AreaSaveAs.setFont(new Font("Helvetica", Font.BOLD,14));
				AreaSaveAs.setBounds(5,40,290-8,60);
				AreaSaveAs.setBackground(Color.WHITE);
				shot2.getContentPane().add(AreaSaveAs);

				b=new JButton("OK");
				b.setActionCommand("OkSA");
				b.setBounds(60,105,85,22);
				b.addActionListener(this);
				shot2.getContentPane().add(b);

				b=new JButton("Cancel");
				b.setActionCommand("Cancel");
				b.setBounds(146,105,85,22);
				b.addActionListener(this);
				shot2.getContentPane().add(b);


				shot2.setSize(300,185);
				shot2.setResizable(false);
				shot2.setVisible(true);
				Rectangle bounds = Jtmp.getBounds();
				shot2.setLocation(bounds.x+67,bounds.y+104);

				shot2.addWindowListener(this);
////////////////////////
				}
			else
				{
				fileSaved=fileIO.SaveF(StringOpen, TMachine.TMmodeID);
				if(fileSaved==0)	TextEditor.messbox.append("Warning	: Nothing Saved\n");
				else	TextEditor.messbox.append("File data saved successfully\n");
				}
			}
		if (label=="SaveAs")
			{
			Jtmp.setEnabled(false);

			shot2 = new JFrame("Save As");
			shot2.getContentPane().setBackground(Color.GRAY);
			shot2.getContentPane().setLayout(null);

			strings = new JTextArea("File Name :");
			strings.setFont(new Font("Helvetica", Font.BOLD,14));
			strings.setBounds(100,10,100,30);
			strings.setBackground(Color.GRAY);
			strings.setEditable(false);
			shot2.getContentPane().add(strings);

			AreaSaveAs = new TextArea(StringSave,1,1,TextArea.SCROLLBARS_NONE);
			AreaSaveAs.setFont(new Font("Helvetica", Font.BOLD,14));
			AreaSaveAs.setBounds(5,40,290-8,60);
			AreaSaveAs.setBackground(Color.WHITE);
			shot2.getContentPane().add(AreaSaveAs);

			b=new JButton("OK");
			b.setActionCommand("OkSA");
			b.setBounds(60,105,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);

			b=new JButton("Cancel");
			b.setActionCommand("Cancel");
			b.setBounds(146,105,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);


			shot2.setSize(300,185);
			shot2.setResizable(false);
			shot2.setVisible(true);
			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+67,bounds.y+104);

			shot2.addWindowListener(this);
			}
		if (label=="SaveTape")
			{
			if (StringOpen.length()==0)
				{
//	Executing Save As...
				Jtmp.setEnabled(false);

				shot2 = new JFrame("Save As");
				shot2.getContentPane().setBackground(Color.GRAY);
				shot2.getContentPane().setLayout(null);

				strings = new JTextArea("File Name :");
				strings.setFont(new Font("Helvetica", Font.BOLD,14));
				strings.setBounds(100,10,100,30);
				strings.setBackground(Color.GRAY);
				strings.setEditable(false);
				shot2.getContentPane().add(strings);

				AreaSaveAs = new TextArea(StringSave,1,1,TextArea.SCROLLBARS_NONE);
				AreaSaveAs.setFont(new Font("Helvetica", Font.BOLD,14));
				AreaSaveAs.setBounds(5,40,290-8,60);
				AreaSaveAs.setBackground(Color.WHITE);
				shot2.getContentPane().add(AreaSaveAs);

				b=new JButton("OK");
				b.setActionCommand("OkSA");
				b.setBounds(60,105,85,22);
				b.addActionListener(this);
				shot2.getContentPane().add(b);

				b=new JButton("Cancel");
				b.setActionCommand("Cancel");
				b.setBounds(146,105,85,22);
				b.addActionListener(this);
				shot2.getContentPane().add(b);


				shot2.setSize(300,185);
				shot2.setResizable(false);
				shot2.setVisible(true);
				Rectangle bounds = Jtmp.getBounds();
				shot2.setLocation(bounds.x+67,bounds.y+104);

				shot2.addWindowListener(this);
////////////////////////
				}
			else
				{
				FileTMmode=fileIO.CheckFileInfo(StringOpen);
				fileSaved=fileIO.SaveTape(StringOpen, FileTMmode);
				if(fileSaved==0)	TextEditor.messbox.append("Warning	: Nothing Saved\n");
				else	TextEditor.messbox.append("Tape saved successfully\n");
				}
			}
		if (label=="Exit")
			{
			System.exit(1);
			}
		if (label=="Start")
			{
			Start();
			}
		if (label=="Step")
			{
			Stop();
			Step();
			}
		if (label=="Stop")
			{
			Stop();
			}
		if (label=="Reset")
			{
			Reset();
			}
		if (label=="ChangeAlphabet")
			{			
//			TextEditor.messbox.setText("Changing Alphabet");
			
			Jtmp.setEnabled(false);
			
			shot2 = new JFrame("Alphabet");
			shot2.getContentPane().setBackground(Color.LIGHT_GRAY);
			shot2.getContentPane().setLayout(null);
			
			shot2.setSize(300,185+22);
			shot2.setResizable(false);
			shot2.setVisible(true);
			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+67,bounds.y+104);

			b=new JButton("Close");
			b.setActionCommand("Cancel");
			b.setBounds(102,130,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);
			
			b=new JButton("Add");
			b.setActionCommand("AddSymbolAlphabet");
			b.setBounds(195,40,85,22);
			b.addActionListener(this);
//			b.setBackground(Color.GREEN);
//			b.setForeground(Color.BLACK);
			shot2.getContentPane().add(b);
			
			b=new JButton("Remove");
			b.setActionCommand("RemSymbolAlphabet");
			b.setBounds(195,70,85,22);
			b.addActionListener(this);
//			b.setBackground(Color.GREEN);
//			b.setForeground(Color.BLACK);
			shot2.getContentPane().add(b);

			Container = new TextArea("",1,1,TextArea.SCROLLBARS_HORIZONTAL_ONLY); 
			
			Container.setFont(new Font("Helvetica", Font.BOLD,14));
			Container.setBounds(5,46,150,40);
			Container.setEnabled(true);
			Container.setBackground(Color.LIGHT_GRAY);
			Container.setEditable(false);
			shot2.getContentPane().add(Container);

			if (alphabet.checkEmpty()==1) Container.setText("#");
						
			else Container.setText(alphabet.getSet());
			
			AddRem = new TextArea("",1,1,TextArea.SCROLLBARS_NONE); 
			
			AddRem.setFont(new Font("Helvetica", Font.BOLD,14));
			AddRem.setBounds(163,52,22,22);
			AddRem.setEnabled(true);
			AddRem.setBackground(Color.WHITE);
			AddRem.setEditable(true);
			shot2.getContentPane().add(AddRem);
						
			shot2.addWindowListener(this);
			
			}
		if (label=="AddSymbolAlphabet")
			{
			if (AddRem.getText().length()==1)
				{
				alphabet.Insert(AddRem.getText());
				Container.setText(alphabet.getSet());
				}
			AddRem.setText("");	
//			System.out.println("AA1");
			}
		if (label=="RemSymbolAlphabet")
			{
			if (AddRem.getText().length()==1)
				{
				alphabet.Remove(AddRem.getText());
				Container.setText(alphabet.getSet());
				}
			AddRem.setText("");				
//			System.out.println("AA2");
			}
			
		if (label=="ChangeStates")
			{
//			TextEditor.messbox.setText("Changing States");
			
			Jtmp.setEnabled(false);
			
			shot2 = new JFrame("States");
			shot2.getContentPane().setBackground(Color.LIGHT_GRAY);
			shot2.getContentPane().setLayout(null);
			
			shot2.setSize(300,185+22);
			shot2.setResizable(false);
			shot2.setVisible(true);
			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+67,bounds.y+104);

			b=new JButton("Close");
			b.setActionCommand("Cancel");
			b.setBounds(102,130,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);
			
			b=new JButton("Add");
			b.setActionCommand("AddSymbolStates");
			b.setBounds(195,40,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);
			
			b=new JButton("Remove");
			b.setActionCommand("RemSymbolStates");
			b.setBounds(195,70,85,22);
			b.addActionListener(this);
			shot2.getContentPane().add(b);
			
			b=new JButton("I");
			b.setActionCommand("Initial");
			b.setBounds(10,92,40,20);
			b.addActionListener(this);
//			b.setBackground(Color.GREEN);
//			b.setForeground(Color.BLACK);
			shot2.getContentPane().add(b);
			
			b=new JButton("F");
			b.setActionCommand("Final");
			b.setBounds(85,92,40,20);
			b.addActionListener(this);
//			b.setBackground(Color.GREEN);
//			b.setForeground(Color.BLACK);
			shot2.getContentPane().add(b);
			

			Container = new TextArea("",1,1,TextArea.SCROLLBARS_HORIZONTAL_ONLY); 
			
			Container.setFont(new Font("Helvetica", Font.BOLD,14));
			Container.setBounds(5,46,150,40);
			Container.setEnabled(true);
			Container.setBackground(Color.LIGHT_GRAY);
			Container.setEditable(false);
			shot2.getContentPane().add(Container);
			
			Initial = new TextArea("",1,1,TextArea.SCROLLBARS_NONE); 
			
			Initial.setFont(new Font("Helvetica", Font.BOLD,14));
			Initial.setBounds(55,91,22,22);
			Initial.setEnabled(true);
			Initial.setBackground(Color.LIGHT_GRAY);
			Initial.setEditable(false);
			shot2.getContentPane().add(Initial);
			
			Final = new TextArea("",1,1,TextArea.SCROLLBARS_NONE); 
			
			Final.setFont(new Font("Helvetica", Font.BOLD,14));
			Final.setBounds(130,91,22,22);
			Final.setEnabled(true);
			Final.setBackground(Color.LIGHT_GRAY);
			Final.setEditable(false);
			shot2.getContentPane().add(Final);
			
			

			if (states.checkEmpty()==1) Container.setText("");
						
			else Container.setText(states.getSet());
			
			Initial.setText(states.getState('0')+"");
			Final.setText(states.getState('1')+"");
			
			
			AddRem = new TextArea("",1,1,TextArea.SCROLLBARS_NONE); 
			
			AddRem.setFont(new Font("Helvetica", Font.BOLD,14));
			AddRem.setBounds(163,52,22,22);
			AddRem.setEnabled(true);
			AddRem.setBackground(Color.WHITE);
			AddRem.setEditable(true);
			shot2.getContentPane().add(AddRem);
						
			shot2.addWindowListener(this);
			
			
			}
		if (label=="AddSymbolStates")
			{
			if (AddRem.getText().length()==1)
				{
				states.Insert(AddRem.getText()+" ");
				Container.setText(states.getSet());
				}
			AddRem.setText("");	
//			System.out.println("AA1");
			}
		if (label=="RemSymbolStates")
			{
			if (AddRem.getText().length()==1)
				{
				String In = new String();
				String Fin = new String();

				In=states.getState('0')+"0"; 
				Fin=states.getState('1')+"1";
					
				if(In.compareTo(AddRem.getText()+"0")==0) states.Remove(In);
				else if(Fin.compareTo(AddRem.getText()+"1")==0) states.Remove(Fin);
				else states.Remove(AddRem.getText()+" ");
				Container.setText(states.getSet());
				Initial.setText(states.getState('0')+"");
				Final.setText(states.getState('1')+"");
//				System.out.println("In = "+In+" Fin = "+Fin);
				}
			AddRem.setText("");		
//			System.out.println("AA2");
			}
				
		if (label=="Initial")
			{
				if (AddRem.getText().length()==1)
				{
//				if(states.checkExist(""+states.getState('0'))==1) states.Remove(""+states.getState('0'));
				states.Insert(AddRem.getText()+"0");
				Initial.setText(states.getState('0')+"");
				Container.setText(states.getSet());
				}
			AddRem.setText("");	
//			System.out.println("AAL");
			}
		if (label=="Final")
			{		   
			if (AddRem.getText().length()==1)
				{
				states.Insert(AddRem.getText()+"1");
				Final.setText(states.getState('1')+"");
				Container.setText(states.getSet());
				}
			AddRem.setText("");	
//			System.out.println("AAK");
			}
				
		if (label=="Load")		
			{
			Stop();
			try
				{
				Thread.sleep(speedexec);
				}
			catch(InterruptedException E)
				{
				}

			flag=1;

			// Probably fix of 'Initial Tape String'...
			MachStr=TextEditor.machStr.getText();
			MachStrFix="";
			for(i=0;i<MachStr.length();i++)	if(MachStr.charAt(i)!='\n')	MachStrFix = MachStrFix + MachStr.charAt(i);
			if (MachStr.length()!=MachStrFix.length())
				{
				MachStr=MachStrFix;
				TextEditor.machStr.setText(MachStr);
				TextEditor.messbox.append("Warning	: 'Initial Tape String' fixed\n"); 
				}
			////////////////////////////////////
			for(i=0;i<MachStr.length();i++)
				{
				if (alphabet.checkExist(""+MachStr.charAt(i))==0)
					{
					TextEditor.messbox.append("Error	: Symbol '"+MachStr.charAt(i)+"' in 'Initial Tape String' does not exist in the Alphabet\n");
					flag=0;
					break;
					}
				}

			// Probably fix of 'Initial Tape Position'...
			MachPos=TextEditor.machPos.getText();
			MachPosFix="";
			for(i=0;i<MachPos.length();i++)	if(MachPos.charAt(i)!='\n')	MachPosFix = MachPosFix + MachPos.charAt(i);
			if (MachPosFix.length()!=MachPosFix.length())
				{
				MachPos=MachPosFix;
				TextEditor.machStr.setText(MachPos);
				TextEditor.messbox.append("Warning	: 'Initial Tape Position' fixed\n"); 
				}
			/////////////////////////////////////////////
			for(i=0;i<MachPos.length();i++)
				{
				if ((MachPos.charAt(i)<'0')||(MachPos.charAt(i)>'9'))
					{
					TextEditor.messbox.append("Error	: 'Initial Tape Position' does not represent an integer\n");
					flag=0;
					break;
					}
				}
			// String to int:
			MachPosINT=0;
			for(i=0; i<MachPos.length(); i++)
				{
				pow10=1;
				for(j=i;j<MachPos.length()-1;j++)	pow10=pow10*10;
				temp=pow10*(int)(MachPos.charAt(i)-'0');
				MachPosINT=MachPosINT+temp;
				}
			/////////////

			if ((MachPosINT>1000000)&&(flag==1))
				{
				TextEditor.messbox.append("Error	: 'Initial Tape Position' is a big integer\n");
				flag=0;
				}

			if (flag==1)
				{
				tape.resetTape();
				Controller.stop=1;

				MachStrFix="";
				for(i=0;i<MachStr.length();i++)
					{
					if(MachStr.charAt(i)=='#')	MachStrFix = MachStrFix + ' ';
					else MachStrFix = MachStrFix + MachStr.charAt(i);
					}
				MachStr=MachStrFix;

					for(i=0;i<MachStr.length();i++)
						{
						tape.addValue(MachStr.charAt(i)+"",0);
						tape.moveR(0);
						}
					for(i=0;i<MachStr.length();i++)	tape.moveL(0);			
				Controller.stop=0;

				// Refreshing...
				if(TMachine.TMmodeID==2)	TextEditor.machPos.setText(""+MachPosINT);
				else	TextEditor.machPos.setText("0");
				////////////////
				for(i=0;i<MachPosINT;i++)	tape.moveR(0);
				// Refreshing...
				tape.PrintL();
				tape.PrintR();
				////////////////
				TextEditor.messbox.append("Load was successfull\n");
				}
			else	TextEditor.messbox.append("Warning	: Load was unsuccessfull\n");
			}
					
		if (label=="Creators")
			{
			Jtmp.setEnabled(false);			
			shot2 = new JFrame("Creators");
			shot2.setSize(480,200+22);
			shot2.setBackground(Color.LIGHT_GRAY);
			
			Rectangle bounds = Jtmp.getBounds();
			shot2.setLocation(bounds.x+180,bounds.y+105);
			
//			shot2.show();
			
			TextArea CreatorJ = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
			CreatorJ.setFont(new Font("Helvetica", Font.BOLD,11));
			CreatorJ.setBounds(75,10,385,60);
			CreatorJ.setBackground(Color.LIGHT_GRAY);
			CreatorJ.setEditable(false);
			CreatorJ.setText("  Name		:    John Tsivourakis\n");
			CreatorJ.append("  Department	:    Applied Mathematics Department\n");
			CreatorJ.append("  A.M.		:    216\n");
			CreatorJ.append("  Email		:    iatsibou@tem.uoc.gr, iatsivou@csd.uoc.gr");
//			CreatorJ.setEnabled(false);
			shot2.getContentPane().add(CreatorJ);

			TextArea CreatorG = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
			CreatorG.setFont(new Font("Helvetica", Font.BOLD,11));
			CreatorG.setBounds(75,70,385,60);
			CreatorG.setBackground(Color.LIGHT_GRAY);
			CreatorG.setEditable(false);
			CreatorG.setText("  Name		:    Grigoris Galiatsatos\n");
			CreatorG.append("  Department	:    Applied Mathematics Department\n");
			CreatorG.append("  A.M.		:    178\n");
			CreatorG.append("  Email		:    gggaliat@tem.uoc.gr, gggaliat@csd.uoc.gr");
//			CreatorG.setEnabled(false);
			shot2.getContentPane().add(CreatorG);

			ImageIcon John = new ImageIcon("Images/JohnIcon2.gif");
			JButton bJ=new JButton(John);
			bJ.setBounds(10,12,58,55);
//			bJ.setEnabled(false);
			shot2.getContentPane().add(bJ);

			ImageIcon Grhgoris = new ImageIcon("Images/GrhgorisIcon2.gif");
			JButton bG=new JButton(Grhgoris);
			bG.setBounds(10,72,58,55);
//			bG.setEnabled(false);
			shot2.getContentPane().add(bG);

			b=new JButton("Close");
			b.setActionCommand("Cancel");
			b.setBounds(195,135,85,25);
			b.addActionListener(this);
			shot2.getContentPane().add(b);

			shot2.setResizable(false);
			shot2.addWindowListener(this);
			}
		if (label=="OkN")
			{
			Jtmp.setEnabled(true);
			shot2.setEnabled(false);
			shot2.setVisible(false);
//			Jtmp.show();

			states.ResetStates();
			alphabet.ResetAlphabet();
			TextEditor.messbox.setText("");
			TextEditor.machName.setText("");
			TextEditor.machStr.setText("");
			TextEditor.machPos.setText("");
			TextEditor.commands.setText("");

// Loading chosen data...
			if (FlagModeN==1)
				{
				basic.setState(true);
				general.setState(false);
				tape.resetTape();
				fileIO.Tape=" ";
				fileIO.InitTapePosINT=0;
				setInitialTape();
				TM_mode(1);
				}
			if (FlagModeN==2)
				{
				basic.setState(false);
				general.setState(true);
				tape.resetTape();
				fileIO.Tape=" ";
				fileIO.InitTapePosINT=0;
				setInitialTape();
				TM_mode(2);
				}
			if (FlagSpeedN==1)
				{
				slow.setState(true);
				fast.setState(false);
				vfast.setState(false);
				comp.setState(false);
				Transition.speedexec=1000;
				}
			if (FlagSpeedN==2)
				{
				slow.setState(false);
				fast.setState(true);
				vfast.setState(false);
				comp.setState(false);
				Transition.speedexec=500;
				}
			if (FlagSpeedN==3)
				{
				slow.setState(false);
				fast.setState(false);
				vfast.setState(true);
				comp.setState(false);
				Transition.speedexec=150;
				}
			if (FlagSpeedN==4)
				{
				slow.setState(false);
				fast.setState(false);
				vfast.setState(false);
				comp.setState(true);
				Transition.speedexec=80;
				}


			FlagModeN=1;
			FlagSpeedN=2;
			}
		if (label=="OkO")
			{
			// Restore Inerface...
			Jtmp.setEnabled(true);
			shot2.setEnabled(false);
			shot2.setVisible(false);
//			Jtmp.show();
			
			// Process event...
			StringOpen=AreaOpen.getText();

			// Probably fix of StringOpen...
			StringOpenFix="";
			for(i=0;i<StringOpen.length();i++)	if (StringOpen.charAt(i)!='\n')	StringOpenFix=StringOpenFix+StringOpen.charAt(i);
			if (StringOpen.length()!=StringOpenFix.length())
				{
				StringOpen=StringOpenFix;
				TextEditor.messbox.append("Warning	: Given name of opening file fixed to \""+StringOpen+"\"\n");
				}
			/////////////////////////////
			FileTMmode=fileIO.CheckFileInfo(StringOpen);
			if (FileTMmode!=0)	ResetAll();
			fileLoaded=fileIO.OpenF(StringOpen, FileTMmode);
			if(fileLoaded==0)	TextEditor.messbox.append("Warning	: Nothing Loaded\n");
			else
				{
				Jtmp.setTitle("Turing Machine Simulator - ...\\"+StringOpen);
				TextEditor.messbox.append("File data loaded successfully\n");
				}
			}
		if (label=="OkSA")
			{
			// Restore Inerface...
			Jtmp.setEnabled(true);
			shot2.setEnabled(false);
			shot2.setVisible(false);
//			Jtmp.show();
			
			// Process event...
			StringSave=AreaSaveAs.getText();
			// Probably fix of StringSave...
			StringSaveFix="";
			for(i=0;i<StringSave.length();i++)	if (StringSave.charAt(i)!='\n')	StringSaveFix=StringSaveFix+StringSave.charAt(i);
			if (StringSave.length()!=StringSaveFix.length())
				{
				StringSave=StringSaveFix;
				TextEditor.messbox.append("Warning	: Given name of saving file fixed to \""+StringSave+"\"\n");
				}
			/////////////////////////////
			// Probably add of ending '.txt' to StringSave...
			if(StringSave.length()<4)	StringSave=StringSave+".txt";
			else
				{
				if(StringSave.charAt(StringSave.length()-4)!='.')	StringSave=StringSave+".txt";
				else if(StringSave.charAt(StringSave.length()-3)!='t')	StringSave=StringSave+".txt";
				else if(StringSave.charAt(StringSave.length()-2)!='x')	StringSave=StringSave+".txt";
				else if(StringSave.charAt(StringSave.length()-1)!='t')	StringSave=StringSave+".txt";
				}
			/////////////////////////////////////////////////
			fileSaved=fileIO.SaveF(StringSave, TMachine.TMmodeID);
			if(fileSaved==0)	TextEditor.messbox.append("Warning	: Nothing Saved\n");
			else
				{
				TextEditor.messbox.append("File data saved successfully\n");
				StringOpen=StringSave;
				Jtmp.setTitle("Turing Machine Simulator - ...\\"+StringOpen);
				}
			}
		if (label=="Cancel")
			{
			Jtmp.setEnabled(true);
			shot2.setEnabled(false);
			shot2.setVisible(false);
//			Jtmp.show();
			}
		if (label=="Clear")
			{
			TextEditor.messbox.setText("");
			}
		if (label=="GoLeft")
			{
			Stop();
			tape.moveL(1);
			}
		if (label=="GoRight")
			{
			Stop();
			tape.moveR(1);
			}
		}

	public void itemStateChanged(ItemEvent e)
		{
		String labelItem;
		labelItem = ""+e.getItem();

//		TextEditor.messbox.append(labelItem+"\n");
		if (labelItem.compareTo("Basic")==0)
			{
			tape.resetTape();
			fileIO.Tape=" ";
			fileIO.InitTapePosINT=0;
			setInitialTape();
			TM_mode(1);
			TextEditor.messbox.setText("");
			TextEditor.machName.setText("");
			TextEditor.machStr.setText("");
			TextEditor.machPos.setText("0");
			TextEditor.commands.setText("");
			states.ResetStates();
			alphabet.ResetAlphabet();
			basic.setState(true);
			general.setState(false);
			}
		if (labelItem.compareTo("General")==0)
			{
			tape.resetTape();
			fileIO.Tape=" ";
			fileIO.InitTapePosINT=0;
			setInitialTape();
			TM_mode(2);
			TextEditor.messbox.setText("");
			TextEditor.machName.setText("");
			TextEditor.machStr.setText("");
			TextEditor.machPos.setText("");
			TextEditor.commands.setText("");
			states.ResetStates();
			alphabet.ResetAlphabet();
			basic.setState(false);
			general.setState(true);
			}
		if (labelItem.compareTo("Slow")==0)
			{
			slow.setState(true);
			fast.setState(false);
			vfast.setState(false);
			comp.setState(false);
			Transition.speedexec=1000;
			}
		if (labelItem.compareTo("Medium")==0)
			{
			slow.setState(false);
			fast.setState(true);
			vfast.setState(false);
			comp.setState(false);
			Transition.speedexec=500;
			}
		if (labelItem.compareTo("Fast")==0)
			{
			slow.setState(false);
			fast.setState(false);
			vfast.setState(true);
			comp.setState(false);
			Transition.speedexec=150;
			}
		if (labelItem.compareTo("Very Fast")==0)
			{
			slow.setState(false);
			fast.setState(false);
			vfast.setState(false);
			comp.setState(true);
			Transition.speedexec=80;
			}

		if (labelItem.compareTo("Basic Turing Machine")==0)
			{
			FlagModeN=1;
			}
		if (labelItem.compareTo("General Turing Machine")==0)
			{
			FlagModeN=2; 
			}
		if (labelItem.compareTo("Slow Speed")==0)
			{
			FlagSpeedN=1;
			}
		if (labelItem.compareTo("Medium Speed")==0)
			{
			FlagSpeedN=2;
			}
		if (labelItem.compareTo("Fast Speed")==0)
			{
			FlagSpeedN=3;
			}
		if (labelItem.compareTo("Very Fast Speed")==0)
 			{
			FlagSpeedN=4;
			}

		}
/**
 * Gia ektelesh entolwn kata thn energopoihsh enos frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowActivated_WindowEvent
 */
	public void windowActivated(WindowEvent e) 
		{
		}

/**
 * Gia ektelesh entolwn afou kleisei ena frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowClosed_WindowEvent
 */
	public void windowClosed(WindowEvent e) 
		{
		}

/**
 * Gia ektelesh entolwn kata to kleisimo enos frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowClosing_WindowEvent
 */
	public void windowClosing(WindowEvent e) 
		{
		Jtmp.setEnabled(true);
		shot2.setEnabled(false);
		shot2.setVisible(false);
//		Jtmp.show();
		}

/**
 * Gia ektelesh entolwn otan ena frame apenergopoih8ei.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowDeactivated_WindowEvent
 */
	public void windowDeactivated(WindowEvent e) 
		{
		}

/**
 * Gia ektelesh entolwn kata to apopagwma enos frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowDeiconified_WindowEvent
 */
	public void windowDeiconified(WindowEvent e) 
		{
		}

/**
 * Gia ektelesh entolwn kata to pagwma enos frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowIconified_WindowEvent
 */
	public void windowIconified(WindowEvent e) 
		{
		}

/**
 * Gia ektelesh entolwn kata to anoigma enos frame.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B>  void_windowOpened_WindowEvent
 */
	public void windowOpened(WindowEvent e) 
		{
		}
	}
