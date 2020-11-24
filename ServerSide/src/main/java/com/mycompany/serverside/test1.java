package com.mycompany.serverside;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class test1 extends JFrame {

    Point A = new Point(220, 80);
    Point B = new Point(100, 180);
    Point C = new Point(340, 180);
    Point D = new Point(140, 305);
    Point E = new Point(300, 305);

    int draw1[] = new int[25];
    int draw2[] = new int[25];

    int [][] global_adj_mat = new int[5][5];

    test1(int[][] adj_mat) {
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                global_adj_mat[i][j] = adj_mat[i][j];

    }

    public void paint(Graphics g) {
        Image img = createImageWithText();
        g.drawImage(img, 20, 20, this);
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.setColor(Color.yellow);
        g.fillOval(200, 50, 55, 55);
        g.fillOval(80, 150, 55, 55);
        g.fillOval(320, 150, 55, 55);
        g.fillOval(120, 275, 55, 55);
        g.fillOval(280, 275, 55, 55);
        g.setColor(Color.black);
        g.drawString("A", A.x, A.y);
        g.drawString("B", B.x, B.y);
        g.drawString("C", C.x, C.y);
        g.drawString("D", D.x, D.y);
        g.drawString("E", E.x, E.y);
        for (int i = 0; i < 25; i++) {
            draw1[i] = -1;
            draw2[i] = -1;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (global_adj_mat[i][j] > 0) {
                    int n = 0;
                    int m = 0;
                    if (draw1[0] == -1)
                        draw1[0] = i;
                    else {
                        while (draw1[n] != -1)
                            n++;
                        draw1[n] = i;
                    }
                    if (draw2[0] == -1)
                        draw2[0] = j;
                    else {
                        while (draw2[m] != -1)
                            m++;
                        draw2[m] = j;
                    }
                }
            }
        }

        for(int i=0; i<25;i++){
            if(draw1[i]>-1 && draw2[i]>-1){
                int a1=0,b1=0,a2=0,b2=0;
                switch(draw1[i]){
                    case 0:
                        a1 = A.x;
                        b1 = A.y;
                        break;
                    case 1:
                        a1 = B.x;
                        b1 = B.y;
                        break;
                    case 2:
                        a1 = C.x;
                        b1 = C.y;
                        break;
                    case 3:
                        a1 = D.x;
                        b1 = D.y;
                        break;
                    case 4:
                        a1 = E.x;
                        b1 = E.y;
                        break;
                }
                switch(draw2[i]){
                    case 0:
                        a2 = A.x;
                        b2 = A.y;
                        break;
                    case 1:
                        a2 = B.x;
                        b2 = B.y;
                        break;
                    case 2:
                        a2 = C.x;
                        b2 = C.y;
                        break;
                    case 3:
                        a2 = D.x;
                        b2 = D.y;
                        break;
                    case 4:
                        a2 = E.x;
                        b2 = E.y;
                        break;
                }
                drawArrow(g, a1, b1, a2, b2);
            }
        }
        return bufferedImage;
    }


    public void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int midx = (x1 + x2)/2;
        int midy = (y1 + y2)/2;
        x1 = (midx + x1)/2;
        x2 = (midx + x2)/2;
        y1 = (midy + y1)/2;
        y2 = (midy + y2)/2;
        g.drawLine(x1, y1, x2, y2);
        drawTip(g, x1, y1,x2, y2);

    }

    private void drawTip(Graphics g, int x1, int y1, int x2, int y2) {
        int x,y;
        double rads = 0.523599;
        double hyp_multiplier = 10;
        int diff_y = y2 - y1;
        int diff_x = x2 - x1;
        double t = Math.atan2(diff_y, diff_x);
        double r = rads + t;
        for (int j = 0; j < 2; j++) {
            x = (int)(x2 - hyp_multiplier * Math.cos(r));
            y = (int)(y2 - hyp_multiplier * Math.sin(r));
            g.drawLine(x2, y2, x, y);
            r = t - rads;
        }
    }


    public static void main(String[] args) {
        int adj_mat[][] = new int[][] { { 0, 1, 0, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 0 }, };
        JFrame frame = new test1(adj_mat);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setTitle("Graph Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}