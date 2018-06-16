select
    `d`.`id` AS `id`,
    `d`.`userid` AS `userid`,
    `d`.`name` AS `name`,
    `d`.`createddate` AS `createddate`,
    `d`.`updateddate` AS `updateddate`,
    `d`.`archived` AS `archived`,
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
    )
group by
    `d`.`id`,
    `d`.`userid`,
    `d`.`name`,
    `d`.`createddate`,
    `d`.`updateddate`,
    `d`.`archived`;

select
    `u`.`id` AS `id`,
    `u`.`email` AS `email`,
    `u`.`ipaddress` AS `ipaddress`,
    `u`.`createddate` AS `createddate`,
    `u`.`updateddate` AS `updateddate`,
    `u`.`fullname` AS `fullname`,
    `u`.`facebookid` AS `facebookid`,
    count( distinct `c`.`id` ) AS `cards`,
    count( distinct `d`.`id` ) AS `decks`
from
    (
        (
            `lazylearn_api`.`user` `u`
        left join `lazylearn_api`.`deck` `d` on
            (
                (
                    `u`.`id` = `d`.`userid`
                )
            )
        )
    left join `lazylearn_api`.`card` `c` on
        (
            (
                `u`.`id` = `c`.`userid`
            )
        )
    )
group by
    `u`.`id`,
    `u`.`email`,
    `u`.`ipaddress`,
    `u`.`createddate`,
    `u`.`updateddate`,
    `u`.`fullname`,
    `u`.`facebookid`;
