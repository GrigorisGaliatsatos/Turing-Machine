import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

/**
 * Auth einai h klash pou dhmiourgei ena sunolo anaparastashs katastasewn mias Mhxanhs Turing.
 * Apoteleitai apo ena sunolo stoixeiwn Object. To sunolo epekteinetai/allazei
 * me thn boh8eia twn me8odwn ths idias klashs. To ka8e stoixeio periexei kai thn plhroforia
 * an h katastash pou anaparista einai arxikh, endiamesh h telikh katastash ths Turing Machine.
 */


public class States
	{
	int i,j;
	private Vector Set=new Vector();
/**
 * Kataskeuazei ena keno sunolo katastasewn gia Mhxanh Turing.
 */
	public States()
		{
		}


/**
 * Elegxei an to  sunolo katastasewn einai keno sunolo.
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_checkEmpty_void
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 1 to sunolo einai adeio, 
 * h 0 an to sunolo den einai adio.
 */
	public int checkEmpty()
		{
		if(Set.isEmpty()) return 1;
		else return 0;
		}

/**
 * Epistrefei to mege8os tou sunolou katastasewn (Dhladh apo posa stoixeia apoteleitai).
 * <BR>
 * <B>Type:</B> Accessor 
 * <BR>
 * <B>Signature:</B> int_sizeSet_void
 * <BR>
 * <B>Post:</B> epistrofh akeraiou pou dhlwnei to mege8os tou  sunolou katastasewn.
 */
	public int sizeSet()
		{
		return Set.size();
		}

/**
 * Elenxei ean ena symbolo anhkei sto sunolo katastasewn.
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_checkExist_Object
 * <BR>
 * <B>Parameter:</B> insert to stoixeio pou 8eloume na elenksoume an anhkei sto  sunolo katastasewn.
 * <BR>
 * <B>Pre:</B> insert einai Object ths morfhs: "onomasia sumbolou-<keno>,<keno> = xarakthras pou gia 
 * <BR>
 * 0 einai Arxikh h katastash,gia 1 einai Telikh.Oi ypoloipes katastaseis mporoun na exoun otidhpote. "
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an to stoixeio anhkei sto  sunolo katastasewn, 
 * 0 an to stoixeio den anhkei sto  sunolo katastasewn.
 */
	public int checkExist(Object insert)
		{

		for(i=0;i<=(Set.size()-1);i++)
					{
					StringBuffer Vec1 = new StringBuffer(1);
					String Vec2 = new String();
					Vec1.append(Set.elementAt(i));
					Vec2=Vec1.toString();
		
					if (insert.toString().charAt(0)==Vec2.charAt(0)) 
								{
								j=0;
								return 1;
								}
					if ((insert.toString().charAt(1)=='0')&&(Vec2.charAt(1)=='0'))
								{
								j=1;
								return 1;
								}
					if ((insert.toString().charAt(1)=='1')&&(Vec2.charAt(1)=='1')) 
								{
								j=2;
								return 1;
								}
					}
		return 0;
		}

/**
 * Epistrefei thn katastash me ton deikth state, diaforetika an den brethei auth epistrefei ton xarakthra '-'. 
 * <BR>
 * <B>Type:</B> Accesor
 * <BR>
 * <B>Signature:</B> char_getState_char
 * <BR>
 * <B>Parameter:</B> state o deikths ths antistoixhs katastashs.
 * <BR>
 */


public char getState(char state)
	{
		
	for(i=0;i<=Set.size()-1;i++)
			{
			if (Set.get(i).toString().charAt(1)==state)
					{
					return Set.get(i).toString().charAt(0); 		
					}
			}	
//	System.out.println("H katastash me deikth "+state+" den brethike");		
	return '-';

	}


/**
 * Eisagei ena stoixeio apo to sunolo katastasewn.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_Insert_Object
 * <BR>
 * <B>Parameter:</B> insert to stoixeio pou 8eloume na topo8ethsoume sto  sunolo katastasewn.
 * <BR>
 * <B>Pre:</B> insert einai Object ths morfhs: onomasia sumbolou-<keno>,<keno> = xarakthras pou gia 
 * <BR>
 * 0 einai Arxikh h katastash,gia 1 einai Telikh.Oi ypoloipes katastaseis mporoun na exoun otidhpote. "
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an h topo8etish htan epituxhs, h
 * 0 an h topo8etish den htan epituxhs.
 */
	public int Insert(Object insert)
		{
		if (insert.toString().length()!=2) 
					{
	//				System.out.println("Symbolo "+insert.toString().charAt(0)+" Mh apodektou mhkous(2 chars)");
					return 0;
					}					
		else
					{
					if((insert.toString().charAt(0)==' ')||(insert.toString().charAt(0)=='-')) return 0;
					if (checkExist(insert)==1)
								{
								if (j==0)
									{
//									System.out.println("Symbolo "+insert.toString().charAt(0)+" yparxei hdh sto Synolo Katastasewn");
									}
								else if (j==1) 
										{
//										System.out.println("Error : Yparxei hdh h arxikh katastash sto Synolo Katastasewn");
										}
									else if(j==2)
											{
//											System.out.println("Error : Yparxei hdh h telikh katastash sto Synolo Katastasewn");
											}
								return 0;
								}
					else
								{
								Set.add(insert);
//								System.out.println("Symbolo "+insert.toString().charAt(0)+" prostethike sto Synolo Katastasewn");
			   					}  
		}
		return 0;
		}

/**
 * Diagrafei ena symbolo apo to sunolo katastasewn.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_Remove_Object
 * <BR>
 * <B>Parameter:</B> outofset einai to symbolo pou 8eloume na bgaloyme apo to sunolo katastasewn.
 * <BR>
 * <B>Pre:</B> outofset einai Object ths morfhs: onomasia sumbolou-<keno>,<keno> = xarakthras pou gia 
 * <BR>
 * 0 einai Arxikh h katastash,gia 1 einai Telikh.Oi ypoloipes katastaseis mporoun na exoun otidhpote. "
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an h afairesh htan epituxhs, h
 * 0 an h afairesh den htan epituxhs.
 */
	public int Remove(Object outofset)
		{
		if (outofset.toString().length()!=2) 
					{
//					System.out.println("Symbolo "+outofset.toString().charAt(0)+" gia afairesh den einai epitreptou mhkous(2 chars)");
					return 0;
					}
					
		if (checkExist(outofset)==0)
					{
//					System.out.println("Symbolo "+outofset.toString().charAt(0)+" den yparxei sto Synolo Katastasewn");
					return 0;
					}
	    else
					{
					Set.remove(outofset);
//					System.out.println("Symbolo "+outofset.toString().charAt(0)+" afairethike apo to Synolo Katastasewn");
					}   
		return 0;
		}

/**
 * Adeiazei to sunolo katastasewn.
 * <BR>
 * <B>Type:</B> Mutative Transformer
 * <BR>
 * <B>Signature:</B> void_ResetStates_void
 * <BR>
 */

	public void ResetStates()
		{
		Set.clear();
		}

/**
 * Epistrefei ta sumbola tou sunolou katastasewn me kena metaksu tous.
 * <BR>
 * <B>Type:</B> Accessor
 * <BR>
 * <B>Signature:</B> String_getSet_void
 */
	public String getSet()
		{
		int i;
		String Symbols = new String();
		Symbols="";
		
		for(i=0;i<=Set.size()-1;i++)
				{
				if(i!=Set.size()-1) Symbols+=Set.elementAt(i).toString().charAt(0)+" ";
				else Symbols+=Set.elementAt(i).toString().charAt(0);		
				}
		return Symbols;
		}
	}

