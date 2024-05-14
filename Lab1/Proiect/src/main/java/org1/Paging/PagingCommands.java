package org1.Paging;

public enum PagingCommands {
    STOP("stop"), NEXT("next");
    private String val;
    PagingCommands(String value){
        this.val = value;
    }
}
