select
    `d`.`id` as `id`,
    `d`.`userid` as `userid`,
    `d`.`name` as `name`,
    `d`.`createddate` as `createddate`,
    `d`.`updateddate` as `updateddate`,
    `d`.`archived` as `archived`,
    count( `c`.`id` ) as `totalcard`,
    sum( if(( `c`.`wakeupon` < now()), 1, 0 )) as `totaltimeupcard`
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
    `u`.`id` as `id`,
    `u`.`email` as `email`,
    `u`.`ipaddress` as `ipaddress`,
    `u`.`createddate` as `createddate`,
    `u`.`updateddate` as `updateddate`,
    `u`.`fullname` as `fullname`,
    `u`.`facebookid` as `facebookid`,
    count( distinct `c`.`id` ) as `cards`,
    count( distinct `d`.`id` ) as `decks`
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
