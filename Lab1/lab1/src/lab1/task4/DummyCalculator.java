package lab1.task4;

public class DummyCalculator {
    static int getType(String s){
        try {
            Integer.parseInt(s);
            return 1;
        }catch(NumberFormatException e){
            try{
                Double.parseDouble(s);
                return 2;
            }catch(NumberFormatException f){
                try{

                    Boolean.parseBoolean(s);
                    return 0;
                }catch(NumberFormatException g){
                    return -1;
                }
            }
        }
    }
    public static void main(String[] args){
        int arg1=getType(args[0]), arg2=getType(args[2]);
        if(arg1==0 && arg2==0){
            Boolean x=Boolean.valueOf(args[0]);
            Boolean y=Boolean.valueOf(args[2]);
            if(args[1].equals("&")){
                System.out.println((x && y));
            }else{
                System.out.println((x || y));
            }
        } else if(arg1==1 && arg2==1){
            int x = Integer.valueOf(args[0]);
            int y = Integer.valueOf(args[2]);
            switch(args[1]){
                case "+":{
                    System.out.println((x+y));
                    break;}
                case "-":{
                    System.out.println((x-y));
                    break;
                }
                case "*":{
                    System.out.println((x*y));
                    break;
                }
                case "/":{
                    System.out.println((x/y));
                }
            }
        }else{
            double x = Double.valueOf(args[0]);
            double y = Double.valueOf(args[2]);
            switch(args[1]){
                case "+":{
                    System.out.println((x+y));
                    break;}
                case "-":{
                    System.out.println((x-y));
                    break;
                }
                case "*":{
                    System.out.println((x*y));
                    break;
                }
                case "/":{
                    System.out.println((x/y));
                }
            }
        }

    }
}
