-- vdeck
select
    `d`.`id` as `id`,
    `d`.`userid` as `userid`,
    `d`.`name` as `name`,
    `d`.`createdon` as `createdon`,
    `d`.`updatedon` as `updatedon`,
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
    `d`.`createdon`,
    `d`.`updatedon`,
    `d`.`archived`;

-- vuser
select
    `u`.`id` as `id`,
    `u`.`email` as `email`,
    `u`.`registeripaddress` as `registeripaddress`,
    `u`.`createdon` as `createdon`,
    `u`.`updatedon` as `updatedon`,
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
    `u`.`registeripaddress`,
    `u`.`createdon`,
    `u`.`updatedon`,
    `u`.`fullname`,
    `u`.`facebookid`;
