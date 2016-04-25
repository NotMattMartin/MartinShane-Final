/*
        ONLINE EDIT
        http://collabedit.com/5hggg
        
        Resources:
        https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html
        s
*/
import java.awt.*;
import java.applet.*;
    

@SuppressWarnings("serial")
public class FinalProject extends Applet
{
    /*
        IMAGE SOURCES:
        bg = https://drive.google.com/uc?export=download&id=0Bzif0GL1CL-NVUVGRy1JZFVTVGc
    */
	
	public void paint(Graphics g)
    {
		if(lose != true)
        {
        	drawAll(g,cx,cy,ex,ey); //draw all the things
        	if(click) //if mouse clicked then
        	{
        		move(g);
        		Expo.delay(100);
        		moveE(g);
        	}
        }
    }

	public boolean mouseDown(Event e, int x, int y) //is the mouse down
	{
		click = true;
		mx = x;
		my = y;
		repaint();
		return true;
	}

    
    public void update(Graphics g)   {   paint(g);   } //paint without deleting everything

    
    Image bg,character,enemies; //init images
    int cx = 960; int cy = 600; //character x and y
    int ex = 100; int ey = 200; //enemies x and y
    int mx = 0  ; int my = 0  ; //mouse x and y 
    boolean click;int ch = 200; // click boolean and character health
    boolean lose; int turn = 0; //lose yet and turn number
    
    public void init() // init things and make sure images are loaded
    {
        bg = getImage(getDocumentBase(),"bg.png");
        character = getImage(getDocumentBase(),"character.png");
        enemies = getImage(getDocumentBase(),"enemies.png");
        //character = getImage(getDocumentBase(),"character.png");
        //enemies = getImage(getDocumentBase(),"enemies.png");
        
        // The following MediaTracker/try/catch code ensures that
            // all images are loaded before the program continues.
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(bg,1);
        tracker.addImage(character,1);
        tracker.addImage(enemies,1);
        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
    
    
    public void drawAll(Graphics g, int cx, int cy, int ex, int ey) // draws all
    {
    	drawBG(g);
        drawEnemies(g,ex,ey);
        drawCharacter(g,cx,cy);
        drawCHealth(g);
    }
    
    public void DrawPlayerText(Graphics g)//draw player turn info stuff
    {
        if(lose != true)//if didnt lose
        {
            Expo.setColor(g, Expo.green);
            Expo.setFont(g,"Arial",Font.BOLD,100);
            Expo.drawString(g, "Player turn #" + turn, 200, 200);
        }
    }
    
    public void DrawEnemyText(Graphics g)//draw enemy turn info stuff
    {
        if(lose != true) //if didnt lose
        {
            Expo.setColor(g, Expo.green);
            Expo.setFont(g,"Arial",Font.BOLD,100);
            Expo.drawString(g, "Barbarian turn #" + turn, 200, 200);
        }
    }
    
    public void drawBG(Graphics g) // draws background
    {
        Expo.setBackground(g,Expo.black);
        g.drawImage(bg,0,0,this);
    }
    
    public void drawCharacter(Graphics g, int cx, int cy) //draws char
    {
    	g.drawImage(character,cx,cy,this);
    }
    
    public void drawEnemies(Graphics g, int ex, int ey) //draws enemies
    {
    	g.drawImage(enemies,ex,ey,this);
    }
    
    public void drawCHealth(Graphics g) //draws health bar
    {
    	if(cx == ex && cy == ey) //if enemies is on you take away health
        {
        	ch -= 10;
        }
    	Expo.setColor(g, Expo.black);
    	Expo.drawRectangle(g, 10, 950, 210, 975);
    	Expo.setColor(g, Expo.red);
    	Expo.fillRectangle(g, 11, 951, 11+ch, 974);
    	Expo.setColor(g, Expo.black);
    	if(ch <= 0) //if your health is negative
    	{
    		Expo.setBackground(g, Expo.black);
    		Expo.setColor(g, Expo.red);
            Expo.setFont(g,"Arial",Font.BOLD,100);
            Expo.drawString(g, "You Lose", 200, 200);
            lose = true;
    	}
    }
    public void move(Graphics g) //moves char
    {
    	if (mx >= 960) //is has to move up
    	{
    		for (int x = cx;x <= mx; x+=10)
    		{
    			cx = x;
    			Expo.delay(50);
    			drawAll(g,cx,cy,ex,ey);
    			DrawPlayerText(g);
    		}
    	}
    	else
    	{
    		for (int x = cx;x >= mx; x-=10) //if doesn't
    		{
    			cx = x;
    			Expo.delay(50);
    			drawAll(g,cx,cy,ex,ey);
    			DrawPlayerText(g);
    		}
    	}
    	
    	if (my >= 540) //if top half
    	{
    		for (int y = cy;y <= my; y+=10) //it has to move up
    		{
    			cy = y;
    			Expo.delay(50);
    			drawAll(g,cx,cy,ex,ey);
    			DrawPlayerText(g);
    		}
    	}
    	else
    	{
    		for (int y = cy;y >= my; y-=10) //if doesn't
    		{
    			cy = y;
    			Expo.delay(50);
    			drawAll(g,cx,cy,ex,ey);
    			DrawPlayerText(g);
    		}
    	}
    }
    
    public void moveE(Graphics g) //moves enemy
    {
    	
    	if (cx > ex) //if character x is more than enemy move enemy to a higher x
        {
            for (int x = ex;x <= cx; x+=10)
            {
                ex = x;
                Expo.delay(50);
                drawAll(g,cx,cy,ex,ey);
                DrawEnemyText(g);
            }
        }
        else
        {
            for (int x = ex;x >= cx; x-=10)//if character x is less than enemy move enemy to a less x
            {
                
                ex = x;
                Expo.delay(50);
                drawAll(g,cx,cy,ex,ey);
                DrawEnemyText(g);
            }
        }
        
        if (cy > ey)
        {
            for (int y = ey;y <= cy; y+=10) //same as before but y
            {
                
                ey = y;
                Expo.delay(50);
                drawAll(g,cx,cy,ex,ey);
                DrawEnemyText(g);
            }
        }
        else
        {
            for (int y = ey;y >= cy; y-=10)
            {
                
                ey = y;
                Expo.delay(50);
                drawAll(g,cx,cy,ex,ey);
                DrawEnemyText(g);
    		}
    	}
    }
}