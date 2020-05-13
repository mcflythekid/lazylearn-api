alter table card drop column articleid;
alter table card add minpairid varchar(100);


delete from card where minpairlanguage is not null;
delete from deck where type = 'minpair';