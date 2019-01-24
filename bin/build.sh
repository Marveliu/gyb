#!/bin/sh

#gyb-nb-service-cms.jar gyb-nb-service-gy.jar gyb-nb-service-library.jar gyb-nb-service-msg.jar gyb-nb-service-sys.jar gyb-nb-service-xm.jar gyb-nb-task.jar gyb-nb-web-api.jar gyb-nb-web-platform.jar

#log="gyb-build.txt"
buildPath="jarData"
filelist=`ls`
modules=("gyb-nb-service-sys" "gyb-nb-service-library" "gyb-nb-service-xm" "gyb-nb-service-gy" "gyb-nb-web-api"  "gyb-nb-task" "gyb-nb-service-cms" "gyb-nb-service-msg" "gyb-nb-web-platform")
target="target"

#rm ${log}
#touch ${log}
rm -r ${buildPath}
mkdir ${buildPath}

cd gyb-framework
mvn clean install -DskipTests
cd ../
cd gyb-app
mvn clean package -DskipTests nutzboot:shade

for module in ${modules[@]}
do
 # 判断是否为目录
 if [ -d "$module" ]
  then
     cd "$module/$target"
     find ./ -name "${module}*" -exec cp {} ../../../jarData/"${module}.jar" \;
     echo "get jar of $module"
     cd ../../
 fi
done
