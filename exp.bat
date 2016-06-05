FOR %%F IN (expediente*.tex) DO SET NombreArchivo=%%F
pdflatex "%NombreArchivo%"