@echo off
cd\
cd %~dp0%
javac -encoding UTF-8 -d bin -sourcepath src -cp lib/commons-configuration-1.10.jar;lib/commons-lang-2.6.jar;lib/commons-logging-1.2.jar src/netspy/Main.java