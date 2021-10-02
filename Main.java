package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    Scanner s = new Scanner(System.in);
    Random rand = new Random();
    boolean toss() {
        int me,com;
        String result;
        System.out.print("Choose odd or even for toss (o / e) : ");
        String ch = s.next();
        System.out.print("your turn : choose between 1 to 10 : ");
        me = s.nextInt();
        com = rand.nextInt(10) + 1;
        System.out.println("Your opponent chose : "+ com);
        System.out.println("******************************************************************");
        if ((me + com) % 2 ==0)
            result = "e";
        else
            result = "o";
        return result.equals(ch);
    }

    String choose(boolean b) {
        int comch;
        if (b) {
            System.out.println("You won the toss :: ");
            System.out.print("Choose to bat or bowl first ? ('bat'/'bowl') :   ");
            return s.next();
        }
        else {
            System.out.println("You lose the toss :: ");
            comch = rand.nextInt(2) + 1;
            if (comch == 1) {
                System.out.println("Your opponent chose to bat first :: ");
                return "bowl";
            } else {
                System.out.println("Your opponent chose to bowl first :: ");
                return "bat";
            }
        }
    }

    int match(String b,int count,int prevscore) {
        int me,com;
        int mesum = 0,comsum = 0;
        if (count == 1) {
            prevscore = 100000000;
        }
        do {
            System.out.print("Your turn(1-10) :      ");
            me = s.nextInt();
            com = rand.nextInt(10) + 1;
            System.out.println("Opponent turn(1-10) :    "+ com);
            System.out.println("******************************************************************");
            System.out.println("******************************************************************");
            mesum += me;
            comsum += com;
            if(count==2 && b.equals("bat") && mesum > prevscore)
                break;
            if (count == 2 && b.equals("bowl") && comsum > prevscore)
                break;
        } while(me != com);
        if (me == com) {
            mesum -= me;
            comsum -= com;
        }

        if (b.equals("bat"))
            return mesum;
        else
            return comsum;
    }

    void result(String b) {
        int myscore,comscore;
        if (b.equals("bat")) {
            System.out.println("You are to bat : ");
            myscore = match("bat",1,0);
            System.out.println("Your score :  "+ myscore);
            System.out.println("******************************************************************");
            System.out.println("******************************************************************");
            System.out.println("You are to bowl : ");
            comscore = match("bowl",2,myscore);
            System.out.println("Opponent score : "+ comscore);
            System.out.println("******************************************************************");
            System.out.println("******************************************************************");
        }
        else {
            System.out.println("You are to bowl : ");
            comscore = match("bowl",1,0);
            System.out.println("Opponent score :  "+ comscore);
            System.out.println("******************************************************************");
            System.out.println("******************************************************************");
            System.out.println("You are to bat : ");
            myscore = match("bat",2,comscore);
            System.out.println("Your score : "+ myscore);
            System.out.println("******************************************************************");
            System.out.println("******************************************************************");
        }

        if (myscore > comscore) {
            System.out.println("You won the match :: ");
            System.out.println("******************************************************************");
        }
        else if(comscore > myscore) {
            System.out.println("Your opponent won the match :: ");
            System.out.println("******************************************************************");
        }
        else {
            System.out.println("Match Draw :: ");
            System.out.println("******************************************************************");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
	    boolean t;
	    String ch,again;
        Main m = new Main();
        do {
            t = m.toss();
            ch = m.choose(t);
            m.result(ch);
            System.out.println("Do you want to play again :: (y/n) ");
            again = s.next();
        } while(again.equals("y"));
    }
}
