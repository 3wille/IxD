import javax.swing.*;


public class PACT
{
	static final JFrame window = new SimpleExampleGUI();
	
	public static void main(String[] args)
    {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Simple GUI");
        window.setSize(800, 600);
        window.setVisible(true);
    }
}