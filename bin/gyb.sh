#!/bin/bash
# 端口号
#PORTS=()
# 系统模块
MODULES=("gyb-nb-service-sys" "gyb-nb-service-library" "gyb-nb-service-xm" "gyb-nb-service-gy"  "gyb-nb-task"  "gyb-nb-service-msg" "gyb-nb-web-platform")
# 系统模块名称
MODULE_NAMES=("基础系统服务" "任务技能库服务" "阶段性任务管理服务" "雇员关系管理服务"  "任务调度服务" "消息队列消费者服务" "客户端界面服务")
# jar包数组
JARS=(gyb-nb-service-sys.jar gyb-nb-service-library.jar gyb-nb-service-xm.jar gyb-nb-service-gy.jar   gyb-nb-task.jar  gyb-nb-service-msg.jar gyb-nb-web-platform.jar)
# jar包路径
JAR_PATH='jarData'
# 日志路径
LOG_PATH='logs'


start() {
  local MODULE=
  local MODULE_NAME=
  local JAR_NAME=
  local command="$1"
  local commandOk=0
  local count=0
  local okCount=0
 # local port=0
  for ((i=0;i<${#MODULES[@]};i++))
  do
    MODULE=${MODULES[$i]}
    MODULE_NAME=${MODULE_NAMES[$i]}
    JAR_NAME=${JARS[$i]}
    commandOk=1
    count=0
    PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
    if [ -n "$PID" ];then
      echo "$MODULE---$MODULE_NAME:已经运行,PID=$PID"
    else
      rm $LOG_PATH/$JAR_NAME.out
      touch $LOG_PATH/$JAR_NAME.out
      exec java -jar $JAR_PATH/$JAR_NAME >> $LOG_PATH/$JAR_NAME.out &
      sleep 5s
      while [ -z "$PID" ]
      do
        if (($count == 5));then
          echo "$MODULE---$MODULE_NAME:$(expr $count \* 10)秒内未启动,请检查!"
          break
        fi
          count=$(($count+1))
          echo "${MODULE_NAME}启动中.................."
          sleep 5s
          PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
#          PID=`ps -ef |grep $(echo gyb-nb-service-sys.jar  | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
	  done
      okCount=$(($okCount+1))
      echo "$MODULE---$MODULE_NAME:已经启动成功,PID=$PID"
    fi
  done
  echo "............本次共启动:${okCount}个服务..........."
}

stop() {
  local MODULE=
  local MODULE_NAME=
  local JAR_NAME=
  local command="$1"
  local commandOk=0
  local okCount=0
  for((i=0;i<${#MODULES[@]};i++))
  do
    MODULE=${MODULES[$i]}
    MODULE_NAME=${MODULE_NAMES[$i]}
    JAR_NAME=${JARS[$i]}
      commandOk=1
      PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
      if [ -n "$PID" ];then
        echo "$MODULE---$MODULE_NAME:准备结束,PID=$PID"
        kill $PID
        PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
        while [ -n "$PID" ]
        do
          sleep 3s
          PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
        done
        echo "$MODULE---$MODULE_NAME:成功结束"
        okCount=$(($okCount+1))
      else
        echo "$MODULE---$MODULE_NAME:未运行"
      fi
  done
  echo "............本次共停止:${okCount}个服务............"
}


case "$1" in
  start)
    start "$2"
  ;;
  stop)
    stop "$2"
  ;;
  restart)
    stop "$2"
  sleep 3s
  start "$2"
;;
*)
  echo "第一个参数请输入:start|stop|restart"
  exit 1
;;
esac
