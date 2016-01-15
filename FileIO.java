import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
//import java.util.*;

/**
 * Auth h klash, analambanei tis diergasies eisodou eksodou se arxeia, 
 * kai deinei thn dinatothta elegxou an ena arxeio periexei swsta 
 * dedomena mias basikhs h genikeumenhs Mhxanhs Turing.
 */
public class FileIO
	{
	private States states;
	private Alphabet alphabet;
	private Tape tape;

	public String Tape = new String();
	public int InitTapePosINT=0;

/**
 * Kataskeuazei thn klash elenxou kai epeksergasias arxeiwn mias Mhxanhs Turing.
 */
	public FileIO(States iostates, Alphabet ioalphabet, Tape iotape)
		{
		states = iostates;
		alphabet = ioalphabet;
		tape = iotape;
		}

/**
 * Anoigei ena arxeio me to onoma pou anaparistatai sto String StrOpen kai diabazei ta dedomena gia
 * mia Mhxanh Turing. Dexetai ws eisodo to String StrOpen, kai ton akeraio
 * TMmode, pou upodhlwnei an h Mhxanh Turing einai basikh h genikeumenh.
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> int_OpenF_String_int
 * <BR>
 * <B>Parameter:</B>StrOpen einai to onoma tou arxeiou pros anoigma gia anagnwsh
 * <BR>
 * <B>Parameter:</B>TMode akeraios pou upodhlwnei to mode ths Mhxanhs Turing.
 * <BR>
 * <B>Pre:</B> TMmode einai akeraios (1 gia basikh TM, 2 gia genikeumenh TM)
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 0 sthn apotuxia, h 1 sthn epituxia ekteleshs ths me8odou 
 */
	public int OpenF(String StrOpen, int TMmode)
		{
		BufferedReader Inpt=null;
		String MachName = new String();
		String TmpStat = new String();
		String TmpAlph = new String();
		String SEStates = new String();
		String Commands = new String();
		String InitTapePos = new String();
		String TapeRest = new String();
		int i, j, k, lines=0, EndOfInt, pow10, temp;

		InitTapePosINT=0;
		Tape=" ";

		if (TMmode==0)	return 0;

// Initializing Strings...
		MachName = "";
		TmpStat = "";
		TmpAlph = "";
		Tape = "";
		SEStates = "";
		Commands = "";
		InitTapePos = "";
		TapeRest = "";

// Checking file permitions and file structure....
		if (StrOpen.compareTo("")==0)	return 0;
		else
			{
			try
				{
				Inpt= new BufferedReader( new FileReader(StrOpen));
				}
			catch (FileNotFoundException ex1)
				{
//				System.err.println("Error	: File not found");
				TextEditor.messbox.append("Error	: File not found\n");
				return 0;
				}
			catch (IOException ex2)
				{
//				System.err.println("Error	: I/O error");
				TextEditor.messbox.append("Error	: I/O error\n");
				return 0;
				}

			try
				{
				String FileIn=new String();

//	Taking file data....
				for(lines=1;(FileIn=Inpt.readLine())!=null;lines++)
					{
					if (lines==1)
						{
						if (((FileIn.compareTo("Basic")==0)&&(TMmode==2))||((FileIn.compareTo("Generalised")==0)&&(TMmode==1)))
							{
							TextEditor.messbox.append("Error	: Asynchronous TMmode error");
							return 0;
							}
						}
					if (lines==2)	MachName=FileIn;
					if (lines==3)	for(j=0;j<FileIn.length();j++)	if((j%2)==0)	TmpStat=TmpStat+FileIn.charAt(j); 
					if (lines==4)	for(j=0;j<FileIn.length();j++)	if((j%2)==0)	TmpAlph=TmpAlph+FileIn.charAt(j);
					if (lines==5)	for(j=0;j<FileIn.length();j++)	Tape=Tape+FileIn.charAt(j);
					if (lines==6)
						{
						if (TMmode==1)
							{
							InitTapePos="0";
							SEStates=""+FileIn.charAt(0)+FileIn.charAt(2);
							}
						else
							{
							for(EndOfInt=2;EndOfInt<FileIn.length();EndOfInt++)	if((FileIn.charAt(EndOfInt)<'0')||(FileIn.charAt(EndOfInt)>'9'))	break;
							SEStates=""+FileIn.charAt(0)+FileIn.charAt(EndOfInt+1);
							for(k=2;k<EndOfInt;k++) InitTapePos=InitTapePos+FileIn.charAt(k);
							}
						}
					if (lines>6)	Commands=Commands+FileIn+"\n";
					}
				Inpt.close();
				}
			catch(IOException ex4)
				{
//				System.err.println("Error	: I/O error");
				TextEditor.messbox.append("Error	: I/O error\n");
				return 0;
				}
//	Loading file data....

//			TextEditor.messbox.append("(1): "+TMmode+"\n");
			Controller.TM_mode(TMmode);
			if (TMmode==1)
				{
				Actions.basic.setState(true);
				Actions.general.setState(false);
				}
			else if (TMmode==2)
				{
				Actions.basic.setState(false);
				Actions.general.setState(true);
				}

//			TextEditor.messbox.append("(2): "+MachName+"\n");
			TextEditor.machName.setText(MachName);

//			TextEditor.messbox.append("(3): "+TmpStat+"\n");
			states.ResetStates();
			for(i=0;i<TmpStat.length();i++)	states.Insert(TmpStat.charAt(i)+"2");

//			TextEditor.messbox.append("(4): "+TmpAlph+"\n");
			alphabet.ResetAlphabet();
			for(i=0;i<TmpAlph.length();i++)	alphabet.Insert(TmpAlph.charAt(i)+"");

//			TextEditor.messbox.append("(5): "+Tape+"\n");
			TextEditor.machStr.setText(Tape);
			TapeRest="";
			for(i=0;i<Tape.length();i++)
				{
				if(Tape.charAt(i)=='#')	TapeRest=TapeRest+' ';
				else	TapeRest=TapeRest+Tape.charAt(i);
				}
			Tape=TapeRest;

			tape.resetTape();
				Controller.stop=1;
					for(i=0;i<Tape.length();i++)
						{
						tape.addValue(Tape.charAt(i)+"",0);
						tape.moveR(0);
						}
					for(i=0;i<Tape.length();i++)	tape.moveL(0);			
				Controller.stop=0;

//			TextEditor.messbox.append("(6): "+SEStates.charAt(0)+", "+SEStates.charAt(1)+"\n");
			states.Remove(SEStates.charAt(0)+"2");
			states.Insert(SEStates.charAt(0)+"0");
			TextEditor.StateView2.setText(""+SEStates.charAt(0));
			states.Remove(SEStates.charAt(1)+"2");
			states.Insert(SEStates.charAt(1)+"1");

//			TextEditor.messbox.append("(6): "+InitTapePos+"\n");
	// String to int:
			InitTapePosINT=0;
			for(i=0; i<InitTapePos.length(); i++)
				{
				pow10=1;
				for(j=i;j<InitTapePos.length()-1;j++)	pow10=pow10*10;
				temp=pow10*(int)(InitTapePos.charAt(i)-'0');
				InitTapePosINT=InitTapePosINT+temp;
				}
			if(TMmode==2)	TextEditor.machPos.setText(""+InitTapePosINT);
			else	TextEditor.machPos.setText("0");
	/////////////

			for(i=0;i<InitTapePosINT;i++)	tape.moveR(0);
			tape.PrintL();
			tape.PrintR();

//			TextEditor.messbox.append("(*): "+Commands+"\n");
			TextEditor.commands.setText(Commands);	
			}
		return 1;
		}

/**
 * Kataskeuazei ena arxeio me to onoma pou anaparistatai sto String StrSave kai grafei ta dedomena Mhxanhs Turing.
 * Dexetai ws eisodo to Sring StrSave, kai ton akeraio TMmode, pou upodhlwnei an 
 * h Mhxanh Turing einai basikh h genikeumenh.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_SaveF_String_int
 * <BR>
 * <B>Parameter:</B> StrOpen einai to onoma tou arxeiou pros anoigma h dimiourgia gia egrafh
 * <BR>
 * <B>Parameter:</B> TMode akeraios pou upodhlwnei to mode ths Mhxanhs Turing
 * <BR>
 * <B>Pre:</B> TMmode einai akeraios (1 gia basikh TM, 2 gia genikeumenh TM)
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 0 sthn apotuxia, h 1 sthn epituxia ekteleshs ths me8odou 
 */
	public int SaveF(String StrSave, int TMmode)
		{
		String Tmp = new String();
		String MachName = new String();
		String MachNameFix = new String();
		String TmpStat = new String();
		String TmpAlph = new String();
		String Tape_InitTapePos = new String();
		String SEStates = new String();
		String Commands = new String();
		String InitTapePos = new String();
		String MachStrFix = new String();
		String MachPosFix = new String();
		int i, j, l, k, w1, w2, pow10, temp;

// Initializing Strings...
		Tmp ="";
		MachName ="";
		TmpStat ="";
		TmpAlph ="";
		Tape_InitTapePos ="";
		SEStates ="";
		Commands ="";
		InitTapePos ="";
		MachNameFix ="";
		MachStrFix ="";
		MachPosFix ="";

//	Refreshing last tape data...
		tape.PrintL();
		tape.PrintR();

//	Checking TM data...

	TextEditor.messbox.append("Checking TM data...\n");
	//	TM mode...
		if((TMmode!=1)&&(TMmode!=2))
				{
				TextEditor.messbox.append("Error	: Unreachable TMmode error\n");
				return 0;
				}

	// Probably fix of 'Machine Name'...
		MachName=TextEditor.machName.getText();
		MachNameFix="";
		for(w1=0;w1<MachName.length();w1++)	if(MachName.charAt(w1)!='\n')	MachNameFix = MachNameFix + MachName.charAt(w1);
		if (MachName.length()!=MachNameFix.length())
			{
			MachName=MachNameFix;
			TextEditor.machName.setText(MachName);
			TextEditor.messbox.append("Warning	: 'Machine Name' fixed\n"); 
			}
	////////////////////////////////////
		if (MachName.length()>15)
				{
				TextEditor.messbox.append("Error	: 'Machine Name' is too big\n");
				return 0;
				}

	// TM States Set...
		if (states.checkEmpty()==1)
				{
				TextEditor.messbox.append("Error	: The States set is empty (nothing to save)\n");
				return 0;
				}
		else	TmpStat = states.getSet();

	// TM Alphabet Set...
		if (alphabet.checkEmpty()==1)
				{
				TextEditor.messbox.append("Error	: The Alphabet set is empty (nothing to save)\n");
				return 0;
				}
		else	TmpAlph = alphabet.getSet();

	//	'Initial Tape String' check...
		// Probably fix of 'Initial Tape String'...
		Tape=TextEditor.machStr.getText();
		MachStrFix="";
		for(w1=0;w1<Tape.length();w1++)	if(Tape.charAt(w1)!='\n')	MachStrFix = MachStrFix + Tape.charAt(w1);
		if (Tape.length()!=MachStrFix.length())
			{
			Tape=MachStrFix;
			TextEditor.machStr.setText(Tape);
			TextEditor.messbox.append("Warning	: 'Initial Tape String' fixed\n"); 
			}
		////////////////////////////////////
		for(w1=0;w1<Tape.length();w1++)
			{
			if (alphabet.checkExist(""+Tape.charAt(w1))==0)
				{
				TextEditor.messbox.append("Error	: Symbol '"+Tape.charAt(w1)+"'in 'Initial Tape String' does not exist in the Alphabet\n");
				return 0;
				}
			}

	//	'Initial Tape Position' check...
		InitTapePos=TextEditor.machPos.getText();
		MachPosFix="";
		// Probably fix of 'Initial Tape Position'...
		for(w1=0;w1<InitTapePos.length();w1++)	if(InitTapePos.charAt(w1)!='\n')	MachPosFix = MachPosFix + InitTapePos.charAt(w1);
		if (MachPosFix.length()!=MachPosFix.length())
			{
			InitTapePos=MachPosFix;
			TextEditor.machStr.setText(InitTapePos);
			TextEditor.messbox.append("Warning	: 'Initial Tape Position' fixed\n"); 
			}
		/////////////////////////////////////////////
		for(w1=0;w1<InitTapePos.length();w1++)
			{
			if ((InitTapePos.charAt(w1)<'0')||(InitTapePos.charAt(w1)>'9'))
				{
				TextEditor.messbox.append("Error	: 'Initial Tape Position' does not represent an integer\n");
				return 0;
				}
			}
		// String to int:
//		System.out.println("InitTapePos = "+InitTapePos);
		InitTapePosINT=0;
		for(w1=0; w1<InitTapePos.length(); w1++)
			{
			pow10=1;
			for(w2=w1;w2<InitTapePos.length()-1;w2++)	pow10=pow10*10;
			temp=pow10*(int)(InitTapePos.charAt(w1)-'0');
			InitTapePosINT=InitTapePosINT+temp;
			}
		/////////////
		if (InitTapePosINT>1000000)
			{
			TextEditor.messbox.append("Error	: 'Initial Tape Position' is a big integer\n");
			return 0;
			}

	//	TM Start-End State...
	SEStates=""+states.getState('0');
	if (SEStates.charAt(0)=='-')
		{
		TextEditor.messbox.append("Error	: Initial state has not been defined\n");
		return 0;
		}

	SEStates=SEStates+states.getState('1');
	if (SEStates.charAt(1)=='-')
		{
		TextEditor.messbox.append("Error	: Final state has not been defined\n");
		return 0;
		}

	//	TM Command fix for save...
		Commands=TextEditor.commands.getText();
		j=0;
		l=Commands.length();
		for(i=0;i<l-1;i++) if(Commands.charAt(i)!='\n') break;

		for(i=0;i<=l-1;i++)
			{
			if(Commands.charAt(i)=='\n') j++;				
			}
		if (l>=1)
			{
			for(i=l-1;(i>=0)&&(Commands.charAt(i)=='\n');i--);
			j=j-(l-i)+1;
			}
		String tmp[] = new String[j+1];
		tmp = Commands.split("\n",j+1);

       	if (l!=0)
			{
			for(i=0;i<=tmp[j].length()-1;i++) if(tmp[j].charAt(i)=='\n')
				{
				tmp[j] = tmp[j].substring(0,i);
				}
			}

//	Saving file data....
		try
			{
			PrintWriter out= new PrintWriter( new FileWriter(StrSave));

//			TextEditor.messbox.append("(1): "+TMmode+"\n");
			if (TMmode==1)	out.println("Basic");
			else if (TMmode==2)	out.println("Generalised");

//			TextEditor.messbox.append("(2): "+MachName+"\n");
			out.println(MachName);

//			TextEditor.messbox.append("(3): "+TmpStat+"\n");
			out.println(TmpStat);

//			TextEditor.messbox.append("(4): "+TmpAlph+"\n");
			out.println(TmpAlph);

//			TextEditor.messbox.append("(5): "+Tape+"\n");
			out.println(Tape);

//			TextEditor.messbox.append("(6): "+SEStates+"\n");

//			TextEditor.messbox.append("(6): "+InitTapePosINT+"\n");
			if(TMmode==1)	out.println(SEStates.charAt(0)+" "+SEStates.charAt(1));			
			else if(TMmode==2)	out.println(SEStates.charAt(0)+" "+InitTapePosINT+" "+SEStates.charAt(1));	

//			TextEditor.messbox.append("(*): "+Commands+"\n");
			for(i=0;i<=j;i++)	out.println(tmp[i]);
			out.close();
			}
		catch(IOException ex4)
			{
//			System.err.println("Error	: I/O error");
			TextEditor.messbox.append("Error	: I/O error\n");
			return 0;
			}

		return 1;
		}

/**
 * Anoigei ena arxeio me to onoma pou anaparistatai sto String StrOpen,
 * kai elegxei an ta dedomena tou aforoun mia Mhxanh Turing, kai
 * an aforoun Mhxanh Turing eksetazei ti tupou einai h TM(basikh h gennikeumenh).
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B>  int_CheckFileInfo_String
 * <BR>
 * <B>Parameter:</B> StrOpen einai to onoma tou arxeiou pros anoigma gia anagnwsh
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 0 sthn apotuxia ekteleshs algori8mou kai
 * otan ta dedomena den aforoun Mhxanh Turing, epistrofh akeraiou me timh 1 an to arxeio me onoma StrChck
 * periaxei dedomena pou aforoun basikh Mhxanh Turing, h thn timh 2 an aforoun
 * genikeumenh Mhxanh Turing 
 */
	public int CheckFileInfo(String StrChck)
		{
		int i, j, k, lines=0, mode = 0, flag=0, EndOfInt;
		BufferedReader Inpt=null;
		String MachName = new String();
		String TmpStat = new String();
		String TmpAlph = new String();
		String SEStates = new String();
		String Commands = new String();
		String InitTapePos = new String();

// Initializing Strings...
		MachName = "";
		TmpStat = "";
		TmpAlph = "";
		Tape = "";
		SEStates = "";
		Commands = "";
		InitTapePos = "";

// Checking file permitions and file structure....
		if (StrChck.compareTo("")==0)	return 0;
		else TextEditor.messbox.append("Checking file info...\n");
		try
			{
			Inpt= new BufferedReader( new FileReader(StrChck));
			}
		catch (FileNotFoundException ex1)
			{
//			System.err.println("Error	: file \""+StrChck+"\" not found!");
			TextEditor.messbox.append("Error	: file \""+StrChck+"\" not found\n");
			return 0;
			}
		catch (IOException ex2)
			{
//			System.err.println("Error : read error");
			TextEditor.messbox.append("Error	: read error\n");
			return 0;
			}

		try
			{
			String FileIn=new String();

// Checking file data....

			for(lines=1;(FileIn=Inpt.readLine())!=null;lines++)
				{
				if (lines==1)
					{
					if (FileIn.compareTo("Basic")==0)
						{
						mode=1;
//						TextEditor.messbox.append("Line 1 ->TM Mode	: Bacic\n");
						}
					else if (FileIn.compareTo("Generalised")==0)
						{
						mode=2;
//						TextEditor.messbox.append("Line 1 ->TM Mode	: Generalised\n");
						}
					else
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 1st line of input file\n");
						TextEditor.messbox.append("       	  (must be \"Basic\" or \"Generalised\")\n");
						break;
						}
					}
				if (lines==2)
					{
					if (FileIn.length()>15)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 2nd line of input file\n");
						TextEditor.messbox.append("       	  (too big string for 'Machine Name')\n");
						break;
						}
//					else	TextEditor.messbox.append("Line 2 ->TM Name	: "+FileIn+"\n");
					}
				if (lines==3)
					{
					for(j=0;j<FileIn.length();j++)
						{
						if (((j%2)==0)&&(FileIn.charAt(j)==' '))	flag=1;
						if (((j%2)!=0)&&(FileIn.charAt(j)!=' '))	flag=1;
						}
					if(flag==1)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 3nd line of input file\n");
						TextEditor.messbox.append("       	  (must be in the form \"* * *...\")\n");
						break;
						}
					else
						{
						for(j=0;j<FileIn.length();j++)
							{
							if((j%2)==0)	TmpStat=TmpStat+FileIn.charAt(j); 
							}
//						TextEditor.messbox.append("Line 3 ->TM States	: ");
//						for(j=0;j<TmpStat.length();j++)
//							{
//							TextEditor.messbox.append(""+TmpStat.charAt(j));
//							if(j!=TmpStat.length()-1)	TextEditor.messbox.append(", ");
//							else	TextEditor.messbox.append("\n");
//							}
//						if(j==0)	TextEditor.messbox.append("\n");
						}
					}
				if (lines==4)
					{
					flag=0;
					for(j=0;j<FileIn.length();j++)
						{
						if(mode==1)	if (((j%2)==0)&&((FileIn.charAt(j)=='R')||(FileIn.charAt(j)=='L')))	flag=2;
						}
					for(j=0;j<FileIn.length();j++)
						{
						if (((j%2)==0)&&(FileIn.charAt(j)==' '))	flag=1;
						if (((j%2)!=0)&&(FileIn.charAt(j)!=' '))	flag=1;
						}
					if(flag==1)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 4th line of input file\n");
						TextEditor.messbox.append("       	  (must be in the form \"* * *...\")\n");
						break;
						}
					else if(flag==2)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 4th line of input file\n");
						TextEditor.messbox.append("       	  (must not have the symbols: 'R' 'L')\n");
						break;
						}
					else
						{
						for(j=0;j<FileIn.length();j++)	if((j%2)==0)	TmpAlph=TmpAlph+FileIn.charAt(j);
//						TextEditor.messbox.append("Line 4 ->TM Alphabet	: ");
//						for(j=0;j<TmpAlph.length();j++)
//							{
//							TextEditor.messbox.append(""+TmpAlph.charAt(j));
//							if(j!=TmpAlph.length()-1)	TextEditor.messbox.append(", ");
//							else	TextEditor.messbox.append("\n");
//							}
						}
					}
				if (lines==5)
					{
					flag=0;
					for(j=0;j<FileIn.length();j++)
						{
						for(k=0;k<TmpAlph.length();k++)
							{
							if(FileIn.charAt(j)=='#')	break;
							else if(FileIn.charAt(j)==TmpAlph.charAt(k)) break;
							}
						if(k==TmpAlph.length()) flag=2;
						}
					for(j=0;j<FileIn.length();j++)
						{
						if (((j%2)==0)&&((FileIn.charAt(j)=='R')||(FileIn.charAt(j)=='L')||(FileIn.charAt(j)==' ')))	flag=1;
						}
					if(flag==1)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 5th line of input file\n");
						TextEditor.messbox.append("       	  (must not have the symbols: 'R' 'L' or ' ')\n");
						break;
						}
					else if(flag==2)
						{
						mode=0;
						TextEditor.messbox.append("Error	: at 5th line of input file\n");
						TextEditor.messbox.append("       	  (some symbols does not exit in the Alphabet)\n");
						break;
						}
					else
						{
						for(j=0;j<FileIn.length();j++)	Tape=Tape+FileIn.charAt(j);
//						TextEditor.messbox.append("Line 5 ->TM Tape	: "+Tape+"\n");
						}
					}
				if (lines==6)
					{
					if (mode==1)
						{
						if (FileIn.length()<3)
							{
							mode=0;
							TextEditor.messbox.append("Error	: at 6th line of input file\n");
							TextEditor.messbox.append("       	  (something is missing)\n");
							break;
							}
						if (FileIn.length()>3)
							{
							for(j=3;j<FileIn.length();j++)	if(FileIn.charAt(j)!=' ')	break;
							if (j!=FileIn.length())
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (too many state symbols)\n");
								break;
								}
							}

						if (FileIn.charAt(1)!=' ')
							{
							mode=0;
							TextEditor.messbox.append("Error	: at 6nd line of input file\n");
							TextEditor.messbox.append("       	  (must be in the form \"* *\")\n");
							break;
							}
						else if((FileIn.charAt(0)==' ')||(FileIn.charAt(2)==' '))
							{
							mode=0;
							TextEditor.messbox.append("Error	: at 6th line of input file\n");
							TextEditor.messbox.append("       	  (something is missing)\n");
							break;
							}
						else if((FileIn.charAt(0)=='#')||(FileIn.charAt(2)=='#'))
							{
							mode=0;
							TextEditor.messbox.append("Error	: at 6th line of input file\n");
							TextEditor.messbox.append("       	  (must not have the symbol: '#')\n");
							break;
							}
						else
							{
							for(k=0;k<TmpStat.length();k++)	if(FileIn.charAt(0)==TmpStat.charAt(k)) break;
							if (k==TmpStat.length())
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  ('start' symbol does not exit in the Alphabet)\n");
								break;
								}
							for(k=0;k<TmpStat.length();k++)	if(FileIn.charAt(2)==TmpStat.charAt(k)) break;
							if (k==TmpStat.length())
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  ('end' symbol does not exit in the Alphabet)\n");
								break;
								}
							else if (FileIn.charAt(0)==FileIn.charAt(2))
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (S/E symbols are same)\n");
								break;
								}
							else
								{
								SEStates=""+FileIn.charAt(0)+FileIn.charAt(2);
//								TextEditor.messbox.append("Line 6 ->TM S/E Sts	: "+SEStates.charAt(0)+", "+SEStates.charAt(1)+"\n");
								}														
							}
						}
					else if (mode==2)
						{
						if (FileIn.length()<3)
							{
							mode=0;
							TextEditor.messbox.append("Error	: at 6th line of input file\n");
							TextEditor.messbox.append("       	  (something is missing)\n");
							break;
							}
						if (FileIn.length()>=3)
							{
							if (FileIn.charAt(1)!=' ')
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6nd line of input file\n");
								TextEditor.messbox.append("       	  (must be in the form \"* * *\")\n");
								break;
								}
							for(EndOfInt=2;EndOfInt<FileIn.length();EndOfInt++)	if((FileIn.charAt(EndOfInt)<'0')||(FileIn.charAt(EndOfInt)>'9'))	break;
							if (EndOfInt==2)
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (there is not 'Init Tape Poss')\n");
								break;
								}
							if (FileIn.length()<=EndOfInt+1)
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6nd line of input file\n");
								TextEditor.messbox.append("       	  (something is missing)\n");
								break;
								}
							if (FileIn.charAt(EndOfInt)!=' ')
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6nd line of input file\n");
								TextEditor.messbox.append("       	  (must be in the form \"* * *\")\n");
								break;
								}
							for(j=EndOfInt+2;j<FileIn.length();j++)	if(FileIn.charAt(j)!=' ')	break;
							if (j!=FileIn.length())
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (too many state symbols)\n");
								break;
								}

							if((FileIn.charAt(0)==' ')||(FileIn.charAt(EndOfInt+1)==' '))
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (something is missing)\n");
								break;
								}

							else if((FileIn.charAt(0)=='#')||(FileIn.charAt(EndOfInt+1)=='#'))
								{
								mode=0;
								TextEditor.messbox.append("Error	: at 6th line of input file\n");
								TextEditor.messbox.append("       	  (must not have the symbol: '#')\n");
								break;
								}
							else
								{
								for(k=0;k<TmpStat.length();k++)	if(FileIn.charAt(0)==TmpStat.charAt(k)) break;
								if (k==TmpStat.length())
									{
									mode=0;
									TextEditor.messbox.append("Error	: at 6th line of input file\n");
									TextEditor.messbox.append("       	  ('start' symbol does not exit in the Alphabet)\n");
									break;
									}
								for(k=0;k<TmpStat.length();k++)	if(FileIn.charAt(EndOfInt+1)==TmpStat.charAt(k)) break;
								if (k==TmpStat.length())
									{
									mode=0;
									TextEditor.messbox.append("Error	: at 6th line of input file\n");
									TextEditor.messbox.append("       	  ('end' symbol does not exit in the Alphabet)\n");
									break;
									}
								else if (FileIn.charAt(0)==FileIn.charAt(EndOfInt+1))
									{
									mode=0;
									TextEditor.messbox.append("Error	: at 6th line of input file\n");
									TextEditor.messbox.append("       	  (S/E symbols are same)\n");
									break;
									}
								else
									{
									SEStates=""+FileIn.charAt(0)+FileIn.charAt(EndOfInt+1);
									for(k=2;k<=EndOfInt;k++) InitTapePos=InitTapePos+FileIn.charAt(k);

//									TextEditor.messbox.append("Line 6 ->TM S/E Sts	: "+SEStates.charAt(0)+", "+SEStates.charAt(1)+"\n");
//									TextEditor.messbox.append("Line 6 ->TM InTpPos	: "+InitTapePos+"\n");
									}
								}
							}
						}
					}
