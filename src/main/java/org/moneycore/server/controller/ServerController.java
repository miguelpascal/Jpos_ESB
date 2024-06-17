package org.moneycore.jposcustomremoteserver.controller;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    private Logger log = LoggerFactory.getLogger(ServerController.class);

    @GetMapping("/")
    public String index() {
        log.debug("vérification de routing");
        return "Greetings from ESB SERVER!";
    }

    @GetMapping("echo")
    public String echo() throws NameRegistrar.NotFoundException, ISOException {
        log.info("echo");
        MUX mux = QMUX.getMUX("moneycore-mux");
        ISOMsg msg = new ISOMsg();
        msg.setMTI("0800");
        msg.set(11, "000001");
        msg.set(70, "301");
        ISOMsg respMsg = mux.request(msg, 2000);
        log.info("RespMsg: {}", respMsg);
        return "echo";
    }
}
