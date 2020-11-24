package com.mycompany.serverside;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;


public class Graph extends JFrame{

    // Declare global matrix
//    int adjMatrix[][] = new int[5][5];

    public void paint(Graphics g) {
        super.paint(g);
        Image img = createImageWithText();
        g.drawImage(img, 20,120,this);
    }

    private Image createImageWithText() {
        BufferedImage bufferedImage = new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();

//        File outputfile = new File("saved.png");
//        ImageIO.write(bufferedImage, "png", outputfile);

        int[][] nodeLocations = new int[5][2];
        int diff = 50; int diffY=80;
        int x = 200; int y = 100;
        nodeLocations[0][0] = x;
        nodeLocations[0][1] = y;

        nodeLocations[1][0] = x-diff;
        nodeLocations[1][1] = y+diffY;

        nodeLocations[2][0] = x+diff;
        nodeLocations[2][1] = y+diffY;

        nodeLocations[3][0] = x-diff*2;
        nodeLocations[3][1] = y+diffY*2;

        nodeLocations[4][0] = x;
        nodeLocations[4][1] = y+diffY*2;

        g.setColor(Color.white);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        g.setColor(Color.red);
        int xf = 50;int yf=30;
        for(int i=0;i<5;++i) {
            String a = String.valueOf(i);
            g.setColor(Color.green);
            g.drawString(a,nodeLocations[i][0]+10, nodeLocations[i][1]+18);
            g.setColor(Color.black);
            g.drawOval(nodeLocations[i][0], nodeLocations[i][1], 30, 30);
        }

        for (int i=0;i<5;++i) {
            for(int j=0;j<5;++j) {
                if(adjMatrix[i][j]!=0) {
                    if(adjMatrix[j][i]==0) {

                        int x1 =nodeLocations[i][0];
                        int y1 =nodeLocations[i][1];
                        int x2 =nodeLocations[j][0];
                        int y2 =nodeLocations[j][1];

                        float[] mid = new float[2];
                        mid[0] = ((float)x1 + (float)x2)/2;
                        mid[1] = ((float)y1 + (float)y2)/2;
                        x1 = (int) ((mid[0] + x1)/2);
                        y1 = (int) ((mid[1] + y1)/2);

                        x2 = (int) ((mid[0] + x2)/2);
                        y2 = (int) ((mid[1] + y2)/2);

                        g.drawLine(x1 +10, y1+18, x2+10, y2+18);
                        //g.drawLine((int)((x1 +10)/1.5), (int)((y1+18)/1.5),(int) ((x2+10)/1.5),(int) ((y2+18)/1.5));

                        float[] pointPer = new float[2];
                        pointPer[0] =  (((x1+x2)/2) + x2)/2;
                        pointPer[1] =  (((y1+y2)/2) + y2)/2;
                        System.out.println(y2-y1);
                        System.out.println(x2-x1);
                        float m;
                        float[] p1 = new float[2];
                        float[] p2 = new float[2];

                        if((y2-y1)!=0 && (x2-x1)!=0) {
                            m = ((float)(y2-y1))/((float)(x2-x1));
                            System.out.println(m);
                            m = -1/m;
                            System.out.println(m);
                            float c = pointPer[1] - m*pointPer[0];
                            p1[0] = pointPer[0]-5;
                            p1[1]= m*(pointPer[0]-5) +c;

                            p2[0] = pointPer[0]+5;
                            p2[1]= m*(pointPer[0]+5) + c;

                        }
                        else if(x2-x1==0) {
                            p1[0] = pointPer[0]-5;
                            p1[1]= pointPer[1];
                            p2[0] = pointPer[0]+5;
                            p2[1]= pointPer[1];
                        }
                        else if(y2-y1==0) {
                            p1[0] = pointPer[0];
                            p1[1]= pointPer[1]-5;
                            p2[0] = pointPer[0];
                            p2[1]= pointPer[1]+5;
                        }

                        g.drawLine((int)p1[0] + 10,(int) p1[1]+18, x2+10, y2+18);
                        g.drawLine((int)p2[0] + 10,(int) p2[1]+18, x2+10, y2+18);
                    }

                    else {

                        int x1 =nodeLocations[i][0];
                        int y1 =nodeLocations[i][1];
                        int x2 =nodeLocations[j][0];
                        int y2 =nodeLocations[j][1];

                        float[] mid = new float[2];
                        mid[0] = ((float)x1 + (float)x2)/2;
                        mid[1] = ((float)y1 + (float)y2)/2;
                        x1 = (int) ((mid[0] + x1)/2);
                        y1 = (int) ((mid[1] + y1)/2);

                        x2 = (int) ((mid[0] + x2)/2);
                        y2 = (int) ((mid[1] + y2)/2);

                        g.drawLine(x1 +10, y1+18, x2+10, y2+18);

                        float[] pointPer = new float[2];
                        pointPer[0] =  (((x1+x2)/2) + x2)/2;
                        pointPer[1] =  (((y1+y2)/2) + y2)/2;
                        System.out.println(y2-y1);
                        System.out.println(x2-x1);
                        float m;
                        float[] p1 = new float[2];
                        float[] p2 = new float[2];

                        if((y2-y1)!=0 && (x2-x1)!=0) {
                            m = ((float)(y2-y1))/((float)(x2-x1));
                            System.out.println(m);
                            m = -1/m;
                            System.out.println(m);
                            float c = pointPer[1] - m*pointPer[0];
                            p1[0] = pointPer[0]-5;
                            p1[1]= m*(pointPer[0]-5) +c;

                            p2[0] = pointPer[0]+5;
                            p2[1]= m*(pointPer[0]+5) + c;

                        }
                        else if(x2-x1==0) {
                            p1[0] = pointPer[0]-5;
                            p1[1]= pointPer[1];
                            p2[0] = pointPer[0]+5;
                            p2[1]= pointPer[1];
                        }
                        else if(y2-y1==0) {
                            p1[0] = pointPer[0];
                            p1[1]= pointPer[1]-5;
                            p2[0] = pointPer[0];
                            p2[1]= pointPer[1]+5;
                        }

                        g.drawLine((int)p1[0] + 10,(int) p1[1]+18, x2+10, y2+18);
                        g.drawLine((int)p2[0] + 10,(int) p2[1]+18, x2+10, y2+18);

                        x1 =nodeLocations[j][0];
                        y1 =nodeLocations[j][1];
                        x2 =nodeLocations[i][0];
                        y2 =nodeLocations[i][1];

                        mid = new float[2];
                        mid[0] = ((float)x1 + (float)x2)/2;
                        mid[1] = ((float)y1 + (float)y2)/2;
                        x1 = (int) ((mid[0] + x1)/2);
                        y1 = (int) ((mid[1] + y1)/2);


                        x2 = (int) ((mid[0] + x2)/2);
                        y2 = (int) ((mid[1] + y2)/2);


                        pointPer[0] =  (((x1+x2)/2) + x2)/2;
                        pointPer[1] =  (((y1+y2)/2) + y2)/2;
                        System.out.println(y2-y1);
                        System.out.println(x2-x1);


                        if((y2-y1)!=0 && (x2-x1)!=0) {
                            m = ((float)(y2-y1))/((float)(x2-x1));
                            System.out.println(m);
                            m = -1/m;
                            System.out.println(m);
                            float c = pointPer[1] - m*pointPer[0];
                            p1[0] = pointPer[0]-5;
                            p1[1]= m*(pointPer[0]-5) +c;

                            p2[0] = pointPer[0]+5;
                            p2[1]= m*(pointPer[0]+5) + c;

                        }
                        else if(x2-x1==0) {
                            p1[0] = pointPer[0]-5;
                            p1[1]= pointPer[1];
                            p2[0] = pointPer[0]+5;
                            p2[1]= pointPer[1];
                        }
                        else if(y2-y1==0) {
                            p1[0] = pointPer[0];
                            p1[1]= pointPer[1]-5;
                            p2[0] = pointPer[0];
                            p2[1]= pointPer[1]+5;
                        }

                        g.drawLine((int)p1[0] + 10,(int) p1[1]+18, x2+10, y2+18);
                        g.drawLine((int)p2[0] + 10,(int) p2[1]+18, x2+10, y2+18);

                    }



                }
            }
        }
        return bufferedImage;
    }

    public static void main(String[] args) {

        // Declare a scanner input object
        Scanner input = new Scanner(System.in);

        /* Take Matrix input */

        // Declare the matrix

        int entry;
        // Read the matrix values
        System.out.println("Enter the elements of the matrix");
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                entry = input.nextInt();
                if(entry >= 1)
                    entry = 1;
                else
                    entry = 0;
//                adjMatrix[i][j] = entry;
            }

        JFrame frame= new Graph();
        frame.setSize(1000, 1000);
        frame.setVisible(true);

    }

}