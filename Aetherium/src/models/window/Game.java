package models.window;

import models.Enums.ID;
import models.Objects.GameObjects.*;
import models.Objects.GameObjects.Canyon.*;
import models.Objects.GameObjects.Water.*;
import models.Objects.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{

    public static final long serialVersionUID = 1L;
    public boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private SpriteSheet ss;


    private BufferedImage level = null;
    private BufferedImage level2 = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage grass = null;

    private Camera camera;

    private boolean paid_1 = false;
    private boolean le1 = true;



    public Game(){
        playerWindow.createWindow(this);
        start();

        handler = new Handler();
        camera = new Camera(0, 0);
        this.addKeyListener(new KeyInput(handler));

        BufferedImageLoader loader = new BufferedImageLoader();

            level = loader.loadImage("/Field2.png");  //Field2.png
            level2 = loader.loadImage("/Fieldte.png");
            //level = loader.loadImage("/Field.png");

        sprite_sheet = loader.loadImage("/Spritesheet.png");

        ss = new SpriteSheet(sprite_sheet);

        grass = ss.grabImage(1,1,32, 32);

        this.addMouseListener(new MouseInput(handler, camera));
        loadLevel(level);

    }
    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(){
        new Game();
    }

    @Override
    public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTickets = 60;
    double ns = 1000000000 / amountOfTickets;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (isRunning){
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;
        while(delta >= 1){
            tick();
            //updates
            delta--;
        }
        render();
        frames++;

        if (System.currentTimeMillis() - timer > 1000){
            timer += 1000;
            frames = 0;
            //updates = 0
        }
    }
    stop();
    }
    public void tick(){
        for (int i = 0; i < handler.object.size(); i++) {
         if (handler.object.get(i).getId() == ID.Player){
             camera.tick(handler.object.get(i));
         }

        }
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2D = (Graphics2D) g;

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,800,600);  //800, 600





        g2D.translate(-camera.getX(), -camera.getY());

        for (int xx = 0; xx < 30*72; xx+=32){
            for (int yy = 0; yy < 30*72; yy+=32){
                g.drawImage(grass, xx, yy, null);
            }
        }

        //g2D.translate(-camera.getX(), -camera.getY());

        handler.render(g);

        g2D.translate(camera.getX(), camera.getY());

        //g2D.translate(-camera.getX(), -camera.getY());

        g.setColor(Color.gray);
        g.fillRect(5, 5, 200, 32);
        g.setColor(Color.green);
        g.fillRect(5, 5, (handler.hp / 10) * 2 , 32);      //handler.hp * 2
        g.setColor(Color.black);
        g.drawRect(5, 5, 200, 32);


        g.setColor(Color.gray);
        g.fillRect(5, 5 + 32 + 5, 150, 20);
        g.setColor(Color.orange);
        g.fillRect(5, 5 + 32 + 5, handler.xp * 3 , 20);      //handler.hp * 2
        g.setColor(Color.BLACK);
        g.drawString("Stufe: " + handler.xp_Stufe, 20 + 32, 20 + 32 + 5);
        g.setColor(Color.black);
        g.drawRect(5, 5 + 32 + 5, 150, 20);



        g.setColor(Color.lightGray);
        g.fillRect(  6 + 150, 6 + 32 + 5, 60, 20);

        g.setColor(Color.BLACK);
        g.drawString("Coins: " + handler.gold,  10 + 150, 20 + 32 + 5);  //20 + 32 +


        g.setColor(Color.gray);
        g.fillRect(5,  5 + 32 , 200, 5);
        g.setColor(Color.orange);
        g.fillRect(5,  5 + 32 , handler.countdown_2 * 4 , 5);      //handler.hp * 2
        g.setColor(Color.black);
        g.drawRect(5,  5 + 32 , 200, 5);

        //g.setColor(Color.gray);
        //g.fillRect(5,  5 + 32 , 150, 10);
        //g.setColor(Color.orange);
        //g.fillRect(5,  5 + 32 , handler.countdown_2 * 3 , 10);      //handler.hp * 2
        //g.setColor(Color.black);
        //g.drawRect(5,  5 + 32 , 150, 10);

        g.setColor(Color.gray);
        g.fillRect(5, 25 + 32 + 5, 100, 10);
        g.setColor(Color.yellow);
        g.fillRect(5, 25 + 32 + 5, (handler.countdown_1 / 10) , 10);      //handler.hp * 2
        g.setColor(Color.black);
        g.drawRect(5, 25 + 32 + 5, 100, 10);


        if (handler.isdeat){
            g.setColor(Color.black);
            g.drawString("GAME OVER", 400, 300);
        }

        if (handler.pausenmnue){
            //handler.menuedotpostition = 1;

            g.setColor(Color.black);
            g.drawRect(250, 50, 400, 400);
            g.setColor(Color.gray);
            g.fillRect(250, 50, 400, 400);



            g.setColor(Color.black);
            g.drawString("|| Pause", 430, 100);     //330

            g.setColor(Color.black);
            g.drawString("Level: " + handler.level, 530, 100);     //330

            g.setColor(Color.black);
            g.drawString("Back to game", 330, 150 + 9);

            g.setColor(Color.black);
            g.drawString("Save Game", 330, 200 + 9);

            g.setColor(Color.black);
            g.drawString("Settings", 330, 250 + 9);

            g.setColor(Color.black);
            g.drawString("Exit Game", 330, 300 + 9);



            if (handler.menuedotpostition == 1) {
                g.setColor(Color.red);
                g.fillOval(300, 150, 10, 10);
            }
            else if (handler.menuedotpostition == 2) {
                g.setColor(Color.red);
                g.fillOval(300, 200, 10, 10);
            }
            else if (handler.menuedotpostition == 3){
                g.setColor(Color.red);
                g.fillOval(300, 250, 10, 10);
            }
            else if (handler.menuedotpostition == 4){
                g.setColor(Color.red);
                g.fillOval(300, 300, 10, 10);
            }

            /*
            if (handler.wanttosave == true && handler.countdown_for_menuedot != 0){

                g.setColor(Color.lightGray);
                g.fillRect(350, 150, 200, 100);
                g.setColor(Color.black);
                g.drawRect(350, 150, 200, 100);

                g.setColor(Color.black);
                g.drawString("Funktion noch nicht verfügbar", 360, 185 );

            }
            */



        }



        if (handler.ifplayeronnpc == true){

            g.setColor(Color.lightGray);
            g.fillRect(200, 534, 500, 20);
            g.setColor(Color.black);
            g.drawRect(200, 534, 500, 20);
            if (handler.xp_Stufe >= 2){
                //if (handler.countdown_for_talking_1 == 0 && paid_1 == false) {

                //    handler.countdown_for_talking_1 = 500;
                //}
                //if (handler.countdown_for_talking_1 > 0) {
                if (paid_1 == false) {
                    g.setColor(Color.black);
                    g.drawString("I need 3 coins for tip please [E]", 400, 550);
                    //}
                }
                if (handler.countdown_for_Gold_loosing == 0 && paid_1 == false && handler.playerwanttotalk == true){
                    handler.gold = handler.gold - 3;
                    paid_1 = true;
                    handler.countdown_for_Gold_loosing = 300;
                }
                if(handler.countdown_for_Gold_loosing != 0) {
                    g.setColor(Color.red);
                    g.drawString("-3 coins", 10 + 150, 15 + 32 + 20 + 5);

                    g.setColor(Color.black);
                    g.drawString("Thanks for the tip", 400, 550);

                }
                if (handler.countdown_for_Gold_loosing == 0 && paid_1 == true) {

                    g.setColor(Color.black);
                    g.drawString("Let's Start the Adventure", 400, 550); //Hello I am a NPC!
                    //le1 = true;
                    lvl2();

                }

            }
            else {
                g.setColor(Color.black);
                g.drawString("You must first kill hedrons to get xp.Then we can talk", 300, 550);

                //le1 = true;

            }

        }


        //g2D.translate(-camera.getX(), -camera.getY());

        //handler.render(g);

        g.dispose();
        bs.show();
    }



    private void lvl2(){
        loadLevel(level2);
    }

    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++){
            for (int yy = 0; yy < h; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if ((red == 0) && (green == 0) && (blue == 0)){
                    handler.addObject(new Water_full(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 255) && (blue == 0)){
                    handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, ss));
                }
                if ((red == 255) && (green == 0) && (blue == 0)){
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler, ss));
                }

                if ((red == 39) && (green == 198) && (blue == 101)){
                    handler.addObject(new NPC(xx*32, yy*32, ID.NPC, handler, ss));
                }



                if ((red == 255) && (green == 0) && (blue == 255)){
                    handler.addObject(new Water_left(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 0) && (blue == 255)){
                    handler.addObject(new Water_right(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 255) && (blue == 255)){
                    handler.addObject(new Water_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 255) && (blue == 255)){
                    handler.addObject(new Water_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 127) && (blue == 255)){
                    handler.addObject(new Water_left_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 0) && (green == 255) && (blue == 127)){
                    handler.addObject(new Water_right_down(xx*32, yy*32, ID.Block, ss));
                }


                if ((red == 100) && (green == 255) && (blue == 100)){
                    handler.addObject(new Canyon_left(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 100) && (green == 100) && (blue == 255)){
                    handler.addObject(new Canyon_right(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 255) && (green == 100) && (blue == 100)){
                    handler.addObject(new Canyon_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 255) && (green == 255) && (blue == 100)){
                    handler.addObject(new Canyon_left_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 255) && (green == 100) && (blue == 255)){
                    handler.addObject(new Canyon_right_down(xx*32, yy*32, ID.Block, ss));
                }
                if ((red == 255) && (green == 255) && (blue == 255)){
                    handler.addObject(new Canyon_full(xx*32, yy*32, ID.Block, ss));
                }



            }
        }
    }
}

