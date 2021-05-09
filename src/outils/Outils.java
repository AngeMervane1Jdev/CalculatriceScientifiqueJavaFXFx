package outils;

public class Outils {
    //-----------------Factoriel---------------------------------------------------
    static public double fact(String n){
        int result=1;
        for (int i = 1; i <= Integer.parseInt(n); i++) {
            result *= i;
        }
        return result;

    }

  static   public String fromDec(String dep,int bd)
    {
        String s="";
        int aux=Integer.parseInt(dep);
        while(aux/bd!=0)
        {
            if(aux%bd<10)
            {s=(aux%bd)+s;}
            else
            {s=toHexaCarct(aux%bd)+s;}
            aux=aux/bd;
        }
        if(aux%bd<10) {s=(aux%bd)+s;}
        else {s=toHexaCarct(aux%bd)+s;}
        return s;
    }
    static public int conversionCarct(char c)
    {
        if(c=='F') return 15;
        else if(c=='E') return 14;
        else if(c=='D') return 13;
        else if(c=='C') return 12;
        else if(c=='B') return 11;
        else if(c=='A') return 10;
        else return(Character.getNumericValue(c));
    }
   static char toHexaCarct(int x)
    {
        if(x==10) return 'A';
        else if(x==11) return 'B';
        else if(x==12) return 'C';
        else if(x==13) return 'D';
        else if(x==14) return 'E';
        else return 'F';
    }
   public static int toDec(String dep, int bs)
    {
        int i=(dep.length())-1;
        String s=dep;
        int puis=0;
        int res=0;
        while(i>=0)
        {
            res=res+conversionCarct(s.charAt(i))*((int)(Math.pow(bs,puis)));
            puis++;
            i--;
        }
        return res;
    }
      public static boolean isNumber(char c){
        return c == '1' || c == '2' || c == '3' || c == '4' ||
                c == '5' || c == '6' || c == '7' || c == '9' ||
                c == '8' || c == '0';
    }
    public static boolean isOp(char c){
        return c == '-' || c == '+';
    }
}
