
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class AirCraftClass extends JPanel implements KeyListener, ActionListener {
    //enemy Plane String Name
    public String enemyPlane1="enemyplane.PNG";

    private boolean gameIsOn=true;


    Timer tm = new Timer(4, this);


    private BufferedImage imagePlane;

    private BufferedImage imageEnemyPlane;
    private BufferedImage imageBullet;

    //Plane movement control
    private int planeXAxis = 0;
    private int planeYAxis = 200;
    private int xVelocity = 0;
    private int yVelocity = 0;

    //plane firing
    private boolean fireBullet = false;
    private boolean isFireBulletOn = false;

    //bullet movement control
    private int bulletXAxis = planeXAxis + 65;
    private int bulletYAxis = planeYAxis + 25;


    //enemyplane control
    private int enemyPlaneXAxis = 1000;
    private int enemyPlaneYAxis = 200;
    private boolean coalitionOccurred = true;



    AirCraftClass() {
        if(gameIsOn){
            tm.start();

            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            addKeyListener(this);
        } }




    JLabel label;

    public void frame() {

        addKeyListener(this);
        if(gameIsOn){

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setTitle("AirCraft War");
            frame.setResizable(false);
           JLabel newLabel = new JLabel("Torab");
            frame.add(newLabel);



            AirCraftClass airCraftClass = new AirCraftClass();
            frame.getContentPane().add(airCraftClass);

            frame.revalidate();
            frame.repaint();



            frame.setLocation(200, 50);
            frame.setSize(1000, 500);
            frame.setVisible(true);

        } }



    int planeCounter = 1;
    //game control


    public void paintComponent(Graphics g) {
System.out.println(planeCounter);
        if(planeCounter>10)
        {
            FirstStageComplete.popUpWindow();
            gameIsOn=false;
        }

        try {
            if(!gameIsOn){
                super.paintComponent(g);
                //g.fill3DRect(0, 0, 1000, 75, true);
                g.fill3DRect(0, 400, 1000, 75, true);

            }else {
                if(planeCounter>10)
                {
                    FirstStageComplete.popUpWindow();
                    gameIsOn=false;

                }
                super.paintComponent(g);
                //g.fill3DRect(0, 0, 1000, 75, true);
                g.fill3DRect(0, 400, 1000, 75, true);


                if (planeYAxis <= 73) {
                    planeYAxis = 74;

                }
                if (planeYAxis >= 330) {
                    planeYAxis = 328;

                }


                if (planeXAxis >= 925) {
                    planeXAxis = 925;

                }

                if (planeXAxis <= 0) {
                    planeXAxis = 1;

                }



                //fighter plane control
                imagePlane = ImageIO.read(new File("plane.PNG"));
                g.drawImage(imagePlane, planeXAxis, planeYAxis, null);


                //fighter plane bullet control
                if (fireBullet) {
                    isFireBulletOn = true;
                    imageBullet = ImageIO.read(new File("bullet.PNG"));
                    g.drawImage(imageBullet, bulletXAxis, bulletYAxis, null);
                    bulletXAxis++;

                    if (bulletXAxis >= 950) {


                        isFireBulletOn = false;

                        fireBullet = false;
                    }


                    //bullet collision
                    if (bulletXAxis >= enemyPlaneXAxis - 40) {

                        if (bulletYAxis <= enemyPlaneYAxis + 110 & bulletYAxis >= enemyPlaneYAxis - 30) {
                       /* System.out.println("collision");*/

                            if (planeCounter % 2 == 1) {
                                enemyPlaneXAxis = 1100 + planeCounter * 10;
                                enemyPlaneYAxis = enemyPlaneYAxis - planeCounter * 15;
                                planeCounter++;
                                if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {
                                    gameIsOn=false;


                                }
                            } else {
                                enemyPlaneXAxis = 1100 + planeCounter * 10;
                                enemyPlaneYAxis = 200 + planeCounter * 15;
                                planeCounter++;
                                if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {
                                    gameIsOn=false;
                                    planeCounter++;

                                }
                            }

                            isFireBulletOn = false;
                            fireBullet = false;


                        }
                    }

                }


                //enemy pane control
                if (coalitionOccurred) {
                    imageEnemyPlane = ImageIO.read(new File(enemyPlane1));
                    g.drawImage(imageEnemyPlane, enemyPlaneXAxis, enemyPlaneYAxis, null);

                    enemyPlaneXAxis--;
                }
                if (enemyPlaneXAxis < 0) {
                    if (planeCounter % 2 == 1) {
                        enemyPlaneXAxis = 1100 + planeCounter * 10;
                        enemyPlaneYAxis = enemyPlaneYAxis - planeCounter * 15;
                        planeCounter++;
                        if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {

                            gameIsOn=false;
                            FirstStageComplete.popUpWindow();
                        }
                    } else {
                        enemyPlaneXAxis = 1100 + planeCounter * 10;
                        enemyPlaneYAxis = 200 + planeCounter * 15;
                        planeCounter++;
                        if (enemyPlaneYAxis < 80 || enemyPlaneYAxis > 340) {
                            gameIsOn=false;


                        }
                    }
                }


                //Dead Control
            /*System.out.print("planeXAxis = "+planeXAxis);
            System.out.println(" enemyPlaneXAxis +"+enemyPlaneXAxis );*/

                if (planeXAxis== enemyPlaneXAxis - 65) {

                    if(planeYAxis+70>=enemyPlaneYAxis && planeYAxis-70<=enemyPlaneYAxis)
                    {
                        System.out.println(planeYAxis +" "+enemyPlaneYAxis);
                        gameIsOn=false;
                        System.out.println("plane crash");

                    }


                    GameOverWindow.popUpWindow();
                }


            } } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            if (!(planeYAxis < 80)) {

                xVelocity = 0;
                yVelocity = -1;
            }

        }
        if (keyCode == KeyEvent.VK_S) {


            if (!(planeYAxis > 318)) {
                xVelocity = 0;
                yVelocity = 1;

            }

        }

        if (keyCode == KeyEvent.VK_D) {


            if (planeXAxis < 970) {
                xVelocity = 1;
                yVelocity = 0;
            }

        }

        if (keyCode == KeyEvent.VK_A) {

            if (planeXAxis > 0) {
                xVelocity = -1;
                yVelocity = 0;
            }


        }
        if (keyCode == KeyEvent.VK_P) {

            if (!isFireBulletOn) {
                bulletXAxis = planeXAxis + 65;
                bulletYAxis = planeYAxis + 25;
                fireBullet = true;
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        xVelocity = 0;
        yVelocity = 0;

        if (!isFireBulletOn) {
            bulletXAxis = planeXAxis + 65;
            bulletYAxis = planeYAxis + 25;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        planeXAxis = planeXAxis + xVelocity;
        planeYAxis = planeYAxis + yVelocity;


        repaint();
    }
}
