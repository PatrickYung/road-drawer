#!/bin/bash

exec_file='road-query-engine-be'

if [ "$#" -ne 1 ]; then
        echo "Usage:";
        echo "start stop restart";
fi

if [ "$#" -eq 1 ]
then
        if [ $1 == 'start' ]
        then
                echo "start..."
                nohup java -jar ${exec_file} --spring.profiles.active=dev > /dev/null 2>&1 &
        elif [ $1 == 'stop' ]
        then
                echo "stop..."
                pkill -9 -f ${exec_file}
        elif [ $1 == 'restart' ]
        then
                echo "restart..."
                pkill -9 -f ${exec_file}
                nohup java -jar ${exec_file} --spring.profiles.active=dev > /dev/null 2>&1 &
        else
                echo "argument only support start, stop, restart"
        fi
fi