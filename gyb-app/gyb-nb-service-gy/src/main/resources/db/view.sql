CREATE View v_gy AS
SELECT
	`sys_user`.`username` AS `username`,
	`sys_user`.`email` AS `email`,
	`sys_user`.`email_checked` AS `email_checked`,
	`sys_user`.`wsid` AS `wsid`,
	`sys_user`.`avatar` AS `avatar`,
	`gy_inf`.`id` AS `gyid`,
	`gy_inf`.`userid` AS `userid`,
	`gy_inf`.`realname` AS `realname`,
	`gy_inf`.`qq` AS `qq`,
	`gy_inf`.`birthday` AS `birthday`,
	`gy_inf`.`phone` AS `phone`,
	`getDicnameByCode` ( concat( 'sex', `gy_inf`.`sex` ) ) AS `sex`,
	`gy_inf`.`idcard` AS `idcard`,
	`getDicNameByCode` ( `gy_inf`.`college` ) AS `college`,
	`gy_inf`.`school` AS `school`,
	`gy_inf`.`major` AS `major`,
	`sys_user`.`isOnline` AS `isOnline`,
	`sys_user`.`loginAt` AS `loginAt`,
	`sys_user`.`loginIp` AS `loginIp`,
	`sys_user`.`loginCount` AS `loginCount`,
	`getDicnameByCode` ( concat( 'stulevel', `gy_inf`.`stuLevel` ) ) AS `stuLevel`,
	`gy_inf`.`regYear` AS `regYear`,
	`gy_inf`.`status` AS `status`,
	`getDicnameByCode` ( concat( 'gyauth', `gy_auth`.`status` ) ) AS `gyauthstatusname`,
	`gy_auth`.`id` AS `authid`,
	`gy_auth`.`stuCardB` AS `stuCardB`,
	`gy_auth`.`stuCardF` AS `stuCardF`,
	`gy_auth`.`idcardB` AS `idcardB`,
	`gy_auth`.`idcardF` AS `idcardF`,
	`gy_auth`.`reAuthTime` AS `reAuthTime`,
	`sys_user`.`disabled` AS `disabled`,
	`gy_auth`.`status` AS `gyauthstatus`
FROM
	(
		( `gy_inf` LEFT JOIN `gy_auth` ON ( ( `gy_auth`.`gyid` = `gy_inf`.`id` ) ) )
	JOIN `sys_user` ON ( ( `gy_inf`.`userid` = `sys_user`.`id` ) )
	)