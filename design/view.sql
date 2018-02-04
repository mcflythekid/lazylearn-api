SELECT 
	d.id
	,d.userid
	,d.`name`
	,d.createdon
	,d.updatedon
	,COUNT(c.id) AS totalcard
	,SUM(IF(c.wakeupon < NOW(), 1, 0)) AS totaltimeupcard
FROM deck d
LEFT JOIN card c ON d.id = c.deckid
GROUP BY
	d.id
	,d.userid
	,d.`name`
	,d.createdon
	,d.updatedon 