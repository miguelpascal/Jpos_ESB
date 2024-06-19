package org.moneycore.server.service;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyISORequestListener implements ISORequestListener, Configurable {
    private static final Logger logger = LoggerFactory.getLogger(MyISORequestListener.class);

    protected String queueName;
    protected Space sp;
    protected String destinationMux;

    public static final String REQUEST = "REQUEST";
    public static final String ISOSOURCE = "ISOSOURCE";

    @Override
    public void setConfiguration(Configuration cfg) throws ConfigurationException {
        queueName = cfg.get("queue");
        destinationMux = cfg.get("destination-mux");
        sp =  SpaceFactory.getSpace(cfg.get("space"));
    }

    public boolean process (ISOSource source, ISOMsg m) {
        try{

            logger.info("INSIDE MY REQUEST LISTNER PROCESS : " + queueName + " : "+ destinationMux);

            Context ctx = new Context();
            ctx.put (REQUEST, m);
            ctx.put (ISOSOURCE, source);
            sp.out (queueName, ctx);
        }catch(Exception ex){
            System.out.println("MY REQUEST LISTNER ERROR : "+ex);
        }
        return true;
    }
}
