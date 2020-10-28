package example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by acer on 17-Nov-17.
 */
class Draw extends JPanel {

    String[]names = new String[5];
    int[] positions = new int[5];       //positions is the array that contains THE POSITIONS OF THE PLAYERS;
    Color[] playerColor = new Color[5]; //playerColor is the array that contains THE COLORS OF THE PLAYERS
                                        //5 is maximum number of players;
    BufferedImage[] playerImage = new BufferedImage[5];
    BufferedImage[] blackHoleImage = new BufferedImage[20]; //>5 is planet >14 is start end
    BufferedImage[] planetImage = new BufferedImage[9];



    int[][] snakes=new int[2][6];            //snakes array
    int[][] ladders=new int[2][9];           //ladders array
    //static int[][] miniGames = new int[][]{{22,23,24,55,57,58,59,85,86,87},{0,0,0,0,0,0,0,0,0,0}};      //miniGames array
    static int[] playersGameDone=new int[]{0,0,0,0,0};          //check for players game status;

    static int sizeOfSqr=80;   //height and width of a square;
    int places=0;
    int numOfPlayers;
    int xPlayer;
    int a[];

    int colorStable=0;
    Random random=new Random();

    private JButton playButton;
    private JLabel lblWhoPlay;
    DiceInterface diceInterface=new DiceInterface();

    AudioPlayer audioPlayer=new AudioPlayer();

     public void update(int numOfPlyer,String[] playerNames){
         numOfPlayers = numOfPlyer;
         names = playerNames;
    }

