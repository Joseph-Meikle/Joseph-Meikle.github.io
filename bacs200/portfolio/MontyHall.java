    /*******************************************************************************
    *                     Final Project                                            *
    *                                                                              *
    * PROGRAMMER:        Joseph Meikle (meik4379@bears.unco.edu)                   *
    * CLASS:             CS160 – Structured Programming                            *
    * INSTRUCTOR:        Dean Zeller                                               *
    * TERM:              Spring 2020                                               *
    * SUBMISSION DATE:   5/3/2020                                                  *
    *                                                                              *
    * DESCRIPTION:                                                                 *
    * This program uses simulations to test the Monty Hall problem.                *
    *                                                                              *
    * COPYRIGHT:                                                                   *
    * This program is copyright (c)2020 Joseph Meikle and Dean Zeller.             *
    * It is original work without use of outside sources.                          *
    *                                                                              *
    *******************************************************************************/
    import java.util.*;
    import java.awt.*;
    public class Final {
    static int switchsuccess=0;
    static int switchfail=0;
    static int samesuccess=0;
    static int samefail=0;
    static int testn=0;
        /***********************************************************************
        * Method:   main                                                       *
        * Purpose:  Takes the player through the simulation.                   *
        * Parameters:                                                          *
        *     N/A                                                              *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
    public static void main(String[] args) {
      int prize=0;
      int doorstate=0;
      int selection=0;
      int elimination=0;
      int oldselection=0;
      StdDraw.setPenRadius(0.05);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      StdDraw.setPenColor(255,255,255);
      Font font = new Font("Arial", Font.BOLD, 25);
      StdDraw.setFont(font);
      StdDraw.enableDoubleBuffering();
      StdDraw.pause(1000);
      StdDraw.text(.5,.76+.14,"There is a well known puzzle,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.68+.14,"known as the Monty Hall problem.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.56+.14,"There are three doors,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.48+.14,"with a prize behind one of them.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.36+.14,"After you choose a door,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.28+.14,"an unchosen door is eliminated.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.16+.14,"If you switch to the remaining door,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.08+.14,"2 in 3 times you will recieve your prize.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.1,"Let's try it out.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      StdDraw.show();
      StdDraw.pause(1500);
      doors(0,0,0);
      StdDraw.show();
      prize=(int)(Math.random()*3+1);
      StdDraw.pause(1000);
      StdDraw.setPenColor(255,255,255);
      StdDraw.text(.5,.82+0.08,"The prize has been placed.",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.82,"Click on a door to choose it.",0);
      StdDraw.show();
      selection = click();
      oldselection = selection;
      String x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.setPenColor(255,255,255);
      StdDraw.text(.5,.18,"You have chosen door number "+selection+".",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.1,"Now an unchosen door will be eliminated.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      elimination = (int)(Math.random()*3+1);
      while (elimination==selection || elimination==prize) {elimination = (int)(Math.random()*3+1);}
      x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      //StdDraw.text(.45,.5,x,0);//fortesting
      //StdDraw.text(.45,.3,(int)x.charAt(0)+"",0);//fortesting
      //StdDraw.text(.45,.5,prize+"",0);//fortesting
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.pause(500);
      StdDraw.setPenColor(255,255,255);
      StdDraw.text(.5,.82+0.08,"Would you like to keep your door,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.82,"or switch to the remaining door?",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.18,"Click on the same door to keep it,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.1,"or the remaining door to switch.",0);
      StdDraw.show();
      selection=click();
      while (selection==elimination) {selection=click();}
      x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.pause(500);
      StdDraw.setPenColor(255,255,255);
      if (oldselection==selection) {StdDraw.text(.5,.82+0.08,"You've chosen to keep your door.",0);}
      else {StdDraw.text(.5,.82+0.08,"You've chosen to switch doors.",0);}
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.82,"Now we'll reveal where the prize was.",0);
      StdDraw.show();
      StdDraw.pause(2000);
      doors(3,3,3);
      trophy(prize);
      StdDraw.show();
      StdDraw.setPenColor(255,255,255);
      StdDraw.pause(1000);
      if (prize==selection) {StdDraw.text(.5,.18,"Well done, you chose the right door,",0);}
      else {StdDraw.text(.5,.18,"That's too bad,",0);}
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.1,"but one test can't prove anything.",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      doors(0,0,0);
      StdDraw.setPenColor(255,255,255);
      StdDraw.show();
      StdDraw.pause(500);
      StdDraw.text(.5,.82+0.08,"Let's quickly run 10 iterations.",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.82,"Now we can start to find the probabilities.",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.18,"A new test will begin as the last one ends.",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.1,"Your data will be tracked on the top.",0);
      StdDraw.show();
      StdDraw.pause(2000);
      simulation(false,10);
      //doors(0,0,0);
      //StdDraw.show();
      
      StdDraw.pause(1500);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      StdDraw.show();
      StdDraw.pause(500);
      StdDraw.setPenColor(255,255,255);
      StdDraw.text(.5,.76+.14,"Let's take a look at the results.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.68+.14-.04,"Switching paid off "+(Math.round((switchsuccess*100/(double)(switchsuccess+switchfail))))+"% of the time.");
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.56+.14,"Compared with the ideal 67% of the time,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      if (switchsuccess/(double)(switchsuccess+switchfail)>=.6 && switchsuccess/(double)(switchsuccess+switchfail)<=.7) {StdDraw.text(.5,.48+.14,"we're right on the mark.",0);}
      else if (switchsuccess/(double)(switchsuccess+switchfail)>=.5 && switchsuccess/(double)(switchsuccess+switchfail)<.85) {StdDraw.text(.5,.48+.14,"we're not that far off.",0);}
      else {StdDraw.text(.5,.48+.14,"it doesn't really hold up.",0);}
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.36+.14,"However, while 10 tests is a start,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.28+.14,"we're going to need many, many more.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      //StdDraw.text(.5,.16+.14,"Would you like to continue manually,",0);
      StdDraw.text(.5,.16+.14,"Now the test will run itself,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.08+.14,"and we'll see the results of 1000 tests.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      //StdDraw.text(.5,.1,"Let's try it out.",0);
      /*StdDraw.setPenColor(50,40,20);
      StdDraw.rectangle(.3,.11,.1,.03);
      StdDraw.filledRectangle(.3,.11,.1,.03);
      StdDraw.rectangle(.7,.11,.1,.03);
      StdDraw.filledRectangle(.7,.11,.1,.03);
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.text(.3,.1,"Manual",0);
      StdDraw.text(.7,.1,"Auto",0);
      StdDraw.show();
      int X=button(.2975,.1075,.125,.08,.6975,.1075,.125,.08,true);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      //StdDraw.text(.5,.08+.14,X+"",0);
      StdDraw.show();
      */
      //StdDraw.pause(200);
      switchsuccess=0;
      switchfail=0;
      samesuccess=0;
      samefail=0;
      testn=0;
      simulation(/*X==2*/true,1000);
      StdDraw.pause(1500);
      
      
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      StdDraw.show();
      StdDraw.pause(500);
      StdDraw.setPenColor(255,255,255);
      StdDraw.text(.5,.76+.14,"Let's take another look at the data.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.68+.14-.04,"Switching paid off "+(Math.round((switchsuccess*100/(double)(switchsuccess+switchfail))))+"% of the time,");
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.68+.06-.04,"and staying paid off "+(Math.round((samesuccess*100/(double)(samesuccess+samefail))))+"% of the time.");
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.56+.02,"Compared with the ideal 67% of the time,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      if (switchsuccess/(double)(switchsuccess+switchfail)>=.6 && switchsuccess/(double)(switchsuccess+switchfail)<=.7) {StdDraw.text(.5,.48+.02,"we're right on the mark.",0);}
      else if (switchsuccess/(double)(switchsuccess+switchfail)>=.5 && switchsuccess/(double)(switchsuccess+switchfail)<.85) {StdDraw.text(.5,.48+.02,"we're not that far off.",0);}
      else {StdDraw.text(.5,.48+.02,"it doesn't really hold up.",0);}
      StdDraw.show();
      StdDraw.pause(1500);
      StdDraw.text(.5,.36+.02,"More tests will get us closer,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.28+.02,"but you've got better things to do.",0);
      StdDraw.show();
      StdDraw.pause(1500);
      //StdDraw.text(.5,.16+.14,"Would you like to continue manually,",0);
      StdDraw.text(.5,.16+.02,"Thank you for participating,",0);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.text(.5,.08+.02,"and have a great summer.",0);
      StdDraw.show();
      StdDraw.pause(2000);
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      StdDraw.show();
      StdDraw.pause(1000);
   }
        /***********************************************************************
        * Method:   door                                                       *
        * Purpose:  Draws a door in a specified state in a specified location. *
        * Parameters:                                                          *
        *     Location x and y, state of door (ie: selected) z.                *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
   private static void door(double x, double y,int z){
      StdDraw.setPenColor(StdDraw.YELLOW);
      if (z==1) {StdDraw.setPenColor(255,255,255);}
      if (z==2) {StdDraw.setPenColor(50,40,20);}
      StdDraw.filledRectangle(x,y,.043*3,.083*3); //.258 by .498
      StdDraw.setPenColor(50,40,20);
      if (z==3) {StdDraw.setPenColor(0,0,0);}
      StdDraw.filledRectangle(x,y,.04*3,.08*3);
      StdDraw.setPenColor(StdDraw.YELLOW);
      if (z==1) {StdDraw.setPenColor(255,255,255);}
      if (z==2) {StdDraw.setPenColor(50,40,20);}
      if (z==3) {StdDraw.setPenColor(0,0,0);}
      StdDraw.filledCircle(x+.018*3,y,.006*3);
   }
        /***********************************************************************
        * Method:   doors                                                      *
        * Purpose:  Draws three doors.                                         *
        * Parameters:                                                          *
        *     State of each door, a, b, c.                                     *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
   private static void doors(int a, int b, int c){
      door(.179,.5,a);
      door(.5,.5,b);
      door(1-.179,.5,c);
   }
        /***********************************************************************
        * Method:   click                                                      *
        * Purpose:  Waits until the user clicks on a door.                     *
        * Parameters:                                                          *
        *     N/A                                                              *
        * Return Value:  (int) Door clicked.                                   *
        ***********************************************************************/
   private static int click(){
      while (StdDraw.isMousePressed()){}
      while (!StdDraw.isMousePressed()){}
      if (StdDraw.mouseY()<.5+.249 && StdDraw.mouseY()>.5-.249){
        if (StdDraw.mouseX()>.179-.129 && StdDraw.mouseX()<.179+.129 )
        {
           return 1; 
        }
        if (StdDraw.mouseX()>.5-.129 && StdDraw.mouseX()<.5+.129 )
        {
           return 2; 
        }
        if (StdDraw.mouseX()>1-.179-.129 && StdDraw.mouseX()<1-.179+.129 )
        {
           return 3; 
        }
      }
      return click();
   }
        /***********************************************************************
        * Method:   click                                                      *
        * Purpose:  Lets the user click buttons too (not included in final).   *
        * Parameters:                                                          *
        *     Button 1 and 2 location and size x1,y1,dx1,dy1,x2,y2,dx2,dy2.    *
        * Return Value:  (int) Button or door pressed.                         *
        ***********************************************************************/
   private static int click(double x1,double y1,double dx1,double dy1,double x2,double y2,double dx2,double dy2){
      while (StdDraw.isMousePressed()){}
      while (!StdDraw.isMousePressed()){}
      if (button(x1,y1,dx1,dy1,x2,y2,dx2,dy2,false)!=0) {return button(x1,y1,dx1,dy1,x2,y2,dx2,dy2,false)+3;}
      if (StdDraw.mouseY()<.5+.249 && StdDraw.mouseY()>.5-.249){
        if (StdDraw.mouseX()>.179-.129 && StdDraw.mouseX()<.179+.129 )
        {
           return 1; 
        }
        if (StdDraw.mouseX()>.5-.129 && StdDraw.mouseX()<.5+.129 )
        {
           return 2; 
        }
        if (StdDraw.mouseX()>1-.179-.129 && StdDraw.mouseX()<1-.179+.129 )
        {
           return 3; 
        }
      }
      return click();
   }
        /***********************************************************************
        * Method:   trophy                                                     *
        * Purpose:  Draws the prize behind the doors.                          *
        * Parameters:                                                          *
        *      Which door it's behind n.                                       *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
   private static void trophy(int n){
      StdDraw.setPenColor(StdDraw.GREEN);
      StdDraw.filledCircle(-0.142+.321*n,.5,.05); 
      StdDraw.setPenColor(255,255,255);
      Font font = new Font("Arial", Font.BOLD, 25);
      Font biggerfont = new Font("Arial", Font.BOLD, 35);
      StdDraw.setFont(biggerfont);
      StdDraw.text(-0.142+.321*n,.493,"$",0);
      StdDraw.setFont(font);
   }
        /***********************************************************************
        * Method:   simulation                                                 *
        * Purpose:  Runs the test over and over.                               *
        * Parameters:                                                          *
        *     auto- Whether it's running automatically, count- How many tests. *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
   private static void simulation(boolean auto,int count)
   { 
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      doors(0,0,0);
      testn++;
      data();
      StdDraw.show();
      int prize=(int)(Math.random()*3+1);
      int selection=-1;
      if (auto) {selection = choose(-1,-1);}
      else {selection = click();}
      int oldselection = selection;
      int elimination = 0;
      String x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      //elimination = (int)(Math.random()*3+1);
      //while (elimination==selection || elimination==prize) {elimination = (int)(Math.random()*3+1);}
      elimination=choose(selection,prize);
      x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      data();
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      if (auto) {
          //selection = (int)(Math.random()*3+1);
          //while (selection==elimination) {elimination = (int)(Math.random()*3+1);}
          selection=choose(elimination,-1);
        }
      else {
          selection=click();
          while (selection==elimination) {selection=click();}
        }
      x = 1000+Math.pow(10,selection-1)+2*Math.pow(10,elimination-1)+"";
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      StdDraw.pause(5);
      if (!auto) {StdDraw.pause(95);}
      if (prize==selection) {
        if (oldselection==selection) {samesuccess++;}
        else {switchsuccess++;}
        }
      else {if (oldselection==selection) {samefail++;}
        else {switchfail++;}
      }
      StdDraw.setPenColor(80,200,250);
      StdDraw.filledRectangle(0,0,1,1);
      data();
      doors((int)x.charAt(3)-48,(int)x.charAt(2)-48,(int)x.charAt(1)-48);
      StdDraw.show();
      doors(3,3,3);
      trophy(prize);
      StdDraw.show();
      StdDraw.pause(5);
      if (!auto) {StdDraw.pause(195);}
      if (count>1 || count<=-1) {simulation(auto,count-1);}
    }
        /***********************************************************************
        * Method:   data                                                       *
        * Purpose:  Displays the data on the top of the screen.                *
        * Parameters:                                                          *
        *     N/A                                                              *
        * Return Value:  N/A                                                   *
        ***********************************************************************/
   private static void data(){
      StdDraw.setPenColor(255,255,255);
      StdDraw.textLeft(.1,.9,"Test "+testn);
      StdDraw.textLeft(.4,.92,"Switch success: "+switchsuccess+" / "+(switchsuccess+switchfail));
      StdDraw.textLeft(.4,.85,"Same success: "+samesuccess+" / "+(samesuccess+samefail));
    }
        /***********************************************************************
        * Method:   button                                                     *
        * Purpose:  Lets the user click button (not included in final).        *
        * Parameters:                                                          *
        *     Button 1 and 2 location and size x1,y1,dx1,dy1,x2,y2,dx2,dy2,    *
        *       whether or not to wait for a click wait.                       *
        * Return Value:  (int) Button pressed.                                 *
        ***********************************************************************/
   private static int button(double x1,double y1,double dx1,double dy1,double x2,double y2,double dx2,double dy2,boolean wait){
      if (wait) {
        while (StdDraw.isMousePressed()){}
        while (!StdDraw.isMousePressed()){}
      }
      if (StdDraw.mouseX()>=x1 && StdDraw.mouseX()<=x1+dx1 && StdDraw.mouseY()>=y1 && StdDraw.mouseY()<=y1+dy1)
      {
         return 1; 
      }
      if (StdDraw.mouseX()>=x2 && StdDraw.mouseX()<=x2+dx2 && StdDraw.mouseY()>=y2 && StdDraw.mouseY()<=y2+dy2)
      {
         return 2; 
      }
      if (!wait) {return 0;}
      return button(x1,y1,dx1,dy1,x2,y2,dx2,dy2,wait);
    }
        /***********************************************************************
        * Method:   choose                                                     *
        * Purpose:  Gives a number between 1 and 3, not specific numbers.      *
        * Parameters:                                                          *
        *     Numbers not to return not, not2.                                 *
        * Return Value:  A random int between 1 and 3.                         *
        ***********************************************************************/
   private static int choose(int not,int not2)
   {
      if (not+not2==-2) {return (int)(Math.random()*3+1);}
      if (not>0 && not2>0 && not!=not2){
         if (not+not2==3) {return 3;}
         if (not+not2==4) {return 2;}
         if (not+not2==5) {return 1;}
        }
      if (not*not2<0 || not==not2) {
         if (not==1) {return (int)(Math.random()*2+2);} 
         if (not==3) {return (int)(Math.random()*2+1);}
         if (not==2) {
            int temp=(int)Math.random()*2+1;
            if (temp==2) return 3;
            else return temp;
            }
        }
      return 0;
    }
}
