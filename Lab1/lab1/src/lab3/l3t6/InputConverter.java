package lab3.l3t6;

import java.util.ArrayList;
import java.util.List;

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

                    Boolean.parseBoolean(s);
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
            if(arg1 == -1 || arg2 == -1)return new ArrayList<CalculatorRequest>();
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
