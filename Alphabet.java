import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

/**
 * Auth einai h klash pou dhmiourgei ena alfabhto mias Mhxanhs Turing.
 * Apoteleitai apo ena sunolo stoixeiwn Object. To sunolo epekteinetai/allazei
 * me thn boh8eia twn me8odwn ths idias klashs.
 */


public class Alphabet
	{
	private int i,j;
	private Vector Set=new Vector();

/**
 * Kataskeuazei ena alfabhto gia Mhxanh Turing, me monadiko symbolo to keno.
 */
	public Alphabet()
		{
		Set.add("#");
		}


/**
 * Elegxei an to alfabhto den exei alla stoixeia ektos tou kenou.
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_checkEmpty_void
 * <BR>
 * <B>Post:</B> epistrofh akeraiou me timh 1 to sunolo 
 * den exei alla stoixeia ektos tou kenou, h 0 alliws.
 */
	public int checkEmpty()
		{
		if(Set.size()==1) return 1;
		else return 0;
		}

/**
 * Epistrefei to mege8os tou alfabhtou (Dhladh apo posa stoixeia apoteleitai).
 * <BR>
 * <B>Type:</B> Accessor 
 * <BR>
 * <B>Signature:</B> int_sizeSet_void
 * <BR>
 * <B>Post:</B> epistrofh akeraiou pou dhlwnei to mege8os tou alfabhtou.
 */
	public int sizeSet()
		{
		return Set.size();
		}

/**
 * Elenxei ean ena stoixeio anhkei sto alfabhto.
 * <BR>
 * <B>Type:</B> Observer
 * <BR>
 * <B>Signature:</B> int_checkExist_Object
 * <BR>
 * <B>Parameter:</B> insert to stoixeio pou 8eloume na elenksoume an anhkei sto alfabhto.
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an to stoixeio anhkei sto alfabhto, h
 * 0 an to stoixeio den anhkei sto alfabhto.
 */
 
	public int checkExist(Object insert)
		{
		StringBuffer temp = new StringBuffer(1);
		String temp2 = new String();
		temp.append(insert);
		temp2=temp.toString();

		for(i=0;i<=(Set.size()-1);i++) 
				{
				StringBuffer Vec1 = new StringBuffer(1);
				String Vec2 = new String();
				Vec1.append(Set.elementAt(i));
				Vec2=Vec1.toString();

				if(temp2.compareTo(Vec2)==0)
						{
						return 1;
						}

				}
		
		return 0;
		}

/**
 * Eisagei ena stoixeio sto alfabhto.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_Insert_Object
 * <BR>
 * <B>Parameter:</B> insert to stoixeio pou 8eloume na topo8ethsoume sto alfabhto.
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an h topo8etish htan epituxhs, h
 * 0 an h topo8etish den htan epituxhs.
 */
	public int Insert(Object insert)
		{
		if (insert.toString().length()!=1) 
				{
//				System.out.println("Symbolo "+insert+" Mh apodektou mhkous(1)");
				return 0;
				}					
		else
				{
				if ((insert.toString().compareTo("L")==0)||(insert.toString().compareTo("R")==0)) 
						{
						if (TMachine.TMmodeID==1)
							{
//							System.out.println("To Symbolo "+insert+" den einai apodektis morfhs ");
							return 0;
							}
						}
				if (checkExist(insert)==1)
						{
//			   			System.out.println("Symbolo "+insert+" yparxei hdh sto Alphabhto");
						return 0;
						}
			 	else
						{
						Set.add(insert);
//						System.out.println("Symbolo "+insert.toString().charAt(0)+" prostethike sto Alphabhto");
						}  
				}
		return 0;
		}

/**
 * Diagrafei ena stoixeio apo to alfabhto.
 * <BR>
 * <B>Type:</B> Transformer
 * <BR>
 * <B>Signature:</B> int_Remove_Object
 * <BR>
 * <B>Parameter:</B> outofset to stoixeio pou 8eloume na diagrapsoume apo to alfabhto.
 * <BR>
 * <B>Post:</B> epistrefei ton akeraio 1 an h afairesh htan epituxhs, h
 * 0 an h afairesh den htan epituxhs.
 */
	public int Remove(Object outofset)
		{
		
	    if (checkExist(outofset)==0)
				{
//				System.out.println("Symbolo "+outofset+" den yparxei sto Alphabhto");
				return 0;
				}
		else
				{
				
				if (outofset.toString().compareTo("#")==0)
					{
//					System.out.println("Apagoreuetai h afairesh tou symbolou "+outofset);
					return 0;
					}
				else
					{
					Set.remove(outofset);
//					System.out.println("Symbolo "+outofset.toString().charAt(0)+" afairethike apo to Alphabhto");
			  		}
				}
			     
		return 0;
		}

/**
 * Adeiazei to Alphabhto (Plhn tou kenou symbolou #).
 * <BR>
 * <B>Type:</B> Mutative Transformer
 * <BR>
 * <B>Signature:</B> void_ResetAlphabet_void
 * <BR>
 */

	public void ResetAlphabet()
		{
		Set.clear();
		Set.add("#");
		}


/**
 * Epistrefei mia string anaparastash tou alphabhtou me kena metaksu twn symbolwn.
 * <BR>
 * <B>Type:</B> Mutative Accessor
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
				if(i!=Set.size()-1) Symbols+=Set.elementAt(i)+" ";
				else Symbols+=Set.elementAt(i); 
				}
		
		return Symbols;
		}
	}
