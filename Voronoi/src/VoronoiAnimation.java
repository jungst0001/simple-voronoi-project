import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class VoronoiAnimation extends JFrame {
	private JLabel pic;
    private int x = 0;
    private String[] list;
    
    public VoronoiAnimation(int size, int iterNum){
//        super("Java SlideShow");
    	pic = new JLabel();
        pic.setSize(size, size);
        
        list = new String[iterNum];
        for(int i = 0; i < iterNum; i++){
        	String s = "voronoi" + i + ".png";
        	list[i] = s;
        }
        
        SetImageSize(0);

       //set a timer
        Timer timer = new Timer(250, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                if(x < list.length ){
                	SetImageSize(x);
                	x++;
                	System.out.println(x);
                }
			}
		});
        add(pic);
        timer.start();
//        setLayout(null);
        setBounds(0, 0, size, size);
//        getContentPane().setBackground(Color.decode("#bdb67b"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //create a function to resize the image 
    private void SetImageSize(int i){
//    	System.out.println(i);
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
//        Image newImg = img;
        ImageIcon newImc = new ImageIcon(newImg);
        pic.setIcon(newImc);
//        System.out.println(i);
    }
}
