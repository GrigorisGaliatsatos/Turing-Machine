import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Auth einai h kuria klash dhmiourgias interface mias Mhxanhs Turing. Kataskeuazei
 * to arxiko frame ths efarmoghs, to opoio epekteinetai mesw twn klasewn: Actions, TextEditor.
 */
public class TMachine
	{
	static int TMmodeID=1;

/**
 * Ekteleitai otan trexoume thn efarmogh san application. Edw kataskeuazetai 
 * to kyrio inteface ths TMachine.
 * <BR>
 * <B>Signature:</B>  void_main_String[]
 */
	public static void main(String s[])
		{
		Tape ChangeFromEditor;

		TextEditor texteditor;
		Actions actions;
		TMachine TM=new TMachine();

		JFrame shot = new JFrame("Turing Machine Simulator  ( TMachine )");
		shot.getContentPane().setBackground(Color.GRAY);
		shot.setSize(800,620);

		shot.getContentPane().setLayout(null);

		actions = new Actions(shot);

		texteditor = new TextEditor(shot);
		ChangeFromEditor=actions.TakeCurrTape();
		texteditor.CurrentTape(ChangeFromEditor);

		Controller.TM_mode(TMmodeID);

		shot.setResizable(false);
		shot.setVisible(true);
		shot.setLocation(80,40);
		shot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
