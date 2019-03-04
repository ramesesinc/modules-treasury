[getOpenChecks]
SELECT refno, receivedfrom, amount, amtused 
FROM checkpayment
WHERE objid IN ( 
   SELECT refid 
   FROM cashreceiptpayment_noncash npc
   INNER JOIN cashreceipt c ON c.objid=npc.receiptid
   LEFT JOIN cashreceipt_void cv ON cv.receiptid = c.objid 
   WHERE c.remittanceid =  $P{remittanceid} AND cv.objid IS NULL
)   
AND (amount - amtused) > 0 


[insertRemittanceFund]
INSERT INTO remittance_fund ( 
	objid, remittanceid, fund_objid, fund_title, amount
)
SELECT 
    ( ISNULL( cr.remittanceid, '-' ) + f.objid ), 
    cr.remittanceid, f.objid, f.title,
    SUM( cri.amount ) 
FROM cashreceipt cr 
	inner join remittance r ON r.objid = cr.remittanceid 
	inner join cashreceiptitem cri on cri.receiptid = cr.objid 
	inner join fund f ON f.objid = cri.item_fund_objid 
	left join cashreceipt_void cv ON cv.receiptid = cr.objid 
WHERE cr.remittanceid = $P{remittanceid}  
	and cv.objid IS NULL 
	and cr.state <> 'CANCELLED'
GROUP BY 
	( ISNULL( cr.remittanceid, '-' ) + f.objid ), 
	( ISNULL( r.controlno, '-') + f.code ), 
	cr.remittanceid, f.objid, f.code, f.title 




