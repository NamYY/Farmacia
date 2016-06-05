FOR %%F IN (remision*.tex) DO SET NombreArchivo=%%F
pdflatex "%NombreArchivo%"
move remision*.pdf C:\Users\NaMYY\Desktop\Farmacia\Remisiones
move remision*.tex C:\Users\NaMYY\Desktop\Farmacia\Remisiones
del *.aux
del *.log