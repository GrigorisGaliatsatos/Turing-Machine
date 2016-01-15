import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth h klash analambanei ton elegxo leitourgias mias Turing Machine 
 * ths Turing Machine, analoga me tis leitourgies pou epilegei o xrhsths.
 * Parallhla an xreiastei kalei tis antistoixes me8odous ths klashs Transition.
 */
public abstract class Controller extends Transition implements Runnable
	{
	public FileIO fileIO = new FileIO(states, alphabet, tape);
	int HStep=1;	
	static int stop=0;
	private String tmp_;
/**
 * Kalwntas auth thn methodo, ksekina h ektelesh twn entolwn ths mhxanhs Turing.
 * Auto ginetai me xrhsh Thread. 
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_Start_void 
 */
	public void Start()
		{
		int i;

		TextEditor.machStr.setBackground(Color.LIGHT_GRAY);
		TextEditor.machStr.setEditable(false);
		TextEditor.machPos.setBackground(Color.LIGHT_GRAY);
		TextEditor.machPos.setEditable(false);
		TextEditor.commands.setBackground(Color.LIGHT_GRAY);
		TextEditor.commands.setEditable(false);

		for(i=1;i<=15;i++)
			{

			if (TextEditor.cellj[i].getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
				{
				if (i!=8)	TextEditor.cellj[i].setBackground(Color.CYAN);
				}
			TextEditor.cellj[i].setEditable(false);
			}

		TextEditor.messbox.append("Machine started\n");

		if (stop==0)
			{
			Thread S = new Thread(this);
			S.start();
			}
		}

/**
 * Ektelei mia entolh (h ena bhma) ths Mhxanhs Turing, afou ginoun oi katallhloi
 * elegxoi orthotitas ths entolhs. 
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_Step_void
 */
	public int Step()
		{
		int i,j,n,l,k;
		if (HStep==1) 
			{
			if (curstate == ' ')
				{
				curstate = states.getState('0');
				}
			else if (curstate==states.getState('1'))
				{
				TextEditor.messbox.append("End of program"+'\n');
				return 0;
				}
			String text = new String();
			text=TextEditor.commands.getText();
			j=0;
			l=text.length();
			for(i=0;i<l-1;i++) if(text.charAt(i)!='\n') break;
				
			for(i=0;i<=l-1;i++)
				{
				if(text.charAt(i)=='\n') j++;				
				}
			if (l>=1)
				{
				for(i=l-1;(i>=0)&&(text.charAt(i)=='\n');i--);
				j=j-(l-i)+1;
				}
			String tmp[] = new String[j+1];
			tmp = text.split("\n",j+1);
			
        	if (l!=0)
				{
				for(i=0;i<=tmp[j].length()-1;i++) if(tmp[j].charAt(i)=='\n')
					{
					tmp[j] = tmp[j].substring(0,i);
					}
				}
			
			if (tape.checkTape()==0) return 0;
			
			for(i=0;i<=j;i++) if(checkcom(tmp[i])==0) return 0;
					
			for(i=0;i<=j-1;i++)
				{
				for(k=i+1;k<=j;k++)
					{
					if ((tmp[i].charAt(0)==tmp[k].charAt(0))&&(tmp[i].charAt(2)==tmp[k].charAt(2))) 
						{
						TextEditor.messbox.append("Error	: At commands  \""+tmp[i]+"\", \""+tmp[k]+"\"\n");
						TextEditor.messbox.append("       	  (The first 2 symbols \""+tmp[i].charAt(0)+" "+tmp[i].charAt(2)+"\" are the same)\n");
					
						return 0;
						}
					}
				}
					
			n=0;
			for(i=0;i<=j;i++) 
				{
				if ((tmp[i].charAt(0)==curstate)&&(tmp[i].charAt(2)=='#')&&(tape.getValue().toString().charAt(0)==' ')) 
				{
				n=i;
				break;
				}
				
				
				if ((tmp[i].charAt(0)==curstate)&&(tmp[i].charAt(2)==tape.getValue().toString().charAt(0))) 
					{
					n=i;
					break;
					}
				}
	
			if (i==j+1)
				{
				if(tape.getValue().toString().charAt(0)==' ')	TextEditor.messbox.append("Error	: Pair  \""+curstate+" "+'#'+"\" not found\n");
				else	TextEditor.messbox.append("Error	: Pair  \""+curstate+" "+tape.getValue().toString()+"\" not found\n");
				
				return 0;
				}
			tmp_=tmp[n];				   
			}
		if (TMachine.TMmodeID==1) 
		{
		TextEditor.messbox.append("Command \""+tmp_+"\" full-executed\n");
		execcom(tmp_,1);
		}
		if(TMachine.TMmodeID==2) 
			{
			if (HStep==1)	TextEditor.messbox.append("Command \""+tmp_+"\" step1-executed\n");
			if (HStep==2)			
				{
				if (tape.checkTape()==0) return 0;
				TextEditor.messbox.append("Command \""+tmp_+"\" step2-executed\n");
				}
			execcom(tmp_,HStep);
			if(HStep==1) HStep=2;
			else if(HStep==2) HStep=1;
			}
		return 1;
		}

/**
 * Prokalei ton termatismo ekteleshs ths Start.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_Stop_void
 */
	public void Stop()
		{
		int i;

		TextEditor.machStr.setBackground(Color.WHITE);
		TextEditor.machStr.setEditable(true);
		TextEditor.machPos.setBackground(Color.WHITE);
		TextEditor.machPos.setEditable(true);
		TextEditor.commands.setBackground(Color.WHITE);
		TextEditor.commands.setEditable(true);

		for(i=1;i<=15;i++)
			{
			if (TextEditor.cellj[i].getBackground().getRGB()!=Color.LIGHT_GRAY.getRGB())
				{
				if (i!=8)	TextEditor.cellj[i].setBackground(Color.WHITE);	
				TextEditor.cellj[i].setEditable(true);
				}
			}

		if (stop==1)	stop=0;
		}						

/**
 * Prokalei thn biaia epanekinish ths mhxanhs Turing, epanaferontas thn Tainia 
 * me ta dedomena tou arxeiou pou eixe fortwthei kai thn trexousa katastash sthn arxikh.  
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_Reset_void
 */
	public void Reset()
		{
		Stop();
		TM_mode(TMachine.TMmodeID);
		ResetAll();
		setInitialTape();
		TextEditor.StateView2.setText(""+states.getState('0'));
		HStep=1;
			try
				{
				Thread.sleep(speedexec);
				}
			catch(InterruptedException E)
				{
				}
		TextEditor.messbox.append("Machine reseted\n");
		}

/**
 * Arxikopoiei thn tainia, kai topo8etei thn kefalh sthn 8esh arxikopoihshs 
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_setInitialTape_void
 */
	public void setInitialTape()
		{
		int i;

		stop=1;
		for(i=0;i<fileIO.Tape.length();i++)
			{
			tape.addValue(fileIO.Tape.charAt(i)+"",0);
			tape.moveR(0);
			}
		for(i=0;i<fileIO.Tape.length();i++)	tape.moveL(0);			
		stop=0;

		for(i=0;i<fileIO.InitTapePosINT;i++)	tape.moveR(0);
		tape.PrintL();
		tape.PrintR();
		}
/**
 * Allazei ola osa xreiazontai, gia thn metatroph ths Turing Machine apo
 * basikh se genikeumenh, kai antistrofa.
 * <BR>
 * <B>Type:</B> Mutative Transformer
 * <BR>
 * <B>Signature:</B> void_TM_mode_int
 * <BR>
 * <B>Parameter:</B> TMode akeraios pou upodhlwnei to mode ths Mhxanhs Turing
 * <BR>
 * <B>Pre:</B> TMmode einai akeraios (1 gia basikh TM, 2 gia genikeumenh TM)
 */
	static void TM_mode(int mode)
		{
		int i;		

		if (mode==1)
			{
			TMachine.TMmodeID=1;
			for(i=1;i<=7;i++)
				{
				TextEditor.cellj[i].setBackground(Color.LIGHT_GRAY);
				TextEditor.cellj[i].setEditable(false);
				}
			TextEditor.machPos.setText("0");
			TextEditor.machPos.setBackground(Color.LIGHT_GRAY);
			TextEditor.machPos.setEditable(false);
			}
		else if (mode==2)
			{
			TMachine.TMmodeID=2;
			for(i=1;i<=15;i++)
				{
				if (i!=8)
					{
					TextEditor.cellj[i].setBackground(Color.WHITE);
					TextEditor.cellj[i].setEditable(true);
					}
				}
			TextEditor.machPos.setBackground(Color.WHITE);
			TextEditor.machPos.setEditable(true);
			}
		}

/**
 * Ektelei tou Thread pou dhmiourgoume sthn Start.
 * <BR>
 * <B>Type:</B> Mutative Accessor
 * <BR>
 * <B>Signature:</B> void_run_void
 */
		
	public void run()
		{
		int selfstop=1;

		stop=1;
		
		while ((stop==1)&&(selfstop==1))
			{
			try
				{
				Thread.sleep(speedexec);
				}
			catch(InterruptedException E)
				{
				}
			if (stop==0)	break;
			selfstop=Step();
			}
		Stop();
		TextEditor.messbox.append("Machine stopped\n");
		}
	}
