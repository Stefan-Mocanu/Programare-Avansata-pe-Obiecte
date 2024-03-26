package lab4.l4t2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final public class InputConverter {
    private static int getType(String s) {
        try {
            Integer.parseInt(s);
            return 1;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(s);
                return 2;
            } catch (NumberFormatException f) {
                try {

                    if(!Objects.equals(s, "true") && !Objects.equals(s, "false")) throw new NumberFormatException();
                    return 0;
                } catch (NumberFormatException g) {
                    return -1;
                }
            }
        }
    }

    public static List<CalculatorRequest> mapRequests(String[] args) {
        List<CalculatorRequest> res = new ArrayList<CalculatorRequest>();
        for (int i = 0; i < args.length; i += 3) {
            int arg1 = getType(args[i]), arg2 = getType(args[i + 2]);
            try {
                if (arg1 == -1 || arg2 == -1)throw new UnknownOperandTypeException();
            }catch(UnknownOperandTypeException e){
                System.err.println(args[i]+args[i+1]+args[i+2]);
                continue;
            }
            if (arg1 == 0 && arg2 == 0) {
                Boolean x = Boolean.valueOf(args[i]);
                Boolean y = Boolean.valueOf(args[i + 2]);
                res.add(new CalculatorRequest(x, y, args[i + 1]));
            } else if (arg1 == 1 && arg2 == 1) {
                int x = Integer.valueOf(args[i]);
                int y = Integer.valueOf(args[i + 2]);
                res.add(new CalculatorRequest(x, y, args[i + 1]));
            } else {
                double x = Double.valueOf(args[i]);
                double y = Double.valueOf(args[i + 2]);
                res.add(new CalculatorRequest(x, y, args[i + 1]));
            }


        }
        return res;
    }
}
