@echo off
setlocal
call vars.bat
call curl.bat -XPUT "http://%ES_HOSTPORT%/%PRJ_ID%/document/%DOC_ID%" -d @doc-%DOC_ID%.json
::call curl.bat -XPUT "http://%ES_HOSTPORT%/%PRJ_INDEX%/document/%DOC_ID%" -d @doc-%DOC_ID%.json

::?routing=tag:search.twitter.com,2005:524293909925486593
:: PRJ_ID PRJ_INDEX PRJ_WRITE_ALIAS
endlocal
