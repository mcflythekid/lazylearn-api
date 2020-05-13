alter table vocab add audiohint varchar(100) default null;

create table vocab_saved(
    id varchar(100),
    word varchar(100),
    phonetic varchar(100),
    phrase varchar(1000),
    audiopath varchar(1000),
    languageid int,
    platformid int,
    createddate datetime,
    updateddate datetime,
    createdby varchar(100),
    constraint vocab_saved_id primary key (id),
    constraint vocab_saved_idx unique(languageid, word)
);

alter table card drop column articleid;
alter table card add minpairid varchar(100);

--delete from card where minpairlanguage is not null;
--delete from deck where type = 'minpair';