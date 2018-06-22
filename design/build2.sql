ALTER TABLE lazylearn_api.deck ADD CONSTRAINT deck_un UNIQUE KEY (vocabtype,vocabdeckid) ;

ALTER TABLE lazylearn_api.card ADD vocabid varchar(100) NULL;
CREATE INDEX card_vocabid_idx USING BTREE ON lazylearn_api.card (vocabid) ;

create or replace view `detailed_deck` as
  select
      `d`.`id` AS `id`,
      `d`.`userid` AS `userid`,
      `d`.`name` AS `name`,
      `d`.`createddate` AS `createddate`,
      `d`.`updateddate` AS `updateddate`,
      `d`.`archived` AS `archived`,
      `d`.`vocabdeckid` AS `vocabdeckid`,
      `vd`.`name` AS `vocabdeckname`,
      count( `c`.`id` ) AS `totalcard`,
      sum( if(( `c`.`wakeupon` < now()), 1, 0 )) AS `totaltimeupcard`
  from
      (
          	  `lazylearn_api`.`deck` `d`
		      left join `lazylearn_api`.`card` `c` on
		          (
		              (
		                  `d`.`id` = `c`.`deckid`
		              )
		          )
		      left join `lazylearn_api`.`vocabdeck` `vd` on
		          (
		              (
		                  `d`.`vocabdeckid` = `vd`.`id`
		              )
		          )
      )
  group by
      `d`.`id`,
      `d`.`userid`,
      `d`.`name`,
      `d`.`createddate`,
      `d`.`updateddate`,
      `d`.`archived`,
      `d`.`vocabdeckid`,
      `vd`.`name`;