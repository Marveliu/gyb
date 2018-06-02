#!/bin/bash


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
    MODULE=${MODULES[$2]}
    MODULE_NAME=${MODULE_NAMES[$2]}
    JAR_NAME=${JARS[$2]}
    PID=`ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}'`
    if [ -n "$PID" ];then
      echo "$MODULE---$MODULE_NAME:已经运行,PID=$PID"
    else
      rm $LOG_PATH/$JAR_NAME.out
      touch $LOG_PATH/$JAR_NAME.out
      exec java -jar -Dnutz.profiles.active=prod $JAR_PATH/$JAR_NAME >> $LOG_PATH/$JAR_NAME.out &
      sleep 5s
      while [ -z "$PID" ]
      do
        if (($count == 5));then
          echo "$(expr $count \* 10)秒内未启动,请检查!"
          break
        fi
          echo "${MODULE_NAME}启动中.................."
          sleep 5s
          PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
	  done
      echo "$MODULE---$MODULE_NAME:已经启动成功,PID=$PID"
    fi
}

stop() {
    MODULE=${MODULES[$2]}
    MODULE_NAME=${MODULE_NAMES[$2]}
    JAR_NAME=${JARS[$2]}
      PID=`ps -ef |grep $(echo $JAR_NAME | awk -F/ '{print $NF}') | grep -v grep | awk '{print $2}'`
      if [ -n "$PID" ];then
        echo "$MODULE---$MODULE_NAME:准备结束,PID=$PID"
        kill $PID
        echo "$MODULE---$MODULE_NAME:成功结束"
      else
        echo "$MODULE---$MODULE_NAME:未运行"
      fi
}


case "$1" in
  start)
    start
  ;;
  stop)
    stop
  ;;
  restart)
    stop
  sleep 3s
  start "$2"
;;
*)
  echo "第一个参数请输入:start|stop|restart"
  exit 1
;;
esac
