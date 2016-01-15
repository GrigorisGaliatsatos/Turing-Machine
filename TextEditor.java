import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth einai h klash pou dhmiourgei perioxes keimenou sto frame ths klashs
 * TMachine, kai epekteinei thn epikoinwnia tou xrhsth me thn Mhxanh Turing,
 * (mesw twn perioxwn autwn). Kaleitai mono apo thn klash TMachine.
 */

public class TextEditor implements TextListener
	{
	private JTextArea strings;
	private TextArea cell1;
	private TextArea cell2;
	private TextArea cell3;
	private TextArea cell4;
	private TextArea cell5;
	private TextArea cell6;
	private TextArea cell7;
	private TextArea cell8;
	private TextArea cell9;
	private TextArea cell10;
	private TextArea cell11;
	private TextArea cell12;
	private TextArea cell13;
	private TextArea cell14;
	private TextArea cell15;
	private JTextArea StateView1;
	private JTextArea JmachName;
	private JTextArea JmachStr;
	private JTextArea JmachPos;

	public Tape tape;
	static JTextArea StateView2;
	static TextArea  cellj[] = new TextArea[16];
	static TextArea commands;
	static TextArea messbox;
	static TextArea machName;
	static TextArea machStr;
	static TextArea machPos;

	private int flag=0;
	private int i=15, j;

/**
 * Kataskeuazei thn klash dhmiourgias olwn twn aparaithtwn perioxwn
 * keimenou gia mia Mhxanh Turing.
 * <BR>
 * <B>Parameter:</B> JFrame Shot einai to kyrio Frame tou Interface ths TM.
 * <BR>
 *
 */
	public TextEditor(JFrame shot)
		{
		commands = new TextArea("");
		commands.setBounds(100,250,200,250);
		commands.setBackground(Color.white);
		shot.getContentPane().add(commands);

		messbox = new TextArea();
		messbox.setBounds(350,250,350,250);
		messbox.setBackground(Color.LIGHT_GRAY);
		messbox.setEditable(false);
		shot.getContentPane().add(messbox);

		JmachName = new JTextArea("Machine Name");
		JmachName.setBounds(250,130,120,20);
		JmachName.setBackground(Color.GRAY);
		JmachName.setEditable(false);
		JmachName.setFont(new Font("Helvetica", Font.BOLD,14));
		shot.getContentPane().add(JmachName);

		machName = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
		machName.setBounds(375,130,175,20);
		machName.setBackground(Color.WHITE);
		machName.setEditable(true);
//		machName.addTextListener(this);
		shot.getContentPane().add(machName);

		JmachStr = new JTextArea("Initial Tape String");
		JmachStr.setBounds(250,160,120,20);
		JmachStr.setBackground(Color.GRAY);
		JmachStr.setEditable(false);
		JmachStr.setFont(new Font("Helvetica", Font.BOLD,11));
		shot.getContentPane().add(JmachStr);

		machStr = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
		machStr.setBounds(375,160,175,20);
		machStr.setBackground(Color.WHITE);
		machStr.setEditable(true);
		shot.getContentPane().add(machStr);

		JmachPos = new JTextArea("Initial Tape Position");
		JmachPos.setBounds(250,185,120,20);
		JmachPos.setBackground(Color.GRAY);
		JmachPos.setEditable(false);
		JmachPos.setFont(new Font("Helvetica", Font.BOLD,11));
		shot.getContentPane().add(JmachPos);

		machPos = new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
		machPos.setBounds(375,185,175,20);
		machPos.setBackground(Color.WHITE);
		machPos.setEditable(false);
		shot.getContentPane().add(machPos);

		strings = new JTextArea(" TM Message Box ");
		strings.setBounds(475,230,100,20);
		strings.setBackground(Color.GRAY);
		strings.setEditable(false);
		shot.getContentPane().add(strings);

		strings = new JTextArea(" TM State Box ");
		strings.setBounds(160,230,80,20);
		strings.setBackground(Color.GRAY);
		strings.setEditable(false);
		shot.getContentPane().add(strings);


//	Tape Display...

		cell1 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell1.setBounds(190+25,35,22,25);
		cell1.setBackground(Color.WHITE);
		cell1.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell1.addTextListener(this);

		cell2 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell2.setBounds(190+50,35,22,25);
		cell2.setBackground(Color.WHITE);
		cell2.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell2.addTextListener(this);

		cell3 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell3.setBounds(190+75,35,22,25);
		cell3.setBackground(Color.WHITE);
		cell3.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell3.addTextListener(this);

		cell4 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell4.setBounds(190+100,35,22,25);
		cell4.setBackground(Color.WHITE);
		cell4.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell4.addTextListener(this);

		cell5 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell5.setBounds(190+125,35,22,25);
		cell5.setBackground(Color.WHITE);
		cell5.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell5.addTextListener(this);

		cell6 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell6.setBounds(190+150,35,22,25);
		cell6.setBackground(Color.WHITE);
		cell6.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell6.addTextListener(this);

		cell7 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell7.setBounds(190+175,35,22,25);
		cell7.setBackground(Color.WHITE);
		cell7.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell7.addTextListener(this);

		cell8 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell8.setBounds(190+200,35,22,25);
		cell8.setBackground(Color.RED);
		cell8.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell8.addTextListener(this);

		cell9 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell9.setBounds(190+225,35,22,25);
		cell9.setBackground(Color.WHITE);
		cell9.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell9.addTextListener(this);

		cell10 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell10.setBounds(190+250,35,22,25);
		cell10.setBackground(Color.WHITE);
		cell10.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell10.addTextListener(this);
		
		cell11 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell11.setBounds(190+275,35,22,25);
		cell11.setBackground(Color.WHITE);
		cell11.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell11.addTextListener(this);

		cell12 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell12.setBounds(190+300,35,22,25);
		cell12.setBackground(Color.WHITE);
		cell12.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell12.addTextListener(this);

		cell13 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell13.setBounds(190+325,35,22,25);
		cell13.setBackground(Color.WHITE);
		cell13.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell13.addTextListener(this);

		cell14 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell14.setBounds(190+350,35,22,25);
		cell14.setBackground(Color.WHITE);
		cell14.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell14.addTextListener(this);

		cell15 = new TextArea("",1,1,TextArea.SCROLLBARS_NONE);
		cell15.setBounds(190+375,35,22,25);
		cell15.setBackground(Color.WHITE);
		cell15.setFont(new Font("Helvetica", Font.PLAIN ,18));
		cell15.addTextListener(this);

		StateView1 = new JTextArea("");
		StateView1.setBounds(134+200,7,50,24);
		StateView1.setBackground(Color.GRAY);
		StateView1.setFont(new Font("Helvetica", Font.PLAIN ,20));
		StateView1.setEditable(false);
		StateView1.setText("State:");
		
		shot.getContentPane().add(StateView1);

		StateView2 = new JTextArea("");
		StateView2.setBounds(189+200,7,24,25);
		StateView2.setBackground(Color.GRAY);
		StateView2.setFont(new Font("Helvetica", Font.ITALIC ,20));
		StateView2.setEditable(false);
		StateView2.setText("-");
		
		shot.getContentPane().add(StateView2);


		cell1.setText(" ");
		cell2.setText(" ");
		cell3.setText(" ");
		cell4.setText(" ");
		cell5.setText(" ");
		cell6.setText(" ");
		cell7.setText(" ");
		cell8.setText(" ");
		cell9.setText(" ");
		cell10.setText(" ");
		cell11.setText(" ");
		cell12.setText(" ");
		cell13.setText(" ");
		cell14.setText(" ");
		cell15.setText(" ");


		cellj[1]=cell1;
		cellj[2]=cell2;
		cellj[3]=cell3;
		cellj[4]=cell4;
		cellj[5]=cell5;
		cellj[6]=cell6;
		cellj[7]=cell7;
		cellj[8]=cell8;
		cellj[9]=cell9;
		cellj[10]=cell10;
		cellj[11]=cell11;
		cellj[12]=cell12;
		cellj[13]=cell13;
		cellj[14]=cell14;
		cellj[15]=cell15;
		machName.setText("<none>");

		for(j=1;j<=15;j++)	shot.getContentPane().add(cellj[j]);	
		
		}

/**
 * Se auth thn methodo ginetai anathesh ths tainias ths TM sthn Tape gia na xrhsimopoihthei apo thn TextEditor   
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_CurrentTape_Tape
 * <BR>
 * <B>Parameter:</B> Tape TxtEdtrtape einai stigmiotypo ths tainias.
 * <BR>
 *
 */
	public void CurrentTape(Tape TxtEdtrtape)
		{
		tape=TxtEdtrtape;
		}

/**
 * Auth h methodos ka8orizei ta gegonota pou 8a ginoun an peiraxthoun sygkekrimenes 
 * perioxes keimenou,opws p.x. ta kelia ths tainias. 
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_textValueChanged_TextEvent
 * <BR>
 * <B>Parameter:</B> TextEvent e einai event pou symbainoun se perioxes keimenou.
 * <BR>
 *
 */

	public void textValueChanged(TextEvent e)
		{
		char B;
		String label;
		label = e.toString();
		String Tmp;

//		System.out.println("label = "+label);
		i++;
		if (i==16)
			{
			i=0;
			if (Controller.stop==0)
			{
			Tmp=cell1.getText();
			if (Tmp.length()==0)
				{
				cell1.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell1.setText(""+B+"");
				}

			Tmp=cell2.getText();
			if (Tmp.length()==0)
				{
				cell2.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell2.setText(""+B+"");
				}

			Tmp=cell3.getText();
			if (Tmp.length()==0)
				{
				cell3.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell3.setText(""+B+"");
				}

			Tmp=cell4.getText();
			if (Tmp.length()==0)
				{
				cell4.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell4.setText(""+B+"");
				}

			Tmp=cell5.getText();
			if (Tmp.length()==0)
				{
				cell5.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell5.setText(""+B+"");
				}

			Tmp=cell6.getText();
			if (Tmp.length()==0)
				{
				cell6.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell6.setText(""+B+"");
				}

			Tmp=cell7.getText();
			if (Tmp.length()==0)
				{
				cell7.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell7.setText(""+B+"");
				}

			Tmp=cell8.getText();
			if (Tmp.length()==0)
				{
				cell8.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell8.setText(""+B+"");
				}

			Tmp=cell9.getText();
			if (Tmp.length()==0)
				{
				cell9.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell9.setText(""+B+"");
				}

			Tmp=cell10.getText();
			if (Tmp.length()==0)
				{
				cell10.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell10.setText(""+B+"");
				}

			Tmp=cell11.getText();
			if (Tmp.length()==0)
				{
				cell11.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell11.setText(""+B+"");
				}

			Tmp=cell12.getText();
			if (Tmp.length()==0)
				{
				cell12.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell12.setText(""+B+"");
				}

			Tmp=cell13.getText();
			if (Tmp.length()==0)
				{
				cell13.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell13.setText(""+B+"");
				}

			Tmp=cell14.getText();
			if (Tmp.length()==0)
				{
				cell14.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell14.setText(""+B+"");
				}

			Tmp=cell15.getText();
			if (Tmp.length()==0)
				{
				cell15.setText(" ");
//				System.out.println("Mhdeniko mhkos.");
				}
			else
				{
				B=Tmp.charAt(0);
				cell15.setText(""+B+"");
				}
			}
			}

if ((Controller.stop==0)&&(i!=0))
{
// Load current cells's data...

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text7")==0)
				{
//				update tape from cell 1...
				if (cell1.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=6;j++)	tape.moveL(0);
					tape.addValue(""+cell1.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=6;j++)	tape.moveR(0);
					cell1.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text6")==0)
				{
//				update tape from cell 2...
				if (cell2.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=5;j++)	tape.moveL(0);
					tape.addValue(""+cell2.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=5;j++)	tape.moveR(0);
					cell2.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text5")==0)
				{
//				update tape from cell 3...
				if (cell3.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=4;j++)	tape.moveL(0);
					tape.addValue(""+cell3.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=4;j++)	tape.moveR(0);
					cell3.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text4")==0)
				{
//				update tape from cell 4...
				if (cell4.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=3;j++)	tape.moveL(0);
					tape.addValue(""+cell4.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=3;j++)	tape.moveR(0);
					cell4.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text3")==0)
				{
//				update tape from cell 5...
				if (cell5.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=2;j++)	tape.moveL(0);
					tape.addValue(""+cell5.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=2;j++)	tape.moveR(0);
					cell5.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text2")==0)
				{
//				update tape from cell 6...
				if (cell6.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=1;j++)	tape.moveL(0);
					tape.addValue(""+cell6.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=1;j++)	tape.moveR(0);
					cell6.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text1")==0)
				{
//				update tape from cell 7...
				if (cell7.getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
					{
					for(j=0;j<=0;j++)	tape.moveL(0);
					tape.addValue(""+cell7.getText().charAt(0),0);
//					System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
					for(j=0;j<=0;j++)	tape.moveR(0);
					cell7.setCaretPosition(0);
					}
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text0")==0)
				{
//				update tape from cell 8...
				tape.addValue(""+cell8.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				cell8.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text8")==0)
				{
//				update tape from cell 9...
				for(j=0;j<=0;j++)	tape.moveR(0);
				tape.addValue(""+cell9.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=0;j++)	tape.moveL(0);
				cell9.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text9")==0)
				{
//				update tape from cell 10...
				for(j=0;j<=1;j++)	tape.moveR(0);
				tape.addValue(""+cell10.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=1;j++)	tape.moveL(0);
				cell10.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text10")==0)
				{
//				update tape from cell 11...
				for(j=0;j<=2;j++)	tape.moveR(0);
				tape.addValue(""+cell11.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=2;j++)	tape.moveL(0);
				cell11.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text11")==0)
				{
//				update tape from cell 12...
				for(j=0;j<=3;j++)	tape.moveR(0);
				tape.addValue(""+cell12.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=3;j++)	tape.moveL(0);
				cell12.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text12")==0)
				{
//				update tape from cell 13...
				for(j=0;j<=4;j++)	tape.moveR(0);
				tape.addValue(""+cell13.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=4;j++)	tape.moveL(0);
				cell13.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text13")==0)
				{
//				update tape from cell 14...
				for(j=0;j<=5;j++)	tape.moveR(0);
				tape.addValue(""+cell14.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=5;j++)	tape.moveL(0);
				cell14.setCaretPosition(0);
				}

			if (label.compareTo("java.awt.event.TextEvent[TEXT_VALUE_CHANGED] on text14")==0)
				{
//				update tape from cell 15...
				for(j=0;j<=6;j++)	tape.moveR(0);
				tape.addValue(""+cell15.getText().charAt(0),0);
//				System.out.println("Correspondence : "+label+"<---->"+mybar.contr.trans.tape.getValue());
				for(j=0;j<=6;j++)	tape.moveL(0);
				cell15.setCaretPosition(0);
				}
///////////////////////////////
			}
		}
	}
