FOR %%F IN (mPrev.tex) DO SET NombreArchivo=%%F
pdflatex "%NombreArchivo%"
del *.tex
del *.aux
del *.log