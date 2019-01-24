#!/usr/bin/env bash
updir=./../apidoc   #要上传的文件夹
todir=/             #目标文件夹
ip=                 #服务器
user=gyb-api        #ftp用户名
password=           #ftp密码

sss=`find $updir -type d -printf $todir/'%P\n'| awk '{if ($0 == "")next;print "mkdir " $0}'` 
aaa=`find $updir -type f -printf 'put %p %P \n'`

ftp -nv $ip <<EOF 
user $user $password
type binary 
prompt 
$sss 
cd $todir 
$aaa 
quit 
EOF