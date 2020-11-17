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

    public Rectangle bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10,20,20);

    public boolean gameOver, started = true;

    public ArrayList<Rectangle> columns = new ArrayList<>();

    public int ticks, yMotions;

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

        int speed = 10;
        ticks++;

        if(started) {


            for (int i = 0; i < columns.size(); i++) {

                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);
                    if (column.y == 0) {
                        addColumn(false);


                    }
                }

                column.x -= speed;

            }

            if (ticks % 2 == 0 && yMotions < 15) {

                yMotions += 2;
            }
            bird.y += yMotions;
            for (Rectangle column : columns) {
                if (column.intersects(bird)) {
                    gameOver = true;
                }
            }
            if (bird.y > HEIGHT - 120 || bird.y < 0) {
                gameOver = true;
            }
        }

            renderer.repaint();

    }

    public void addColumn(boolean start){
        int width = 100;
        int space = 300;
        int height = 50 + rand.nextInt(300);

        if(start){

            columns.add(new Rectangle(WIDTH + width + columns.size() * 300,HEIGHT-height-120,width,height));
            columns.add(new Rectangle(WIDTH + width + (columns.size()-1) * 300,0,width,HEIGHT-height-space));

        }
        else {

            columns.add(new Rectangle(columns.get(columns.size()-1).x + 600, HEIGHT-height-120,width,height));
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
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",1,100 ));

        if(gameOver){
            g.drawString("Game Over",100, HEIGHT / 2-50);
        }


    }

    public static void main(String[] args) {

        flappyBird = new FlappyBird();
    }



}
