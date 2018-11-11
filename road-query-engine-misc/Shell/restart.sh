#!/bin/bash

origin_file='road-query-engine-rs-*.jar' # 原 jar 文件名，* 表示版本号
exec_file='road-query-engine-rs.jar' # 用于执行的文件名
base_dir=/usr/local/apache-tomcat-8/webapps/road-query-engine-be # 后端文件所在路径

# 杀掉 lte-detective 进程
echo 'kill background road-query-engine-rs'
pkill -9 -f ${exec_file}

# 重命名
echo 'rename jar file'
cd ${base_dir}
mv ${origin_file} ${exec_file}

# 后台启动 lte-detective 进程
echo "execute jar..."
nohup java -jar ${exec_file} --spring.profiles.active=dev > /dev/null 2>&1 &