@echo off
g:
cd G:\MyWork\Spring code\springboot

git status
echo 获取远程代码
git fetch origin/master
git log -p master.. origin/master
git merge origin/master master
git checkout master

echo 开始更新Maven仓库
call mvn clean install -Ptest -Dmaven.test.skip=true
echo 更新完毕!!!
cd package

echo 关闭当前服务进程
taskkill /FI "windowtitle eq OAHelper*" /im cmd.exe

title OAHelper
echo 启动新服务
call mvn spring-boot:run