package org1.command;

import org1.Main;
import org1.cont.UserAnonim;

public class Logout implements Command {
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=0){
            throw new NrInvalidArgs("Logout");
        }
        Main.usr = new UserAnonim();
    }
}
