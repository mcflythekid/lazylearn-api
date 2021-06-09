#!/bin/bash

# Customizable
JAR="../app.jar"
MEMORY="128m"
LOG="../nohup.out"
CONFIG="../config.json"

# Do not modify
PID="./pid"
CMD="java"
ENV=""
COMMAND="$CMD -jar -Xmx$MEMORY $JAR ./config.json"

status() {
    echo
    echo "==== Status"

    if [ -f $PID ]
    then
        echo
        echo "Pid file: $( cat $PID ) [$PID]"
        echo
        ps -ef | grep -v grep | grep $( cat $PID ) | grep $CMD
        echo
        echo "Check alive:"
    else
        echo
        echo "No Pid file"
    fi
}

start() {
    if [ -f $PID ]
    then
        echo
        echo "Already started. PID: [$( cat $PID )]"
    else
        echo "==== Start"
        touch $PID
        if env $ENV nohup $COMMAND >>$LOG 2>&1 &
        then echo $! >$PID
             echo "Done."
             echo "$(date '+%Y-%m-%d %X'): START" >>$LOG
        else echo "Error... "
             /bin/rm $PID
        fi
    fi
}

stop() {
    echo "==== Stop"

    if [ -f $PID ]
    then
        if kill $( cat $PID )
        then echo "Done."
             echo "$(date '+%Y-%m-%d %X'): STOP" >>$LOG
        fi
        /bin/rm $PID
    else
        echo "No pid file. Already stopped?"
    fi
}

case "$1" in
    'start')
            start
            ;;
    'stop')
            stop
            ;;
    'restart')
            stop ; echo "Sleeping..."; sleep 1 ;
            start
            ;;
    'status')
            status
            ;;
    *)
            echo
            echo "Usage: $0 { start | stop | restart | status }"
            echo
            exit 1
            ;;
esac

exit 0