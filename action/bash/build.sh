#!/bin/bash
#Desc 打包: $cmd clean|compile|package|install|deploy
#Auth zongf
#Date 2023-05-17


# 工作空间,项目根目录
workspace=`cd ../.. && pwd`
echo "workspace:$workspace"

# 打包命令
mvnCmd="mvn $1 -DskipTests=true "

# 按依赖顺序依次打包
cd $workspace/base/swan-dependencies && $mvnCmd

cd $workspace/base/swan-parent && $mvnCmd

cd $workspace/commons && $mvnCmd

cd $workspace/starters && $mvnCmd

cd $workspace/tools && $mvnCmd
