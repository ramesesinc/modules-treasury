package ccom.rameses.enterprise.treasury.components;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.common.*;
import com.rameses.seti2.models.*;
import com.rameses.util.*;

class CashDenominationModel extends ComponentBean {
        
    @Binding
    def binding;
    
    def formatter;
    def model;
    def handler;
    
    int qty1000 = 0;
    int qty500 = 0;
    int qty200 = 0;
    int qty100 = 0;
    int qty50 = 0;
    int qty20 = 0;
    int qty10 = 0;
    int qty5 = 0;
    int qty1 = 0;
    int qtyc50 = 0;
    int qtyc25 = 0;
    int qtyc10 = 0;
    int qtyc05 = 0;
    int qtyc01= 0;
    
    double d1000 = 0.0;
    double d500 = 0.0;
    double d200 = 0.0;
    double d100 = 0.0;
    double d50 = 0.0;
    double d20 = 0.0;
    double d10 = 0.0;
    double d5 = 0.0;
    
    double d1 = 0.0;
    double dc50 = 0.0;
    double dc25 = 0.0;
    double dc10 = 0.0;
    double dc05 = 0.0;
    double dc01 = 0.0;
    
    def total = 0.0;
    def amount = 0.0;
    def cashremaining = 0.0;
    
    void calcTotals() {
        if(amount == null ) amount = 0;
        total = NumberUtil.round(d1000+d500+d200+d100+d50+d20+d10+d5+d1+dc50+dc25+dc10+dc05+dc01);
        cashremaining = NumberUtil.round(amount - total);
        
        if(handler) handler( [total: total, cashremaining: cashremaining ]);
        binding.refresh("total|cashremaining");
    };

    void loadData( def denomination, def qtyFld, def amtFld ) {
        def d = model.find{ it.denomination == denomination };
        if(d) {
            this[(qtyFld)] = d.qty;
            this[(amtFld)] = d.amount;
        }
    };
    
    void initZero() {
        qty1000 = 0;
        qty500 = 0;
        qty200 = 0;
        qty100 = 0;
        qty50 = 0;
        qty20 = 0;
        qty10 = 0;
        qty5 = 0;
        qty1 = 0;
        qtyc50 = 0;
        qtyc25 = 0;
        qtyc10 = 0;
        qtyc05 = 0;
        qtyc01= 0;
        d1000 = 0.0;
        d500 = 0.0;
        d200 = 0.0;
        d100 = 0.0;
        d50 = 0.0;
        d20 = 0.0;
        d10 = 0.0;
        d5 = 0.0;
        d1 = 0.0;
        dc50 = 0.0;
        dc25 = 0.0;
        dc10 = 0.0;
        dc05 = 0.0;
        dc01 = 0.0;
        total = 0.0;
        cashremaining = 0.0;      
    }
    
    void init() {
        initZero();
        model = getValue();
        if(model) {
            loadData( 1000, 'qty1000', 'd1000');
            loadData( 500, 'qty500', 'd500');
            loadData( 200, 'qty200', 'd200');
            loadData( 100, 'qty100', 'd100');
            loadData( 50, 'qty50', 'd50');
            loadData( 20, 'qty20', 'd20');
            loadData( 10, 'qty10', 'd10');
            loadData( 5, 'qty5', 'd5'); 
            loadData( 1, 'qty1', 'd1');
            loadData( 0.50, 'qtyc50', 'dc50');
            loadData( 0.25, 'qtyc25', 'dc25');
            loadData( 0.10, 'qtyc10', 'dc10');
            loadData( 0.05, 'qtyc05', 'dc05');
            loadData( 0.01, 'qtyc01', 'dc01');
        };
        else {
            model = [];
            setValue(model);
        }
        calcTotals();
    };
    
    void reload() {
        calcTotals();
    }
    
    void updateEntry(def denomination, def qty,  def amtFld) {
        this[(amtFld)] = qty * denomination;
        def d = model.find{ it.denomination == denomination };
        if(d) {
            d.qty = qty;
            d.amount = this[(amtFld)];
        }
        else {
            model << [caption: denomination, denomination:denomination, qty: qty, amount: this[(amtFld)]];
        }
        calcTotals();
    }
    
    @PropertyChangeListener 
    def listener = [
        "qty1000": {q-> updateEntry(1000, q, 'd1000'); },
        "qty500": {q-> updateEntry(500, q, 'd500'); },
        "qty200": {q-> updateEntry(200, q, 'd200');},
        "qty100": {q-> updateEntry(100, q, 'd100');},
        "qty50": {q-> updateEntry(50, q, 'd50');},
        "qty20": {q-> updateEntry(20, q, 'd20');},
        "qty10": {q-> updateEntry(10, q, 'd10'); },
        "qty5": {q-> updateEntry(5, q, 'd5');},
        "qty1": {q-> updateEntry(1, q, 'd1');},
        "qtyc50": {q-> updateEntry(0.50, q, 'dc50');},
        "qtyc25": {q-> updateEntry(0.25, q, 'dc25');},
        "qtyc10": {q-> updateEntry(0.10, q, 'dc10');},
        "qtyc05": {q-> updateEntry(0.05, q, 'dc05');},
        "qtyc01": {q-> updateEntry(0.01, q, 'dc01');},
    ];
    
}