//				if(lines>6)
//					{
//					if(lines<10)	TextEditor.messbox.append("Line "+lines+" ->Command	: "+FileIn+"\n");
//					else	TextEditor.messbox.append("Line"+lines+"->Command	: "+FileIn+"\n");
//					}
				}
			if(mode==0)	lines++;
			for(lines--;(FileIn=Inpt.readLine())!=null;lines++);
			Inpt.close();
			}
		catch(IOException ex4)
			{
//			System.err.println("Error	: I/O error");
			TextEditor.messbox.append("Error	: I/O error\n");
			return 0;
			}

		if (lines<6)
			{
			TextEditor.messbox.append("Error	: Insufficient file data"+lines+"\n");
			return 0;
			}
		if (lines==6)	TextEditor.messbox.append("Warning	: There are not commands in the file\n");
		return mode;
		}

/**
 * Anoigei ena arxeio me to onoma tou arxeiou pou hdh exei anoixtei mia fora kata thn
 * ektelesh ths me8odou 'OpenF(String StrOpen, int TMmode)' ths idias klashs. Ean den exei hdh
 * ektelestei mia fora h proanafer8hsa me8odos, tote h 'SaveTape()' apotugxanei kata thn ektelesh ths.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_SaveTape_String_int
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 0 sthn apotuxia, h 1 sthn epituxia ekteleshs ths me8odou 
 */
	public int SaveTape(String StrOpen, int TMmode)
		{
		BufferedReader Inpt=null;
		String NewFile = new String();
		String NewFilePart = new String();
		String TapeFix = new String();

		int i, lines;

		NewFile ="";
		NewFilePart ="";
		TapeFix ="";

		if (StrOpen.compareTo("")==0)	return 0;
		else
			{
			String	Tape_HP= new String();

			//	TM Tape check...
			if(tape.checkTape()==0)	return 0;
			Tape_HP=tape.getTape_HeadPos();
			Tape="";
			for(i=0;Tape_HP.charAt(i)!='#';i++)	Tape=Tape+Tape_HP.charAt(i);

			try
				{
				Inpt= new BufferedReader( new FileReader(StrOpen));
				}
			catch (FileNotFoundException ex1)
				{
//				System.err.println("Error	: File not found");
				TextEditor.messbox.append("Error	: File not found\n");
				return 0;
				}
			catch (IOException ex2)
				{
//				System.err.println("Error	: I/O error");
				TextEditor.messbox.append("Error	: I/O error\n");
				return 0;
				}

			try
				{
				String FileIn=new String();

//	Taking file data....
				for(lines=1;(FileIn=Inpt.readLine())!=null;lines++)
					{
					if (lines!=5)	NewFile = NewFile + FileIn + "\n";
					else
						{
						NewFile = NewFile + Tape + "\n";
						}
					}
				Inpt.close();
				}
			catch(IOException ex4)
				{
//				System.err.println("Error	: I/O error");
				TextEditor.messbox.append("Error	: I/O error\n");
				return 0;
				}

			try
				{
				PrintWriter out= new PrintWriter( new FileWriter(StrOpen));
				for(i=0;i<NewFile.length();i++)
					{
					if (NewFile.charAt(i)=='\n')
						{
						out.println(NewFilePart);
						NewFilePart ="";
						}
					else	NewFilePart = NewFilePart + NewFile.charAt(i);
					}

				out.close();
				}
			catch(IOException ex4)
				{
//				System.err.println("Error	: I/O error");
				TextEditor.messbox.append("Error	: I/O error\n");
				return 0;
				}
			}
//	Tape fix...
		TapeFix="";
		for(i=0;i<Tape.length();i++)
			{
			if(Tape.charAt(i)==' ')	TapeFix = TapeFix + '#';
			else TapeFix = TapeFix + Tape.charAt(i);
			}
		Tape=TapeFix;
		TextEditor.machStr.setText(Tape);
		return 1;
		}
	}
