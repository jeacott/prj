@echo off
setlocal
call curl.bat -XPUT "http://epbygomw0024:9200/_snapshot/my_backup/snapshot1?wait_for_completion=true" -d @snapshottake.json
endlocal