@echo off
g:
cd G:\MyWork\Spring code\springboot\package

echo 关闭当前服务进程
taskkill /FI "windowtitle eq OAHelper*" /im cmd.exe

title OAHelper
echo 启动新服务
call mvn spring-boot:run