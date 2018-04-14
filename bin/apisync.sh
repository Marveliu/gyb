updir=./../apidoc   #要上传的文件夹
todir=/             #目标文件夹
ip=47.97.4.117      #服务器
user=gyb-api        #ftp用户名
password=gyb-api    #ftp密码

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