    static String lable;        //To print the number inside of square;
    int[] placesArray=new int[6];

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.black);

        int num=100;

        snakes= new int[][]{{39, 60, 66, 92, 98, 50}, {2, 21, 35, 69, 63, 11}};
        ladders=new int[][]{{6, 8, 30, 64, 71, 96, 84, 75, 27},{47, 16, 54, 79, 93, 3, 58, 44, 76}};


        try {
            playerImage[0] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\P1.png"));
            playerImage[1] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\P2.png"));
            playerImage[2] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\P3.png"));
            playerImage[3] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\P4.png"));
            playerImage[4] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\P5.png"));
            blackHoleImage[0] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\Bh1.png"));
            blackHoleImage[1] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\Bh2.png"));
            blackHoleImage[2] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\Hole.png"));
            blackHoleImage[5] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet1.png"));
            blackHoleImage[6] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet3.png"));
            blackHoleImage[7] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet4.png"));
            blackHoleImage[8] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet6.png"));
            blackHoleImage[9] =ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet7.png"));
            blackHoleImage[10] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet8.png"));
            blackHoleImage[11] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet9.png"));
            blackHoleImage[12] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet10.png"));
            blackHoleImage[13] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\planet11.png"));
            blackHoleImage[15] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\start.png"));
            blackHoleImage[16] = ImageIO.read(new File("C:\\Users\\DELL\\IdeaProjects\\snakes-and-ladders\\src\\example\\finish.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * print snake , ladder arrays

            for (int j=0;j<=3;j++){
                System.out.print(snakes[0][j]+" , "+snakes[1][j]+"\t");
                System.out.print(ladders[0][j]+" , "+ladders[1][j]+"\n");
            }
         */

        int lineSpace=0;    //lineSpace between two squares;
        int x,y,numXY;
        for (int i=1;i<=10;i++) {
            for (int j=1;j<=10;j++) {       //MAKING 10x10 MATRIX;
                numXY = NumberProcess.getNumber(i,j);



                //g.drawImage(bh,i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                g.setColor(Color.white);
                 g.fillRect(i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace);          //PRINTING THE CELLS;

                FontMetrics fm = g.getFontMetrics();
                Rectangle2D r = fm.getStringBounds(String.valueOf(i*10+j),g);
                if (i%2==0&&j%2==0){
                    g.setColor(Color.yellow);
                    g.fillRect(i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace);
                }else if(j%2==1&&i%2==1){
                    g.setColor(Color.yellow);
                    g.fillRect(i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace);
                }

                //blackHole set
                if(i==3&&j==10){
                    g.drawImage(blackHoleImage[5],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==5&&j==1){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==6&&j==10){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==7&&j==6){
                    g.drawImage(blackHoleImage[6],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==8&&j==10){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==5&&j==9){
                    g.drawImage(blackHoleImage[7],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==10&&j==8){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==7&&j==5){
                    g.drawImage(blackHoleImage[8],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==4&&j==4){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==2&&j==3){
                    g.drawImage(blackHoleImage[9],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==5&&j==3){
                    g.drawImage(blackHoleImage[10],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==7&&j==8){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==10&&j==3){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==8&&j==1){
                    g.drawImage(blackHoleImage[11],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==3&&j==5){
                    g.drawImage(blackHoleImage[12],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==4&&j==2){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==6&&j==3){
                    g.drawImage(blackHoleImage[0],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==4&&j==6){
                    g.drawImage(blackHoleImage[13],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else

                //failDown
                if(i==1&&j==5){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==2&&j==7){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==3&&j==1){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==6&&j==4){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==9&&j==1){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==10&&j==6){
                    g.drawImage(blackHoleImage[2],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }

                x = (int)r.getWidth()/2;
                y = (int)r.getHeight()/2;
                g.setFont(new Font(null,Font.BOLD,17));
                g.setColor(Color.black);

                if(numXY!=100) {
                    lable = String.valueOf(numXY);
                    g.drawString(lable, i * sizeOfSqr - lineSpace + 3 * x, j * sizeOfSqr - lineSpace + 4 * y);       //NUMBERING THE CELLS;
                }

                //start end
                if(i==1&&j==1){
                    g.drawImage(blackHoleImage[16],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }else if(i==1&&j==10){
                    g.drawImage(blackHoleImage[15],i*sizeOfSqr,j*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);
                }

                num--;
            }
        }

        g.setColor(Color.black);        //Draw Ladders
        for (int j=0;j<=8;j++) {
            int[] start,end;
            int[] xPoints,yPoints;

            start=NumberProcess.getIJ(ladders[0][j]);
            end=NumberProcess.getIJ(ladders[1][j]);

            int x1=(int) ((start[0]+0.5)*sizeOfSqr);
            int x2=(int) ((end[0]+0.5)*sizeOfSqr);
            int x3=x2-4*lineSpace;
            int x4=x1-4*lineSpace;
            xPoints=new int[]{x1,x2,x3,x4};

            int y1=(int) ((start[1]+0.5)*sizeOfSqr);
            int y2=(int) ((end[1]+0.5)*sizeOfSqr);
            int y3=y2+4*lineSpace;
            int y4=y1+4*lineSpace;
            yPoints=new int[]{y1,y2,y3,y4};

            g.fillPolygon(xPoints,yPoints,4);
        }

        g.setColor(Color.RED);          //Draw snakes
        for (int j=0;j<=5;j++) {
            int[] start,end;
            int[] xPoints,yPoints;

            start=NumberProcess.getIJ(snakes[0][j]);
            end=NumberProcess.getIJ(snakes[1][j]);

            int x1=(int) ((start[0]+0.5)*sizeOfSqr);
            int x2=(int) ((end[0]+0.5)*sizeOfSqr);
            int x3=x2-4*lineSpace;
            int x4=x1-4*lineSpace;
            xPoints=new int[]{x1,x2,x3,x4};

            int y1=(int) ((start[1]+0.5)*sizeOfSqr);
            int y2=(int) ((end[1]+0.5)*sizeOfSqr);
            int y3=y2+4*lineSpace;
            int y4=y1+4*lineSpace;
            yPoints=new int[]{y1,y2,y3,y4};

            g.fillPolygon(xPoints,yPoints,4);
        }

        g.setColor(Color.CYAN);
        g.drawRect(sizeOfSqr*12,sizeOfSqr,sizeOfSqr*7,sizeOfSqr*10);            //border of the score board

        g.setColor(Color.BLUE);
        g.setFont(new Font(null,Font.BOLD,16));

        int lastPossition=0;
        try {
            for (int i = 0; i <= numOfPlayers-1; i++) {

//                if (colorStable==0) {
//                    playerColor[i] = randColor();           //IT AVOID CHANGING PLAYERS COLORS AT ANY TIME
//                }
//                g.setColor(playerColor[i]);
                g.drawImage(playerImage[i],(int)(12.5*sizeOfSqr),(3+2*i)*sizeOfSqr/2,sizeOfSqr/2,sizeOfSqr/2,this);


                //System.out.println(xPlayer);
                if (positions[i]<100) {
                    String lable=" "+names[i] + " : [ " + positions[i] + " ]: need " + (100 - positions[i]) + " Points && Mini Game " + ((playersGameDone[i])==1 ? "Selected." : "not Selected.");
                    g.drawString(lable, 13 * sizeOfSqr, (2 + i) * sizeOfSqr);                //PRINTING THE NAMES OF PLAYERS;
                }else {
                    g.drawString(" "+names[i] + " : [ " + positions[i] +" ] Finished. "+placesArray[i] +(placesArray[i]==1?"st":(placesArray[i]==2?"nd":(placesArray[i]==3?"rd":"th"))), 13 * sizeOfSqr, (2 + i) * sizeOfSqr);
                }
                //g.fillRect((int) (12.5*sizeOfSqr),(3+2*i)*sizeOfSqr/2,sizeOfSqr/2,sizeOfSqr/2);       //DRAW A SMALL SQUARE BEFORE THE NAME OF PLAYER;
                g.setColor(Color.CYAN);
                g.drawRect((int) (12.5*sizeOfSqr),((3+2*xPlayer)*sizeOfSqr/2),(int) (sizeOfSqr*6.5),sizeOfSqr/2+3*lineSpace);
                                //g.fillPolygon([(int) (12.5*sizeOfSqr),(int) (12.5*sizeOfSqr),((int) (12.5*sizeOfSqr)+sizeOfSqr/2)],[(3+2*i)*sizeOfSqr/2,((3+2*i)*sizeOfSqr/2)-sizeOfSqr/2,sizeOfSqr],3);
                                                                                            //" Tn=a+(n-1)d " have used for Draw small squares;
                lastPossition++;
            }

        }catch (Exception e){
            //JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

        g.setColor(Color.CYAN);
        g.setFont(new Font(null,Font.ITALIC+Font.BOLD,30));
        //g.drawString("Created by Darshana Ariyarathna | +94774901245 | darshana.uop@gmail.com ",2*sizeOfSqr,12*sizeOfSqr);
        g.drawString("OOP_015_023_032 Game-Final ",2*sizeOfSqr,12*sizeOfSqr);

        g.setFont(new Font(null,Font.BOLD + Font.TRUETYPE_FONT,25));
        g.drawString("Random Adventure",sizeOfSqr,sizeOfSqr/2);

        if (colorStable==0) {       //WRITE A CODE IF YOU NEED MAKE ONCE TIME ,NOT REPETITIVE;

            playButton = new JButton("Play");
            playButton.setEnabled(true);
            playButton.setBounds(12 * sizeOfSqr, (2 + lastPossition) * sizeOfSqr, 2 * sizeOfSqr, sizeOfSqr / 2);
            add(playButton);

            lblWhoPlay = new JLabel();
            lblWhoPlay.setVisible(true);
            lblWhoPlay.setFont(new Font(null, Font.BOLD, 15));
            lblWhoPlay.setText(names[xPlayer] + "'s turn.");
            //lblWhoPlay.setForeground(playerColor[0]);
            lblWhoPlay.setForeground(Color.WHITE);
            lblWhoPlay.setBounds(15 * sizeOfSqr, (2 + lastPossition) * sizeOfSqr, 2 * sizeOfSqr, sizeOfSqr / 2);
            add(lblWhoPlay);


            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            int dVal=0;
                            for (int i=0;i<=10;i++){
                                dVal =getDiceValue();
                                Main.diceInterface.Update(dVal);
                                Main.diceInterface.repaint();
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e1){}
                            }
                            playGame(dVal);
                            Controll.refresh(numOfPlayers, names);
                            repaint();

                        }
                    }).start();

                }
            });
        }

        for (int i=numOfPlayers-1;i>=0;i--){
            //System.out.println("lion , "+positions[i]);
                a = NumberProcess.getIJ(positions[i]);
                g.setColor(playerColor[i]);


                g.drawImage(playerImage[i],a[0]*sizeOfSqr,a[1]*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace,this);


            //g.fillRect(a[0]*sizeOfSqr,a[1]*sizeOfSqr, sizeOfSqr-lineSpace, sizeOfSqr-lineSpace);
        }

        colorStable++;          //IT AVOID CHANGING PLAYERS COLORS AT ANY TIME

    }

    public Color randColor(){
        int r,g,b;
        r = (int)(Math.random()*256);
        g = (int)(Math.random()*256);
        b = (int)(Math.random()*256);

        return(new Color(r,g,b));
    }


    public int[] playGame(int DiceValue) {

        boolean key=false;
        boolean keyBypass = true;


        int old = positions[xPlayer];
        int dice = 0;

        positions[xPlayer] = positions[xPlayer] + DiceValue;
        /**
         * to set snakes and ladders.
         *
        if (positions[xPlayer]==20){
            positions[xPlayer]=90;
        }
         */

        for (int i=0;i<=8;i++){

            if (positions[xPlayer]==ladders[0][i]){
                this.repaint();
                i = random.nextInt(9);
                JOptionPane.showMessageDialog(null,"Take a interdimensional travel to another planet!.",names[xPlayer]+" Found a Ladder",JOptionPane.PLAIN_MESSAGE);
                positions[xPlayer]=ladders[1][i];
            }
        }
        for (int i=0;i<=5;i++){

            if (positions[xPlayer]==snakes[0][i]){
                this.repaint();

                JOptionPane.showMessageDialog(null,"Oh no!! you've arrived at the block hole(Fall down 5 floor)",names[xPlayer]+" Snake",JOptionPane.PLAIN_MESSAGE);
                positions[xPlayer]=snakes[1][i];
            }
        }
//        for (int t=0;t<=9;t++){
//            if ((miniGames[0][t])==positions[xPlayer] && (miniGames[1][t])==0 && playersGameDone[xPlayer]==0){
//                miniGames[1][t]++;
//                this.repaint();
//                JOptionPane.showMessageDialog(null,names[xPlayer]+" It's Mini Game time, choose your Destiny...!!","Mini Game",JOptionPane.PLAIN_MESSAGE);
//                //audioPlayer.say("miniGame.wav");
//                playersGameDone[xPlayer]++;
//                break;
//            }else{
//
//            }
//        }
        //System.out.println(positions[xPlayer]+"\t"+xPlayer+"\t");

        if (positions[xPlayer] >= 100) {
            positions[xPlayer] = 100;
            places++;
            //System.out.println(xPlayer+" , "+placesArray[xPlayer]+" , "+places);
            placesArray[xPlayer] = places;

            //System.out.println(xPlayer+" , "+placesArray[xPlayer]+" , "+places);

            JOptionPane.showMessageDialog(null, "Congratulations " + names[xPlayer] + " You have completed your JOURNEY..!! "+places+" !!", "End of the JOURNEY..!!", JOptionPane.PLAIN_MESSAGE);

            playButton.setEnabled(true);
            if (places==numOfPlayers-1){
                playButton.setEnabled(false);
            }
            //setEnabled(false);
        }
        /**
            key=Boolean.valueOf(positions[xPlayer]==100);

            if (key){
                while (key){
                    xPlayer++;
                    key=Boolean.valueOf(positions[xPlayer]==100);
                    keyBypass=false;
                }
            }else{
            }
            if (keyBypass) {
                xPlayer++;
            }else {
                keyBypass=true;
            }
            */

            //System.out.println("1]"+xPlayer);
            xPlayer=nextPlayersNumber(xPlayer);
            //System.out.println("2]"+xPlayer);
            lblWhoPlay.setText(names[xPlayer] + "'s turn");
        //}

        return positions;
    }
    public int nextPlayersNumber(int xPlayer){
        int nextNum=0,n=0;
        boolean key=true;

        //System.out.println("*****");
        if (xPlayer +1 ==  numOfPlayers) {
            xPlayer = -1;
        }
        try{
            while (key){
                //System.out.println("111");
                if (positions[xPlayer+1]<100){
                    key=false;
                    xPlayer++;
                    //System.out.println("222");
                    //return nextNum;
                }else {
                    xPlayer++;
                    //System.out.println("333");

                    if (xPlayer + 1 == numOfPlayers) {
                        xPlayer = -1;
                    }
                }
            }
        }catch (Exception e){}
        nextNum=xPlayer;
        //System.out.println(nextNum);
        return nextNum;
    }

    public int getDiceValue(){

        int diceValue = random.nextInt(6)+1;
        return diceValue;
    }


}
