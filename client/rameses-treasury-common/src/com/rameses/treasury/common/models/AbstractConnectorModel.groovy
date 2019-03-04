package com.rameses.treasury.common.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*
import com.rameses.util.*;

public abstract class AbstractConnectorModel  {

    @Invoker
    def invoker;
    
    //must be passed from the outside
    String connection;
    
    public def createService(String serviceName) {
        return InvokerProxy.instance.create(serviceName, null, getConnection() );
    }
    
}