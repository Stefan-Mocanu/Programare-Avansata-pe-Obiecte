package org1.command;

import org1.Paging.Paginare;

public class ChangePaging implements Command {
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1){
            throw new NrInvalidArgs("SchimbarePaginare");
        }
        Paginare.nrRezultatePagina = Integer.parseInt(arg[0]);
    }
}
