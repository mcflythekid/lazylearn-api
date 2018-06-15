
CREATE TABLE lazylearn_api.vocab (
	id varchar(100) NULL,
	userid varchar(100) NULL,
	createddate datetime NULL,
	updateddate datetime NULL,
	data1 varchar(100) NULL,
	data2 varchar(100) NULL,
	data3 varchar(100) NULL,
	data4 varchar(100) NULL,
	data5 varchar(100) NULL,
	CONSTRAINT vocab_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_bin;
CREATE INDEX vocab_userid_idx USING BTREE ON lazylearn_api.vocab (userid) ;



ALTER TABLE lazylearn_api.card MODIFY COLUMN archived int(11) NULL;
ALTER TABLE lazylearn_api.deck MODIFY COLUMN archived int(11) NULL;
ALTER TABLE lazylearn_api.deck DROP COLUMN hyperdeckid;
ALTER TABLE lazylearn_api.deck DROP COLUMN hyperdecktype;
