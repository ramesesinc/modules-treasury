#To correct the cashreceiptpayment_noncash

#create temp table first
CREATE TABLE `cashreceiptpayment_noncash_old` (
  `objid` varchar(50) NOT NULL,
  `receiptid` varchar(50) DEFAULT NULL,
  `refno` varchar(25) DEFAULT NULL,
  `refdate` datetime DEFAULT NULL,
  `reftype` varchar(50) DEFAULT NULL,
  `amount` decimal(16,2) DEFAULT NULL,
  `particulars` varchar(255) DEFAULT NULL,
  `account_objid` varchar(50) DEFAULT NULL,
  `account_code` varchar(50) DEFAULT NULL,
  `account_name` varchar(100) DEFAULT NULL,
  `account_fund_objid` varchar(50) DEFAULT NULL,
  `account_fund_name` varchar(50) DEFAULT NULL,
  `account_bank` varchar(100) DEFAULT NULL,
  `fund_objid` varchar(50) DEFAULT NULL,
  `refid` varchar(50) DEFAULT NULL,
  `checkid` varchar(50) DEFAULT NULL,
  `voidamount` decimal(16,4) DEFAULT NULL,
  `remittancefundid` varchar(100) DEFAULT NULL,
  `remittanceid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`objid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO cashreceiptpayment_noncash_old SELECT * FROM cashreceiptpayment_noncash;

UPDATE cashreceiptpayment_noncash SET remittanceid = NULL, remittancefundid = NULL; 

ALTER TABLE cashreceiptpayment_noncash DROP FOREIGN KEY `fk_cashreceiptpayment_noncash_remittancefund`; 
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN remittanceid;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN remittancefundid;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN fund_objid;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_objid;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_code;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_name;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_fund_objid;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_fund_name;
ALTER TABLE cashreceiptpayment_noncash DROP COLUMN account_bank;


DELETE FROM cashreceiptpayment_noncash;

INSERT INTO cashreceiptpayment_noncash 
(objid,receiptid,refno,refdate,reftype,amount,
particulars,refid,checkid,
voidamount)
SELECT 
MAX(objid),receiptid,MAX(refno),MAX(refdate),MAX(reftype),SUM(amount),
MAX(particulars),refid,checkid,
SUM(voidamount)
FROM cashreceiptpayment_noncash_old 
GROUP BY 
receiptid,refid,checkid;



