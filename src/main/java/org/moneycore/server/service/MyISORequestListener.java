package org.moneycore.jposcustomremoteserver.service;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

public class MyISORequestListener implements ISORequestListener {
    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        return true;
    }
}
