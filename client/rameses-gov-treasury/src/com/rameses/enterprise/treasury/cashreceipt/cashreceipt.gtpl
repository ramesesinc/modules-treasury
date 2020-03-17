<%
def df = new java.text.DecimalFormat("#,##0.00");
boolean multireceipt = (entity.receipts ? true : false);

def receipts = entity.receipts;
if ( receipts == null ) receipts = [entity];  
%>

<% if ( !multireceipt ) {%> 
<table width="380" cellpadding="0" >
    <tr>
        <td><font size="5"><b>Cash Tendered</b></font></td>
        <td>:</td>
        <td align="right"><font size="6"><b>${df.format(entity.totalcash)}</b></font></td>
    </tr>
    <tr style="color:red">
        <td><font size="5"><b>Change</b></font></td>
        <td>:</td>
        <td align="right"><font size="6"><b>${df.format(entity.cashchange)}</b></font></td>
    </tr>
</table>
<%}%>

<% receipts.each { rct-> %>
<br>
<hr>
<br>
<table width="400">
    <%if(rct.voided){%>
        <tr>
            <td colspan="2">
                <h1><font color=red>VOID</font></h1>
            </td>
        </tr>
    <%}%>

    <tr>
        <td>Receipt No </td>
        <td><font size="4"> <b>${rct.receiptno}</font> </b></td>
    </tr>
    <tr>
        <td>Mode </td>
        <td><b>${rct.txnmode}</font> </b></td>
    </tr>
    <tr>
        <td>Receipt Date</td>
        <td><b>${rct.receiptdate}</b></td>
    </tr>
    <tr>
        <td>Payer</td>
        <td>${rct.paidby}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${rct.paidbyaddress}</td>
    </tr>
    <tr>
        <td colspan="2">
            <br>
            ${ (rct.remarks)? rct.remarks: ''}
            </br>
        </td>
    </tr>
    
    <tr>
        <td colspan="2">
            <hr>
            <table width="100%">
                <tr>
                    <th>Item code</th>
                    <th>Title</th>
                    <th>Amount</th>
                    <th>Remarks</th>
                </tr>
                <%rct.items.each{ %>
                    <tr>
                        <td>${it?.item.code}</td>
                        <td>${it?.item.title}</td>
                        <td>${df.format(it?.amount)}</td>
                        <td>${  (it?.remarks) ? it.remarks : ''}</td>
                    </tr>
                <%}%>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <hr>
                <b>AMOUNT : ${df.format(rct.amount)}<BR></b>
           </hr>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <br>
            <%if( rct.paymentitems ){%> 
                <b>Checks and other payments</b>
                <br>
                <table>
                    <tr>
                        <th>Type</th>
                        <th>Particulars</th>
                        <th>Amount</th>
                    </tr>
                    <%rct.paymentitems.each{ %>
                        <tr>
                            <td>${it.reftype}</td>
                            <td>${it?.particulars}</td>
                            <td>${df.format(it?.amount)}</td>
                        </tr>
                    <%}%>
                </table>
              <%}%>  
        </td>
    </tr>
</table>
<%}%>
