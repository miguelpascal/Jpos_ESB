package org.moneycore.server.iso;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.NameRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class QueryRemoteHost implements TransactionParticipant {
        private static final Logger logger = LoggerFactory.getLogger(QueryRemoteHost.class);

        @Override
        public int prepare(long id, Serializable context) {

            logger.info("INSIDE QUERYREMOTEHOST PREPARE");
            Context ctx = (Context) context;

            try {
                ISOSource source = (ISOSource) ctx.get("ISOSOURCE");
                ISOMsg queryRemote = (ISOMsg) ctx.get("REQUEST");

                // -- forward msg to destination host
                MUX remoteMux = (QMUX) NameRegistrar.getIfExists("mux.moneycore-esb-mux");

                System.out.println("===Outgoing Message To Remote Server: ");
                System.out.println(ISOUtil.hexdump(queryRemote.pack()));

                queryRemote = remoteMux.request(queryRemote, 10000000);

                System.out.println("===Incoming Message From Remote Server: ");
                logger.info("Incoming Message From Remote Server: " + queryRemote);

                if (queryRemote == null) {
                    ctx.put("CHKCARDRESP", "911");
                } else {
                    // Modify the response message to client (if required)
                    ctx.put("PROCESSRESPONSE", queryRemote);
                    queryRemote.setResponseMTI();
                    return PREPARED;
                }
                source.send(queryRemote);
                return PREPARED | NO_JOIN | READONLY;

            } catch (Exception ex) {
                logger.error("Query Remote Host | error | " + ex);
                ex.printStackTrace();
            }

            return 0;
        }

        @Override
        public void abort(long id, Serializable context) {
            logger.info("INSIDE QUERYREMOTEHOST ABORT");
            Context ctx = (Context) context;
            try {

                ISOSource source = (ISOSource) ctx.get("ISOSOURCE");
                ISOMsg responseabort = (ISOMsg) ctx.get("REQUEST");

                responseabort.setResponseMTI();
                responseabort.set(38, "MWS000");
                responseabort.set(39, "99");

                source.send(responseabort);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
