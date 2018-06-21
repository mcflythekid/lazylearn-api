ALTER TABLE lazylearn_api.deck ADD CONSTRAINT deck_un UNIQUE KEY (vocabtype,vocabdeckid) ;

ALTER TABLE lazylearn_api.card ADD vocabid varchar(100) NULL;
CREATE INDEX card_vocabid_idx USING BTREE ON lazylearn_api.card (vocabid) ;
