UPDATE checkpayment SET depositvoucherid = NULL, fundid = NULL, depositslipid = NULL;
UPDATE collectionvoucher SET depositvoucherid = NULL;
DELETE FROM depositslip;
DELETE FROM deposit_fund_transfer;
DELETE FROM depositvoucher_fund;
DELETE FROM depositvoucher;