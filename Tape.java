import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth h klash dhmiourgei mia tainia Mhxanhs Turing (me dipla sundedemenh lista),
 * kai exei thn dunatothta na dhmiourgei/diagragei kombous me diplh sundesh
 * deiktwn, opws epishs kai thn eisagwgh/diagrafh stoixeiwn tupou Object mesa
 * se autous. O prwtos kai teleutaios kombos exoun deksia kai aristera null
 * pointers antistoixa. Mporei ekswterika na metakini8ei o pointer deksia h aristera
 * panw shn tainia.
 */
public class Tape
	{
	int tcells, j, i=0;
	public int counter=-1;
	private Alphabet talphabet;
	Object value;
	Cell Head = null;
	Cell TMhead = null;
	Cell Tail = null;
	Cell tmp = new Cell();

/**
 * Kataskeuazei mia dipla sundedemenh lista me null pointers arxhs kai telous.
 */
	public Tape(Alphabet alphabet)
		{
		talphabet = alphabet;
		}

/**
 * Kataskeuazei mia dipla sundedemenh lista sugekrimenou mhkous me null pointers arxhs kai telous.
 * <BR>
 * <B>Parameter:</B> cells o akeraios pou dhlwnei to apxiko mhkos kataskeuhs ths dipla sundedemenhs listas.
 * <BR>
 * <B>Pre:</B> cells einai 8etikos akeraios.
 * <BR>
 */
	public Tape(Alphabet alphabet, int cells)
		{
		talphabet = alphabet;
		tcells = cells;
//		if(cells<0) System.out.println("Prepei Cells>=0");
		
		Cell nodei = new Cell();
		Head = nodei;
		Tail = nodei;
//		Head.value= "t";
		Head.value= " ";
	
		for(j=0;j<=cells-2;j++)
				{
				Cell temp = new Cell();

				temp.value=Head.value;
//				Head.value= "t";
				Head.value= " ";
				if(i==0)
					{
					Tail = temp;
					Tail.prev = Head;
					Head.next = Tail;
					i=1;
					}
					else
					{
					temp.prev = Head;
					temp.next = Head.next;
	 				Head.next.prev = temp;
					Head.next = temp;
					}
				
				}
				
			Cell tmp = new Cell();
			tmp = Head;
			TMhead = tmp;
//			TMhead.value = ">";
			TMhead.value = " ";
		}


/**
 * Elegxei an olo to periexomeno ths tainias apoteleite apo sumbola tou alfabhtou..
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_Tchecktape_void 
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an ola ta sumbola ths tainias einai sumbola 
 * tou alfabhtou, h 0 an uparxei kapoio sumbolo ths tainias pou den anhkei sto alfabhto.
 */

public int checkTape()
	{
	Cell tmp = new Cell();
	
	tmp = Head;
	while (tmp!=Tail)
		{
		if (tmp.value.toString().charAt(0)==' ')
			{
			tmp = tmp.next;
			continue;
			}

		if (tmp.value.toString().charAt(0)=='#')
			{
			TextEditor.messbox.append("Error	: Tape error\n");
			TextEditor.messbox.append("       	  (Symbol '#' never exists (as a symbol) in the Alphabet)\n");
			return 0;
			}
		if(talphabet.checkExist(tmp.value)==0)
			{
			TextEditor.messbox.append("Error	: Tape error\n");
			TextEditor.messbox.append("       	  (Symbol '"+tmp.value.toString().charAt(0)+"'in tape does not exist in the Alphabet)\n");
			return 0;
			}

		tmp=tmp.next;
		}

	return 1;
	}

/**
 * Kataskeuazei enan kainourgio adeio kombo, sthn dipla sundedemenh lista,
 * meta ton trexwn komdo.
 * <BR>
 * <B>Type:</B> Mutative Tranformer
 * <BR>
 * <B>Signature:</B> void_newNodeR_void
 * <BR>
 */
	public void newNodeR()
		{
		if (TMhead == Tail)
			{
			Cell tmp = new Cell();
// 	    	tmp.value = "N";
 	    	tmp.value = " ";
			tmp.prev = TMhead;
			TMhead.next = tmp;
			Tail = tmp;
			Tail.prev = TMhead;
			}
		else	
			{
			Cell tmp = new Cell();
// 	    	tmp.value = "N";
 	    	tmp.value = " ";
			tmp.next = TMhead.next;
			tmp.prev = TMhead;
			TMhead.next.prev = tmp;
			TMhead.next = tmp;
			}
		}
/**
 * Kataskeuazei enan kainourgio kombo, sthn dipla sundedemenh lista,
 * prin ton trexwn komdo.
 * <BR>
 * <B>Type:</B> Mutative Tranformer
 * <BR>
 * <B>Signature:</B> void_newNodeL_void
 */
	public void newNodeL()
		{
		if (TMhead == Head)
			{
			Cell tmp = new Cell();
// 	    	tmp.value = "N";
 	    	tmp.value = " ";
			tmp.next = TMhead;
			TMhead.prev = tmp;
			Head = tmp;
			Head.next = TMhead;
			}
		else	
			{
	    	Cell tmp = new Cell();
// 	    	tmp.value = "N";
 	    	tmp.value = " ";
			tmp.prev = TMhead.prev;
			TMhead.prev.next = tmp;
			tmp.next = TMhead;
			TMhead.prev = tmp;   
			}
		}

/**
 * Metakinei thn kefalh ths mhxanhs Turing kata mia thesi deksia.
 *
 * <BR>
 * <B>Type:</B> Mutative Tranformer
 * <BR>
 * <B>Signature:</B> void_moveR_int
 * <BR>
 * <B>Parameter:</B> refresh kaleitai me 1 an theloume na ginetai 
 * ananewsh ths tainias sthn o8onh.
 */


public void moveR(int refresh)
	{
	Cell tmp = new Cell();

	if (TMhead!=Tail)
		{
		TMhead = TMhead.next;
		}
	else
		{
		newNodeR();
		TMhead = TMhead.next;
		}

	tmp = TMhead;
	for(j=1;j<8;j++)
		{
		if (TMhead!=Tail)
			{
			TMhead = TMhead.next;
			}
			else
			{
			newNodeR();
			TMhead = TMhead.next;
			}
		}
	TMhead = tmp;

	if (TMachine.TMmodeID==1)
		{
		for(i=7;i>=1;i--)
			{
// TM is stopped... (else) is running...
			if (Controller.stop==0)
				{
				if (TextEditor.cellj[i].getBackground().getRGB()==Color.LIGHT_GRAY.getRGB())
					{
					if (refresh==1)
						{
						TextEditor.cellj[i].setBackground(Color.WHITE);
						TextEditor.cellj[i].setEditable(true);
						}
					break;
					}
				}
			else if (Controller.stop==1)
				{
				if (TextEditor.cellj[i].getBackground().getRGB()==Color.LIGHT_GRAY.getRGB())
					{
					if (refresh==1)
						{
						TextEditor.cellj[i].setBackground(Color.CYAN);
						TextEditor.cellj[i].setEditable(false);
						}
					break;
					}
				}
			}

	if (refresh==1)
		{
// Flag 'counter' for 'editable'-'not editable' cells...
			for(i=1;i<=7;i++)	if (TextEditor.cellj[i].getBackground().getRGB()==Color.LIGHT_GRAY.getRGB())	break;

			if(i==8)	counter++;
			else	counter=0;
			}
		}
	if (refresh==1)	
		{	
		PrintL();
		PrintR();
		}
	}
	
/**
 * Metakinei thn kefalh ths mhxanhs Turing kata mia thesi aristera.
 * <BR>
 * <B>Type:</B> Mutative Tranformer
 * <BR>
 * <B>Signature:</B> void_moveL_int
 * <BR>
 * <B>Parameter:</B> refresh kaleitai me 1 an theloume na ginetai 
 * ananewsh ths tainias sthn o8onh. 
 */
		
	public void moveL(int refresh)
	{
	Cell tmp = new Cell();
	if (TMhead!=Head)
		{
		TMhead = TMhead.prev;
		}
	else
		{
		if (TMachine.TMmodeID==1)
			{
			TextEditor.messbox.append("Warning	: TM Head can not move left, head's position at the start of tape\n");
			return;
			}
		else 
			{
			newNodeL();
			TMhead = TMhead.prev;
			}
		}

	if (TMachine.TMmodeID==2)
		{
		tmp = TMhead;
		for(j=1;j<8;j++)
			{
			if (TMhead!=Head)
				{
				TMhead = TMhead.prev;
				}
				else
				{
				newNodeL();
				TMhead = TMhead.prev;
				}
			}
		TMhead = tmp;
		}

	if (TMachine.TMmodeID==1)
		{
		if(counter==1)	if (refresh==1) counter=0;
		if (counter==0)
			{
			for(i=1;i<=7;i++)
				{
// TM is stopped... (else) is running...
				if (Controller.stop==0)
					{
					if (TextEditor.cellj[i].getBackground().getRGB()==Color.WHITE.getRGB())
						{
						if (refresh==1)
							{
							TextEditor.cellj[i].setBackground(Color.LIGHT_GRAY);
							TextEditor.cellj[i].setEditable(false);
							}
						break;
						}
					}
				else if (Controller.stop==1)
					{
					if (TextEditor.cellj[i].getBackground().getRGB()==Color.CYAN.getRGB())
						{
						if (refresh==1)
							{
							TextEditor.cellj[i].setBackground(Color.LIGHT_GRAY);
							TextEditor.cellj[i].setEditable(false);
							}
						break;
						}
					}
				}
			}
		else
			{
			if (refresh==1) counter--;
			}
		}
	if (refresh==1)	
		{
		PrintL();
		PrintR();
		}
	}


/**
 * Topo8etei ena stoixeio tupou Object mesa ston trexwn kombo.
 * <BR>
 * <B>Type:</B> Mutative Tranformer
 * <BR>
 * <B>Signature:</B> void_addValue_Object_int
 * <BR>
 * <B>Parameter:</B> in stoixeio eisodou  ston kombo.
 * <BR>
 * <BR>
 * <B>Parameter:</B> refresh kaleitai me 1 an theloume na ginetai 
 * ananewsh ths tainias sthn o8onh. 
 * <BR>
 * <B>Pre:</B> in einai Object (einai monaxa enas xarakthras)
 */
	public void addValue(Object in, int refresh)
		{
		if (TMhead == null)	return;
		else TMhead.value = in;
		if (refresh==1)	
			{
			PrintL();
			PrintR();		
			}
		}


/**
 * Pairnei ena stoixeio tupou Object apo ton trexwn kombo.
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> Object_getValue_void
 * <BR>
 * <B>Post:</B> epistrefei mia timh tupou Object
 */
	public Object getValue()
		{
		Object Value;
		TextEditor.cellj[8].setCaretPosition(0);
		if (TMhead==null)	return "Error";
		else
			{
			Value = TMhead.value;
			return Value;
			}
		}


/**
 * Kanei reset sthn dipla sunedemenh lista, adeiazontas
 * to periexomeno olwn twn kombwn.
 * <BR>
 * <B>Type:</B> Mutative Transformer
 * <BR>
 * <B>Signature:</B> void_resetTape_void
 */
public void resetTape()
	{
	Cell tmp = new Cell();
	Cell tmp_ = new Cell();
		
	if (Head==null)	return;
	else
		{
		tmp = Head;
//		tmp.value = "r";
		tmp.value = " ";
		}

	for(j=0;j<=tcells-2;j++)
		{
		tmp = tmp.next;
//		tmp.value = "r";
		tmp.value = " ";
		}
	Tail = tmp;
	tmp.next = null;

	tmp = Head;
	TMhead = Head;
	if(TMachine.TMmodeID==2)	for(j=1;j<=7;j++)	TMhead=TMhead.next;
	TMhead.value = ">";
	TMhead.value = " ";

	PrintL();
	PrintR();	
	}

/**
 * Epistrefei se ena String thn tainia kai thn thesi ths kefalhs panw se auth..
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> String_getTape_void
 */

public String getTape_HeadPos()
	{
	String Str_Tape = new String();
	String FixedStr_Tape = new String();
	Str_Tape="";
	FixedStr_Tape="";
	Cell tmp = new Cell();
	int i, diff, diff2, HP=0;
	
	if (Head==null)	return " #0";
	tmp=Head;

	i=0;
	while (tmp!=null)
		{
		i++;
		if(tmp==TMhead)	HP=i;
		Str_Tape+=tmp.value;
		tmp=tmp.next;
		}

// Starting ' '....	
	for(i=0;i<Str_Tape.length();i++)	if(Str_Tape.charAt(i)!=' ')	break;
	diff=i;
	if(diff==Str_Tape.length())	return " #0";
	for(i=diff;i<Str_Tape.length();i++)	FixedStr_Tape=FixedStr_Tape+Str_Tape.charAt(i);
	HP=HP-(diff+1);
	if(HP<0)	HP=0;

// Ending ' '....
	for(i=FixedStr_Tape.length()-1;i>0;i--)	if(FixedStr_Tape.charAt(i)!=' ')	break;
	diff2=FixedStr_Tape.length()-1-i;

	FixedStr_Tape="";

	for(i=diff;i<Str_Tape.length()-diff2;i++)	FixedStr_Tape=FixedStr_Tape+Str_Tape.charAt(i);

	return FixedStr_Tape+'#'+HP;
	}

/**
 * Typwnei ta stoixeia ths tainias 7 kelia aristera apo thn trexwn thesi ths kefalhs.
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> void_PrintL_void
 */

public void PrintL()
	{
	if (Head == null)	return;
	tmp = TMhead;

	TextEditor.cellj[8].setText(""+tmp.value);
	for(j=7; j>=1; j--)
		{
		if (tmp.prev!=null)
			{
			tmp=tmp.prev;
			TextEditor.cellj[j].setText(tmp.value+"");
			}
		else
			{
//			TextEditor.cellj[j].setText("C");
			TextEditor.cellj[j].setText(" ");
			}
		}
	}

/**
 * Typwnei ta stoixeia ths tainias 7 kelia deksia apo thn trexwn thesi ths kefalhs.
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> void_PrintL_void
 */

public void PrintR()
	{
	int j;

	if (Head == null)	return;
	tmp = TMhead;

	for(j=9; j<=15; j++)
		{
		if (tmp.next!=null)
			{
			tmp=tmp.next;
			TextEditor.cellj[j].setText(tmp.value+"");
			}
		else
			{
//			TextEditor.cellj[j].setText("C");
			TextEditor.cellj[j].setText(" ");
			}
		}
	}	

/**
 * Eswterikh klash ths klashs Tape, pou anaparista ena keli Mhxanhs Turing.
 * Dhladh ena kombo me deksio kai aristero pointer.
 */
class Cell
	{
	public Object value;
	Cell next;
	Cell prev;
	}
	}





