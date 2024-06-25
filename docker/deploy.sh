#!/bin/sh

port(){
	firewall-cmd --add-port=80/tcp --permanent
	service firewalld restart
}

run(){
  echo "rm:"
  rm -rfv ./mysql/initdb/*.sql
  echo "cp:"
  cp -v ../sql/*.sql ./mysql/initdb
#  docker-compose up --build -d seele-mysql seele-redis seele-nacos
  docker-compose up --build -d seele-nginx
}

stop(){
	docker-compose stop
}

remove(){
	docker-compose rm
}

down(){
	docker-compose down --rmi local
	sh ./clean.sh
}

print_usage(){
    echo ""
  	echo "Usage: sh $0 {port|run|stop|remove|down}"
  	echo ""
  	echo "port: Open the firewall."
  	echo "run: Run each module."
  	echo "stop: Stop each module."
  	echo "remove: Remove each module."
  	echo "down: Down each module."
}

if [ $# -gt 0 ]; then
    case "$1" in
    "port")
      port
    ;;
    "run")
        run
      ;;
    "stop")
      stop
    ;;
    "remove")
      remove
    ;;
    "down")
      down
    ;;
    esac
else
    echo "No arguments provided."
    print_usage
fi