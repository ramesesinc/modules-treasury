[markBatchAsDeleted]
UPDATE af_control af, af_control_detail ad 
SET af.state = 'DELETED' 
WHERE ad.aftxnitemid = $P{aftxnitemid}
AND ad.controlid = af.objid 
AND af.batchno = $P{batchno}

[removeDeletedAfControlDetail]
DELETE 
FROM af_control_detail
WHERE aftxnitemid = $P{aftxnitemid}
AND controlid IN ( SELECT objid FROM af_control WHERE state = 'DELETED' )

[reindexBatchno]
UPDATE af_control af, af_control_detail ad 
SET batchno = batchno - 1 
WHERE ad.controlid = af.objid 
AND ad.aftxnitemid = $P{aftxnitemid} 
AND af.batchno > $P{batchno}

[updateAfTxnitemQtyServed]
UPDATE aftxnitem afi
SET qtyserved = (SELECT COUNT(*) FROM af_control_detail WHERE aftxnitemid = $P{aftxnitemid} )
WHERE afi.objid = $P{aftxnitemid} 

[updateAfTxnitemTotals]
UPDATE aftxnitem 
SET cost = (SELECT 
		SUM(af.cost) 
		FROM af_control af 
		INNER JOIN af_control_detail ad ON ad.controlid=af.objid 
		WHERE ad.aftxnitemid = $P{aftxnitemid}
		GROUP BY ad.aftxnitemid ) / qtyserved
WHERE objid = $P{aftxnitemid} 



[updateAfControlAfterPosting]
UPDATE af_control af, af_control_detail ad, aftxn at
SET 
  af.state = (CASE WHEN ad.txntype = 'COLLECTION' THEN 'ISSUED'
  		 	    WHEN ad.txntype = 'FORWARD' THEN 'ISSUED' 
  		 	    WHEN ad.txntype = 'MANUAL_ISSUE' THEN 'ISSUED' 
  		 	    WHEN ad.txntype = 'SALE' THEN 'SOLD'
  		 	    ELSE 'OPEN' END), 
  af.owner_objid = CASE WHEN ad.txntype = 'RETURN' THEN NULL ELSE at.issueto_objid END,
  af.owner_name = CASE WHEN ad.txntype = 'RETURN' THEN NULL ELSE at.issueto_name END,
  af.assignee_objid = CASE WHEN ad.txntype = 'RETURN' THEN NULL ELSE at.issueto_objid END,
  af.assignee_name = CASE WHEN ad.txntype = 'RETURN' THEN NULL ELSE at.issueto_name END,
  af.respcenter_objid = at.respcenter_objid,
  af.respcenter_name =  at.respcenter_name,
  af.lockid = NULL 
WHERE af.objid = ad.controlid 
AND ad.refid = at.objid   
AND at.objid = $P{aftxnid}

[revertAfControlState]
UPDATE af_control af, af_control_detail ad 
SET af.state = 'OPEN' 
WHERE af.objid = ad.controlid 
AND ad.aftxnitemid = $P{aftxnitemid} 


