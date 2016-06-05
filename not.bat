FOR %%F IN (nota*.tex) DO SET NombreArchivo=%%F
pdflatex "%NombreArchivo%"
move nota*.pdf C:\Users\NaMYY\Desktop\Farmacia\Notas
move nota*.tex C:\Users\NaMYY\Desktop\Farmacia\Notas
del *.aux
del *.log