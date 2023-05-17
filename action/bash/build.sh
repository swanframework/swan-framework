#!/bin/bash
#Desc 打包
#Auth zongf
#Date 2023-05-17


# 工作空间,项目根目录
workspace=`cd ../.. && pwd`
echo "workspace:$workspace"

# 打包命令
cmd="mvn clean install -DskipTests=true "

# 清空控制台
clear

# 按依赖顺序依次打包
cd $workspace/base/swan-dependencies && $cmd

cd $workspace/base/swan-parent && $cmd

cd $workspace/commons && $cmd

cd $workspace/starters && $cmd

cd $workspace/tools && $cmd
