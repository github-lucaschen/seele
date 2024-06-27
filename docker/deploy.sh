#!/bin/sh

port(){
  #  nacos
  firewall-cmd --add-port=8848/tcp --permanent
  firewall-cmd --add-port=9848/tcp --permanent
  #  redis
  firewall-cmd --add-port=6379/tcp --permanent
  #  mysql
  firewall-cmd --add-port=3306/tcp --permanent
  #  nginx
	firewall-cmd --add-port=80/tcp --permanent
  #	gateway
	firewall-cmd --add-port=8080/tcp --permanent
  #	base
	firewall-cmd --add-port=18080/tcp --permanent
	service firewalld restart
}

base(){
  echo "clean:"
  sh ./clean.sh
  echo "cp:"
  sh ./copy.sh
  docker-compose up --build -d seele-mysql seele-redis seele-nacos
}

run(){
  echo "mavem clean:"
  sh ../bin/clean.sh
  echo "maven package:"
  sh ../bin/package.sh
  echo "clean:"
  sh ./clean.sh
  echo "cp:"
  sh ./copy.sh
  docker-compose up --build -d
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
  	echo "Usage: sh $0 {port|base|run|stop|remove|down}"
  	echo ""
  	echo "port: Open the firewall."
  	echo "base: Run base service."
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
    "base")
        base
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