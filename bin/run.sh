#!/usr/bin bash

PID=$(ps -ef | grep joke-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then

    echo Application is already stopped
else

    echo kill $PID

    kill $PID
fi