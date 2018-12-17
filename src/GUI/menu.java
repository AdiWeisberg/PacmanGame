
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Iterator;

import Coords.ConvertFactory;
import Coords.ImageFactory;
import Coords.Map;
import GIS.GIS_element;
import GameElements.Fruit;
import GameElements.Game;
import GameElements.Pacman;
import Geom.Point3D;

/**
 *
 * @author annaf
 */
public class menu extends JFrame implements MouseListener , ActionListener{
	// private variables
	private Container window;
	private ResizableImagePanel _panel;
	private Graphics _paper;
	private int x, y;
	private static Game game;
	private int isGamer;//1=pacman,2=fruit,3=run,4=save,5=Open
	private BufferedImage image;
	private final JFileChooser openFileChosser;
	private JMenuItem menuItem1, menuItem2,menuItem3,menuItem4,menuItem5,menuItem6; // an item in a menu


	//private Map map;
	//private final JFilesaver saveFile;

	public menu(){
		super("Panter Map!"); //setTitle("Map Counter");  // "super" Frame sets its title
		this.setBounds(0,0,getWidth(),getHeight()); //setSize(1433,700);        // "super" Frame sets its initial window size
		//      Exit the program when the close-window button clicked
		try {
			String path = "Ariel.jpg";
			if(!path.endsWith("jpg") && !path.endsWith("png")) {
				throw new IOException("Can't read input file!");
			}
			this.image =ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		//cf = new ConvertFactory(this.getImg());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//	pack();

		game =new Game();
		openFileChosser= new JFileChooser();
		openFileChosser.setCurrentDirectory(new File("C:\\Users\\n&e\\eclipse-workspace\\second_year\\GeoLnfo_System\\csvFile"));
		//openFileChosser.setFileFilter(new FileNameExtensionFilter("csv"));
		//map=new Map();
		//this.image=map.getImg();
	}



	public void createGui(){              				
		//	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());

		//	Add "panel" to be used for drawing            
		_panel = new ResizableImagePanel();
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);

		// A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
		JMenuBar menuBar;   // the menu-bar
		JMenu menu;         // each menu in the menu-bar

		menuBar = new JMenuBar();
		// First Menu
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
		menuBar.add(menu);  // the menu-bar adds this menu

		menuItem1 = new JMenuItem("Fruit", KeyEvent.VK_F);
		menu.add(menuItem1); // the menu adds this item

		menuItem2 = new JMenuItem("Pacman", KeyEvent.VK_S);
		menu.add(menuItem2); // the menu adds this item
		menuItem3 = new JMenuItem("Run");
		menu.add(menuItem3); // the menu adds this item   
		menuItem4 = new JMenuItem("Save Game");
		menu.add(menuItem4); // the menu adds this item

		menuItem5 = new JMenuItem("Open Game");
		menu.add(menuItem5); // the menu adds this item
		menuItem6 = new JMenuItem("Clear Game");
		menu.add(menuItem6); // the menu adds this item
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menuItem3.addActionListener(this);
		menuItem4.addActionListener(this);
		menuItem5.addActionListener(this);
		menuItem6.addActionListener(this);

		setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar
		// panel (source) fires the MouseEvent.
		//	panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object option = e.getSource();
		if(option==menuItem1) {
			isGamer = 2;
		}
		else if(option==menuItem2) {
			isGamer=1;
		}
		else if(option==menuItem3) {
			isGamer=3;
		}
		else if(option==menuItem4) {
			this.saveAs();
		}
		else if(option==menuItem5) {
		
			int returnValue = openFileChosser.showOpenDialog(this);
			if(returnValue==openFileChosser.APPROVE_OPTION) {
				try {
					game.csvToGame(openFileChosser.getSelectedFile().getPath());
					repaint();
				}catch(Exception ew) {
				}
			}
		}
		else if(option==menuItem6) {
			game.clearFruits();
			game.clearPacman();
			repaint();
		}
	}

	protected void paintElement() throws ParseException {
		//	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();
		if(isGamer==2){//fruit
			_paper.setColor(Color.YELLOW);
			_paper.fillOval(x,y,10,10);
			Point3D p=new Point3D(x,y,0);
			ConvertFactory conver=new ConvertFactory(image);
			Point3D p2=conver.PicselToGps(p);
			Fruit fruit=new Fruit(p2);
			game.addFruit(fruit);
		} 
		if(isGamer==1) {//pacman
			_paper.setColor(Color.RED);
			_paper.fillOval(x,y,20,20);
			Point3D p=new Point3D(x,y,0);
			new ConvertFactory(image);
			Point3D p2=ConvertFactory.PicselToGps(p);
			Pacman pacman =new Pacman(p2);
			game.addPacman(pacman);
		}
		if(isGamer==3) {//Run

		}
		_paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
		_paper.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
	}


	@Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();  
		try {
			paintElement();
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	// Not Used, but need to provide an empty body for compilation
	@Override
	public void mouseReleased(MouseEvent event){}
	@Override
	public void mouseClicked(MouseEvent event){
		System.out.println("mouse Clicked");
		System.out.println("("+ event.getX() + "," + event.getY() +")");
		x = event.getX();
		y = event.getY();
		Point3D p=new Point3D(x,y,0);
		//ConvertFactory conver=new ConvertFactory(image);
		new ConvertFactory(this.image);
		Point3D P2= ConvertFactory.PicselToGps(p);

		System.out.println("("+ P2.x() + "," + P2.y() +")");
		ResizableImagePanel pan = new ResizableImagePanel();

	}
	private void saveAs() {
		final JFileChooser saveAsFileChooser = new JFileChooser();
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("csv File", "csv");
		saveAsFileChooser.setFileFilter(extensionFilter);
		int actionDialog = saveAsFileChooser.showOpenDialog(this);
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = saveAsFileChooser.getSelectedFile();
		if (!file.getName().endsWith(".csv")) {
			file = new File(file.getAbsolutePath() + ".csv");
		}
		BufferedWriter outFile = null;

		//	outFile = new BufferedWriter(new FileWriter(file));
		game.GameTocsv(file.getName());
		//output.write(outFile);


	}
	@Override
	public void mouseExited(MouseEvent event){}
	@Override
	public void mouseEntered(MouseEvent event){
		System.out.println("mouse entered");
	}
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0,getWidth(),getHeight(), this);

		Iterator<GIS_element> itr0= game.getFruits().iterator();
		Iterator<GIS_element> itr1 = game.getPacmans().iterator();
		new ConvertFactory(this.image);
		while(itr0.hasNext()) {
			g.setColor(Color.yellow);
			Fruit fruit=(Fruit)itr0.next();
			Point3D p=ConvertFactory.GpsToPicsel(fruit.getPoint());
			g.fillOval((int)p.x(),(int) p.y(), 20,20);
		}
		while(itr1.hasNext()) {
			g.setColor(Color.RED);
			Pacman pacman=(Pacman)itr1.next();
			Point3D p=ConvertFactory.GpsToPicsel(pacman.getPoint());
			g.fillOval((int)p.x(),(int) p.y(), 20,20);
		}
		System.out.println(game.getFruits().size());

//				if(x!=-1 && y!=-1)
//				{
//					int r = 10;
//					x = x - (r / 2);
//					y = y - (r / 2);
//					g.fillOval(x, y, r, r);
//				}
	}
	public static void main(String[] args) {
		menu frame = new menu();
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
	}

}