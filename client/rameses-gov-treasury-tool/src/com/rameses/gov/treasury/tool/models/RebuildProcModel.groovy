package com.rameses.gov.treasury.tool.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;

class RebuildProcModel {

    @Binding 
    def binding;
    
    @FormTitle     
    def formTitle = 'Rebuild';
    
    def mode;
    def message = 'Rebuilding on progress...';
    def donemessage = 'Rebuilding completed';
    def errormessage = 'Processed with errors';
    
    def initHandler;
    def doneHandler;
    
    void init() {
        mode = 'init'; 
        if ( initHandler ) {
            initHandler( asyncHandler ); 
        }
    }   
    
    void switchMode( newmode ) {
        mode = newmode; 
        if ( binding ) {
            binding.fireNavigation( mode ); 
        }
    }
        
    def doRetry() {
        init(); 
        return 'default'; 
    }
    
    def asyncHandler = [
        onError: {o-> 
            if ( o instanceof Throwable ) {
                errormessage = o.message; 
            } else {
                errormessage = o.toString(); 
            }
            
            switchMode('error');
        }, 
        onTimeout: {
            asyncHandler.retry(); 
        },
        onCancel: {
            //do nothing 
        }, 
        onMessage: {o-> 
            if ( mode == 'error' ) return;
            
            boolean done = false; 
            if (o == com.rameses.common.AsyncHandler.EOF) {
                done = true; 
            } 
            else if (o instanceof Throwable) { 
                asyncHandler.cancel();
                errormessage = o.message; 
            } 
            else {
                done = true; 
            }

            try { 
                switchMode( done ? 'done' : 'error');
            } 
            finally {
                if ( done && doneHandler ) {
                    doneHandler(); 
                }
            }
        } 
    ] as com.rameses.common.AbstractAsyncHandler     
}
