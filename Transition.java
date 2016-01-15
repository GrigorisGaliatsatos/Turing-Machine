import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth h klash ektelei tis leitourgies Mhxanhs Turing, kanontas
 * tous aparaithtous elegxous egkyrwn entolwn metabashs
 * (xrhsimopoihsh epitreptou alfabhtou, sumbolwn, katastasewn).
 * Tis entoles metabashs tis anakta apo thn klash TextEditor.
 */
public abstract class Transition
	{
	public States states = new States();
	public Alphabet alphabet = new Alphabet();
	public Tape tape = new Tape(alphabet, 15);

	static int speedexec=1000;

	private int i, j, n;
	public char curstate = ' ';

/**
 * Epanekkinei thn Mhxanh Turing.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_ResetAll_void 
 */
	public void ResetAll()
		{
		curstate = ' ';
//		System.out.println("Kayla "+curstate+"END");
		tape.resetTape();
		}

/**
 * Elegxei an mia entolh metabashs pou anaparistatai sto String command, 
 * einai swsth ws pros thn suntaksh kai to periexomeno.
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_checkcom_String
 * <BR>
 * <B>Parameter:</B> command String anaparastashs mias entolhs metabashs
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an ola einai swsta, h 0 an uparxei kapoio la8os.
 */
	public int checkcom(String command)
		{
		int i,j,l,n;
		if (TMachine.TMmodeID==1)
				{
				l=command.length();
				if (l!=7) 
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid command length)\n");
						return 0;
						} 

				String tmp = new String();
				tmp = command.charAt(0)+" ";		
				if (states.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the States Set)\n");

						return 0;
						}

				tmp = command.charAt(4)+" ";		
				if (states.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the States Set)\n");
						return 0;
						}

				if (command.charAt(1)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");

						return 0;
						}

				if (command.charAt(3)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");
						return 0;
						}
				if (command.charAt(5)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");

						return 0;
						}

				tmp = command.charAt(2)+"";
				if(tmp.compareTo(" ")==0) tmp="#";
				if (alphabet.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the Alphabet)\n");
						return 0;
						}
				tmp = command.charAt(6)+"";			
				if ((command.charAt(6)=='R')||(command.charAt(6)=='L')||(alphabet.checkExist(tmp)==1));
				else
					{
					TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
					TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the Alphabet, or it is not 'R' or 'L')\n");

					return 0;
					}

				}
		
		if (TMachine.TMmodeID==2)
				{
				l=command.length();
				if (l!=9) 
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid command length)\n");
						return 0;
						} 
				String tmp = new String();
				tmp = command.charAt(0)+" ";		
				if (states.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the States Set)\n");
						return 0;
						}

				tmp = command.charAt(4)+" ";
				if (states.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the States Set)\n");
						return 0;
						}

				if (command.charAt(1)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");
						return 0;
						}

				if (command.charAt(3)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");
						return 0;
						}

				if (command.charAt(5)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");
						return 0;
						}

				if (command.charAt(7)!=' ')
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Invalid format of spaces in command)\n");
						return 0;
						}

				tmp = command.charAt(2)+"";
				if(tmp.compareTo(" ")==0) tmp=""+'#';
				if (alphabet.checkExist(tmp)==0)
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the Alphabet)\n");
						return 0;
						}

				tmp = command.charAt(6)+"";			
				if (alphabet.checkExist(tmp)==0)	
						{
						TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
						TextEditor.messbox.append("       	  (Symbol '"+tmp+"' does not exist in the Alphabet)\n");
						return 0;
						}

				tmp = command.charAt(8)+"";			
				if ((command.charAt(8)=='R')||(command.charAt(8)=='L')||(command.charAt(8)=='N'));
				else
					{
					TextEditor.messbox.append("Error	: At command  \""+command+"\"\n");
					TextEditor.messbox.append("       	  (Symbol '"+tmp+"' is not 'R', 'L', or 'N')\n");
					return 0;
					}		
				}

		return 1;
		}

/**
 * Ektelei thn entolh metabashs pou anaparistatai sto String command.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_execcom_String_int
 * <BR>
 * <B>Parameter:</B> command String anaparastashs mias entolhs metabashs
 */
	public void execcom(String command,int HStep)
		{
		String S = new String();
		Object Tmp;

		tape.PrintL();
		tape.PrintR();

		if (HStep==1)
			{
			curstate=command.charAt(4);
			
			}		
		
		if (TMachine.TMmodeID==1)		
				{
				if (command.charAt(6)=='R')
					{
					tape.moveR(1);
					}
				else if (command.charAt(6)=='L')
						{
						tape.moveL(1);
						}
					else
					 	{
						if(command.charAt(6)!='#') S=""+command.charAt(6);
						else S=""+' ';
			 			tape.addValue(S,1);
						}
				TextEditor.StateView2.setText(""+curstate);	
				
				if (curstate==states.getState('1'))
						{
//						System.out.println("Telos Programmatos");
						return;
						}
							
				}
		if (TMachine.TMmodeID==2)				 
				{
				if (HStep==1)
					{
				 	if(command.charAt(6)!='#') S=""+command.charAt(6);
				 	else S=""+' ';
				 	tape.addValue(S,1);
					}
					
				if (HStep==2)
					{
					if (command.charAt(8)=='R')
						{
						tape.moveR(1);
						}
					else if (command.charAt(8)=='L')
						{
						tape.moveL(1);
						}
					TextEditor.StateView2.setText(""+curstate);	
					
					if (curstate==states.getState('1'))
							{
//							System.out.println("Telos Programmatos");
							return;
							}
					
					}	
				}
		}
	}
