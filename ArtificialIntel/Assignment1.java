package ArtificialIntel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ArtificialIntel.Data.Cell;
import ArtificialIntel.Data.Grid;
import ArtificialIntel.Data.GridStorage;
import ArtificialIntel.Data.Agent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 *
 */
public class Assignment1 implements KeyListener
{
    private int sizeTile;
    private Image image;
    private ImageIcon imageIcon;
    private JLabel jLabel;
    private JFrame jFrame;
    public Agent agent; //should be displayed using a circle on the grid
    public Grid g;

    Assignment1()
    {
        sizeTile = getInt( "How many pixels per square? [1 - 100]?" );
        agent = new Agent(2, 0);
    }
    
    /**
     * @param args the command line arguments: Unused.
     */
    public static void main(String[] args) 
    {
        Assignment1 assignment1 = new Assignment1();
        Grid grid = assignment1.restoreGrid();
        assignment1.InitializeGUI(grid);
        assignment1.paint(grid);
        assignment1.view();
    }
    
    protected void InitializeGUI(Grid grid){
        int imageSize = Math.max(grid.getWidth(), grid.getHeight()) * sizeTile;
        image = new BufferedImage( imageSize, imageSize, BufferedImage.TYPE_INT_ARGB );
        imageIcon = new ImageIcon( image );
        jLabel = new JLabel( imageIcon );
        jFrame = new JFrame( "Artificial Intel" );
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.addKeyListener(this);
        Container container = jFrame.getContentPane();
        container.setLayout( new BorderLayout() );
        container.add( jLabel, BorderLayout.CENTER );

       
        
        jFrame.pack();
    }

    protected Grid restoreGrid(){
        Grid grid = null;
        try{
            String currentDirectory = System.getProperty("user.dir");
            String fileName = currentDirectory + "/resources/" + "grid0.txt";
            grid = GridStorage.restoreGrid(fileName);
            agent.gridWidth = grid.getWidth();
            agent.gridHeight = grid.getHeight();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return grid;
    }
    private int getInt( String question )
    {
        String intString = JOptionPane.showInputDialog( question );
        return Integer.parseInt( intString );
    }

   
    private void paint(Grid grid)
    {
        g = grid;
        Graphics graphics = image.getGraphics();
        // paint the cells
        graphics.setColor( Color.white );
        for ( int row = 0; row < grid.getHeight(); row++ )
        {
            for ( int col = 0; col < grid.getWidth(); col ++ )
            {
                Cell cell = grid.cells[col][row];
                if(cell.IsFree()){
                    graphics.setColor( Color.white );
                }
                else{
                    graphics.setColor( Color.gray );
                }
                graphics.fillRect( col * sizeTile, row * sizeTile, sizeTile, sizeTile );
            }
        }

        // this loop just draws lines around every cell
        for ( int row = 0; row < grid.getHeight(); row++ )
        {
            for ( int col = 0; col < grid.getWidth(); col++ )
            {

                graphics.setColor(Color.orange);
                int x = col * sizeTile;
                int y = row * sizeTile;
                int x2 = (col+1) * sizeTile;
                int y2 = (row+1) * sizeTile;

                graphics.drawLine(x, y, x2, y);
                graphics.drawLine(x2, y, x2, y2);
                graphics.drawLine(x2, y2, x, y2);
                graphics.drawLine(x, y2, x, y);
            }
        }
        graphics.setColor(Color.BLUE);
        graphics.fillOval(agent.getX() * sizeTile, agent.getY() * sizeTile, 5, 5);

        graphics.setColor(Color.GREEN);
        graphics.fillOval(grid.start.x * sizeTile, grid.start.y * sizeTile, 5, 5);

        graphics.setColor(Color.RED);
        graphics.fillOval(grid.goal.x * sizeTile, grid.goal.y * sizeTile, 5, 5);


    }
   
    private void view() { jFrame.setVisible( true ); }

    @Override
    public void keyPressed(KeyEvent e) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillOval(agent.getX() * sizeTile, agent.getY() * sizeTile, 5, 5);
        graphics.setColor(Color.BLUE);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            agent.x += 1;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            agent.x -= 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            agent.y -= 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            agent.y += 1;
        }
        graphics.fillOval(agent.getX() * sizeTile, agent.getY() * sizeTile, 5, 5);

        graphics.setColor( Color.white );
        for ( int row = 0; row < g.getHeight(); row++ )
        {
            for ( int col = 0; col < g.getWidth(); col ++ )
            {
                Cell cell = g.cells[col][row];
                if(cell.IsFree()){
                    graphics.setColor( Color.white );
                }
                else{
                    graphics.setColor( Color.gray );
                }
                graphics.fillRect( col * sizeTile, row * sizeTile, sizeTile, sizeTile );
            }
        }

        graphics.setColor(Color.GREEN);
        graphics.fillOval(g.start.x * sizeTile, g.start.y * sizeTile, 5, 5);

        graphics.setColor(Color.RED);
        graphics.fillOval(g.goal.x * sizeTile, g.goal.y * sizeTile, 5, 5);

        jFrame.revalidate();
        jFrame.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
