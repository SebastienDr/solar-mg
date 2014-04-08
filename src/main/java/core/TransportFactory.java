package core;

public class TransportFactory {

    public Transport createTransport(Route r) {
        Transport t = new Transport(r);
        t.setPosition(r.getStartRoute().getPosition());
        t.setStatus(TransportStatus.LOADING);
        t.setResources(0);
        t.setPrice(9000);
        return t;
    }

}
