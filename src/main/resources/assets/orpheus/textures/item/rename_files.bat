@echo off
setlocal enabledelayedexpansion

for %%f in (wheat_stage*.png) do (
    set "filename=%%~nf"
    set "newname=!filename:wheat_stage=nectar_crop_stage!.png"
    ren "%%f" "!newname!"
)

echo All files renamed.
endlocal
