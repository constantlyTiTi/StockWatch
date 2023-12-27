package ting.stock.exceptions;

public class OverLimitedRequestsException extends RuntimeException {
    public OverLimitedRequestsException(){
        super("the limitation of api call is 30 calls per second");
    }
}
