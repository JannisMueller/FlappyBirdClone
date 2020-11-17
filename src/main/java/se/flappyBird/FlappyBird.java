package se.flappyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird implements ActionListener {


    public static FlappyBird flappyBird;

    public final int WIDTH = 800, HEIGHT = 800;

    public Renderer renderer = new Renderer();

    public Random rand = new Random();

    public Rectangle bird = new Rectangle(WIDTH/ 2-10, HEIGHT /2-10,20,20);

    public ArrayList<Rectangle> columns = new ArrayList<>();

    public int ticks, yMotions;
    //public static Renderer renderer;

    public FlappyBird(){

        JFrame jFrame = new JFrame();
        Timer timer = new Timer(20,this);

        jFrame.add(renderer);
        jFrame.setTitle("Flappy Bird");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setSize(WIDTH,HEIGHT);
        jFrame.setVisible(true);

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ticks++;

        for(int i = 0; i < columns.size(); i++){



        }

        if(ticks % 2 == 0 && yMotions < 15)  {

            yMotions += 2;}

        bird.y += yMotions;

        renderer.repaint();
    }

    public void addColumn(boolean start){
        int width = 500;
        int space = 100;
        int height = 50 + rand.nextInt(300);

        if(start){

            columns.add(new Rectangle(WIDTH + width + columns.size() * 300,HEIGHT-height-120,width,height));
            columns.add(new Rectangle(WIDTH + width + (columns.size()-1) * 300,0,width,HEIGHT-height-space));

        }
        else {

            columns.add(new Rectangle(columns.get(columns.size()-1).x +600,HEIGHT-height-120,width,height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x,0,width,HEIGHT-height-space));

        }

        }

    public void paintColumn(Graphics g, Rectangle column){

        g.setColor(Color.GREEN.darker());
        g.fillRect(column.x, column.y, column.width, column.height);

    }

    public void repaint(Graphics g){

        g.setColor(Color.CYAN);
        g.fillRect(0,0, WIDTH,HEIGHT);

        g.setColor(Color.CYAN);
        g.fillRect(0,0, WIDTH,HEIGHT);

        g.setColor(Color.ORANGE);
        g.fillRect(0, HEIGHT-120, WIDTH,120);

        g.setColor(Color.GREEN);
        g.fillRect(0, HEIGHT-120, WIDTH,20);

        g.setColor(Color.RED);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for(Rectangle column : columns){
            paintColumn(g,column);
        }



    }

    public static void main(String[] args) {

        flappyBird = new FlappyBird();
    }



}
